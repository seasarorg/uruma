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
package org.seasar.uruma.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.exception.IllegalAccessRuntimeException;
import org.seasar.framework.exception.InstantiationRuntimeException;
import org.seasar.framework.exception.InvocationTargetRuntimeException;
import org.seasar.uruma.exception.UnsupportedClassException;

/**
 * Tiger に対応したクラス用ユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class ClassUtil {
    private ClassUtil() {

    }

    /**
     * 新しいインスタンスを生成します。<br />
     * 
     * @param <T>
     *            生成するインスタンスの型
     * @param clazz
     *            生成するインスタンスの {@link Class} オブジェクト
     * @param args
     *            コンストラクタ引数
     * @return 生成したインスタンス
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(final Class<? extends T> clazz,
            final Object... args) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        Constructor constructor = beanDesc.getSuitableConstructor(args);
        Object object = null;
        try {
            object = constructor.newInstance(args);
        } catch (InstantiationException ex) {
            throw new InstantiationRuntimeException(clazz, ex);
        } catch (IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(clazz, ex);
        } catch (InvocationTargetException ex) {
            throw new InvocationTargetRuntimeException(clazz, ex);
        }
        return (T) object;
    }

    /**
     * 新しいインスタンスを生成します。<br />
     * 
     * @param <T>
     *            生成するインスタンスの型
     * @param clazz
     *            生成するインスタンスの {@link Class} オブジェクト
     * @return 生成したインスタンス
     */
    public static <T> T newInstance(final Class<? extends T> clazz) {
        return ClassUtil.<T> newInstance(clazz, (Object[]) null);
    }

    /**
     * <code>clazz</code> が <code>superClass</code> のサブクラスであるかどうかをチェックします。<br />
     * サブクラスでない場合、{@link UnsupportedClassException} をスローします。
     * 
     * @param clazz
     *            チェック対象クラス
     * @param superClass
     *            スーパークラス
     * @throws UnsupportedClassException
     */
    public static void checkSubclass(final Class<?> clazz,
            final Class<?> superClass) {
        if (!superClass.isAssignableFrom(clazz)) {
            throw new UnsupportedClassException(clazz, superClass);
        }
    }

    /**
     * 指定された型へのキャストを行います。<br />
     * 
     * @param <T>
     *            キャスト先の型
     * @param obj
     *            キャスト対象オブジェクト
     * @return キャスト結果
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(final Object obj) {
        return (T) obj;
    }
}
