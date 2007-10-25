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
package org.seasar.uruma.renderer.impl;

import junit.framework.TestCase;

import org.eclipse.swt.widgets.Control;
import org.seasar.uruma.component.UICompositeComponent;
import org.seasar.uruma.component.UIControlComponent;
import org.seasar.uruma.component.impl.ButtonComponent;
import org.seasar.uruma.component.impl.CommonAttriburtesImpl;
import org.seasar.uruma.component.impl.CompositeComponent;
import org.seasar.uruma.component.impl.ControlComponent;
import org.seasar.uruma.component.impl.GridDataInfo;
import org.seasar.uruma.component.impl.GridLayoutInfo;

/**
 * {@link AbstractControlRenderer} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class AbstractControlRendererTest extends TestCase {
    private ControlRenderer controlRenderer;

    /*
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.controlRenderer = new ControlRenderer();
    }

    /**
     * {@link AbstractControlRenderer#inheritLayoutData(UIControlComponent)}
     * メソッドのテストです。<br />
     */
    public void testInheritLayoutData() {
        UICompositeComponent parentComponent = new CompositeComponent();
        GridLayoutInfo gridLayoutInfo = new GridLayoutInfo();
        parentComponent.setLayoutInfo(gridLayoutInfo);
        GridDataInfo parentGridData = new GridDataInfo();
        parentGridData.setHeightHint("200");
        parentGridData.setWidthHint("400");
        parentGridData.setMinimumHeight("150");
        gridLayoutInfo.setCommonLayoutDataInfo(parentGridData);

        UIControlComponent component = new ButtonComponent();
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

    /**
     * {@link AbstractControlRenderer#setCommonAttributes(org.seasar.uruma.component.UIComponent)}
     * メソッドのテストです。<br />
     */
    public void testSetCommonAttributes() {
        UICompositeComponent parentComponent = new CompositeComponent();
        CommonAttriburtesImpl commonAttributes = new CommonAttriburtesImpl();
        commonAttributes.setBackGround("WHITE");
        commonAttributes.setFontHeight("20");
        parentComponent.setCommonAttributes(commonAttributes);

        ButtonComponent component = new ButtonComponent();
        parentComponent.addChild(component);
        component.setFontHeight("15");

        controlRenderer.setCommonAttributes(component);

        assertEquals("1", "WHITE", component.getBackground());
        assertEquals("2", "15", component.getFontHeight());
    }

    /**
     * @author y-komori
     */
    private static class ControlRenderer extends
            AbstractControlRenderer<ControlComponent, Control> {

        @Override
        protected void doRenderControl(final ControlComponent controlComponent,
                final Control control) {
        }

        @Override
        protected Class<Control> getWidgetType() {
            return Control.class;
        }
    }
}
