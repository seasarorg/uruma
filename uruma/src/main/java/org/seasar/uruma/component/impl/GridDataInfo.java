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

import org.eclipse.swt.layout.GridData;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.LayoutDataInfo;

/**
 * {@link GridData} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class GridDataInfo extends AbstractUIElement implements LayoutDataInfo {
    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("exclude 属性")
    private String exclude;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("grabExcessHorizontalSpace 属性")
    private String grabExcessHorizontalSpace;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("grabExcessVerticalSpace 属性")
    private String grabExcessVerticalSpace;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("heightHint 属性")
    private String heightHint;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @FieldDescription("horizontalAlignment 属性")
    private String horizontalAlignment;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("horizontalIndent 属性")
    private String horizontalIndent;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("horizontalSpan 属性")
    private String horizontalSpan;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("minimumHeight 属性")
    private String minimumHeight;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("minimumWidth 属性")
    private String minimumWidth;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @FieldDescription("verticalAlignment 属性")
    private String verticalAlignment;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("verticalIndent 属性")
    private String verticalIndent;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("verticalSpan 属性")
    private String verticalSpan;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("widthHint 属性")
    private String widthHint;

    /**
     * exclude 属性を取得します。<br />
     * 
     * @return exclude 属性
     */
    public String getExclude() {
        return this.exclude;
    }

    /**
     * exclude 属性を設定します。<br />
     * 
     * @param exclude
     *            exclude 属性
     */
    public void setExclude(final String exclude) {
        this.exclude = exclude;
    }

    /**
     * grabExcessHorizontalSpace 属性を取得します。<br />
     * 
     * @return grabExcessHorizontalSpace 属性
     */
    public String getGrabExcessHorizontalSpace() {
        return this.grabExcessHorizontalSpace;
    }

    /**
     * grabExcessHorizontalSpace 属性を設定します。<br />
     * 
     * @param grabExcessHorizontalSpace
     *            grabExcessHorizontalSpace 属性
     */
    public void setGrabExcessHorizontalSpace(
            final String grabExcessHorizontalSpace) {
        this.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
    }

    /**
     * grabExcessVerticalSpace 属性を取得します。<br />
     * 
     * @return grabExcessVerticalSpace 属性
     */
    public String getGrabExcessVerticalSpace() {
        return this.grabExcessVerticalSpace;
    }

    /**
     * grabExcessVerticalSpace 属性を設定します。<br />
     * 
     * @param grabExcessVerticalSpace
     *            grabExcessVerticalSpace 属性
     */
    public void setGrabExcessVerticalSpace(final String grabExcessVerticalSpace) {
        this.grabExcessVerticalSpace = grabExcessVerticalSpace;
    }

    /**
     * heightHint 属性を取得します。<br />
     * 
     * @return heightHint 属性
     */
    public String getHeightHint() {
        return this.heightHint;
    }

    /**
     * heightHint 属性を設定します。<br />
     * 
     * @param heightHint
     *            heightHint 属性
     */
    public void setHeightHint(final String heightHint) {
        this.heightHint = heightHint;
    }

    /**
     * horizontalAlignment 属性を取得します。<br />
     * 
     * @return horizontalAlignment 属性
     */
    public String getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    /**
     * horizontalAlignment 属性を設定します。<br />
     * 
     * @param horizontalAlignment
     *            horizontalAlignment 属性
     */
    public void setHorizontalAlignment(final String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    /**
     * horizontalIndent 属性を取得します。<br />
     * 
     * @return horizontalIndent 属性
     */
    public String getHorizontalIndent() {
        return this.horizontalIndent;
    }

    /**
     * horizontalIndent 属性を設定します。<br />
     * 
     * @param horizontalIndent
     *            horizontalIndent 属性
     */
    public void setHorizontalIndent(final String horizontalIndent) {
        this.horizontalIndent = horizontalIndent;
    }

    /**
     * horizontalSpan 属性を取得します。<br />
     * 
     * @return horizontalSpan 属性
     */
    public String getHorizontalSpan() {
        return this.horizontalSpan;
    }

    /**
     * horizontalSpan 属性を設定します。<br />
     * 
     * @param horizontalSpan
     *            horizontalSpan 属性
     */
    public void setHorizontalSpan(final String horizontalSpan) {
        this.horizontalSpan = horizontalSpan;
    }

    /**
     * minimumHeight 属性を取得します。<br />
     * 
     * @return minimumHeight 属性
     */
    public String getMinimumHeight() {
        return this.minimumHeight;
    }

    /**
     * minimumHeight 属性を設定します。<br />
     * 
     * @param minimumHeight
     *            minimumHeight 属性
     */
    public void setMinimumHeight(final String minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    /**
     * minimumWidth 属性を取得します。<br />
     * 
     * @return minimumWidth 属性
     */
    public String getMinimumWidth() {
        return this.minimumWidth;
    }

    /**
     * minimumWidth 属性を設定します。<br />
     * 
     * @param minimumWidth
     *            minimumWidth 属性
     */
    public void setMinimumWidth(final String minimumWidth) {
        this.minimumWidth = minimumWidth;
    }

    /**
     * verticalAlignment 属性を取得します。<br />
     * 
     * @return verticalAlignment 属性
     */
    public String getVerticalAlignment() {
        return this.verticalAlignment;
    }

    /**
     * verticalAlignment 属性を設定します。<br />
     * 
     * @param verticalAlignment
     *            verticalAlignment 属性
     */
    public void setVerticalAlignment(final String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    /**
     * verticalIndent 属性を取得します。<br />
     * 
     * @return verticalIndent 属性
     */
    public String getVerticalIndent() {
        return this.verticalIndent;
    }

    /**
     * verticalIndent 属性を設定します。<br />
     * 
     * @param verticalIndent
     *            verticalIndent 属性
     */
    public void setVerticalIndent(final String verticalIndent) {
        this.verticalIndent = verticalIndent;
    }

    /**
     * verticalSpan 属性を取得します。<br />
     * 
     * @return verticalSpan 属性
     */
    public String getVerticalSpan() {
        return this.verticalSpan;
    }

    /**
     * verticalSpan 属性を設定します。<br />
     * 
     * @param verticalSpan
     *            verticalSpan 属性
     */
    public void setVerticalSpan(final String verticalSpan) {
        this.verticalSpan = verticalSpan;
    }

    /**
     * widthHint 属性を取得します。<br />
     * 
     * @return widthHint 属性
     */
    public String getWidthHint() {
        return this.widthHint;
    }

    /**
     * widthHint 属性を設定します。<br />
     * 
     * @param widthHint
     *            widthHint 属性
     */
    public void setWidthHint(final String widthHint) {
        this.widthHint = widthHint;
    }
}
