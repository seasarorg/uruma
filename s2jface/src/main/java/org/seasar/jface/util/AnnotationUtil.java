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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * アノテーションを持つクラスに対するユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class AnnotationUtil {
    private static Map<ClassEntry, List<Field>> fieldCache = new HashMap<ClassEntry, List<Field>>();

    private static Map<ClassEntry, List<PropertyDesc>> pdCache = new HashMap<ClassEntry, List<PropertyDesc>>();

    /**
     * 特定のアノテーションが付加されたフィールドを取得します。<br />
     * <p>
     * <code>class</code> で指定されたクラスから <code>annotationClass</code>
     * で指定されたアノテーションが付加されたフィールドオブジェクトのリストを返します。<br />
     * フィールドは、親クラスまでさかのぼってすべて検索されます。
     * </p>
     * 
     * @param clazz
     *            対象クラス
     * @param annotationClass
     *            対象アノテーション
     * @return 見つかったフィールドのリスト
     */
    public static List<Field> getAnnotatedFields(Class<?> clazz,
            Class<? extends Annotation> annotationClass) {
        ClassEntry entry = new ClassEntry(clazz, annotationClass);
        List<Field> result = fieldCache.get(entry);
        if (result != null) {
            return result;
        } else {
            result = new ArrayList<Field>();
            BeanDesc desc = BeanDescFactory.getBeanDesc(clazz);
            int fieldSize = desc.getFieldSize();
            for (int i = 0; i < fieldSize; i++) {
                Field field = desc.getField(i);
                if (field.getAnnotation(annotationClass) != null) {
                    result.add(field);
                    fieldCache.put(entry, result);
                }
            }
            return result;
        }
    }

    /**
     * 特定のアノテーションが付加されたフィールドに対応する {@link PropertyDesc} を取得します。<br />
     * <p>
     * <code>class</code> で指定されたクラスから <code>annotationClass</code>
     * で指定されたアノテーションが付加されたフィールドオブジェクトを検索し、それに対応する {@link PropertyDesc}
     * オブジェクトのリストを返します。 フィールドは、親クラスまでさかのぼってすべて検索されます。
     * </p>
     * 
     * @param clazz
     *            対象クラス
     * @param annotationClass
     *            対象アノテーション
     * @return 見つかった {@link PropertyDesc} のリスト
     */
    public static List<PropertyDesc> getAnnotatedPropertyDescs(Class<?> clazz,
            Class<? extends Annotation> annotationClass) {
        ClassEntry entry = new ClassEntry(clazz, annotationClass);
        List<PropertyDesc> result = pdCache.get(entry);
        if (result != null) {
            return result;
        } else {
            result = new ArrayList<PropertyDesc>();
            BeanDesc desc = BeanDescFactory.getBeanDesc(clazz);
            int fieldSize = desc.getFieldSize();
            for (int i = 0; i < fieldSize; i++) {
                Field field = desc.getField(i);
                if (field.getAnnotation(annotationClass) != null) {
                    String propertyName = field.getName();
                    if (desc.hasPropertyDesc(propertyName)) {
                        result.add(desc.getPropertyDesc(propertyName));
                        pdCache.put(entry, result);
                    }
                }
            }
            return result;
        }
    }

    private static class ClassEntry {
        private Class<?> clazz;

        private Class<? extends Annotation> annotationClass;

        ClassEntry(Class<?> clazz, Class<? extends Annotation> annotationClass) {
            this.clazz = clazz;
            this.annotationClass = annotationClass;
        }

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME
                    * result
                    + ((this.annotationClass == null) ? 0
                            : this.annotationClass.hashCode());
            result = PRIME * result
                    + ((this.clazz == null) ? 0 : this.clazz.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final ClassEntry other = (ClassEntry) obj;
            if (this.annotationClass == null) {
                if (other.annotationClass != null)
                    return false;
            } else if (!this.annotationClass.equals(other.annotationClass))
                return false;
            if (this.clazz == null) {
                if (other.clazz != null)
                    return false;
            } else if (!this.clazz.equals(other.clazz))
                return false;
            return true;
        }
    }
}
