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
package org.seasar.uruma.component.factory.impl;

import org.seasar.framework.xml.TagHandlerRule;
import org.seasar.uruma.component.factory.UrumaTagHandler;
import org.seasar.uruma.component.factory.UrumaTagHandlerRule;
import org.seasar.uruma.component.factory.handler.CommonAttributesTagHandler;
import org.seasar.uruma.component.factory.handler.TemplateTagHandler;

/**
 * {@link UrumaTagHandlerRule} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaTagHandlerRuleImpl extends TagHandlerRule implements
        UrumaTagHandlerRule {

    private static final long serialVersionUID = 719721604945289684L;

    /**
     * {@link UrumaTagHandlerRuleImpl} を構築します。<br />
     */
    public UrumaTagHandlerRuleImpl() {
        // Root components
        addTagHandler(new TemplateTagHandler());

        // Common Attribute
        addTagHandler(new CommonAttributesTagHandler());
    }

    /*
     * @see
     * org.seasar.uruma.component.factory.UrumaTagHandlerRule#addTagHandler(
     * org.seasar.uruma.component.factory.UrumaTagHandler)
     */
    public void addTagHandler(final UrumaTagHandler tagHandler) {
        super.addTagHandler(tagHandler.getElementPath(), tagHandler);
    }

    /*
     * @see
     * org.seasar.uruma.component.factory.UrumaTagHandlerRule#addTagHandler(
     * java.lang.String, org.seasar.uruma.component.factory.UrumaTagHandler)
     */
    public void addTagHandler(final String path,
            final UrumaTagHandler tagHandler) {
        super.addTagHandler(path, tagHandler);
    }

    /*
     * @see
     * org.seasar.uruma.component.factory.UrumaTagHandlerRule#getTagHandlerRule
     * ()
     */
    public TagHandlerRule getTagHandlerRule() {
        return this;
    }
}
