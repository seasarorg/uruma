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
package org.seasar.uruma.component.factory.desc.handler;

import org.seasar.framework.xml.TagHandler;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.factory.desc.UrumaComponentDesc;
import org.xml.sax.Attributes;

/**
 * <code>tagHandler</code> 要素のためのタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class TagHandlerTagHandler extends TagHandler {

    private static final long serialVersionUID = -7204241548646545947L;

    /*
     * @see org.seasar.framework.xml.TagHandler#start(org.seasar.framework.xml.
     * TagHandlerContext, org.xml.sax.Attributes)
     */
    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        UrumaComponentDesc desc = (UrumaComponentDesc) context.peek();
        desc.setTagHandlerClass(attributes.getValue("class"));
    }
}
