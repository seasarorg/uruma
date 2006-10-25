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
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.container.annotation.tiger.AutoBindingType;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.ActionDesc;
import org.seasar.jface.binding.ActionDescFactory;
import org.seasar.jface.binding.EnabledDelegation;
import org.seasar.jface.binding.EnabledDelegationBinder;
import org.seasar.jface.binding.EnabledDelegationBinderFactory;
import org.seasar.jface.binding.MethodBindingSupport;
import org.seasar.jface.binding.ValueBinder;
import org.seasar.jface.component.Template;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.exception.EnabledDelegationException;
import org.seasar.jface.exception.NotFoundException;
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
        // setupMenuBar();
        setupShellStyle(template.getWindowComponent(), modal);
    }

    protected void setupActionComponent() {
        String actionComponentName = getActionComponentName();
        actionComponent = S2ContainerUtil
                .getComponentNoException(actionComponentName);
        if (actionComponent != null) {
            context.setActionComponent(actionComponent);
            actionDesc = ActionDescFactory.getActionDesc(actionComponent
                    .getClass());
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

        if (actionDesc != null) {
            MethodBindingSupport.createListeners(actionDesc, context);
        }

        bindEnabledDelegations();

        ValueBinder.exportValue(context);
        return parent;
    }

    protected void bindEnabledDelegations() {
        for (EnabledDelegation delegation : context.getEnabledDelegations()) {
            Widget delegationWidget = context.getComponent(delegation
                    .getDelegationId());
            if (delegationWidget == null) {
                throw new NotFoundException(NotFoundException.UICOMPONENT,
                        delegation.getDelegationId());
            }

            EnabledDelegationBinder delegator = EnabledDelegationBinderFactory
                    .getEnabledDelegationBinder(delegationWidget.getClass());
            if (delegator == null) {
                throw new EnabledDelegationException(
                        EnabledDelegationException.DELEGATION_WIDGET_NOT_SUPPORTED,
                        delegationWidget.getClass());
            }
            delegator.bind(delegation.getWidget(), delegationWidget, delegation
                    .getType());
        }
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

    public String getActionComponentName() {
        return StringUtil.decapitalize(template.getWindowComponent().getId())
                + "Action";
    }

    /**
     * アクションコンポーネントの初期化メソッドを呼び出します。<br />
     */
    public void initActionComponent() {
        if (actionComponent != null) {
            actionDesc.invokeInitializeMethod(actionComponent);
        }
    }

    Object getActionComponent() {
        return actionComponent;
    }

    public Object getReturnValue() {
        return actionDesc.getReturnValue(actionComponent);
    }
}
