/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.uruma.desc.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.ImportValue;
import org.seasar.uruma.desc.FormDesc;

/**
 * {@link FormDesc} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class FormDescImpl implements FormDesc {
    private Class<?> formClass;

    private BeanDesc beanDesc;

    private List<PropertyDesc> importValueProps;

    private List<PropertyDesc> exportValueProps;

    private List<PropertyDesc> importSelectionProps;

    private List<PropertyDesc> exportSelectionProps;

    /**
     * {@link FormDescImpl} を構築します。<br />
     * 
     * @param formClass
     *            フォームクラスの {@link Class} オブジェクト
     * @throws EmptyRuntimeException
     *             <code>formClass</code> が <code>null</code> であった場合
     */
    public FormDescImpl(final Class<?> formClass) throws EmptyRuntimeException {
        if (formClass == null) {
            throw new EmptyRuntimeException("formClass");
        }

        this.formClass = formClass;
        this.beanDesc = BeanDescFactory.getBeanDesc(formClass);

        setupFields();

        importValueProps = getUnmodifiableList(importValueProps);
        exportValueProps = getUnmodifiableList(exportValueProps);
        importSelectionProps = getUnmodifiableList(importSelectionProps);
        exportSelectionProps = getUnmodifiableList(exportSelectionProps);
    }

    protected void setupFields() {
        setupFieldsByClass(formClass);
        Class<?> superClass = formClass.getSuperclass();
        while (superClass != Object.class && superClass != null) {
            setupFieldsByClass(superClass);
            superClass = superClass.getSuperclass();
        }
    }

    protected void setupFieldsByClass(final Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            setupExportvalueField(field);
            setupImportValueField(field);
            setupExportSelectionField(field);
            setupImportSelectionField(field);
        }
    }

    protected void setupExportvalueField(final Field field) {
        if (field.isAnnotationPresent(ExportValue.class)
                || field.isAnnotationPresent(ImportExportValue.class)) {
            addPropertyDesc(exportValueProps, field);
        }
    }

    protected void setupImportValueField(final Field field) {
        if (field.isAnnotationPresent(ImportValue.class)
                || field.isAnnotationPresent(ImportExportValue.class)) {
            addPropertyDesc(importValueProps, field);
        }
    }

    protected void setupExportSelectionField(final Field field) {
        if (field.isAnnotationPresent(ExportSelection.class)) {
            addPropertyDesc(exportSelectionProps, field);
        }
    }

    protected void setupImportSelectionField(final Field field) {
        if (field.isAnnotationPresent(ImportSelection.class)) {
            addPropertyDesc(importSelectionProps, field);
        }
    }

    protected void addPropertyDesc(List<PropertyDesc> target, final Field field) {
        PropertyDesc pd = beanDesc.getPropertyDesc(field.getName());
        if (target == null) {
            target = new ArrayList<PropertyDesc>();
        }

        target.add(pd);
    }

    protected List<PropertyDesc> getUnmodifiableList(
            final List<PropertyDesc> target) {
        if (target != null) {
            return Collections.unmodifiableList(target);
        } else {
            return null;
        }
    }

    /*
     * @see org.seasar.uruma.desc.FormDesc#getExportSelectionProperties()
     */
    public List<PropertyDesc> getExportSelectionProperties() {
        return exportSelectionProps;
    }

    /*
     * @see org.seasar.uruma.desc.FormDesc#getExportValueProperties()
     */
    public List<PropertyDesc> getExportValueProperties() {
        return exportValueProps;
    }

    /*
     * @see org.seasar.uruma.desc.FormDesc#getImportSelectionProperties()
     */
    public List<PropertyDesc> getImportSelectionProperties() {
        return importSelectionProps;
    }

    /*
     * @see org.seasar.uruma.desc.FormDesc#getImportValueProperties()
     */
    public List<PropertyDesc> getImportValueProperties() {
        return importValueProps;
    }

}
