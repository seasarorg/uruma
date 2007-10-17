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
package org.seasar.jface.binding.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.jface.annotation.ExportSelection;
import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.annotation.ImportExportValue;
import org.seasar.jface.annotation.ImportSelection;
import org.seasar.jface.annotation.ImportValue;
import org.seasar.jface.binding.FormDesc;

/**
 * @author y-komori
 */
public class FormDescImpl implements FormDesc {
    private Class formClass;

    private Map<String, Field> fieldsCache = new HashMap<String, Field>();

    private List<Field> importFields = new ArrayList<Field>();

    private List<Field> exportFields = new ArrayList<Field>();

    private List<Field> importSelectionFields = new ArrayList<Field>();

    private List<Field> exportSelectionFields = new ArrayList<Field>();

    public FormDescImpl(Class formClass) throws EmptyRuntimeException {
        if (formClass == null) {
            throw new EmptyRuntimeException("formClass");
        }

        this.formClass = formClass;

        setupFields();
    }

    protected void setupFields() {
        setupFieldsByClass(formClass);
        Class superClass = formClass.getSuperclass();
        while (superClass != Object.class && superClass != null) {
            setupFieldsByClass(superClass);
            superClass = superClass.getSuperclass();
        }
    }

    protected void setupFieldsByClass(final Class targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (!fieldsCache.containsKey(fieldName)) {
                field.setAccessible(true);
                fieldsCache.put(fieldName, field);

                setupExportField(field);
                setupImportField(field);
                setupExportSelectionField(field);
                setupImportSelectionField(field);
            }
        }
    }

    protected void setupExportField(final Field field) {
        if (field.isAnnotationPresent(ExportValue.class)
                || field.isAnnotationPresent(ImportExportValue.class)) {
            exportFields.add(field);
        }
    }

    protected void setupImportField(final Field field) {
        if (field.isAnnotationPresent(ImportValue.class)
                || field.isAnnotationPresent(ImportExportValue.class)) {
            importFields.add(field);
        }
    }

    protected void setupExportSelectionField(final Field field) {
        if (field.isAnnotationPresent(ExportSelection.class)) {
            exportSelectionFields.add(field);
        }
    }

    protected void setupImportSelectionField(final Field field) {
        if (field.isAnnotationPresent(ImportSelection.class)) {
            importSelectionFields.add(field);
        }
    }

    /*
     * @see org.seasar.jface.binding.FormDesc#getExportSelectionFields()
     */
    public List<Field> getExportSelectionFields() {
        return exportSelectionFields;
    }

    /*
     * @see org.seasar.jface.binding.FormDesc#getExportValueFields()
     */
    public List<Field> getExportValueFields() {
        return exportFields;
    }

    /*
     * @see org.seasar.jface.binding.FormDesc#getImportSelectionFields()
     */
    public List<Field> getImportSelectionFields() {
        return importSelectionFields;
    }

    /*
     * @see org.seasar.jface.binding.FormDesc#getImportValueFields()
     */
    public List<Field> getImportValueFields() {
        return importFields;
    }

}
