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

import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.resource.ResourceRegistry;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ResourceRegistry} の実装クラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class DefaultResourceRegistry implements ResourceRegistry {
    protected static UrumaLogger logger = UrumaLogger.getLogger(DefaultResourceRegistry.class);

    protected boolean initialized;

    protected ImageRegistry imageRegistry;

    /**
     * リソースレジストリを初期化します。<br />
     * リソースレジストリを利用するときには必ず本メソッドを呼び出してください。<br />
     * 既に初期化されている場合は何も行いません。
     * 
     * @param display
     *        {@link Display} オブジェクト
     */
    public synchronized void init(final Display display) {
        if (initialized) {
            return;
        }
        AssertionUtil.assertNotNull("display", display);
        imageRegistry = new ImageRegistry(display);
        initialized = true;
    }

    /**
     * リソースレジストリが管理するリソースを破棄します。<br />
     */
    public synchronized void dispose() {
        if (!initialized) {
            return;
        }
        imageRegistry.dispose();
        imageRegistry = null;
        initialized = false;
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#getImage(java.lang.String)
     */
    public Image getImage(final String key) {
        return getImage(key, null);
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#getImageDescriptor(java.lang.String)
     */
    public ImageDescriptor getImageDescriptor(final String key) {
        return getImageDescriptor(key, null);
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#getImage(java.lang.String, java.net.URL)
     */
    public Image getImage(final String path, final URL parentUrl) {
        AssertionUtil.assertNotNull("path", path);
        Image image = imageRegistry.get(path);
        if (image != null) {
            return image;
        }
        String id = UrumaImageDescriptor.createId(parentUrl, path);
        image = imageRegistry.get(id);
        if (image == null) {
            UrumaImageDescriptor desc = new UrumaImageDescriptor(parentUrl, path);
            image = desc.createImage();
            checkKey(desc.id);
            imageRegistry.put(desc.id, image);
        }
        return image;
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#getImageDescriptor(java.lang.String, java.net.URL)
     */
    public ImageDescriptor getImageDescriptor(final String path, final URL parentUrl) {
        AssertionUtil.assertNotNull("path", path);
        ImageDescriptor desc = imageRegistry.getDescriptor(path);
        if (desc != null) {
            return desc;
        }

        String id = UrumaImageDescriptor.createId(parentUrl, path);
        desc = imageRegistry.getDescriptor(id);
        if (desc == null) {
            desc = new UrumaImageDescriptor(parentUrl, path);
            checkKey(id);
            imageRegistry.put(id, desc);
        }
        return desc;
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#putImage(java.lang.String, org.eclipse.jface.resource.ImageDescriptor)
     */
    public void putImage(final String key, final ImageDescriptor descriptor) {
        AssertionUtil.assertNotNull("key", key);
        AssertionUtil.assertNotNull("descriptor", descriptor);

        checkKey(key);
        imageRegistry.put(key, descriptor);
    }

    /**
     * {@link ResourceBundle} からイメージを読み込み、一括登録します。<br />
     * 本メソッドは {@link #loadImages(ResourceBundle)} メソッドをより簡単に利用するためのものです。
     * 
     * @param bundleBaseName
     *        リソースバンドルの基底名
     */
    public void loadImages(final String bundleBaseName) {
        ResourceBundle imageResources = ResourceBundle.getBundle(bundleBaseName);
        loadImages(imageResources);
    }

    /**
     * {@link ResourceBundle} からイメージを読み込み、一括登録します。<br />
     * <p>
     * 「key=path」の形式で記述されたプロパティファイルを元にした {@link ResourceBundle} からイメージ
     * を一括して読み込みます。
     * </p>
     * <p>
     * 本メソッドではイメージを {@link ImageDescriptor} として登録します。
     * </p>
     * 
     * <p>
     * コーディング例
     * </p>
     * 
     * <pre>
     * ResourceBundle imageResources = ResourceBundle.getBundle(&quot;urumaImages&quot;);
     * ImageManager.loadImages(imageResources);
     * </pre>
     * 
     * @param resourceBundle
     *        リソースバンドルの参照
     */
    public void loadImages(final ResourceBundle resourceBundle) {
        URL parentUrl = ResourceUtil.getResource("");
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String path = resourceBundle.getString(key);
            UrumaImageDescriptor desc = new UrumaImageDescriptor(parentUrl, path);
            checkKey(key);
            imageRegistry.put(key, desc);
        }
    }

    protected void checkKey(final String key) {
        if (imageRegistry.get(key) != null) {
            imageRegistry.remove(key);
        }
    }
}
