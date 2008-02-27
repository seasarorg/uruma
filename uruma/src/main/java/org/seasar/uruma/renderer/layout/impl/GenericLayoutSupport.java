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
package org.seasar.uruma.renderer.layout.impl;

import org.eclipse.swt.widgets.Layout;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.renderer.RendererSupportUtil;
import org.seasar.uruma.renderer.layout.LayoutSupport;
import org.seasar.uruma.util.ClassUtil;

/**
 * 汎用的な {@link LayoutSupport} の実装クラスです。
 * 
 * @author y-komori
 */
public class GenericLayoutSupport implements LayoutSupport {
    private Class<? extends Layout> layoutClass;

    private Class<? extends Object> layoutDataClass;

    /**
     * {@link GenericLayoutSupport} を構築します。<br />
     * 
     * @param layoutClass
     *            {@link Layout} クラス
     * @param layoutDataClass
     *            レイアウトデータクラス
     */
    public GenericLayoutSupport(final Class<? extends Layout> layoutClass,
            final Class<? extends Object> layoutDataClass) {
        this.layoutClass = layoutClass;
        this.layoutDataClass = layoutDataClass;
    }

    public Layout createLayout() {
        return ClassUtil.newInstance(layoutClass);
    }

    public Layout createLayout(final LayoutInfo<?> layoutInfo) {
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

    public Object createLayoutData(final UIComponent uiComponent,
            final LayoutDataInfo layoutDataInfo) {
        if (layoutDataClass != null) {
            Object layoutData = createLayoutData();
            RendererSupportUtil.setAttributes(layoutDataInfo, layoutData,
                    SetTiming.RENDER);
            return layoutData;
        } else {
            return null;
        }
    }
}
