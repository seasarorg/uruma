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
package org.seasar.uruma.rcp.ui;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.seasar.uruma.binding.method.GenericAction;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.impl.WorkbenchComponent;
import org.seasar.uruma.rcp.UrumaActivator;

/**
 * @author y-komori
 * 
 */
public class UrumaActionBarAdvisor extends ActionBarAdvisor {
    private WorkbenchComponent workbench;

    /**
     * {@link UrumaActionBarAdvisor} を構築します。<br />
     * 
     * @param configurer
     *            {@link IActionBarConfigurer} オブジェクト
     */
    public UrumaActionBarAdvisor(final IActionBarConfigurer configurer) {
        super(configurer);
    }

    /*
     * @see org.eclipse.ui.application.ActionBarAdvisor#fillMenuBar(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    protected void fillMenuBar(final IMenuManager menuBar) {
        this.workbench = UrumaActivator.getInstance().getWorkbenchComponent();
        List<UIComponent> children = workbench.getChildren();

        MenuManager menu1 = new MenuManager("テスト");
        IAction action1 = new GenericAction("メニュー");
        menu1.add(action1);
        menuBar.add(menu1);
    }

    /*
     * @see org.eclipse.ui.application.ActionBarAdvisor#makeActions(org.eclipse.ui.IWorkbenchWindow)
     */
    @Override
    protected void makeActions(final IWorkbenchWindow window) {
        // do nothing
    }
}
