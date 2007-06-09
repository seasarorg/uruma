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
package org.seasar.jface.component.impl;

import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.component.LayoutDataInfo;

public class GridDataInfo extends AbstractUIElement implements LayoutDataInfo {
    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    private String exclude;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    private String grabExcessHorizontalSpace;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    private String grabExcessVerticalSpace;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String heightHint;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    private String horizontalAlignment;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String horizontalIndent;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String horizontalSpan;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String minimumHeight;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String minimumWidth;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    private String verticalAlignment;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String verticalIndent;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String verticalSpan;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String widthHint;

    public String getExclude() {
        return this.exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getGrabExcessHorizontalSpace() {
        return this.grabExcessHorizontalSpace;
    }

    public void setGrabExcessHorizontalSpace(String grabExcessHorizontalSpace) {
        this.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
    }

    public String getGrabExcessVerticalSpace() {
        return this.grabExcessVerticalSpace;
    }

    public void setGrabExcessVerticalSpace(String grabExcessVerticalSpace) {
        this.grabExcessVerticalSpace = grabExcessVerticalSpace;
    }

    public String getHeightHint() {
        return this.heightHint;
    }

    public void setHeightHint(String heightHint) {
        this.heightHint = heightHint;
    }

    public String getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    public void setHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public String getHorizontalIndent() {
        return this.horizontalIndent;
    }

    public void setHorizontalIndent(String horizontalIndent) {
        this.horizontalIndent = horizontalIndent;
    }

    public String getHorizontalSpan() {
        return this.horizontalSpan;
    }

    public void setHorizontalSpan(String horizontalSpan) {
        this.horizontalSpan = horizontalSpan;
    }

    public String getMinimumHeight() {
        return this.minimumHeight;
    }

    public void setMinimumHeight(String minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public String getMinimumWidth() {
        return this.minimumWidth;
    }

    public void setMinimumWidth(String minimumWidth) {
        this.minimumWidth = minimumWidth;
    }

    public String getVerticalAlignment() {
        return this.verticalAlignment;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public String getVerticalIndent() {
        return this.verticalIndent;
    }

    public void setVerticalIndent(String verticalIndent) {
        this.verticalIndent = verticalIndent;
    }

    public String getVerticalSpan() {
        return this.verticalSpan;
    }

    public void setVerticalSpan(String verticalSpan) {
        this.verticalSpan = verticalSpan;
    }

    public String getWidthHint() {
        return this.widthHint;
    }

    public void setWidthHint(String widthHint) {
        this.widthHint = widthHint;
    }
}
