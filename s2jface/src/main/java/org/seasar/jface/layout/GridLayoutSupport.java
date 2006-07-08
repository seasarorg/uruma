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

import static org.seasar.jface.renderer.info.ControlInfo.HEIGHT_PROP;
import static org.seasar.jface.renderer.info.ControlInfo.WIDTH_PROP;

import java.util.Map;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.util.PropertyUtil;

/**
 * <code>GridLayout</code>と<code>GridData</code>の生成をサポートする<code>LayoutSupport</code>クラスです。</br>
 * 
 * @author y-komori
 * @see org.eclipse.swt.layout.GridLayout
 * @see org.eclipse.swt.layout.GridData
 */
/**
 * @author y-komori
 * 
 */
public class GridLayoutSupport extends
        AbstractLayoutSupport<GridLayout, GridData> {
    private static final String LAYOUT_NAME = "grid";

    private static final String MARGIN_LAYOUT_PARAM = "margin";

    private static final String SPACING_LAYOUT_PARAM = "spacing";

    private static final String MARGIN_TOP_ATTR = "marginTop";

    private static final String MARGIN_BOTTOM_ATTR = "marginBottom";

    private static final String MARGIN_RIGHT_ATTR = "marginRight";

    private static final String MARGIN_LEFT_ATTR = "marginLeft";

    private static final String MARGIN_HEIGHT_ATTR = "marginHeight";

    private static final String MARGIN_WIDTH_ATTR = "marginWidth";

    private static final String HORIZONTAL_SPACING_ATTR = "horizontalSpacing";

    private static final String VERTICAL_SPACING_ATTR = "verticalSpacing";

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayout()
     */
    public GridLayout createLayout() {
        return new GridLayout();
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#getLayoutType()
     */
    public Class<GridLayout> getLayoutType() {
        return GridLayout.class;
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayoutData()
     */
    public GridData createLayoutData() {
        return new GridData();
    }

    /**
     * <code>GridData</code> オブジェクトを生成します。</br>
     * <p>
     * <code>ControlComponent</code>の <code>layoutData</code>
     * 要素として指定されていた属性は、自動的に <code>GridData</code> の該当する属性に設定されます。
     * </p>
     * <p>
     * <code>ControlComponent</code>に <code>width</code>、<code>height</code>
     * プロパティが指定されていた場合は、自動的に <code>GridData</code> の <code>mimimumWidth</code>、<code>mimimumHeight</code>
     * 属性に設定します。
     * </p>
     * 
     * @see org.seasar.jface.layout.LayoutSupport#createLayoutData(org.seasar.jface.component.impl.ControlComponent)
     * @see org.eclipse.swt.layout.GridData
     */
    public GridData createLayoutData(ControlComponent controlComponent) {
        GridData gridData = createLayoutData();

        Property widthProp = controlComponent.getProperty(WIDTH_PROP);
        if ((widthProp != null) && widthProp.isValueExist()) {
            gridData.minimumWidth = widthProp.getIntValue();
        }

        Property heightProp = controlComponent.getProperty(HEIGHT_PROP);
        if ((heightProp != null) && heightProp.isValueExist()) {
            gridData.minimumHeight = heightProp.getIntValue();
        }

        setupLayoutData(gridData, controlComponent);

        return gridData;
    }

    /**
     * 独自のレイアウトパラメータ処理を行います。</br>
     * <p>
     * <code>margin</code> が指定されていた場合、<code>marginTop</code>、
     * <code>marginBottom</code>、<code>marginRight</code>、<code>marginLeft</code>、
     * <code>marginHeight</code>、<code>marginWidth</code> をすべて同じ値に設定します。
     * </p>
     * <p>
     * <code>spacing</code>が指定されていた場合、<code>horizontalSpacing</code>、
     * <code>verticalSpacing</code>をすべて同じ値に設定します。
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
            PropertyUtil.setField(layout, MARGIN_TOP_ATTR, margin);
            PropertyUtil.setField(layout, MARGIN_BOTTOM_ATTR, margin);
            PropertyUtil.setField(layout, MARGIN_RIGHT_ATTR, margin);
            PropertyUtil.setField(layout, MARGIN_LEFT_ATTR, margin);
            PropertyUtil.setField(layout, MARGIN_HEIGHT_ATTR, margin);
            PropertyUtil.setField(layout, MARGIN_WIDTH_ATTR, margin);
        }

        String spacingStr = layoutParams.get(SPACING_LAYOUT_PARAM);
        if (!StringUtil.isEmpty(spacingStr)) {
            int spacing = Integer.parseInt(spacingStr);
            PropertyUtil.setField(layout, HORIZONTAL_SPACING_ATTR, spacing);
            PropertyUtil.setField(layout, VERTICAL_SPACING_ATTR, spacing);
        }
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#getLayoutName()
     */
    public String getLayoutName() {
        return LAYOUT_NAME;
    }
}
