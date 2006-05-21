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
package org.seasar.jface.renderer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Layout;
import org.seasar.framework.util.ClassUtil;
import org.seasar.jface.exception.NotFoundException;

/**
 * @author y-komori
 * 
 */
public class LayoutFactory {
    protected static Map<String, Class> layoutMap = new HashMap<String, Class>();

    static {
        layoutMap.put("fill", FillLayout.class);
        layoutMap.put("row", RowLayout.class);
        layoutMap.put("grid", GridLayout.class);
        layoutMap.put("form", FormLayout.class);
    }

    public static Layout getLayout(final String type) {
        Class clazz = layoutMap.get(type);
        if (clazz != null) {
            return (Layout) ClassUtil.newInstance(clazz);
        } else {
            throw new NotFoundException(NotFoundException.LAYOUT, type);
        }
    }
}
