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
package org.seasar.uruma.component.impl;

import org.eclipse.swt.layout.RowData;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.LayoutDataInfo;

/**
 * {@link RowData} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class RowDataInfo extends AbstractUIElement implements LayoutDataInfo {
    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("exclude 属性")
    private String exclude;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("高さ")
    private String height;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("幅")
    private String width;

    /**
     * <code>exclude</code> 属性を取得します。<br />
     * 
     * @return <code>exclude</code> 属性
     * @see RowData#exclude
     */
    public String getExclude() {
        return this.exclude;
    }

    /**
     * <code>exclude</code> 属性を設定します。<br />
     * 
     * @param exclude
     *            <code>exclude</code> 属性
     * @see RowData#exclude
     */
    public void setExclude(final String exclude) {
        this.exclude = exclude;
    }

    /**
     * 高さを取得します。<br />
     * 
     * @return 高さ
     */
    public String getHeight() {
        return this.height;
    }

    /**
     * 高さを設定します。<br />
     * 
     * @param height
     *            高さ
     */
    public void setHeight(final String height) {
        this.height = height;
    }

    /**
     * 幅を取得します。<br />
     * 
     * @return 幅
     */
    public String getWidth() {
        return this.width;
    }

    /**
     * 幅を設定します。<br />
     * 
     * @param width
     *            幅
     */
    public void setWidth(final String width) {
        this.width = width;
    }
}
