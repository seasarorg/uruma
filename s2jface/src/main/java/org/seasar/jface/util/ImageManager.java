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
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.exception.ResourceNotFoundException;

/**
 * <code>Image</code> オブジェクトを管理するためのユーティリティクラスです。
 * 
 * @author y-komori
 * 
 */
public class ImageManager {
    protected static final ImageRegistry imageRegistry = new ImageRegistry();

    protected static final Logger logger = Logger.getLogger(ImageManager.class);

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
     * @param clazz
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
     * @throws ResourceNotFoundException
     *             fileName の示すリソースが取得できなかった場合
     */
    public static void putImage(String key, String fileName, Class clazz) {
        if (imageRegistry.get(key) != null) {
            imageRegistry.remove(key);
        }
        InputStream is = clazz.getResourceAsStream(fileName);
        if (is != null) {
            Image image = new Image(Display.getCurrent(), is);
            imageRegistry.put(key, image);
        } else {
            throw new ResourceNotFoundException(fileName);
        }
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
     * <code>ResourceBundle</code> から <code>Image</code>
     * オブジェクトを読み込み、一括登録します。</br> 「key=url」の形式で記述されたプロパティファイルを元にした
     * <code>ResourceBundle</code> から <code>Image</code> オブジェクトを一括して読み込みます。
     * 
     * @param bundle
     *            リソースバンドルの参照
     * @param clazz
     *            ファイルの位置を示す <code>Class</code> オブジェクト
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
     * 指定されたクラスの定数フィールドに対して、<code>ImageManager</code>が
     * 管理するオブジェクトをインジェクションします。</br> インジェクション対象となるのは、以下の条件を満たすフィールドです。</br>
     * <p>
     * <ol>
     * <li><code>public static final</code> な定数フィールドであること
     * <li><code>Image</code>または <code>ImageDescriptor</code> 型のフィールドであること
     * </ol>
     * </p>
     * 以上の条件を満たすフィールドに対して、フィールド名をキーとして <code>ImageManager</code> が登録する
     * <code>Image</code> または <code>ImageDescriptor</code>
     * を検索し、見つかればインジェクションを行います。</br> 見つからなかった場合は、Warning ログを出力します。
     * <p>
     * <b>【例】</b> </br> 以下の例では、<code>ImageHolder</code> クラスの フィールド、<code>IMAGE_A</code>
     * と <code>IMAGE_B</code> に対して、<code>ImageManager</code>
     * が管理するオブジェクトの中から、<code>IMAGE_A</code>、<code>IMAGE_B</code>
     * という名前のキーで登録されたオブジェクトをインジェクションします。
     * 
     * <pre>
     *             public class ImageHolder() {
     *                  public static final Image IMAGE_A;
     *                  public static final ImageDescriptor IMAGE_B;
     *             }
     * </pre>
     * <pre>
     * ImageManager.injectImages(ImageHolder.class);
     * </pre>
     * 
     * </p>
     * 
     * @param clazz
     *            対象クラス
     */
    public static void injectImages(final Class clazz) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        for (int i = 0; i < beanDesc.getFieldSize(); i++) {
            Field field = beanDesc.getField(i);
            String key = field.getName();
            if (!validateMask(field)) {
                continue;
            }

            if (isAssignableFrom(Image.class, field)) {
                Image image = imageRegistry.get(key);
                if (image != null) {
                    FieldUtil.set(field, null, image);
                } else {
                    logBindingFailed(clazz, field);
                }
            } else if (isAssignableFrom(ImageDescriptor.class, field)) {
                ImageDescriptor descriptor = imageRegistry.getDescriptor(key);
                if (descriptor != null) {
                    FieldUtil.set(field, null, descriptor);
                } else {
                    logBindingFailed(clazz, field);
                }
            }
        }
    }

    /**
     * <code>ImageManager</code> が管理する <code>ImageRegistry</code> を破棄します。
     * 
     */
    public static void dispose() {
        imageRegistry.dispose();
    }

    protected static boolean validateMask(Field field) {
        final int MOD_EXPECTED = Modifier.PUBLIC | Modifier.STATIC;
        final int MOD_MASK = MOD_EXPECTED | Modifier.FINAL;
        return (field.getModifiers() & MOD_MASK) == MOD_EXPECTED;
    }

    protected static boolean isAssignableFrom(final Class<?> clazz,
            final Field target) {
        return clazz.isAssignableFrom(target.getType());
    }

    protected static void logBindingFailed(final Class clazz, final Field field) {
        logger.log("WJFC0200",
                new Object[] { clazz.getName(), field.getName() });
    }

}
