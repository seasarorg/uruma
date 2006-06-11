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
package org.seasar.jface.renderer;

import static org.seasar.jface.renderer.info.ScaleInfo.STYLE_PROP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComboboxInfo;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.util.SWTUtil;

/**
 * <code>Combobox</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class ComboboxRenderer extends AbstractControlRenderer<Combo> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        String style = controlComponent.getPropertyValue(STYLE_PROP);
        int styleValue = SWTUtil.getStyle(style, SWT.SIMPLE);
        return styleValue;
    }

    @Override
    protected void doRender(final Combo combo,
            final ControlComponent controlComponent) {
        addItems(combo, controlComponent);
    }

    @Override
    protected Class<Combo> getControlType() {
        return Combo.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return ComboboxInfo.class;
    }

    public String getRendererName() {
        return "combobox";
    }
    
    protected void addItems(final Combo combo,
            final ControlComponent controlComponent) {
        for (Item item : controlComponent.getItemList()) {
            combo.add(item.getValue());
        }
    }
}
