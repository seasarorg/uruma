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

import org.eclipse.swt.SWT;
import org.seasar.eclipse.common.util.SWTUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.binding.enables.EnablesDependingDef;
import org.seasar.uruma.binding.enables.EnablesForType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.renderer.Renderer;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link Renderer} の基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractRenderer implements Renderer {
    private WindowContext windowContext;

    private PartContext context;

    /*
     * @see org.seasar.uruma.renderer.Renderer#preRender(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.WindowContext)
     */
    public WidgetHandle preRender(final UIComponent uiComponent,
            final WidgetHandle parent, final WindowContext context) {
        setWindowContext(context);

        return null;
    }

    /**
     * {@link WindowContext} を取得します。<br />
     * 
     * @return {@link WindowContext} オブジェクト
     */
    protected WindowContext getWindowContext() {
        return this.windowContext;
    }

    /**
     * {@link WindowContext} を設定します。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    protected void setWindowContext(final WindowContext context) {
        AssertionUtil.assertNotNull("context", context);
        this.windowContext = context;
    }

    /**
     * {@link PartContext} を取得します。
     * 
     * @return {@link PartContext} オブジェクト
     */
    protected PartContext getContext() {
        return this.context;
    }

    /**
     * {@link PartContext} を設定します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    protected void setContext(final PartContext context) {
        AssertionUtil.assertNotNull("context", context);
        this.context = context;
    }

    /**
     * {@link WidgetHandle} の実装クラスを生成して返します。<br />
     * 
     * @param uiComponent
     *            {@link WidgetHandle} へ格納する {@link UIComponent} オブジェクト
     * @param widget
     *            {@link WidgetHandle} へ格納するオブジェクト
     * @return 生成した {@link WidgetHandle}
     */
    protected WidgetHandle createWidgetHandle(final UIComponent uiComponent,
            final Object widget) {
        WidgetHandle handle = ContextFactory.createWidgetHandle(widget);
        handle.setUiComponent(uiComponent);
        String id = uiComponent.getId();
        if (!StringUtil.isEmpty(id)) {
            handle.setId(id);
        }

        return handle;
    }

    /**
     * {@link UIComponent} の保持する文字列のスタイル属性を <code>int</code> 値に変換します。<br />
     * 
     * @param uiComponent
     *            {@link UIComponent} オブジェクト
     * @return 変換されたスタイル属性
     */
    protected int getStyle(final UIComponent uiComponent) {
        return SWTUtil.getStyle(uiComponent.getStyle(), getDefaultStyle());
    }

    /**
     * スタイル属性が指定されていない場合のデフォルト値を返します。<br />
     * 通常は、 {@link SWT#NONE} を返します。<br />
     * デフォルト値を変更したい場合、本メソッドをオーバーライドしてください。<br />
     * 
     * @return デフォルトのスタイル属性
     */
    protected int getDefaultStyle() {
        return SWT.NONE;
    }

    /**
     * {@link EnablesDependingDef} のセットアップを行います。<br />
     * 本メソッドは必要に応じてサブクラス内から呼び出してください。<br />
     * 
     * @param handle
     *            {@link WidgetHandle} オブジェクト
     * @param dependable
     *            {@link EnablesDependable} コンポーネント
     */
    protected void setupEnablesDependingDef(final WidgetHandle handle,
            final EnablesDependable dependable) {
        String enablesDependingId = dependable.getEnablesDependingId();
        String enablesForType = dependable.getEnablesFor();

        if (!StringUtil.isEmpty(enablesDependingId)) {
            EnablesForType type = EnablesForType.SELECTION;
            if (!StringUtil.isEmpty(enablesForType)) {
                type = EnablesForType.valueOf(enablesForType);
            }

            EnablesDependingDef def = new EnablesDependingDef(handle,
                    enablesDependingId, type);
            getWindowContext().addEnablesDependingDef(def);
        }
    }
}
