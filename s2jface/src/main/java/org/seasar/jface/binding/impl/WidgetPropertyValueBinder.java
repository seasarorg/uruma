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
package org.seasar.jface.binding.impl;

import java.lang.reflect.Field;

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.binding.WidgetValueBinder;

public class WidgetPropertyValueBinder implements WidgetValueBinder {

    private Class<? extends Widget> widgetType;

    private PropertyDesc propertyDesc;
    
    public WidgetPropertyValueBinder(Class<? extends Widget> widgetType,
            String propertyName) {
        this.widgetType = widgetType;
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(widgetType);
        this.propertyDesc = beanDesc.getPropertyDesc(propertyName);
    }

    public void importValue(Widget src, Object destObject, Field destField) {
        Object srcValue = propertyDesc.getValue(src);
        // TODO バリデーションを含む型変換処理
        
        // 空文字列の入力は null として扱う
        if (srcValue instanceof String && ((String) srcValue).length() == 0) {
            srcValue = null;
        }
        FieldUtil.set(destField, destObject, srcValue);
    }

    public void exportValue(Object srcObject, Field srcField, Widget dest) {
        Object srcValue = FieldUtil.get(srcField, srcObject);
        srcValue = getAnnotatedExportValue(srcValue, srcField);
        propertyDesc.setValue(dest, srcValue);
    }
    
    protected Object getAnnotatedExportValue(Object object, Field field) {
        ExportValue annotation = field.getAnnotation(ExportValue.class);
        String label = annotation.label();
        if (! StringUtil.isEmpty(label)) {
            BeanDesc bd = BeanDescFactory.getBeanDesc(object.getClass());
            PropertyDesc pd =  bd.getPropertyDesc(label);
            object = pd.getValue(object);
        }
        return object;
    }

    public Class<? extends Widget> getWidgetType() {
        return widgetType;
    }

}
