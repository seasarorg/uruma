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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.annotation.tiger.AutoBindingType;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.Form;
import org.seasar.jface.binding.ActionDesc;
import org.seasar.jface.binding.ActionDescFactory;
import org.seasar.jface.binding.BindingFacade;
import org.seasar.jface.binding.ValueBinder;
import org.seasar.jface.component.Template;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.renderer.impl.WindowRenderer;
import org.seasar.jface.util.S2ContainerUtil;

/**
 * @author y-komori
 * 
 */
@Component(autoBinding = AutoBindingType.NONE)
public class S2JFaceApplicationWindow extends ApplicationWindow {

    private Template template;

    private WindowContext context;

    private ActionDesc actionDesc;

    private Object actionComponent;

    // private MenuManagerBuilder menuManagerBuilder;

    public S2JFaceApplicationWindow() {
        super(null);
    }

    public S2JFaceApplicationWindow(Template template, boolean modal) {
        super(null);
        init(template, modal);
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
    public void init(Template template, boolean modal) {
        this.template = template;
        this.context = new WindowContextImpl();

        setupActionComponent();
        setupFormComponent();
        // setupMenuBar();
        setupShellStyle(template.getWindowComponent(), modal);
    }

    protected void setupActionComponent() {
        String actionComponentName = StringUtil.decapitalize(template
                .getWindowComponent().getId())
                + "Action";
        actionComponent = S2ContainerUtil
                .getComponentNoException(actionComponentName);
        if (actionComponent != null) {
            context.setActionComponent(actionComponent);
            actionDesc = ActionDescFactory.getActionDesc(actionComponent
                    .getClass());
        }
    }

    protected void setupFormComponent() {
        Object formComponent = null;
        Object action = context.getActionComponent();
        if (action != null) {
            Form formAnnotation = action.getClass().getAnnotation(Form.class);
            if (formAnnotation != null) {
                Class<?> formClass = formAnnotation.value();
                if (formClass == context.getActionComponent().getClass()) {
                    formComponent = context.getActionComponent();
                } else {
                    formComponent = S2ContainerUtil.getComponent(formClass);
                }
            }
        }

        if (formComponent == null) {
            String formComponentName = StringUtil.decapitalize(template
                    .getWindowComponent().getId())
                    + "Form";
            formComponent = S2ContainerUtil
                    .getComponentNoException(formComponentName);
        }

        if (formComponent != null) {
            context.setFormComponent(formComponent);
            injectFormToAction();
        }
    }

    /**
     * ActionオブジェクトへFormオブジェクトのプロパティが存在する場合、
     * WindowContextが保持するFormオブジェクトをインジェクションする。
     */
    protected void injectFormToAction() {
        String formComponentName = context.getFormComponent().getClass()
                .getSimpleName();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(context
                .getActionComponent().getClass());
        if (beanDesc.hasPropertyDesc(formComponentName)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(formComponentName);
            if (pd.hasWriteMethod()) {
                pd.setValue(context.getActionComponent(), context
                        .getFormComponent());
            }
        }
    }

    protected void setupShellStyle(final WindowComponent component,
            boolean modal) {
        WindowRenderer renderer = (WindowRenderer) component.getRenderer();
        int style = (renderer.getShellStyle(component));

        if (modal) {
            if ((style & (SWT.APPLICATION_MODAL | SWT.PRIMARY_MODAL | SWT.SYSTEM_MODAL)) == 0) {
                style |= SWT.PRIMARY_MODAL;
            }
        } else {
            style &= ~(SWT.APPLICATION_MODAL | SWT.PRIMARY_MODAL | SWT.SYSTEM_MODAL);
        }
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

        BindingFacade.bindAll(actionDesc, context);

        // 画面初期表示時の、Action から 画面への ExportValue 処理を実施
        ValueBinder.exportValue(context);
        ValueBinder.exportSelection(context);

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

    /**
     * アクションコンポーネントの初期化メソッドを呼び出します。<br />
     * 
     * @param argument
     *            ウィンドウへの引数
     */
    public void initActionComponent(Object argument) {
        if (actionComponent != null) {
            actionDesc.setArgumentValue(actionComponent, argument);
            actionDesc.invokeInitializeMethod(actionComponent);
        }
    }

    Object getActionComponent() {
        return actionComponent;
    }

    public Object getReturnValue() {
        if (actionDesc == null) {
            return null;
        }
        return actionDesc.getReturnValue(actionComponent);
    }
}
