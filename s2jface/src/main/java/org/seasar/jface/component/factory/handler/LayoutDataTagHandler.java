/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.LayoutDataInfo;
import org.seasar.jface.component.LayoutInfo;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UIElement;

/**
 * <code>rowData</code>、<code>gridData</code> 要素に対するタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class LayoutDataTagHandler extends S2JFaceGenericTagHandler {

    private static final long serialVersionUID = -3959745141934226850L;

    public LayoutDataTagHandler(
            final Class<? extends LayoutDataInfo> layoutDataInfoClass) {
        super(layoutDataInfoClass);
    }

    @Override
    protected void setParent(UIElement uiElement, TagHandlerContext context) {
        Object parent = context.peek();
        if (parent instanceof UIComponent) {
            UIComponent uiComponent = (UIComponent) parent;
            uiComponent.setLayoutDataInfo((LayoutDataInfo) uiElement);
        } else if (parent instanceof LayoutInfo) {
            LayoutInfo layoutInfo = (LayoutInfo) parent;
            layoutInfo.setCommonLayoutDataInfo((LayoutDataInfo) uiElement);
        }
    }
}
