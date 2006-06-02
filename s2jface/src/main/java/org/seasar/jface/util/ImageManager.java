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
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.seasar.jface.exception.ResourceNotFoundException;

/**
 * <code>Image</code> オブジェクトを管理するためのユーティリティクラスです。
 * 
 * @author y-komori
 * 
 */
public class ImageManager {
    protected static final ImageRegistry imageRegistry = new ImageRegistry();

    private ImageManager() {
    }

    /**
     * 指定されたキーで登録された Image オブジェクトを返します。<br>
     * 
     * @param key
     *            キー
     * @return Imageオブジェクト。見つからない場合は<code>null</code>。
     */
    public static Image getImage(final String key) {
        return imageRegistry.get(key);
    }

    /**
     * <code>url</code>をキーとしてImageオブジェクトを検索します。<br>
     * Imageオブジェクトが登録されていない場合、生成して<code>url</code>をキーとして登録してから返します。
     * 
     * @param url
     *            イメージのURL/キー
     * @param class
     *            ファイルの位置を示す <code>Class</code> オブジェクト
     * 
     * @return Imageオブジェクト
     */
    public static Image getImage(final String url, final Class clazz) {
        Image image = getImage(url);
        if (image == null) {
            InputStream is = clazz.getResourceAsStream(url);
            if (is != null) {
                image = new Image(Display.getCurrent(), is);
                imageRegistry.put(url, image);
            } else {
                throw new ResourceNotFoundException(url);
            }
        }
        return image;
    }

    /**
     * <code>Image</code> オブジェクトを登録します。<br>
     * 既に同じキーで <code>Image</code> が登録されている場合、上書きします。
     * 
     * @param key
     *            キー
     * @param fileName
     *            イメージのファイル名
     * @param clazz
     *            ファイルの位置を示す <code>Class</code> オブジェクト
     */
    public static void putImage(String key, String fileName, Class clazz) {
        if (imageRegistry.get(key) != null) {
            imageRegistry.remove(key);
        }
        Image image = new Image(Display.getCurrent(), clazz
                .getResourceAsStream(fileName));
        imageRegistry.put(key, image);
    }

    /**
     * <code>ImageDescriptor</code> オブジェクトを登録します。<br>
     * 既に同じキーで <code>ImageDescriptor</code> が登録されている場合、上書きします。
     * 
     * @param key
     *            キー
     * @param fileName
     *            イメージのファイル名
     * @param clazz
     *            ファイルの位置を示す <code>Class</code> オブジェクト
     */
    public static void putImageDescriptor(final String key,
            final String fileName, final Class clazz) {
        ImageDescriptor descriptor = ImageDescriptor.createFromFile(clazz,
                fileName);
        imageRegistry.put(key, descriptor);
    }

    /**
     * ResourceBundle から Image を読み込み、一括登録します。<br>
     * 「key=url」の形式で記述されたプロパティファイルを元にしたResourceBundle からImageを一括して読み込みます。
     * 
     * @param bundle
     *            リソースバンドルの参照
     * @param clazz
     *            ファイルの位置を示す <code>Class</code> オブジェクト
     * @throws MissingResourceException
     *             リソースが見つからない場合にスローします。
     */
    public static void loadImages(final ResourceBundle bundle, final Class clazz) {

        Enumeration keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String url = bundle.getString(key);
            putImage(key, url, clazz);
        }
    }

    /**
     * <code>ImageManager</code> が管理する <code>ImageRegistry</code> を破棄します。
     * 
     */
    public static void dispose() {
        imageRegistry.dispose();
    }
}
