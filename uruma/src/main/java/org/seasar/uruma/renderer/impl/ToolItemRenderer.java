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

import org.eclipse.swt.widgets.ToolItem;
import org.seasar.uruma.component.jface.ToolItemComponent;

/**
 * {@link ToolItem} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class ToolItemRenderer extends
        AbstractWidgetRenderer<ToolItemComponent, ToolItem> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected void doRender(final ToolItemComponent toolItemComponent,
            final ToolItem control) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<ToolItem> getWidgetType() {
        return ToolItem.class;
    }
}
