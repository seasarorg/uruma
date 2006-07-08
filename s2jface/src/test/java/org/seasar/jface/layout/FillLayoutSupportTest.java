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
import org.eclipse.swt.layout.FillLayout;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * @author y-komori
 * 
 */
public class FillLayoutSupportTest extends TestCase {
    private FillLayoutSupport support;

    @Override
    protected void setUp() throws Exception {
        support = new FillLayoutSupport();
    }

    public void testCreateLayout() {
        FillLayout layout = support.createLayout();
        assertNotNull("1", layout);
        assertTrue("2", layout instanceof FillLayout);

        layout = support
                .createLayout("type=HORIZONTAL; spacing=10; marginHeight=20; marginWidth=30");
        assertEquals("3", SWT.HORIZONTAL, layout.type);
        assertEquals("4", 10, layout.spacing);
        assertEquals("5", 20, layout.marginHeight);
        assertEquals("6", 30, layout.marginWidth);

        layout = support.createLayout("type=VERTICAL; margin=40");
        assertEquals("7", SWT.VERTICAL, layout.type);
        assertEquals("8", 40, layout.marginHeight);
        assertEquals("9", 40, layout.marginWidth);
    }

    public void testGetLayoutType() {
        assertEquals(FillLayout.class, support.getLayoutType());
    }

    public void testCreateLayoutData() {
        assertNull("1", support.createLayoutData());
        assertNull("2", support.createLayoutData(new ControlComponent()));
    }

    public void testGetLayoutName() {
        assertEquals("fill", support.getLayoutName());
    }
}
