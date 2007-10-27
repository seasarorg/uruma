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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link Separator} のレンダリングを行うためのクラスです。<br />
 * 
 * @author y-komori
 */
public class SeparatorRenderer extends AbstractRenderer {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#preRender(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    public WidgetHandle preRender(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        Separator separator = new Separator();

        MenuManager parentMenuManager = parent.<MenuManager> getCastWidget();
        parentMenuManager.add(separator);

        WidgetHandle handle = createWidgetHandle(uiComponent, separator);
        return handle;
    }

    /*
     * @see org.seasar.uruma.renderer.Renderer#render(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    public WidgetHandle render(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        // Do nothing.
        return null;
    }

    /*
     * @see org.seasar.uruma.renderer.Renderer#renderAfter(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    public void renderAfter(final WidgetHandle widget,
            final UIComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        // Do nothing.
    }
}
