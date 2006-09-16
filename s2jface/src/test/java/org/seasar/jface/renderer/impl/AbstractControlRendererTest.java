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
package org.seasar.jface.renderer.impl;

import junit.framework.TestCase;

import org.eclipse.swt.widgets.Control;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UICompositeComponent;
import org.seasar.jface.component.impl.ButtonComponent;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.component.impl.GridDataInfo;
import org.seasar.jface.component.impl.GridLayoutInfo;
import org.seasar.jface.renderer.impl.AbstractControlRenderer;

public class AbstractControlRendererTest extends TestCase {
    private ControlRenderer controlRenderer;

    @Override
    protected void setUp() throws Exception {
        this.controlRenderer = new ControlRenderer();
    }

    public void testInheritLayoutData() {
        UICompositeComponent parentComponent = new CompositeComponent();
        GridLayoutInfo gridLayoutInfo = new GridLayoutInfo();
        parentComponent.setLayoutInfo(gridLayoutInfo);
        GridDataInfo parentGridData = new GridDataInfo();
        parentGridData.setHeightHint("200");
        parentGridData.setWidthHint("400");
        parentGridData.setMinimumHeight("150");
        gridLayoutInfo.setCommonLayoutDataInfo(parentGridData);

        UIComponent component = new ButtonComponent();
        parentComponent.addChild(component);
        GridDataInfo gridDataInfo = new GridDataInfo();
        gridDataInfo.setMinimumHeight("50");
        gridDataInfo.setMinimumWidth("100");
        gridDataInfo.setHorizontalSpan("3");
        component.setLayoutDataInfo(gridDataInfo);

        controlRenderer.inheritLayoutData(component);

        assertEquals("1", "200", gridDataInfo.getHeightHint());
        assertEquals("2", "400", gridDataInfo.getWidthHint());
        assertEquals("3", "50", gridDataInfo.getMinimumHeight());
        assertEquals("4", "100", gridDataInfo.getMinimumWidth());
        assertEquals("5", "3", gridDataInfo.getHorizontalSpan());
    }

    private class ControlRenderer extends
            AbstractControlRenderer<ControlComponent, Control> {

        @Override
        protected void doRender(ControlComponent controlComponent,
                Control control) {
        }

        @Override
        protected Class<Control> getControlType() {
            return Control.class;
        }
    }
}
