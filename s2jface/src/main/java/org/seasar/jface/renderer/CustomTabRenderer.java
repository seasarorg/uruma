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

import static org.seasar.jface.renderer.info.CustomTabInfo.SIMPLE_STYLE_PROP;
import static org.seasar.jface.renderer.info.CustomTabInfo.CLOSE_BUTTON_PROP;
import static org.seasar.jface.renderer.info.CustomTabInfo.TEXT_PROP;

import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.CustomTabInfo;

/**
 * <code>CustomTab</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class CustomTabRenderer extends AbstractControlRenderer<CTabFolder> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        int style = SWT.NONE;
        return style;
    }

    @Override
    protected void doRender(final CTabFolder ctabFolder,
            final ControlComponent controlComponent) {
        Property simpleStyleValue = controlComponent
                .getProperty(SIMPLE_STYLE_PROP);
        ctabFolder.setSimple(simpleStyleValue != null ?simpleStyleValue.getBooleanValue() : false);
        addTab(ctabFolder, controlComponent);
    }

    @Override
    protected Class<CTabFolder> getControlType() {
        return CTabFolder.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return CustomTabInfo.class;
    }

    public String getRendererName() {
        return "customTab";
    }

    protected void addTab(final CTabFolder ctabFolder,
            final ControlComponent controlComponent) {
        String tabsValue = controlComponent.getPropertyValue(TEXT_PROP);
        Property closeButtonValue = controlComponent
                .getProperty(CLOSE_BUTTON_PROP);
        boolean hasCloseButton = closeButtonValue != null ? closeButtonValue
                .getBooleanValue() : false;
        StringTokenizer st = new StringTokenizer(tabsValue, ",");
        while (st.hasMoreTokens()) {
            CTabItem tabItem = new CTabItem(ctabFolder,
                    hasCloseButton ? SWT.CLOSE : SWT.NONE);
            tabItem.setText(st.nextToken());
        }
    }
}
