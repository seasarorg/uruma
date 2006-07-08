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

import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Layout;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.util.PropertyUtil;

/**
 * <code>RowLayout</code>と<code>RowData</code>の生成をサポートする<code>LayoutSupport</code>クラスです。</br>
 * 
 * @author y-komori
 * @see org.eclipse.swt.layout.RowLayout
 * @see org.eclipse.swt.layout.RowData
 */
public class RowLayoutSupport extends AbstractLayoutSupport<RowLayout, RowData> {
    private static final String LAYOUT_NAME = "row";

    private static final String MARGIN_LAYOUT_PARAM = "margin";

    private static final String MARGIN_TOP_ATTR = "marginTop";

    private static final String MARGIN_BOTTOM_ATTR = "marginBottom";

    private static final String MARGIN_RIGHT_ATTR = "marginRight";

    private static final String MARGIN_LEFT_ATTR = "marginLeft";

    private static final String MARGIN_HEIGHT_ATTR = "marginHeight";

    private static final String MARGIN_WIDTH_ATTR = "marginWidth";

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayout()
     */
    public RowLayout createLayout() {
        return new RowLayout();
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#getLayoutType()
     */
    public Class<RowLayout> getLayoutType() {
        return RowLayout.class;
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#createLayoutData()
     */
    public RowData createLayoutData() {
        return new RowData();
    }

    /**
     * <code>ControlComponent</code>を指定して、レイアウトデータオブジェクトを生成します。</br>
     * レイアウトデータを利用しないレイアウトの場合は、常に<code>null</code>を返します。</br>
     * レイアウトデータオブジェクト生成時、<code>ControlComponent</code>が持つ
     * <code>LayoutData</code>属性をレイアウトデータに設定します。</br> また、<code>ControlComponent</code>の<code>width</code>属性、
     * <code>height</code>属性がセットされている場合、それぞれ<code>RowData</code>の<code>width</code>属性、<code>height</code>属性に設定します。
     * 
     * @param controlComponent
     *            <code>LayoutData</code>属性を使用する<code>ControlComponent</code>オブジェクト
     * @return レイアウトデータオブジェクト
     */
    public RowData createLayoutData(ControlComponent controlComponent) {
        Property widthProp = controlComponent.getProperty(WIDTH_PROP);
        Property heightProp = controlComponent.getProperty(HEIGHT_PROP);

        if ((widthProp != null && widthProp.isValueExist())
                || (heightProp != null) && heightProp.isValueExist()
                || (controlComponent.getLayoutDataNum() > 0)) {
            RowData rowData = createLayoutData();

            if ((widthProp != null) && (widthProp.isValueExist())) {
                rowData.width = widthProp.getIntValue();
            }

            if ((heightProp != null) && (heightProp.isValueExist())) {
                rowData.height = heightProp.getIntValue();
            }

            setupLayoutData(rowData, controlComponent);

            return rowData;
        } else {
            return null;
        }
    }

    /**
     * 独自のレイアウトパラメータ処理を行います。</br>
     * <p>
     * <code>margin</code> が指定されていた場合、<code>marginTop</code>、
     * <code>marginBottom</code>、<code>marginRight</code>、<code>marginLeft</code>、
     * <code>marginHeight</code>、<code>marginWidth</code> をすべて同じ値に設定します。
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
    }

    /*
     * @see org.seasar.jface.layout.LayoutSupport#getLayoutName()
     */
    public String getLayoutName() {
        return LAYOUT_NAME;
    }
}
