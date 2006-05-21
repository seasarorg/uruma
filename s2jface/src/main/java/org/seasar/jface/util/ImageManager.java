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

import java.io.InputStream;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.seasar.jface.exception.ResourceNotFoundException;

/**
 * @author y-komori
 * 
 */
public class ImageManager {
    protected static final ImageManager instance = new ImageManager();

    protected static final ImageRegistry imageRegistry = new ImageRegistry();

    public static ImageManager getInstance() {
        return instance;
    }

    /**
     * 指定されたキーで登録された Image オブジェクトを返します。<br>
     * 
     * @param key
     *            キー
     * @return Imageオブジェクト。見つからない場合は<code>null</code>。
     */
    public Image getImage(final String key) {
        return imageRegistry.get(key);
    }

    /**
     * <code>url</code>をキーとしてImageオブジェクトを検索します。<br>
     * Imageオブジェクトが登録されていない場合、生成して<code>url</code>をキーとして登録してから返します。
     * 
     * @param url
     *            イメージのURL/キー
     * @param display
     *            イメージ生成時に使用する <code>Display</code> オブジェクト
     * @return Imageオブジェクト
     */
    public Image getImage(final String url, final Display display) {
        Image image = getImage(url);
        if (image == null) {
            InputStream is = getClass().getResourceAsStream(url);
            if (is != null) {
                image = new Image(display, is);
                imageRegistry.put(url, image);
            } else {
                throw new ResourceNotFoundException(url);
            }
        }
        return image;
    }

    /**
     * ImageRegistry へ Image を登録します。<br>
     * 
     * @param display
     *            Imageの生成に使用するDisplayの参照
     * @param key
     *            キー
     * @param url
     *            イメージのURL
     */
    public void putImage(Display display, String key, String url) {
        Image image = new Image(display, getClass().getResourceAsStream(url));
        imageRegistry.put(key, image);
    }

    /**
     * ResourceBundle から Image を読み込み、一括登録します。<br>
     * 「key=url」の形式で記述されたプロパティファイルを元にしたResourceBundle からImageを一括して読み込みます。
     * 
     * @param display
     *            Imageの生成に使用するDisplayの参照
     * @param bundle
     *            リソースバンドルの参照
     * @throws MissingResourceException
     *             リソースが見つからない場合にスローします。
     */
    public void putImages(Display display, ResourceBundle bundle) {

        Enumeration keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String url = bundle.getString(key);
            putImage(display, key, url);
        }
    }
}
