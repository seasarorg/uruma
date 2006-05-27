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
package org.seasar.jface.layout;

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Layout;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.util.PropertyUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * @author y-komori
 * 
 * @param <LAYOUT_TYPE>
 * @param <LAYOUT_DATA_TYPE>
 */
public abstract class AbstractLayoutSupport<LAYOUT_TYPE extends Layout, LAYOUT_DATA_TYPE>
        implements LayoutSupport<LAYOUT_TYPE, LAYOUT_DATA_TYPE> {

    protected void setupLayoutData(Object layoutData,
            ControlComponent controlComponent) {
        for (Iterator<String> iter = controlComponent.layoutDataNameIterator(); iter
                .hasNext();) {
            String name = iter.next();
            String value = controlComponent.getLayoutData(name);
            int constant = SWTUtil.getSWTConstant(value);
            if (constant != SWT.NULL) {
                PropertyUtil.setField(layoutData, name, constant);
            } else {
                PropertyUtil.setField(layoutData, name, value);
            }
        }
    }
}
