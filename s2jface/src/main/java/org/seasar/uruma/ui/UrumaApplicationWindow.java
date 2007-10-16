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
package org.seasar.uruma.ui;

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
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.binding.method.MethodBindingSupport;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.impl.WindowComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.desc.PartActionDescFactory;
import org.seasar.uruma.renderer.impl.WindowRenderer;
import org.seasar.uruma.util.S2ContainerUtil;

/**
 * {@link Template} オブジェクトを元にして画面描画を行う、{@link ApplicationWindow} です。
 * 
 * @author y-komori
 */
@Component(autoBinding = AutoBindingType.NONE)
public class UrumaApplicationWindow extends ApplicationWindow {

    private WindowComponent windowComponent;

    private WindowContext windowContext;

    private PartContext partContext;

    private PartActionDesc desc;

    private Object partActionComponent;

    // private MenuManagerBuilder menuManagerBuilder;

    /**
     * {@link UrumaApplicationWindow} を構築します。<br />
     */
    public UrumaApplicationWindow() {
        super(null);
    }

    /**
     * {@link UrumaApplicationWindow} を構築します。<br />
     * 
     * @param context
     *            {@link ApplicationContext} オブジェクト
     * @param component
     *            {@link WindowComponent} オブジェクト
     * @param modal
     *            <code>true</code> の場合、モーダルウィンドウとして開く。<code>false</code>
     *            の場合、モーダレスウィンドウとして開く。
     */
    public UrumaApplicationWindow(final ApplicationContext context,
            final WindowComponent component, final boolean modal) {
        super(null);
        init(context, component, modal);
    }

    /**
     * {@link UrumaApplicationWindow} を初期化します。<br/>
     * <p>
     * デフォルトコンストラクタを使用して本クラスを生成した場合は、必ず本メソッドを呼び出してから利用してください。
     * </p>
     * 
     * @param context
     *            {@link ApplicationContext} オブジェクト
     * @param component
     *            {@link WindowComponent} オブジェクト
     * @param modal
     *            <code>true</code> の場合、モーダルウィンドウとして開く
     */
    public void init(final ApplicationContext context,
            final WindowComponent component, final boolean modal) {
        this.windowComponent = component;
        this.windowContext = ContextFactory.createWindowContext(context,
                component.getId());
        this.partContext = ContextFactory.createPartContext(windowContext,
                component.getId());

        if (!WindowComponent.DEFAULT_ID.equals(windowComponent.getId())) {
            setupActionComponent();
            setupFormComponent();
            // setupMenuBar();
        }
        setupShellStyle(component, modal);
        setupStatusLine();
    }

    protected void setupActionComponent() {
        String id = windowComponent.getId();
        String actionComponentName = StringUtil.decapitalize(id) + "Action";
        partActionComponent = S2ContainerUtil
                .getComponentNoException(actionComponentName);
        if (partActionComponent != null) {
            partContext.setPartActionObject(partActionComponent);
            desc = PartActionDescFactory.getPartActionDesc(partActionComponent
                    .getClass());
        }
    }

    protected void setupFormComponent() {
        Object formObject = null;
        Object actionObject = partContext.getPartActionObject();
        if (actionObject != null) {
            Form formAnnotation = actionObject.getClass().getAnnotation(
                    Form.class);
            if (formAnnotation != null) {
                Class<?> formClass = formAnnotation.value();
                if (formClass == partContext.getPartActionObject().getClass()) {
                    formObject = partContext.getPartActionObject();
                } else {
                    formObject = S2ContainerUtil.getComponent(formClass);
                }
            }
        }

        if (formObject == null) {
            String formComponentName = StringUtil.decapitalize(windowComponent
                    .getId())
                    + "Form";
            formObject = S2ContainerUtil
                    .getComponentNoException(formComponentName);
        }

        if (formObject != null) {
            partContext.setFormObject(formObject);
            injectFormToAction();
        }
    }

    /**
     * パートアクションオブジェクトにフォームオブジェクトのプロパティが存在する場合、 {@link PartContext}
     * が保持するフォームオブジェクトをインジェクションする。
     */
    protected void injectFormToAction() {
        Object partActionObject = partContext.getPartActionObject();
        Object formObject = partContext.getFormObject();

        String formObjectName = formObject.getClass().getSimpleName();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(partActionObject
                .getClass());
        if (beanDesc.hasPropertyDesc(formObjectName)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(formObjectName);
            if (pd.hasWriteMethod()) {
                pd.setValue(partActionObject, formObject);
            }
        }
    }

    protected void setupShellStyle(final WindowComponent component,
            final boolean modal) {
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

    protected void setupStatusLine() {
        String statusLine = windowComponent.getStatusLine();
        if ("true".equals(statusLine)) {
            addStatusLine();
            // TODO StatusLineManager 用の UIComponent が必要
            WidgetHandle handle = ContextFactory
                    .createWidgetHandle(getStatusLineManager());
            windowContext.putWidgetHandle(handle);
        }
    }

    /*
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(final Composite parent) {
        // registMenuToContext();

        // ウィンドウのレンダリングを開始する
        WidgetHandle handle = ContextFactory.createWidgetHandle(parent);
        handle.setId(PartContext.SHELL_ID);
        partContext.putWidgetHandle(handle);

        windowComponent.render(handle, partContext);

        MethodBindingSupport.createListeners(partContext);

        // TODO 画面初期表示時の、Action から 画面への ExportValue 処理を実施
        // ValueBinder.exportValue(windowContext);
        // ValueBinder.exportSelection(windowContext);

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
    public void initActionComponent(final Object argument) {
        if (partActionComponent != null) {
            // WidgetBinder.bindWidgets(partActionComponent, windowContext);
            // TODO あとで見直し
            // desc.setArgumentValue(partActionComponent, argument);
            // desc.invokeInitializeMethod(partActionComponent);
        }
    }

    /**
     * パートアクションコンポーネントを取得します。<br />
     * 
     * @return パートアクションコンポーネント
     */
    public Object getPartActionComponent() {
        return this.partActionComponent;
    }

    /**
     * ウィンドウの戻り値を返します。<br />
     * 
     * @return ウィンドウの戻り値オブジェクト。存在しない場合は <code>null</code>
     */
    public Object getReturnValue() {
        if (desc != null) {
            return desc.getReturnValue(partActionComponent);
        } else {
            return null;
        }
    }
}
