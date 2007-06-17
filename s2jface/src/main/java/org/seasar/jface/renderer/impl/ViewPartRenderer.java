/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.jface.renderer.impl;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;

/**
 * <code>ViewPart</code> のレンダリングを行うためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ViewPartRenderer extends AbstractRenderer {
    /*
     * @see org.seasar.jface.renderer.Renderer#render(org.seasar.jface.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget, org.seasar.jface.WindowContext)
     */
    public Widget render(UIComponent uiComponent, Widget parent,
            WindowContext context) {
        return parent;
    }

    /*
     * @see org.seasar.jface.renderer.Renderer#renderAfter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.jface.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget, org.seasar.jface.WindowContext)
     */
    public void renderAfter(Widget widget, UIComponent uiComponent,
            Widget parent, WindowContext context) {
        // Do nothing.
    }
}
