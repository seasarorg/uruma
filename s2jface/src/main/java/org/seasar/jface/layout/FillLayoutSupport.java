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

import java.util.Map;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Layout;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.util.PropertyUtil;

/**
 * <code>FillLayout</code>の生成をサポートする<code>LayoutSupport</code>クラスです。</br>
 * 
 * @author y-komori
 * @see org.eclipse.swt.layout.FillLayout
 */
public class FillLayoutSupport extends
        AbstractLayoutSupport<FillLayout, Object> {
    private static final String LAYOUT_NAME = "fill";

    private static final String MARGIN_LAYOUT_PARAM = "margin";

    private static final String MARGIN_HEIGHT_ATTR = "marginHeight";

    private static final String MARGIN_WIDTH_ATTR = "marginWidth";

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayout()
     */
    public FillLayout createLayout() {
        return new FillLayout();
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#getLayoutType()
     */
    public Class<FillLayout> getLayoutType() {
        return FillLayout.class;
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayoutData()
     */
    public Object createLayoutData() {
        return null;
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayoutData(org.seasar.jface.component.impl.ControlComponent)
     */
    public Object createLayoutData(ControlComponent controlComponent) {
        return null;
    }

    /**
     * 独自のレイアウトパラメータ処理を行います。</br>
     * <p>
     * <code>margin</code> が指定されていた場合、<code>marginHeight</code>、
     * <code>marginWidth</code> をすべて同じ値に設定します。
     * </p>
     * 
     * @see org.seasar.jface.layout.AbstractLayoutSupport#setupAdditionalLayoutParam(org.eclipse.swt.widgets.Layout,
     *      java.util.Map)
     */
    @Override
    protected void setupAdditionalLayoutParam(final Layout layout,
            final Map<String, String> layoutParams) {
        String marginStr = layoutParams.get(MARGIN_LAYOUT_PARAM);
        if (!StringUtil.isEmpty(marginStr)) {
            int margin = Integer.parseInt(marginStr);
            PropertyUtil.setField(layout, MARGIN_HEIGHT_ATTR, margin);
            PropertyUtil.setField(layout, MARGIN_WIDTH_ATTR, margin);
        }
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#getLayoutName()
     */
    public String getLayoutName() {
        return LAYOUT_NAME;
    }
}
