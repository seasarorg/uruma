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
package org.seasar.jface.layout;

import junit.framework.TestCase;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * @author y-komori
 * 
 */
public class GridLayoutSupportTest extends TestCase {
    private GridLayoutSupport support;

    protected void setUp() throws Exception {
        support = new GridLayoutSupport();
    }

    public void testCreateLayout() {
        GridLayout layout = support.createLayout();
        assertNotNull("1", layout);
        assertTrue("2", layout instanceof GridLayout);

        layout = support
                .createLayout("numColumns=5; makeColumnsEqualWidth=true;"
                        + "marginTop=10; marginBottom=15; marginRight=20; marginLeft=25;"
                        + "marginHeight=30; marginWidth=35; horizontalSpacing=40; verticalSpacing=45");
        assertEquals("3", 5, layout.numColumns);
        assertTrue("4", layout.makeColumnsEqualWidth);
        assertEquals("5", 10, layout.marginTop);
        assertEquals("6", 15, layout.marginBottom);
        assertEquals("7", 20, layout.marginRight);
        assertEquals("8", 25, layout.marginLeft);
        assertEquals("9", 30, layout.marginHeight);
        assertEquals("10", 35, layout.marginWidth);
        assertEquals("11", 40, layout.horizontalSpacing);
        assertEquals("12", 45, layout.verticalSpacing);

        layout = support.createLayout("margin=10; spacing=20");
        assertEquals("13", 10, layout.marginTop);
        assertEquals("14", 10, layout.marginBottom);
        assertEquals("15", 10, layout.marginRight);
        assertEquals("16", 10, layout.marginLeft);
        assertEquals("17", 10, layout.marginHeight);
        assertEquals("18", 10, layout.marginWidth);
        assertEquals("19", 20, layout.horizontalSpacing);
        assertEquals("20", 20, layout.verticalSpacing);
    }

    public void testGetLayoutType() {
        assertEquals(GridLayout.class, support.getLayoutType());
    }

    public void testCreateLayoutData() {
        assertTrue(support.createLayoutData() instanceof GridData);
    }

    public void testCreateLayoutDataControlComponent() {
        assertTrue(support.createLayoutData(new ControlComponent()) instanceof GridData);
    }

    public void testGetLayoutName() {
        assertEquals("grid", support.getLayoutName());
    }
}
