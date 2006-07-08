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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;

/**
 * @author y-komori
 * 
 */
public class RowLayoutSupportTest extends TestCase {
    private RowLayoutSupport support;

    @Override
    protected void setUp() throws Exception {
        support = new RowLayoutSupport();
    }

    public void testCreateLayout() {
        RowLayout layout = support.createLayout();
        assertNotNull("1", layout);
        assertTrue("2", layout instanceof RowLayout);

        layout = support
                .createLayout("fill=true; justify=true;"
                        + "marginBottom=5; marginTop=10; marginLeft=15; marginRight=20; marginHeight=25; marginWidth=30;"
                        + "pack=true; spacing=35; type=HORIZONTAL; wrap=true");
        assertTrue("3", layout.fill);
        assertEquals("4", 5, layout.marginBottom);
        assertEquals("5", 10, layout.marginTop);
        assertEquals("6", 15, layout.marginLeft);
        assertEquals("7", 20, layout.marginRight);
        assertEquals("8", 25, layout.marginHeight);
        assertEquals("9", 30, layout.marginWidth);
        assertTrue("10", layout.pack);
        assertEquals("11", 35, layout.spacing);
        assertEquals("12", SWT.HORIZONTAL, layout.type);
        assertTrue("13", layout.wrap);

        layout = support.createLayout("margin=10; type=VERTICAL");
        assertEquals("14", 10, layout.marginBottom);
        assertEquals("15", 10, layout.marginTop);
        assertEquals("16", 10, layout.marginLeft);
        assertEquals("17", 10, layout.marginRight);
        assertEquals("18", 10, layout.marginHeight);
        assertEquals("19", 10, layout.marginWidth);
        assertEquals("20", SWT.VERTICAL, layout.type);
    }

    public void testGetLayoutType() {
        assertEquals(RowLayout.class, support.getLayoutType());
    }

    public void testCreateLayoutData() {
        assertTrue("1", support.createLayoutData() instanceof RowData);
    }

    public void testGetLayoutName() {
        assertEquals("row", support.getLayoutName());
    }
}
