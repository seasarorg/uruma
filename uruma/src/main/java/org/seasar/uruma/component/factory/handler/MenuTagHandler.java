/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.MenuComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.rcp.configuration.impl.MenuElement;

/**
 * <code>menu</code> 要素に対するタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class MenuTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = 6495474861566651181L;

    /**
     * {@link MenuTagHandler} を構築します。<br />
     */
    public MenuTagHandler() {
        super(null);
    }

    /*
     * @see org.seasar.uruma.component.factory.handler.GenericTagHandler#createUIElement(java.lang.Class,
     *      org.seasar.framework.xml.TagHandlerContext)
     */
    @Override
    protected UIElement createUIElement(
            final Class<? extends UIElement> uiElementClass,
            final TagHandlerContext context) {
        Object parentElement = context.peek();
        if (parentElement instanceof WorkbenchComponent) {
            return super.createUIElement(MenuElement.class, context);
        } else {
            return super.createUIElement(MenuComponent.class, context);
        }
    }
}
