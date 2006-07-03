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
package org.seasar.jface.util;

import java.util.ResourceBundle;

import junit.framework.TestCase;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.seasar.jface.exception.ResourceNotFoundException;

/**
 * @author y-komori
 * 
 */
public class ImageManagerTest extends TestCase {
    private Display display;

    @Override
    protected void setUp() throws Exception {
        display = new Display();
    }

    @Override
    protected void tearDown() throws Exception {
        display.dispose();
    }

    public void testPutImage() {
        ImageManager.putImage("ARG_IMG", "/images/arg.gif");

        assertNotNull(ImageManager.getImage("ARG_IMG"));

        try {
            ImageManager.putImage("DUMMY_IMG", "dummy");
            fail();
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            assertTrue(true);
        }
    }

    public void testPutImageDescriptor() {
        ImageManager.putImageDescriptor("ARG_IMG", "/images/arg.gif");
        Image argImage = ImageManager.getImage("ARG_IMG");
        assertNotNull(argImage);
    }

    public void testGetImage() {
        assertNotNull(ImageManager.loadImage("/images/container.gif"));
        assertNotNull(ImageManager.loadImage("images/container.gif"));

        try {
            ImageManager.loadImage("DUMMY_IMG");
            fail();
        } catch (ResourceNotFoundException ex) {
            assertTrue(true);
        }
    }

    public void testLoadImages() {
        loadImages();

        assertNotNull(ImageManager.getImage("ARG_IMG"));
        assertNotNull(ImageManager.getImage("COMPONENT_IMG"));
        assertNotNull(ImageManager.getImage("CONTAINER_IMG"));
        assertNotNull(ImageManager.getImage("INCLUDE_IMG"));
        assertNotNull(ImageManager.getImage("PROPERTY_IMG"));
    }

    public void testInjectImages() {
        loadImages();
        ImageManager.injectImages(Images.class);

        assertNotNull(Images.ARG_IMG);
        assertEquals(ImageManager.getImage("ARG_IMG"), Images.ARG_IMG);

        assertNotNull(Images.COMPONENT_IMG);

        assertNotNull(Images.CONTAINER_IMG);
        assertEquals(ImageManager.getImage("CONTAINER_IMG"),
                Images.CONTAINER_IMG);

        assertNotNull(Images.INCLUDE_IMG);
    }

    public void testDispose() {
        loadImages();
        Image argImage = ImageManager.getImage("ARG_IMG");
        Image containerImage = ImageManager.getImage("CONTAINER_IMG");
        assertNotNull(argImage);
        assertNotNull(containerImage);

        ImageManager.dispose();

        assertTrue(argImage.isDisposed());
        assertTrue(containerImage.isDisposed());
    }

    protected void loadImages() {
        ResourceBundle imageResources = ResourceBundle
                .getBundle("org/seasar/jface/util/ImageManagerTest");
        ImageManager.loadImages(imageResources);
    }

    public static class Images {
        public static Image ARG_IMG;

        public static ImageDescriptor COMPONENT_IMG;

        public static Image CONTAINER_IMG;

        public static ImageDescriptor INCLUDE_IMG;

        public static Image DUMMY_IMAGE;

        public static ImageDescriptor DUMMY_IMAGE_DESC;

        public static final Image NO_TARGET_1 = null;

        private Image NO_TARGET_2;

        private static Image NO_TARGET_3;

        protected Image NO_TARGET_4;

        protected static Image NO_TARGET_5;

        Image NO_TARGET_6;

        static Image NO_TARGET_7;
    }
}
