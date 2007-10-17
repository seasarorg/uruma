/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.uruma.renderer.impl;

import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.renderer.RendererSupportUtil;
import org.seasar.uruma.util.ClassUtil;
import org.seasar.uruma.util.S2ContainerUtil;
import org.seasar.uruma.viewer.GenericContentProvider;

/**
 * {@link Viewer} のレンダリングを行うための基底クラスです。<br />
 * 
 * @param <COMPONENT_TYPE>
 *            レンダラに対応するコンポーネントの実際の型
 * @param <VIEWER_TYPE>
 *            レンダラが生成するビューアの実際の型
 * @author y-komori
 */
public abstract class AbstractViewerRenderer<COMPONENT_TYPE extends UIComponent, VIEWER_TYPE extends Viewer>
        extends AbstractRenderer {
    /**
     * {@link ILabelProvider} の S2Container 上でのコンポーネント名称サフィックス
     */
    protected static final String LABEL_PROVIDER = "LabelProvider";

    /**
     * {@link IContentProvider} の S2Container 上でのコンポーネント名称サフィックス
     */
    protected static final String CONTENT_PROVIDER = "ContentProvider";

    /**
     * {@link ViewerComparator} の S2Container 上でのコンポーネント名称サフィックス
     */
    protected static final String COMPARATOR = "Comparator";

    /*
     * @see org.seasar.uruma.renderer.Renderer#render(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @SuppressWarnings("unchecked")
    public WidgetHandle render(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        Object parentObj = parent.getWidget();
        if (parentObj instanceof Composite) {
            Composite composite = (Composite) parentObj;

            setContext(context);

            inherit((COMPONENT_TYPE) uiComponent);

            VIEWER_TYPE viewer = createViewer(composite, getStyle(uiComponent));

            try {
                RendererSupportUtil.setAttributes(uiComponent, viewer,
                        SetTiming.RENDER);

                doRender((COMPONENT_TYPE) uiComponent, getViewerType().cast(
                        viewer));
            } catch (Exception ex) {
                throw new RenderException("EURM0001", ex.getMessage());
            }

            WidgetHandle handle = createWidgetHandle(uiComponent, viewer);

            String id = uiComponent.getId();
            if (viewer instanceof ContentViewer) {
                setupContentProvider((ContentViewer) viewer, id);
            }

            if (viewer instanceof StructuredViewer) {
                setupLabelProvider((StructuredViewer) viewer, id);
            }

            return handle;
        } else {
            throw new RenderException(RenderException.TYPE_ERROR, parent,
                    Composite.class);
        }
    }

    /*
     * @see org.seasar.uruma.renderer.Renderer#renderAfter(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @SuppressWarnings("unchecked")
    public void renderAfter(final WidgetHandle handle,
            final UIComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        RendererSupportUtil.setAttributes(uiComponent, handle.getWidget(),
                SetTiming.RENDER_AFTER);
        VIEWER_TYPE viewer = getViewerType().cast(handle.getWidget());
        doRenderAfter(viewer, (COMPONENT_TYPE) uiComponent, parent, context);

        if (viewer instanceof StructuredViewer) {
            setupComparator(StructuredViewer.class.cast(viewer), uiComponent
                    .getId());
        }
    }

    /**
     * <code>viewer</code> に対して {@link IContentProvider} を設定します。<br />
     * <p>
     * 本メソッドでは以下の動作を行います。<br />
     * <ol>
     * <li>S2Container 上に、&lt;コンポーネントのID&gt;ContentProvider という名称で S2
     * コンポーネントが登録されているか確認する。
     * <li>登録されていれば、その S2 コンポーネントが {@link IContentProvider} の実装クラスであるかどうかを確認する。
     * <li>サブクラスであれば、その S2 コンポーネントをコンテントプロバイダとして <code>viewer</code> へ設定する。
     * <li>S2 コンポーネントが見つからない場合、
     * {@link AbstractViewerRenderer#getDefaultContentProvider() getDefaultContentProvider()}
     * メソッドの返すオブジェクトをコンテントプロバイダとして <code>viewer</code> へ設定する。
     * </ol>
     * </p>
     * 
     * @param viewer
     *            設定対象のビューア
     * @param id
     *            ビューアのコンポーネントID
     * @throws RenderException
     *             該当する名称の S2 コンポーネントが {@link IContentProvider} の実装クラスでない場合
     * @see ContentViewer#setContentProvider(IContentProvider)
     */
    protected void setupContentProvider(final ContentViewer viewer,
            final String id) {
        IContentProvider provider = null;
        if (!StringUtil.isEmpty(id)) {
            Object defined = S2ContainerUtil.getComponentNoException(id
                    + CONTENT_PROVIDER);
            if (defined != null) {
                if (defined instanceof IContentProvider) {
                    provider = IContentProvider.class.cast(defined);
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            provider, IContentProvider.class.getName());
                }
            }
        }

        if (provider == null) {
            provider = getDefaultContentProvider();
        }

        if (provider != null) {
            viewer.setContentProvider(provider);
        }
    }

    /**
     * <code>viewer</code> に対して {@link ILabelProvider} を設定します。<br />
     * <p>
     * 本メソッドでは以下の動作を行います。<br />
     * <ol>
     * <li>S2Container 上に、&lt;コンポーネントのID&gt;LabelProvider という名称で S2
     * コンポーネントが登録されているか確認する。
     * <li>登録されていれば、その S2 コンポーネントが
     * {@link AbstractViewerRenderer#getLabelProviderClass() getLabelProviderClass()}
     * メソッドの返すクラスのサブクラスであるかどうかを確認する。
     * <li>サブクラスであれば、その S2 コンポーネントをラベルプロバイダとして <code>viewer</code> へ設定する。
     * <li>S2 コンポーネントが見つからない場合、
     * {@link AbstractViewerRenderer#getDefaultLabelProvider() getDefaultLabelProvider()}
     * メソッドの返すオブジェクトをラベルプロバイダとして <code>viewer</code> へ設定する。
     * </ol>
     * </p>
     * 
     * @param viewer
     *            設定対象のビューア
     * @param id
     *            ビューアのコンポーネントID
     * @throws RenderException
     *             該当する名称の S2 コンポーネントが
     *             {@link AbstractViewerRenderer#getLabelProviderClass() getLabelProviderClass()}
     *             メソッドの返すクラスのサブクラスでない場合
     * @see StructuredViewer#setLabelProvider(IBaseLabelProvider)
     */
    protected void setupLabelProvider(final StructuredViewer viewer,
            final String id) {
        IBaseLabelProvider provider = null;
        if (!StringUtil.isEmpty(id)) {
            Object defined = S2ContainerUtil.getComponentNoException(id
                    + LABEL_PROVIDER);
            if (defined != null) {
                Class<? extends IBaseLabelProvider> providerClass = getLabelProviderClass();
                if (providerClass.isAssignableFrom(defined.getClass())) {
                    provider = providerClass.cast(defined);
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            provider, providerClass.getName());
                }
            }
        }

        if (provider == null) {
            provider = getDefaultLabelProvider();
        }

        if (provider != null) {
            viewer.setLabelProvider(provider);
        }
    }

    /**
     * <code>viewer</code> に対して {@link ViewerComparator} を設定します。<br />
     * <p>
     * 本メソッドでは以下の動作を行います。<br />
     * <ol>
     * <li>S2Container 上に、&lt;コンポーネントのID&gt;Comparator という名称で S2
     * コンポーネントが登録されているか確認する。
     * <li>登録されていれば、その S2 コンポーネントが {@link ViewerComparator} のサブクラスであるかどうかを確認する。
     * <li>サブクラスであれば、その S2 コンポーネントをソータとして <code>viewer</code> へ設定する。
     * <li>S2 コンポーネントが見つからない場合、
     * {@link AbstractViewerRenderer#getDefaultComparator(Viewer) getDefaultComparator()}
     * メソッドの返すオブジェクトをコンパレータとして <code>viewer</code> へ設定する。
     * </ol>
     * </p>
     * 
     * @param viewer
     *            設定対象のビューア
     * @param id
     *            ビューアのコンポーネントID
     * @throws RenderException
     *             該当する名称の S2 コンポーネントが {@link ViewerComparator} のサブクラスでない場合
     * @see StructuredViewer#setComparator(ViewerComparator)
     */
    protected void setupComparator(final StructuredViewer viewer,
            final String id) {
        if (!StringUtil.isEmpty(id)) {
            Object comparator = S2ContainerUtil.getComponentNoException(id
                    + COMPARATOR);
            if (comparator != null) {
                if (comparator instanceof ViewerComparator) {
                    viewer.setComparator(ViewerComparator.class
                            .cast(comparator));
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            comparator, ViewerComparator.class.getName());
                }
            } else {
                viewer.setComparator(getDefaultComparator(getViewerType().cast(
                        viewer)));
            }
        }
    }

    /**
     * 親コンポーネントから属性を引き継ぎます。<br />
     * 親コンポーネントから属性を引き継ぎたい場合、本メソッドをオーバーライドして処理を行ってください。<br />
     * デフォルトでは何も行いません。
     * 
     * @param uiComponent
     *            自コンポーネントの {@link UIComponent} オブジェクト
     */
    protected void inherit(final COMPONENT_TYPE uiComponent) {
        // do nothing.
    }

    /**
     * ビューアを生成します。<br />
     * ビューアの生成を独自に行いたい場合、サブクラスで本メソッドをオーバーライドしてください。<br />
     * 
     * @param parent
     *            親ウィジットオブジェクト
     * @param style
     *            スタイル値
     * @return 生成したウィジットのインタンス
     */
    protected VIEWER_TYPE createViewer(final Composite parent, final int style) {
        Class<VIEWER_TYPE> viewerClass = getViewerType();
        VIEWER_TYPE viewer = ClassUtil.<VIEWER_TYPE> newInstance(viewerClass,
                parent, style);
        return viewer;
    }

    /**
     * デフォルトの {@link IContentProvider} を返します。<br />
     * ユーザ指定の {@link IContentProvider} が S2Container
     * 上に登録されていない場合に使用する、デフォルトのコンテントプロバイダを返します。<br />
     * デフォルトでは、 {@link GenericContentProvider} を返します。<br />
     * デフォルトのコンテントプロバイダを変更したい場合、本メソッドをオーバーライドしてください。<br />
     * 
     * @return デフォルトの {@link IContentProvider}
     */
    protected IContentProvider getDefaultContentProvider() {
        return new GenericContentProvider();
    }

    /**
     * デフォルトの {@link IBaseLabelProvider} を返します。<br />
     * ユーザ指定の {@link IBaseLabelProvider} が S2Container
     * 上に登録されていない場合に使用する、デフォルトのラベルプロバイダを返します。<br />
     * デフォルトでは、 <code>null</code> (ラベルプロバイダを使用しない) を返します。<br />
     * デフォルトのラベルプロバイダを変更したい場合、本メソッドをオーバーライドしてください。<br />
     * 
     * @return デフォルトの {@link IBaseLabelProvider}
     */
    protected IBaseLabelProvider getDefaultLabelProvider() {
        return null;
    }

    /**
     * ラベルプロバイダとして使用すべきクラスを返します。<br />
     * S2Container 上でラベルプロバイダを検索する際、本メソッドの戻り値で示される型のインスタンスであるかどうかをチェックします。<br />
     * デフォルトでは、 {@link IBaseLabelProvider} の {@link Class} オブジェクトを返します。<br />
     * ラベルプロバイダのクラスをさらに絞りたい場合、本メソッドをオーバーライドしてください。<br />
     * 
     * @return ラベルプロバイダとして使用すべきクラス
     */
    protected Class<? extends IBaseLabelProvider> getLabelProviderClass() {
        return IBaseLabelProvider.class;
    }

    /**
     * デフォルトの {@link ViewerComparator} を返します。<br />
     * ユーザ指定の {@link ViewerComparator} が S2Container
     * 上に登録されていない場合に使用する、デフォルトのソータを返します。<br />
     * デフォルトのコンパレータを変更したい場合、本メソッドをオーバーライドしてください。<br />
     * デフォルトでは <code>null</code> (ソートしない) を返します。<br />
     * 
     * @param viewer
     *            レンダリング対象の {@link Viewer}
     * @return デフォルトの {@link ViewerComparator}}
     */
    protected ViewerComparator getDefaultComparator(final VIEWER_TYPE viewer) {
        return null;
    }

    /**
     * 生成するビューアの型を返します。<br />
     * ビューアのインスタンス生成は本クラスで実施するので、サブクラスで本メソッドを実装してビューアの型のみを返してください。
     * 
     * @return 生成するビューアの型
     */
    protected abstract Class<VIEWER_TYPE> getViewerType();

    /**
     * 生成されたビューアに対して各種属性を設定します。<br />
     * 本メソッドをサブクラスで実装し、<code>uiComponent</code> の保持する属性を <code>viewer</code>
     * に対して設定してください。<br />
     * 
     * @param uiComponent
     *            {@link UIComponent} オブジェクト
     * @param viewer
     *            生成されたビューア
     */
    protected abstract void doRender(COMPONENT_TYPE uiComponent,
            VIEWER_TYPE viewer);

    /**
     * 子のレンダリング終了後にレンダリング処理を行います。<br />
     * 子のレンダリング終了後にレンダリング処理を行う場合、本メソッドをオーバーライドしてレンダリング処理を実装してください。<br />
     * デフォルトでは何も行いません。<br />
     * 
     * @param viewer
     *            レンダリング対象ビューア
     * @param uiComponent
     *            レンダリング対象の {@link UIComponent} オブジェクト
     * @param parent
     *            親のウィジットハンドル
     * @param context
     *            {@link PartContext} オブジェクト
     */
    protected void doRenderAfter(final VIEWER_TYPE viewer,
            final COMPONENT_TYPE uiComponent, final WidgetHandle parent,
            final PartContext context) {
        // do nothing.
    }
}
