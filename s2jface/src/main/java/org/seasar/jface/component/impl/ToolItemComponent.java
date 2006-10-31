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
package org.seasar.jface.component.impl;

import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;
import org.seasar.jface.component.EnabledDependable;

/**
 * @author bskuroneko
 * 
 */
public class ToolItemComponent extends AbstractItemComponent implements
        EnabledDependable {

    @ComponentAttribute(conversionType = ConversionType.IMAGE)
    private String disabledImage;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String enabled;

    @ComponentAttribute(conversionType = ConversionType.IMAGE)
    private String hotImage;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String selection;

    @ComponentAttribute(conversionType = ConversionType.TEXT)
    private String toolTipText;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private String width;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String enabledDependId;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String enabledDependType;

    public String getDisabledImage() {
        return this.disabledImage;
    }

    public void setDisabledImage(String disabledImage) {
        this.disabledImage = disabledImage;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getHotImage() {
        return this.hotImage;
    }

    public void setHotImage(String hotImage) {
        this.hotImage = hotImage;
    }

    public String getSelection() {
        return this.selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getToolTipText() {
        return this.toolTipText;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getEnabledDependId() {
        return this.enabledDependId;
    }

    public void setEnabledDependId(String enabledDependId) {
        this.enabledDependId = enabledDependId;
    }

    public String getEnabledDependType() {
        return this.enabledDependType;
    }

    public void setEnabledDependType(String enabledDependType) {
        this.enabledDependType = enabledDependType;
    }
}
