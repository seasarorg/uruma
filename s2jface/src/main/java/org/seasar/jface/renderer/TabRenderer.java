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

import static org.seasar.jface.renderer.info.TabInfo.TEXT_PROP;

import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.TabInfo;

/**
 * <code>Tab</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class TabRenderer extends AbstractControlRenderer<TabFolder> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        int style = SWT.NULL;
        return style;
    }

    @Override
    protected void doRender(final TabFolder tabFolder,
            final ControlComponent controlComponent) {
        addTab(tabFolder, controlComponent);
    }

    @Override
    protected Class<TabFolder> getControlType() {
        return TabFolder.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return TabInfo.class;
    }

    public String getRendererName() {
        return "tab";
    }
    
    protected void addTab(final TabFolder tabFolder,
            final ControlComponent controlComponent) {
        String tabs = controlComponent.getPropertyValue(TEXT_PROP);
        StringTokenizer st = new StringTokenizer(tabs, ",");
        while (st.hasMoreTokens()) {
            TabItem tabItem = new TabItem(tabFolder, SWT.NULL);
            tabItem.setText(st.nextToken());
        }
    }
}
