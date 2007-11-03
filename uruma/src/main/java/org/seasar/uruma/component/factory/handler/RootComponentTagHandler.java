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
package org.seasar.uruma.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.component.UIElement;

/**
 * <code>template</code> 要素の直下に記述される要素に対するタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class RootComponentTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = 440579378337808824L;

    /**
     * {@link RootComponentTagHandler} を構築します。<br />
     * 
     * @param rootComponentClass
     *            ルート要素の {@link Class} オブジェクト
     */
    public RootComponentTagHandler(
            final Class<? extends UIElement> rootComponentClass) {
        super(rootComponentClass);
    }

    /*
     * @see org.seasar.uruma.component.factory.handler.GenericTagHandler#setParent(org.seasar.uruma.component.UIElement,
     *      org.seasar.framework.xml.TagHandlerContext)
     */
    @Override
    protected void setParent(final UIElement uiElement,
            final TagHandlerContext context) {
        Template template = (Template) context.peek();
        template.setRootComponent((UIContainer) uiElement);
    }
}
