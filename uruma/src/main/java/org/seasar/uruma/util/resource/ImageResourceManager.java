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
package org.seasar.uruma.util.resource;

import static org.seasar.uruma.core.UrumaMessageCodes.*;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.URLUtil;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.PathUtil;

/**
 * イメージリソースを統一的に扱うためのクラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
@Deprecated
public class ImageResourceManager {
    private static UrumaLogger LOGGER = UrumaLogger.getLogger(ImageResourceManager.class);

    protected ImageRegistry keyImageRegistry;

    protected ImageRegistry pathImageRegistry;

    /**
     * {@link ImageResourceManager} を構築します。<br />
     * 
     * @param display
     *        {@link Display} オブジェクト
     */
    public ImageResourceManager(final Display display) {
        AssertionUtil.assertNotNull("display", display);
        keyImageRegistry = new ImageRegistry(display);
        pathImageRegistry = new ImageRegistry(display);
    }

    /**
     * {@link ImageResourceManager} を破棄します。<br />
     */
    public void dispose() {
        keyImageRegistry.dispose();
        keyImageRegistry = null;
    }

    /**
     * {@link ResourceBundle} からイメージを読み込み、一括登録します。<br />
     * <p>
     * 「key=path」の形式で記述されたプロパティファイルを元にした {@link ResourceBundle} から {@link Image}
     * オブジェクトを一括して読み込みます。
     * </p>
     * <p>
     * イメージの読み込み方法については、{@link #getImage(String, URL)} メソッドに準じます。<br />
     * すべての方法でイメージを取得できなかった場合、欠落したイメージを表す赤い矩形のイメージを登録します。<br />
     * </p>
     * 
     * <p>
     * コーディング例
     * </p>
     * 
     * <pre>
     * ResourceBundle imageResources = ResourceBundle.getBundle(&quot;urumaImages&quot;);
     * imageManager.loadImages(imageResources);
     * </pre>
     * 
     * @param bundle
     *        リソースバンドルの参照
     */
    public void loadImages(final ResourceBundle bundle) {
        URL parentUrl = ResourceUtil.getResource("");
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String path = bundle.getString(key);
            Image image = internalLoadImage(path, parentUrl);
            if (image != null) {
                put(keyImageRegistry, key, image);
            } else {
                put(keyImageRegistry, key, getMissingImage());
                logImageResourceLoadFailed(parentUrl, path);
            }
        }
    }

    /**
     * イメージを取得します。<br />
     * 本メソッドの振る舞いは {@link #getImage(String, URL) getImage(key, null)} と同等です。<br />
     * 
     * @param key
     *        キー
     * @return {@link Image} オブジェクト
     */
    public Image getImage(final String key) {
        return getImage(key, null);
    }

    /**
     * イメージを取得します。<br />
     * 本メソッドでは以下の順番でイメージを検索します。<br />
     * <ol>
     * <li>{@code key} によって登録されたイメージを検索し、見つかればそのイメージを返します。
     * <li>{@code key} が「/」で始まる場合、キーをクラスパス上のパスと見なしてイメージを読み込みます。
     * <li>{@code parentUrl} が与えられている場合、キーをそのURLからの相対パスと見なしてイメージを読み込みます。
     * <li>{@code key} 自体を URL と見なしてイメージを読み込みます。
     * </ol>
     * すべての方法でイメージを取得できなかった場合、欠落したイメージを表す赤い矩形のイメージを返します。<br />
     * 
     * @param key
     *        キー
     * @param parentUrl
     *        親 URL。{@code null} でも構いません。
     * @return {@link Image} オブジェクト
     */
    public Image getImage(final String key, final URL parentUrl) {
        AssertionUtil.assertNotNull("key", key);

        Image image = keyImageRegistry.get(key);
        if (image != null) {
            return image;
        }

        List<URL> urls = createURLs(key, parentUrl);
        for (URL url : urls) {
            image = pathImageRegistry.get(url.toExternalForm());
            if (image != null) {
                return image;
            }
        }

        image = internalLoadImage(key, parentUrl);
        if (image != null) {
            return image;
        } else {
            logImageResourceLoadFailed(parentUrl, key);
            return getMissingImage();
        }
    }

    protected Image internalLoadImage(final String path, final URL parentUrl) {
        List<URL> urls = createURLs(path, parentUrl);
        for (URL url : urls) {
            Image image = loadImageFromURL(url);
            if (image != null) {
                put(pathImageRegistry, url.toExternalForm(), image);
                return image;
            }
        }
        return null;
    }

    protected List<URL> createURLs(final String path, final URL parentUrl) {
        List<URL> urls = new ArrayList<URL>(3);

        // パスが / で始まっている場合はクラスパスルートからのパスと見なす
        if (path.startsWith("/")) {
            String realPath = path.substring(1, path.length());
            URL url = ResourceUtil.getResourceNoException(realPath);
            if (url != null) {
                urls.add(url);
            }
        }

        // パスを親URLからの相対パスと見なす
        if (parentUrl != null) {
            try {
                URL url = PathUtil.createURL(parentUrl, path, true);
                if (url != null) {
                    urls.add(url);
                }
            } catch (Exception ignore) {
            }
        }

        // パス自体をURLと見なす
        try {
            URL url = new URL(path);
            urls.add(url);
        } catch (MalformedURLException ignore) {
        }

        return urls;
    }

    protected Image loadImageFromURL(final URL url) {
        InputStream is = null;
        try {
            is = URLUtil.openStream(url);
            return loadImageFromInputStream(is);
        } catch (IORuntimeException ignore) {
            return null;
        } finally {
            InputStreamUtil.closeSilently(is);
        }
    }

    protected Image loadImageFromInputStream(final InputStream is) {
        return new Image(Display.getCurrent(), is);
    }

    protected void put(final ImageRegistry registry, final String key, final Image image) {
        if (registry.get(key) != null) {
            registry.remove(key);
        }
        registry.put(key, image);
    }

    protected void put(final ImageRegistry registry, final String key, final ImageDescriptor desc) {
        if (registry.get(key) != null) {
            registry.remove(key);
        }
        registry.put(key, desc);
    }

    protected Image getMissingImage() {
        return ImageDescriptor.getMissingImageDescriptor().createImage();
    }

    protected void logImageResourceLoadFailed(final URL parentUrl, final String path) {
        String parentPath = parentUrl != null ? parentUrl.toExternalForm() : "";
        LOGGER.log(IMAGE_RESOURCE_LOAD_FAILED, path, parentPath);
    }
}
