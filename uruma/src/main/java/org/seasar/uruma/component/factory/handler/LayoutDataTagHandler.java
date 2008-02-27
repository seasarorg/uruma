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
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.UIControlComponent;
import org.seasar.uruma.component.UIElement;

/**
 * <code>rowData</code>、<code>gridData</code> 要素に対するタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class LayoutDataTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = -3959745141934226850L;

    /**
     * {@link LayoutDataTagHandler} を構築します。<br />
     * 
     * @param layoutDataInfoClass
     *            {@link LayoutDataInfo} クラスオブジェクト
     */
    public LayoutDataTagHandler(
            final Class<? extends LayoutDataInfo> layoutDataInfoClass) {
        super(layoutDataInfoClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void setParent(final UIElement uiElement,
            final TagHandlerContext context) {
        Object parent = context.peek();
        if (parent instanceof UIControlComponent) {
            UIControlComponent uiControl = (UIControlComponent) parent;
            uiControl.setLayoutDataInfo((LayoutDataInfo) uiElement);
        } else if (parent instanceof LayoutInfo) {
            LayoutInfo<LayoutDataInfo> layoutInfo = LayoutInfo.class
                    .cast(parent);
            layoutInfo.setCommonLayoutDataInfo((LayoutDataInfo) uiElement);
        }
    }
}
