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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.binding.enables.EnablesDependingDef;
import org.seasar.uruma.binding.enables.EnablesForType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.renderer.RendererSupportUtil;
import org.seasar.uruma.util.ClassUtil;

/**
 * {@link Widget} のレンダリングを行うための基底クラスです。<br />
 * 
 * @param <COMPONENT_TYPE>
 *            レンダラに対応するコンポーネントの実際の型
 * @param <WIDGET_TYPE>
 *            レンダラが生成するウィジットの実際の型
 * @author bskuroneko
 */
public abstract class AbstractWidgetRenderer<COMPONENT_TYPE extends UIComponent, WIDGET_TYPE extends Widget>
        extends AbstractRenderer {
    private UrumaLogger logger = UrumaLogger.getLogger(getClass());

    /*
     * @see org.seasar.uruma.renderer.Renderer#render(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @SuppressWarnings("unchecked")
    public WidgetHandle render(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        setContext(context);

        inherit((COMPONENT_TYPE) uiComponent);

        Object parentObject = parent.getWidget();
        Widget parentWidget = null;

        if (parentObject instanceof Widget) {
            parentWidget = Widget.class.cast(parentObject);
        } else if (parentObject instanceof Viewer) {
            parentWidget = Viewer.class.cast(parentObject).getControl();
        } else {
            throw new RenderException(RenderException.TYPE_ERROR, parentObject,
                    Widget.class.getName());
        }

        WIDGET_TYPE widget = createWidget(parentWidget, getStyle(uiComponent));

        renderWidget((COMPONENT_TYPE) uiComponent, widget);

        WidgetHandle handle = createWidgetHandle(uiComponent, widget);

        if (uiComponent instanceof EnablesDependable) {
            setupEnablesDependingDef(handle, (EnablesDependable) uiComponent);
        }

        return handle;
    }

    /**
     * 生成したウィジットに対するレンダリングを行います。<br />
     * 
     * @param uiComponent
     *            対応する {@link UIComponent}
     * @param widget
     *            生成したウィジット
     */
    protected void renderWidget(final COMPONENT_TYPE uiComponent,
            final WIDGET_TYPE widget) {
        try {
            RendererSupportUtil.setAttributes(uiComponent, widget,
                    SetTiming.RENDER);

            doRender(uiComponent, getWidgetType().cast(widget));

        } catch (Exception ex) {
            throw new RenderException("EURM0001", ex, ex.getMessage());
        }
    }

    protected void setupEnablesDependingDef(final WidgetHandle handle,
            final EnablesDependable dependable) {
        String enablesDependingId = dependable.getEnablesDependingId();
        String enablesForType = dependable.getEnablesForType();

        if (!StringUtil.isEmpty(enablesDependingId)) {
            EnablesForType type = EnablesForType.SELECTION;
            if (!StringUtil.isEmpty(enablesForType)) {
                type = EnablesForType.valueOf(enablesForType);
            }

            EnablesDependingDef def = new EnablesDependingDef(handle,
                    enablesDependingId, type);
            getContext().getWindowContext().addEnablesDependingDef(def);
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
        Object widget = handle.getWidget();
        RendererSupportUtil.setAttributes(uiComponent, widget,
                SetTiming.RENDER_AFTER);
        doRenderAfter(getWidgetType().cast(widget),
                (COMPONENT_TYPE) uiComponent, parent, context);
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
     * ウィジットを生成します。<br />
     * ウィジットの生成を独自に行いたい場合、サブクラスで本メソッドをオーバーライドしてください。<br />
     * 
     * @param parent
     *            親ウィジットオブジェクト
     * @param style
     *            スタイル値
     * @return 生成したウィジットのインタンス
     */
    protected WIDGET_TYPE createWidget(final Widget parent, final int style) {
        Class<WIDGET_TYPE> widgetClass = getWidgetType();
        WIDGET_TYPE widget = ClassUtil.<WIDGET_TYPE> newInstance(widgetClass,
                parent, style);

        if (logger.isDebugEnabled()) {
            logger
                    .debug(UrumaLogger.getObjectDescription(widget)
                            + " created.");
        }

        return widget;
    }

    /**
     * 生成するウィジットの型を返します。<br />
     * ウィジットのインスタンス生成は本クラスで実施するので、サブクラスで本メソッドを実装してウィジットの型のみを返してください。<br />
     * 
     * @return 生成するウィジットの型
     */
    protected abstract Class<WIDGET_TYPE> getWidgetType();

    /**
     * 生成されたウィジットに対して各種属性を設定します。<br />
     * 本メソッドをサブクラスで実装し、<code>uiComponent</code> の保持する属性を <code>widget</code>
     * に対して設定してください。<br />
     * 
     * @param uiComponent
     *            {@link UIComponent} オブジェクト
     * @param widget
     *            生成されたウィジット
     */
    protected abstract void doRender(COMPONENT_TYPE uiComponent,
            WIDGET_TYPE widget);

    /**
     * 子のレンダリング終了後にレンダリング処理を行います。<br />
     * 子のレンダリング終了後にレンダリング処理を行う場合、本メソッドをオーバーライドしてレンダリング処理を実装してください。<br />
     * デフォルトでは何も行いません。<br />
     * 
     * @param widget
     *            レンダリング対象ウィジット
     * @param uiComponent
     *            レンダリング対象の {@link UIComponent} オブジェクト
     * @param parent
     *            親のウィジットハンドル
     * @param context
     *            {@link PartContext} オブジェクト
     */
    protected void doRenderAfter(final WIDGET_TYPE widget,
            final COMPONENT_TYPE uiComponent, final WidgetHandle parent,
            final PartContext context) {
        // do nothing.
    }
}
