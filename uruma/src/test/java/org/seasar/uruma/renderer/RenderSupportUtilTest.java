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
package org.seasar.uruma.renderer;

import junit.framework.TestCase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.exception.RenderException;

/**
 * {@link RendererSupportUtil} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class RenderSupportUtilTest extends TestCase {
    private Display display;

    @Override
    protected void setUp() throws Exception {
        display = Display.getCurrent();
        if (display == null) {
            display = new Display();
            ImageManager.init(display);
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
        src.setBasePath("org/seasar/uruma/renderer");
        DestObject dest = new DestObject();

        RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER);

        assertEquals("1", "StringField1", dest.stringField);
        assertEquals("2", "Text\tField1\nText\tField1\n", dest.textField);
        assertEquals("3", 123, dest.intField);
        assertTrue("4", dest.booleanField);
        assertEquals("5", new RGB(255, 255, 255), dest.colorField.getRGB());
        assertEquals("6", SWT.YES, dest.swtConstField);
        assertEquals("7", ImageManager.getImage("/images/container.gif"),
                dest.imageField);
        assertEquals("8", SWT.CTRL | SWT.ALT | 'A', dest.acceleratorField);
        assertEquals("9", 'A', dest.charField);
        assertEquals("10", 3, dest.intArrayField.length);
        assertEquals("11", 1, dest.intArrayField[0]);
        assertEquals("12", 2, dest.intArrayField[1]);
        assertEquals("13", 3, dest.intArrayField[2]);

        assertEquals("14", "StringField2", dest.getStringProperty());
        assertEquals("15", "Text\tField2\nText\tField2\n", dest
                .getTextProperty());
        assertEquals("16", 456, dest.getIntProperty());
        assertFalse("17", dest.getBooleanProperty());
        assertEquals("18", new RGB(0, 0, 0), dest.getColorProperty().getRGB());
        assertEquals("19", SWT.NO, dest.getSwtConstProperty());
        assertEquals(
                "20",
                ImageManager
                        .getImage("org/seasar/uruma/renderer/../../../../images/container.gif"),
                dest.getImageProperty());
        assertEquals("21", SWT.F2, dest.getAcceleratorProperty());
        assertEquals("22", 'x', dest.getCharProperty());
        assertEquals("23", 1, dest.getIntArrayProperty().length);
        assertEquals("24", 3, dest.getIntArrayProperty()[0]);

        assertNull("25", dest.nonTargetField);
    }

    public void testSetAttributes2() {
        SrcObject2 src = new SrcObject2();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER);
            fail();
        } catch (RenderException ex) {
            assertTrue(true);
        }
    }

    public void testSetAttributes3() {
        SrcObject3 src = new SrcObject3();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER);
            fail();
        } catch (RenderException ex) {
            assertTrue(true);
        }
    }

    public void testSetAttributes4() {
        SrcObject4 src = new SrcObject4();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER);
            fail();
        } catch (RenderException ex) {
            assertTrue(true);
        }
    }
}
