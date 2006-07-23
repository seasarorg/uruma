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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.MethodBindingSupport;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.renderer.MenuManagerRenderer;
import org.seasar.jface.renderer.WindowRenderer;

/**
 * @author y-komori
 * 
 */
public class S2JFaceApplicationWindow extends ApplicationWindow {
    private TemplateComponent template;

    private WindowContext context;

    public S2JFaceApplicationWindow(TemplateComponent template) {
        super(null);
        this.template = template;
        this.context = new WindowContextImpl();

        setupMenuBar();
        setupShellStyle(template.getWindowComponent());
    }

    protected void setupShellStyle(final WindowComponent component) {
        int style = ((WindowRenderer) component.getRenderer())
                .getShellStyle(component);
        setShellStyle(style);
    }

    protected void setupMenuBar() {
        // addMenuBar() は shell の生成前に呼び出さなければならないため、
        // MenuManagerRenderer のみ別扱いでレンダリングを行っている
        WindowComponent windowComponent = template.getWindowComponent();
        String rendererName = (new MenuManagerRenderer()).getRendererName();
        for (UIComponent component : windowComponent.getChildren()) {
            if (rendererName.equals(component.getRendererType())) {
                component.render(null, context);
                if (context.getMenuBar() != null) {
                    addMenuBar();
                }
            }
        }
    }

    @Override
    protected Control createContents(Composite parent) {
        WindowComponent windowComponent = template.getWindowComponent();
        windowComponent.render(parent, context);

        MethodBindingSupport.createListeners(windowComponent.getId(), context);

        return parent;
    }

    @Override
    protected MenuManager createMenuManager() {
        return context.getMenuBar();
    }
}
