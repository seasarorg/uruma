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
package org.seasar.jface.template;

import org.seasar.framework.xml.TagHandlerRule;
import org.seasar.jface.util.ClassUtil;

/**
 * @author y-komori
 * 
 */
public class TemplateTagHandlerRule extends TagHandlerRule {
    private static final long serialVersionUID = 2479004205075412773L;

    public TemplateTagHandlerRule() {
        addTagHandler(TemplateTagHandler.class);
        addTagHandler(WindowTagHandler.class);
        addTagHandler(CompositeTagHandler.class);
        addTagHandler(ControlTagHandler.class);
        addTagHandler(PropertyTagHandler.class);
        addTagHandler(LayoutDataTagHandler.class);
    }

    protected void addTagHandler(Class<? extends AbstractTagHandler> clazz) {
        AbstractTagHandler tagHandler = ClassUtil
                .<AbstractTagHandler> newInstance(clazz);
        addTagHandler(tagHandler.getElementName(), tagHandler);
    }
}
