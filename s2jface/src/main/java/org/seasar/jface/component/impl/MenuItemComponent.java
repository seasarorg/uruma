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

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;
import org.seasar.jface.component.Menu;
import org.seasar.jface.component.MenuItem;

/**
 * メニュー項目情報を保持するためのこんぽーねんとです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class MenuItemComponent extends AbstractItemComponent implements
        MenuItem {

    @ComponentAttribute(conversionType = ConversionType.ACCELERATOR)
    private String accelerator;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String enabled;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String selection;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String enabledDelegationId;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String enabledDelegationType;

    private Menu parentMenu;

    public String getAccelerator() {
        return this.accelerator;
    }

    public void setAccelerator(String accelerator) {
        this.accelerator = accelerator;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getSelection() {
        return this.selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getEnabledDelegationId() {
        return this.enabledDelegationId;
    }

    public void setEnabledDelegationId(String enabledDelegationId) {
        this.enabledDelegationId = enabledDelegationId;
    }

    public String getEnabledDelegationType() {
        return this.enabledDelegationType;
    }

    public void setEnabledDelegationType(String enabledDelegationType) {
        this.enabledDelegationType = enabledDelegationType;
    }

    public Menu getParentMenu() {
        return this.parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    @Override
    protected void doRender(Widget parent, WindowContext context) {
        context.addEnabledDelegationWidget(getWidget(), enabledDelegationId,
                enabledDelegationType);
    }
}
