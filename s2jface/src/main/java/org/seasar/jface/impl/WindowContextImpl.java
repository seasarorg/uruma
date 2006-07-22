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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.exception.DuplicateComponentIdException;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 * 
 */
public class WindowContextImpl implements WindowContext {
    protected Map<String, Widget> componentMap = new HashMap<String, Widget>();

    protected MenuManager menuBar;

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
}
