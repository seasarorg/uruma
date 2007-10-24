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
package org.seasar.jface.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.UIElement;
import org.seasar.jface.component.impl.GradientInfo;
import org.seasar.jface.component.impl.GradientItem;

/**
 * <code>gradientItem</code> 要素に対するタグハンドラです。<br />
 * 
 * @author bskuroneko
 */
public class GradientItemTagHandler extends S2JFaceGenericTagHandler {

    private static final long serialVersionUID = 4653123801348296758L;

    /**
     * {@link GradientInfoTagHandler} を構築します。
     */
    public GradientItemTagHandler() {
        super(GradientItem.class);
    }

    @Override
    public String getElementPath() {
        return "gradientItem";
    }

    @Override
    protected void setParent(final UIElement uiElement,
            final TagHandlerContext context) {
        final GradientInfo parent = (GradientInfo) context.peek();
        parent.addGradientItem((GradientItem) uiElement);
    }
}