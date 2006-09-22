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

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.seasar.framework.container.annotation.tiger.AutoBindingType;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.MethodBindingSupport;
import org.seasar.jface.component.Template;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.renderer.impl.WindowRenderer;

/**
 * @author y-komori
 * 
 */
@Component(autoBinding = AutoBindingType.NONE)
public class S2JFaceApplicationWindow extends ApplicationWindow {
    private Template template;

    private WindowContext context;

    // private MenuManagerBuilder menuManagerBuilder;

    public S2JFaceApplicationWindow() {
        super(null);
    }

    public S2JFaceApplicationWindow(Template template) {
        super(null);
        init(template);
    }

    /**
     * <code>S2JFaceApplicationWindow</code> を初期化します。<br/>
     * <p>
     * デフォルトコンストラクタを使用して本クラスを生成した場合は、必ず本メソッドを呼び出してから利用してください。
     * </p>
     * 
     * @param template
     *            テンプレートオブジェクト
     */
    public void init(Template template) {
        this.template = template;
        this.context = new WindowContextImpl();

        // setupMenuBar();
        setupShellStyle(template.getWindowComponent());
    }

    protected void setupShellStyle(final WindowComponent component) {
        WindowRenderer renderer = (WindowRenderer) component.getRenderer();
        int style = (renderer.getShellStyle(component));
        setShellStyle(style);
    }

    // protected void setupMenuBar() {
    // WindowComponent windowComponent = template.getWindowComponent();
    // Menu menuBar = windowComponent.getMenuBar();
    // if (menuBar != null) {
    // addMenuBar();
    // }
    // }

    @Override
    protected Control createContents(Composite parent) {
        // registMenuToContext();

        WindowComponent windowComponent = template.getWindowComponent();
        windowComponent.render(parent, context);

        MethodBindingSupport.createListeners(windowComponent.getId(), context);

        return parent;
    }

    // @Override
    // protected MenuManager createMenuManager() {
    // WindowComponent windowComponent = template.getWindowComponent();
    // Menu menuBar = windowComponent.getMenuBar();
    // MenuManager menuManager = menuManagerBuilder.createMenuManager(menuBar);
    //
    // return menuManager;
    // }

    // protected void registMenuToContext() {
    // Menu menuBar = template.getWindowComponent().getMenuBar();
    // if (menuBar != null) {
    // String id = menuBar.getId();
    // if (id != null) {
    // context.putComponent(id, getMenuBarManager().getMenu());
    // }
    // }
    // }

    // @Binding(bindingType = BindingType.MUST)
    // public void setMenuManagerBuilder(MenuManagerBuilder menuManagerBuilder)
    // {
    // this.menuManagerBuilder = menuManagerBuilder;
    // }

}
