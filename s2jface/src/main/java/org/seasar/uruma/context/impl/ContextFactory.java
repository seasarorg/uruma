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
package org.seasar.uruma.context.impl;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.exception.DuplicateComponentIdException;
import org.seasar.uruma.util.AssertionUtil;

/**
 * 各種コンテキストを生成するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ContextFactory {

    private ContextFactory() {

    }

    /**
     * {@link ApplicationContext} オブジェクトを生成します。<br />
     * 
     * @return 生成した {@link ApplicationContext} オブジェクト
     */
    public static ApplicationContext createApplicationContext() {
        return new ApplicationContextImpl();
    }

    /**
     * {@link WindowContext} オブジェクトを生成します。<br />
     * 
     * @param parent
     *            親 {@link ApplicationContext} オブジェクト
     * @param name
     *            {@link WindowContext} の名称
     * @return 生成した {@link WindowContext} オブジェクト
     * @thrown DuplicateComponentIdException 名称が重複している場合
     */
    public static WindowContext createWindowContext(
            final ApplicationContext parent, final String name) {
        AssertionUtil.assertNotNull("parent", parent);
        AssertionUtil.assertNotNull("name", name);

        WindowContext context = new WindowContextImpl(name);
        ((ApplicationContextImpl) parent).addWindowContext(context);

        return context;
    }

    /**
     * {@link PartContext} オブジェクトを生成します。<br />
     * 
     * @param parent
     *            親 {@link WindowContext} オブジェクト
     * @param name
     *            {@link PartContext} の名称
     * @return 生成した {@link PartContext} オブジェクト
     * @throws DuplicateComponentIdException
     *             名称が重複している場合
     */
    public static PartContext createPartContext(final WindowContext parent,
            final String name) {
        AssertionUtil.assertNotNull("parent", parent);
        AssertionUtil.assertNotNull("name", name);

        PartContext context = new PartContextImpl(name);
        ((WindowContextImpl) parent).addPartContext(context);

        return context;
    }

    /**
     * {@link WidgetHandle} オブジェクトを生成します。<br />
     * 
     * @param widget
     *            {@link WidgetHandle} へ格納するオブジェクト
     * @param uiComponent
     *            {@link WidgetHandle} に対応させる {@link UIComponent} オブジェクト
     * @return 生成した {@link WidgetHandle} オブジェクト
     */
    public static WidgetHandle createWidgetHandle(final Object widget,
            final UIComponent uiComponent) {
        return new WidgetHandleImpl(widget, uiComponent);
    }
}
