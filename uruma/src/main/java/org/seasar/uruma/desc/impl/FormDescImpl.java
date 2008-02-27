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

    private List<PropertyDesc> importValueProps = new ArrayList<PropertyDesc>(1);

    private List<PropertyDesc> exportValueProps = new ArrayList<PropertyDesc>(1);

    private List<PropertyDesc> importSelectionProps = new ArrayList<PropertyDesc>(
            1);;

    private List<PropertyDesc> exportSelectionProps = new ArrayList<PropertyDesc>(
            1);;

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

        importValueProps = Collections.unmodifiableList(importValueProps);
        exportValueProps = Collections.unmodifiableList(exportValueProps);
        importSelectionProps = Collections
                .unmodifiableList(importSelectionProps);
        exportSelectionProps = Collections
                .unmodifiableList(exportSelectionProps);
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

            setupImportValueField(field);
            setupExportvalueField(field);
            setupImportSelectionField(field);
            setupExportSelectionField(field);
        }
    }

    protected void setupImportValueField(final Field field) {
        if (field.isAnnotationPresent(ImportValue.class)
                || field.isAnnotationPresent(ImportExportValue.class)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(field.getName());
            importValueProps.add(pd);
        }
    }

    protected void setupExportvalueField(final Field field) {
        if (field.isAnnotationPresent(ExportValue.class)
                || field.isAnnotationPresent(ImportExportValue.class)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(field.getName());
            exportValueProps.add(pd);
        }
    }

    protected void setupImportSelectionField(final Field field) {
        if (field.isAnnotationPresent(ImportSelection.class)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(field.getName());
            importSelectionProps.add(pd);
        }
    }

    protected void setupExportSelectionField(final Field field) {
        if (field.isAnnotationPresent(ExportSelection.class)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(field.getName());
            exportSelectionProps.add(pd);
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
