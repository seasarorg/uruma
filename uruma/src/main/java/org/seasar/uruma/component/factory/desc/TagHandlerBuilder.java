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
package org.seasar.uruma.component.factory.desc;

import java.lang.reflect.Constructor;
import java.util.List;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.OgnlUtil;
import org.seasar.framework.xml.TagHandler;

public class TagHandlerBuilder {
    public static TagHandler buildTagHandler(final UrumaComponentDesc desc)
            throws ClassNotFoundException {
        Object[] args = getArgs(desc);
        Class<?>[] argTypes = getArgTypes(args);

        String clazz = desc.getTagHandlerClass();
        BeanDesc bd = BeanDescFactory.getBeanDesc(Class.forName(clazz));
        Constructor<?> constructor = bd.getConstructor(argTypes);

        return null;
    }

    protected static Object[] getArgs(final UrumaComponentDesc desc) {
        int size = desc.getTagHandlerArgs().size();
        List<String> argExprs = desc.getTagHandlerArgs();
        Object[] args = new Object[size];
        for (int i = 0; i < size; i++) {
            Object exp = OgnlUtil.parseExpression(argExprs.get(i));
            args[i] = OgnlUtil.getValue(exp, null);
        }
        return args;
    }

    protected static Class<?>[] getArgTypes(final Object[] args) {
        Class<?>[] types = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            Object object = args[i];
            types[i] = object.getClass();
        }
        return types;
    }
}
