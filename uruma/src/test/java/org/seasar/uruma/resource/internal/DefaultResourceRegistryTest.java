/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package org.seasar.uruma.resource.internal;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.uruma.unit.AbstractShellTestCase;
import org.seasar.uruma.util.PathUtil;

/**
 * {@link DefaultResourceRegistry} のためのテストクラスです。<br />
 * 
 * @author yusuke.komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
@RunWith(Seasar2.class)
public class DefaultResourceRegistryTest extends AbstractShellTestCase {
    protected DefaultResourceRegistry registry;

    /*
     * @see org.seasar.uruma.unit.AbstractShellTestCase#before()
     */
    @Override
    public void before() throws Exception {
        super.before();
        registry = new DefaultResourceRegistry();
        registry.init(display);
    }

    /*
     * @see org.seasar.uruma.unit.AbstractShellTestCase#after()
     */
    @Override
    protected void after() throws Exception {
        super.after();
        registry.dispose();
    }

    /**
     * {@link DefaultResourceRegistry#loadImages(java.util.ResourceBundle)}
     * メソッドのためのテストです。<br />
     */
    public void testLoadImages() {
        ResourceBundle bundle = ResourceBundle.getBundle(PathUtil.getPath(getClass(), getClass()
                .getSimpleName()));
        registry.loadImages(bundle);

        assertNotMissingImage("1", registry.getImage("ARG_IMG"));
        assertNotMissingImage("2", registry.getImage("COMPONENT_IMG"));
        assertNotMissingImage("3", registry.getImage("CONTAINER_IMG"));
        assertNotMissingImage("4", registry.getImage("INCLUDE_IMG"));
        assertNotMissingImage("5", registry.getImage("PROPERTY_IMG"));
        assertMissingImage("6", registry.getImage("DUMMY_IMG"));
    }

    /**
     * {@link DefaultResourceRegistry#getImage(String)} メソッドのためのテストです。<br />
     */
    public void testGetImage() {
        // クラスパスルートからの絶対パスによる指定
        Image image1 = registry.getImage("/images/arg.gif");
        assertNotMissingImage("1", image1);
        Image image2 = registry.getImage("/images/arg.gif");
        assertSame("2", image1, image2);

        // 相対クラスパスによる指定
        URL parentUrl = PathUtil.getParentURL(getClass());
        Image image3 = registry.getImage("arg.gif", parentUrl);
        assertNotMissingImage("3", image1);
        Image image4 = registry.getImage("arg.gif", parentUrl);
        assertSame("4", image3, image4);

        // URLによる指定
        String spec = parentUrl + "/arg.gif";
        Image image5 = registry.getImage(spec);
        assertNotMissingImage("5", image5);
        Image image6 = registry.getImage(spec);
        assertSame("6", image5, image6);
    }

    protected void assertMissingImage(final String message, final Image image) {
        assertNotNull(message, image);

        byte[] imageBytes = image.getImageData().data;
        byte[] missingImageBytes = ImageDescriptor.getMissingImageDescriptor().getImageData().data;
        assertArrayEquals(message, missingImageBytes, imageBytes);
    }

    protected void assertNotMissingImage(final String message, final Image image) {
        assertNotNull(message, image);

        byte[] imageBytes = image.getImageData().data;
        byte[] missingImageBytes = ImageDescriptor.getMissingImageDescriptor().getImageData().data;
        if (Arrays.equals(missingImageBytes, imageBytes)) {
            fail(message);
        }
    }
}
