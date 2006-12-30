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
package org.seasar.jface.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.EnabledDepend;
import org.seasar.jface.binding.WidgetEnabledDependBinder;
import org.seasar.jface.binding.impl.ExportValueBinder;
import org.seasar.jface.binding.impl.ImportValueBinder;
import org.seasar.jface.exception.DuplicateComponentIdException;
import org.seasar.jface.util.AssertionUtil;
import org.seasar.jface.viewer.ViewerAdapter;

/**
 * @author y-komori
 */
public class WindowContextImpl implements WindowContext {

    protected Map<String, Widget> componentMap = new HashMap<String, Widget>();

    private Object actionComponent;

    private Object formComponent;

    private Method initializeMethod;

    protected MenuManager menuBar;

    private List<EnabledDepend> enabledDepends = new ArrayList<EnabledDepend>();

    private List<ImportValueBinder> importValueBinders = new ArrayList<ImportValueBinder>();

    private List<ExportValueBinder> exportValueBinders = new ArrayList<ExportValueBinder>();

    private List<WidgetEnabledDependBinder> widgetEnabledDependBinders = new ArrayList<WidgetEnabledDependBinder>();

    private Map<Widget, ViewerAdapter> viewerAdapterMap = new HashMap<Widget, ViewerAdapter>();

    /*
     * @see org.seasar.jface.WindowContext#getActionObject()
     */
    public Object getActionComponent() {
        return actionComponent;
    }

    /*
     * @see org.seasar.jface.WindowContext#setActionObject(java.lang.Object)
     */
    public void setActionComponent(Object actionObject) {
        this.actionComponent = actionObject;
    }

    /*
     * @see org.seasar.jface.WindowContext#getFormComponent()
     */
    public Object getFormComponent() {
        return formComponent;
    }

    public void setFormComponent(Object formComponent) {
        this.formComponent = formComponent;
    }

    /*
     * @see org.seasar.jface.WindowContext#getInitializeMethod()
     */
    public Method getInitializeMethod() {
        return initializeMethod;
    }

    /*
     * @see org.seasar.jface.WindowContext#setInitializeMethod(java.lang.reflect.Method)
     */
    public void setInitializeMethod(Method method) {
        this.initializeMethod = method;
    }

    /*
     * @see org.seasar.jface.WindowContext#getComponent(java.lang.String)
     */
    public Widget getComponent(String id) {
        return componentMap.get(id);
    }

    /*
     * @see org.seasar.jface.WindowContext#putComponent(java.lang.String,
     *      org.eclipse.swt.widgets.Widget)
     */
    public void putComponent(String id, Widget component) {
        AssertionUtil.assertNotNull("id", id);
        AssertionUtil.assertNotNull("component", component);

        if (!componentMap.containsKey(id)) {
            componentMap.put(id, component);
        } else {
            throw new DuplicateComponentIdException(id);
        }
    }

    /*
     * @see org.seasar.jface.WindowContext#getMenuBar()
     */
    public MenuManager getMenuBar() {
        return this.menuBar;
    }

    /*
     * @see org.seasar.jface.WindowContext#setMenuBar(org.eclipse.jface.action.IMenuManager)
     */
    public void setMenuBar(MenuManager menuManager) {
        this.menuBar = menuManager;
    }

    public void addEnabledDepend(EnabledDepend enabledDepend) {
        enabledDepends.add(enabledDepend);
    }

    public List<EnabledDepend> getEnabledDepends() {
        return this.enabledDepends;
    }

    public void addWidgetEnabledDependBinder(WidgetEnabledDependBinder binder) {
        widgetEnabledDependBinders.add(binder);
    }

    public List<WidgetEnabledDependBinder> getWidgetEnabledDependBinders() {
        return widgetEnabledDependBinders;
    }

    /*
     * @see org.seasar.jface.WindowContext#getViewerAdapter(org.eclipse.swt.widgets.Widget)
     */
    public ViewerAdapter getViewerAdapter(Widget widget) {
        return viewerAdapterMap.get(widget);
    }

    /*
     * @see org.seasar.jface.WindowContext#putViewerAdapter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.jface.viewer.ViewerAdapter)
     */
    public void putViewerAdapter(Widget widget, ViewerAdapter adapter) {
        viewerAdapterMap.put(widget, adapter);
    }
}
