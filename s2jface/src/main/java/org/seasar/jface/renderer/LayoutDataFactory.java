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

import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Layout;
import org.seasar.framework.util.ClassUtil;
import org.seasar.jface.exception.NotFoundException;

/**
 * @author y-komori
 * 
 */
public class LayoutDataFactory {
    protected static Map<Class<? extends Layout>, Class> layoutDataMap = new HashMap<Class<? extends Layout>, Class>();

    static {
        layoutDataMap.put(RowLayout.class, RowData.class);
        layoutDataMap.put(GridLayout.class, GridData.class);
        layoutDataMap.put(FormLayout.class, FormData.class);
    }

    public static Object getLayoutData(final Class<? extends Layout> layoutClass) {
        Class clazz = layoutDataMap.get(layoutClass);
        if (clazz != null) {
            return ClassUtil.newInstance(clazz);
        } else {
            throw new NotFoundException(NotFoundException.LAYOUT_DATA,
                    layoutClass.getName());
        }
    }

}
