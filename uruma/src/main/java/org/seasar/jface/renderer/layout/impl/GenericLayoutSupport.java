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
package org.seasar.jface.renderer.layout.impl;

import org.eclipse.swt.widgets.Layout;
import org.seasar.jface.annotation.component.ComponentAttribute.SetTiming;
import org.seasar.jface.component.LayoutDataInfo;
import org.seasar.jface.component.LayoutInfo;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.renderer.RendererSupportUtil;
import org.seasar.jface.renderer.layout.LayoutSupport;
import org.seasar.jface.util.ClassUtil;

/**
 * @author y-komori
 * 
 */
public class GenericLayoutSupport implements LayoutSupport {
    private Class<? extends Layout> layoutClass;

    private Class<? extends Object> layoutDataClass;

    public GenericLayoutSupport(Class<? extends Layout> layoutClass,
            final Class<? extends Object> layoutDataClass) {
        this.layoutClass = layoutClass;
        this.layoutDataClass = layoutDataClass;

    }

    public Layout createLayout() {
        return ClassUtil.newInstance(layoutClass);
    }

    public Layout createLayout(LayoutInfo layoutInfo) {
        Layout layout = createLayout();
        RendererSupportUtil.setAttributes(layoutInfo, layout, SetTiming.RENDER);
        return layout;
    }

    public Object createLayoutData() {
        if (layoutDataClass != null) {
            return ClassUtil.newInstance(layoutDataClass);
        } else {
            return null;
        }
    }

    public Object createLayoutData(UIComponent uiComponent,
            LayoutDataInfo layoutDataInfo) {
        if (layoutDataClass != null) {
            Object layoutData = createLayoutData();
            RendererSupportUtil.setAttributes(layoutDataInfo, layoutData, SetTiming.RENDER);
            return layoutData;
        } else {
            return null;
        }
    }
}
