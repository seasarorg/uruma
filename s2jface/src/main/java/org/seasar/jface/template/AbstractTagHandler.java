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

import org.seasar.framework.xml.TagHandler;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.PropertyComponent;
import org.seasar.jface.exception.ParseException;
import org.seasar.jface.util.PropertyUtil;

/**
 * @author y-komori
 * 
 */
public abstract class AbstractTagHandler extends TagHandler {
    protected static final String ID_ATTR = "id";

    protected static final String TYPE_ATTR = "type";

    protected static final String RENDERER_TYPE_ATTR = "rendererType";

    abstract protected String getElementName();

    protected void setValue(final UIComponent target,
            final String propertyName, final Object value) {
        if (value != null) {
            if (false == PropertyUtil.setProperty(target, propertyName, value)) {
                target.addProperty(new PropertyComponent(propertyName,
                        value.toString()));
            }
        }
    }

    protected void setValue(final UIComponent target,
            final String propertyName, final Object value, boolean mandatory) {
        if ((true == mandatory) && (value == null)) {
            throw new ParseException("EJFC0100", getElementName(), propertyName);
        }
        setValue(target, propertyName, value);
    }

    protected void setBasePath(final UIComponent component,
            final TagHandlerContext context) {
        component.setBasePath((String) context.getParameter("basePath"));
    }
}
