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

import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.MethodUtil;
import org.seasar.jface.binding.EnabledDependType;
import org.seasar.jface.binding.WidgetEnabledDependBinder;
import org.seasar.jface.exception.EnabledDependException;

/**
 * {@link Table} ウィジットのための {@link WidgetEnabledDependBinder} です。<br />
 * 
 * @author bskuroneko
 */
public class TableEnabledDependBinder implements WidgetEnabledDependBinder {
    
    private Widget widget;
    
    private Table target;

    private EnabledDependType type;
    
    private Method setEnabledMethod;
    
    public TableEnabledDependBinder(Widget widget, Widget target, EnabledDependType type) {
        this.widget = widget;
        this.target = (Table) target;
        this.type = type;
        
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(widget.getClass());
        setEnabledMethod = beanDesc.getMethod("setEnabled",
                new Class[] { boolean.class });
    }

    public void bindEventListeners() {
        Listener listener = new Listener() {
            public void handleEvent(Event event) {
                updateEnabled();
            }
        };
        target.addListener(SWT.Selection, listener);
    }

    public void updateEnabled() {
        if (widget.isDisposed() || target.isDisposed()) {
            return;
        }
        
        boolean enabled = dependEnabled();
        MethodUtil.invoke(setEnabledMethod, widget, new Object[] { enabled });
    }
    
    protected boolean dependEnabled() {
        boolean result = false;
        if (type == EnabledDependType.SELECTION) {
            result = target.getSelectionCount() > 0;
        } else if (type == EnabledDependType.SINGLE) {
            result = target.getSelectionCount() == 1;
        } else if (type == EnabledDependType.PAIR) {
            result = target.getSelectionCount() == 2;
        } else if (type == EnabledDependType.NONE) {
            result = target.getSelectionCount() == 0;
        } else if (type == EnabledDependType.MULTI) {
            result = target.getSelectionCount() >= 2;
        } else {
            throw new EnabledDependException(
                    EnabledDependException.DEPEND_TYPE_NOT_SUPPORTED,
                    target.getClass(), type);
        }
        return result;
    }

}
