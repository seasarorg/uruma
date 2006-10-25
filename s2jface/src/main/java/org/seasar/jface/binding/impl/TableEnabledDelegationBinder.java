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
import org.seasar.jface.binding.EnabledDelegationBinder;
import org.seasar.jface.binding.EnabledDelegationType;
import org.seasar.jface.exception.EnabledDelegationException;

/**
 * @author bskuroneko
 * 
 */
public class TableEnabledDelegationBinder implements EnabledDelegationBinder {

    public void bind(final Widget widget, final Widget delegatedWidget,
            final EnabledDelegationType type) {
        final Table table = (Table) delegatedWidget;
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(widget.getClass());
        final Method setEnabledMethod = beanDesc.getMethod("setEnabled",
                new Class[] { boolean.class });

        setWidgetEnabled(setEnabledMethod, widget, delegationEnabled(table,
                type));

        delegatedWidget.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                setWidgetEnabled(setEnabledMethod, widget, delegationEnabled(
                        table, type));
            }
        });
    }

    protected void setWidgetEnabled(Method setEnabledMethod, Widget widget,
            boolean enabled) {
        MethodUtil.invoke(setEnabledMethod, widget, new Object[] { enabled });
    }

    protected boolean delegationEnabled(Table table, EnabledDelegationType type) {
        boolean result = false;
        if (type == EnabledDelegationType.SELECTION) {
            result = table.getSelectionCount() > 0;
        } else if (type == EnabledDelegationType.SINGLE) {
            result = table.getSelectionCount() == 1;
        } else if (type == EnabledDelegationType.PAIR) {
            result = table.getSelectionCount() == 2;
        } else if (type == EnabledDelegationType.NONE) {
            result = table.getSelectionCount() == 0;
        } else if (type == EnabledDelegationType.MULTI) {
            result = table.getSelectionCount() >= 2;
        } else {
            throw new EnabledDelegationException(
                    EnabledDelegationException.DELEGATION_TYPE_NOT_SUPPORTED,
                    getWidgetType(), type);
        }
        return result;
    }

    public Class<? extends Widget> getWidgetType() {
        return Table.class;
    }

}
