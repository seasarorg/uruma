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
package org.seasar.uruma.component.jface;

import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * {@link org.eclipse.swt.layout.FillLayout} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class FillLayoutInfo extends AbstractUIElement implements
        LayoutInfo<LayoutDataInfo> {
    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("縦方向マージン")
    private String marginHeight;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("横方向マージン")
    private String marginWidth;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("スペーシング")
    private String spacing;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @FieldDescription("種別")
    private String conversionType;

    /**
     * 縦方向マージンを取得します。<br />
     * 
     * @return 縦方向マージン
     */
    public String getMarginHeight() {
        return this.marginHeight;
    }

    /**
     * 縦方向マージンを設定します。<br />
     * 
     * @param marginHeight
     *            縦方向マージン
     */
    public void setMarginHeight(final String marginHeight) {
        this.marginHeight = marginHeight;
    }

    /**
     * 横方向マージンを取得します。<br />
     * 
     * @return 横方向マージン
     */
    public String getMarginWidth() {
        return this.marginWidth;
    }

    /**
     * 横方向マージンを設定します。<br />
     * 
     * @param marginWidth
     *            横方向マージン
     */
    public void setMarginWidth(final String marginWidth) {
        this.marginWidth = marginWidth;
    }

    /**
     * スペーシングを取得します。<br />
     * 
     * @return スペーシング
     */
    public String getSpacing() {
        return this.spacing;
    }

    /**
     * スペーシングを設定します。<br />
     * 
     * @param spacing
     *            スペーシング
     */
    public void setSpacing(final String spacing) {
        this.spacing = spacing;
    }

    /**
     * 種別を取得します。<br />
     * 
     * @return 種別
     */
    public String getType() {
        return this.conversionType;
    }

    /**
     * 種別を設定します。<br />
     * 
     * @param type
     *            種別
     */
    public void setType(final String type) {
        this.conversionType = type;
    }

    /*
     * @see org.seasar.uruma.component.LayoutInfo#getCommonLayoutDataInfo()
     */
    public LayoutDataInfo getCommonLayoutDataInfo() {
        return null;
    }

    /*
     * @see org.seasar.uruma.component.LayoutInfo#setCommonLayoutDataInfo(org.seasar.uruma.component.LayoutDataInfo)
     */
    public void setCommonLayoutDataInfo(final LayoutDataInfo layoutDataInfo) {
        // Do nothing.
    }
}
