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
package org.seasar.jface.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.EnabledDepend;
import org.seasar.jface.binding.WidgetEnabledDependBinder;
import org.seasar.jface.exception.DuplicateComponentIdException;
import org.seasar.jface.util.AssertionUtil;
import org.seasar.jface.viewer.ViewerAdapter;

/**
 * {@link WindowContext} インターフェースに対する実装クラスです。<br />
 * 
 * @author y-komori
 */
public class WindowContextImpl implements WindowContext {

    protected Map<String, Object> componentMap = new HashMap<String, Object>();

    private Object actionComponent;

    private Object formComponent;

    private Method initializeMethod;

    protected MenuManager menuBar;

    private List<EnabledDepend> enabledDepends = new ArrayList<EnabledDepend>();

    private List<WidgetEnabledDependBinder> widgetEnabledDependBinders = new ArrayList<WidgetEnabledDependBinder>();

    private Map<Widget, ViewerAdapter> viewerAdapterMap = new HashMap<Widget, ViewerAdapter>();

    private IStatusLineManager statusLineManager;

    /*
     * @see org.seasar.jface.WindowContext#addEnabledDepend(org.seasar.jface.binding.EnabledDepend)
     */
    public void addEnabledDepend(EnabledDepend enabledDepend) {
        enabledDepends.add(enabledDepend);
    }

    /*
     * @see org.seasar.jface.WindowContext#addWidgetEnabledDependBinder(org.seasar.jface.binding.WidgetEnabledDependBinder)
     */
    public void addWidgetEnabledDependBinder(WidgetEnabledDependBinder binder) {
        widgetEnabledDependBinders.add(binder);
    }

    /*
     * @see org.seasar.jface.WindowContext#getActionObject()
     */
    public Object getActionComponent() {
        return actionComponent;
    }

    /*
     * @see org.seasar.jface.WindowContext#getComponent(java.lang.String)
     */
    public Widget getComponent(String id) {
        Object component = componentMap.get(id);
        if (component != null) {
            if (component instanceof Widget) {
                return Widget.class.cast(component);
            } else if (component instanceof Viewer) {
                Control control = ((Viewer) component).getControl();
                return Widget.class.cast(control);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /*
     * @see org.seasar.jface.WindowContext#getEnabledDepends()
     */
    public List<EnabledDepend> getEnabledDepends() {
        return this.enabledDepends;
    }

    /*
     * @see org.seasar.jface.WindowContext#getFormComponent()
     */
    public Object getFormComponent() {
        return formComponent;
    }

    /*
     * @see org.seasar.jface.WindowContext#getInitializeMethod()
     */
    public Method getInitializeMethod() {
        return initializeMethod;
    }

    /*
     * @see org.seasar.jface.WindowContext#getMenuBar()
     */
    public MenuManager getMenuBar() {
        return this.menuBar;
    }

    /*
     * @see org.seasar.jface.WindowContext#getStatusLineManager()
     */
    public IStatusLineManager getStatusLineManager() {
        return this.statusLineManager;
    }

    /*
     * @see org.seasar.jface.WindowContext#getViewerAdapter(org.eclipse.swt.widgets.Widget)
     */
    public ViewerAdapter getViewerAdapter(Widget widget) {
        return viewerAdapterMap.get(widget);
    }

    /*
     * @see org.seasar.jface.WindowContext#getViewerComponent(java.lang.String)
     */
    public Viewer getViewerComponent(String id) {
        Object component = componentMap.get(id);
        if (component != null && component instanceof Viewer) {
            return Viewer.class.cast(component);
        } else {
            return null;
        }
    }

    public List<Viewer> getViewerComponents() {
        List<Viewer> results = new ArrayList<Viewer>();

        for (Iterator iter = componentMap.values().iterator(); iter.hasNext();) {
            Object component = iter.next();
            if (component instanceof Viewer) {
                results.add(Viewer.class.cast(component));
            }
        }
        return results;
    }

    /*
     * @see org.seasar.jface.WindowContext#getWidgetEnabledDependBinders()
     */
    public List<WidgetEnabledDependBinder> getWidgetEnabledDependBinders() {
        return widgetEnabledDependBinders;
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
     * @see org.seasar.jface.WindowContext#putViewerAdapter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.jface.viewer.ViewerAdapter)
     */
    public void putViewerAdapter(Widget widget, ViewerAdapter adapter) {
        viewerAdapterMap.put(widget, adapter);
    }

    /*
     * @see org.seasar.jface.WindowContext#putViewerComponent(java.lang.String,
     *      org.eclipse.jface.viewers.Viewer)
     */
    public void putViewerComponent(String id, Viewer viewer) {
        AssertionUtil.assertNotNull("id", id);
        AssertionUtil.assertNotNull("viewer", viewer);

        if (!componentMap.containsKey(id)) {
            componentMap.put(id, viewer);
        } else {
            throw new DuplicateComponentIdException(id);
        }
    }

    /*
     * @see org.seasar.jface.WindowContext#setActionObject(java.lang.Object)
     */
    public void setActionComponent(Object actionObject) {
        this.actionComponent = actionObject;
    }

    /*
     * @see org.seasar.jface.WindowContext#setFormComponent(java.lang.Object)
     */
    public void setFormComponent(Object formComponent) {
        this.formComponent = formComponent;
    }

    /*
     * @see org.seasar.jface.WindowContext#setInitializeMethod(java.lang.reflect.Method)
     */
    public void setInitializeMethod(Method method) {
        this.initializeMethod = method;
    }

    /*
     * @see org.seasar.jface.WindowContext#setMenuBar(org.eclipse.jface.action.IMenuManager)
     */
    public void setMenuBar(MenuManager menuManager) {
        this.menuBar = menuManager;
    }

    /*
     * @see org.seasar.jface.WindowContext#setStatusLineManager(org.eclipse.jface.action.IStatusLineManager)
     */
    public void setStatusLineManager(IStatusLineManager statusLineManager) {
        this.statusLineManager = statusLineManager;
    }
}
