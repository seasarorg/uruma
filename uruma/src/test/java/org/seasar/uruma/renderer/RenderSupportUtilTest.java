/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import java.net.URL;

import junit.framework.TestCase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.resource.internal.DefaultResourceRegistry;
import org.seasar.uruma.util.PathUtil;

/**
 * {@link RendererSupportUtil} のためのテストクラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$
 */
public class RenderSupportUtilTest extends TestCase {
    private Display display;

    private DefaultResourceRegistry registry;

    @Override
    protected void setUp() throws Exception {
        display = Display.getCurrent();
        registry = new DefaultResourceRegistry();
        if (display == null) {
            display = new Display();
            registry.init(display);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        registry.dispose();
        if (display != null) {
            display.dispose();
        }
    }

    public void testSetAttributes1() {
        SrcObject src = new SrcObject();
        URL url = ResourceUtil.getResource(getClass().getName().replace('.', '/') + ".class");
        src.setParentURL(PathUtil.getParentURL(url));
        DestObject dest = new DestObject();

        RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER, registry);

        assertEquals("1", "StringField1", dest.stringField);
        assertEquals("2", "Text\tField1\nText\tField1\n", dest.textField);
        assertEquals("3", 123, dest.intField);
        assertTrue("4", dest.booleanField);
        assertEquals("5", new RGB(255, 255, 255), dest.colorField.getRGB());
        assertEquals("6", SWT.YES, dest.swtConstField);
        assertEquals("7", registry.getImage("/images/container.gif", src.getParentURL()),
                dest.imageField);
        assertEquals("8", SWT.CTRL | SWT.ALT | 'A', dest.acceleratorField);
        assertEquals("9", 'A', dest.charField);
        assertEquals("10", 3, dest.intArrayField.length);
        assertEquals("11", 1, dest.intArrayField[0]);
        assertEquals("12", 2, dest.intArrayField[1]);
        assertEquals("13", 3, dest.intArrayField[2]);

        assertEquals("14", "StringField2", dest.getStringProperty());
        assertEquals("15", "Text\tField2\nText\tField2\n", dest.getTextProperty());
        assertEquals("16", 456, dest.getIntProperty());
        assertFalse("17", dest.getBooleanProperty());
        assertEquals("18", new RGB(0, 0, 0), dest.getColorProperty().getRGB());
        assertEquals("19", SWT.NO, dest.getSwtConstProperty());
        assertEquals("20", registry
                .getImage("../../../../images/container.gif", src.getParentURL()), dest
                .getImageProperty());
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
            RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER, registry);
            // TODO エラーログ出力を確認するようにする
            assertTrue(true);
        } catch (RenderException ex) {
            fail(ex.getMessage());
        }
    }

    public void testSetAttributes3() {
        SrcObject3 src = new SrcObject3();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER, registry);
            // TODO エラーログ出力を確認するようにする
            assertTrue(true);
        } catch (RenderException ex) {
            fail(ex.getMessage());
        }
    }

    public void testSetAttributes4() {
        SrcObject4 src = new SrcObject4();
        DestObject dest = new DestObject();

        try {
            RendererSupportUtil.setAttributes(src, dest, SetTiming.RENDER, registry);
            // TODO エラーログ出力を確認するようにする
            assertTrue(true);
        } catch (RenderException ex) {
            fail(ex.getMessage());
        }
    }
}
