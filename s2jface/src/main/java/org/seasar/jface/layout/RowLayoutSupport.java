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

import static org.seasar.jface.component.impl.ControlComponent.HEIGHT_PROP;
import static org.seasar.jface.component.impl.ControlComponent.WIDTH_PROP;

import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * <code>RowLayout</code>と<code>RowData</code>の生成をサポートする<code>LayoutSupport</code>クラスです。</br>
 * 
 * @author y-komori
 * @see org.eclipse.swt.layout.RowLayout
 * @see org.eclipse.swt.layout.RowData
 */
public class RowLayoutSupport extends AbstractLayoutSupport<RowLayout, RowData> {
    private static final String LAYOUT_NAME = "row";

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

    /*
     * @see org.seasar.jface.layout.LayoutSupport#getLayoutName()
     */
    public String getLayoutName() {
        return LAYOUT_NAME;
    }
}
