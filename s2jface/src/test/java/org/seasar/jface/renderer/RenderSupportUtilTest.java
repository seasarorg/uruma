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

import junit.framework.TestCase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.seasar.jface.exception.RenderException;
import org.seasar.jface.util.ImageManager;

/**
 * @author y-komori
 * 
 */
public class RenderSupportUtilTest extends TestCase {
    private Display display;

    @Override
    protected void setUp() throws Exception {
        display = Display.getCurrent();
        if (display == null) {
            display = new Display();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        ImageManager.dispose();
        if (display != null) {
            display.dispose();
        }
    }

    public void testSetAttributes1() {
        SrcObject src = new SrcObject();
        src.setBasePath("org/seasar/jface/renderer");
        DestObject dest = new DestObject();

        RendererSupportUtil.setAttributes(src, dest);

        assertEquals("1", "StringField1", dest.stringField);
        assertEquals("2", "Text\tField1\nText\tField1\n", dest.textField);
        assertEquals("3", 123, dest.intField);
        assertTrue("4", dest.booleanField);
        assertEquals("5", new Color(display, 255, 255, 255), dest.colorField);
        assertEquals("6", SWT.YES, dest.swtConstField);
        assertEquals("7", ImageManager.getImage("/images/container.gif"), dest.imageField);

        assertEquals("8", "StringField2", dest.getStringProperty());
        assertEquals("9", "Text\tField2\nText\tField2\n", dest
                .getTextProperty());
        assertEquals("10", 456, dest.getIntProperty());
        assertFalse("11", dest.getBooleanProperty());
        assertEquals("12", new Color(display, 0, 0, 0), dest.getColorProperty());
        assertEquals("13", SWT.NO, dest.getSwtConstProperty());
        assertEquals("14", ImageManager.getImage("org/seasar/jface/renderer/../template/container.gif"), dest.getImageProperty());

        assertNull("15", dest.nonTargetField);
    }

    public void testSetAttributes2() {
        SrcObject2 src = new SrcObject2();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest);
            fail();
        } catch (RenderException ex) {
            assertTrue(true);
        }
    }

    public void testSetAttributes3() {
        SrcObject3 src = new SrcObject3();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest);
            fail();
        } catch (RenderException ex) {
            assertTrue(true);
        }
    }

    public void testSetAttributes4() {
        SrcObject4 src = new SrcObject4();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest);
            fail();
        } catch (RenderException ex) {
            assertTrue(true);
        }
    }
}
