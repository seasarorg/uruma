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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
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
import org.seasar.uruma.binding.value.ValueBindingSupport;
import org.seasar.uruma.binding.widget.WidgetBinder;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.impl.WindowComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaWindowManager;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.desc.PartActionDescFactory;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.renderer.impl.WindowRenderer;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.S2ContainerUtil;

/**
 * {@link Template} オブジェクトを元にして画面描画を行う、{@link ApplicationWindow} です。
 * 
 * @author y-komori
 */
@Component(autoBinding = AutoBindingType.NONE)
public class UrumaApplicationWindow extends ApplicationWindow implements
        ShellListener {
    private UrumaWindowManager windowManager;

    private WindowComponent windowComponent;

    private WindowContext windowContext;

    private PartContext partContext;

    private PartActionDesc desc;

    private Object partActionComponent;

    /**
     * {@link UrumaApplicationWindow} を構築します。<br />
     */
    public UrumaApplicationWindow(final UrumaWindowManager manager) {
        super(null);
        AssertionUtil.assertNotNull("manager", manager);

        this.windowManager = manager;
    }

    /**
     * {@link UrumaApplicationWindow} を構築します。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     * @param component
     *            {@link WindowComponent} オブジェクト
     * @param modal
     *            <code>true</code> の場合、モーダルウィンドウとして開く。<code>false</code>
     *            の場合、モーダレスウィンドウとして開く。
     */
    public UrumaApplicationWindow(final UrumaWindowManager manager,
            final WindowContext context, final WindowComponent component,
            final boolean modal) {
        this(manager);
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
    public void init(final WindowContext context,
            final WindowComponent component, final boolean modal) {
        this.windowComponent = component;
        this.windowContext = context;
        this.partContext = ContextFactory.createPartContext(windowContext,
                component.getId());

        // プリレンダリング処理を実施
        component.preRender(null, partContext);

        if (!WindowComponent.DEFAULT_ID.equals(windowComponent.getId())) {
            setupActionComponent();
            setupFormComponent();

            setupMenuBar();
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
            pd.setValue(partActionObject, formObject);
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

    protected void setupMenuBar() {
        String menuId = windowComponent.getMenu();
        if (!StringUtil.isEmpty(menuId)) {
            addMenuBar();
        } else if (partContext.hasWidgetHandle(PartContext.DEFAULT_MENU_ID)) {
            windowComponent.setMenu(PartContext.DEFAULT_MENU_ID);
            addMenuBar();
        }
    }

    /*
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    @Override
    protected MenuManager createMenuManager() {
        String menuId = windowComponent.getMenu();

        WidgetHandle handle = partContext.getWidgetHandle(menuId);
        if (handle != null) {
            if (handle.instanceOf(MenuManager.class)) {
                return handle.<MenuManager> getCastWidget();
            } else {
                throw new RenderException(RenderException.TYPE_ERROR, menuId,
                        MenuManager.class.getName());
            }
        } else {
            throw new NotFoundException(NotFoundException.UICOMPONENT, menuId);
        }
    }

    protected void setupStatusLine() {
        String statusLine = windowComponent.getStatusLine();
        if ("true".equals(statusLine)) {
            addStatusLine();
            WidgetHandle handle = ContextFactory
                    .createWidgetHandle(getStatusLineManager());
            handle.setId(PartContext.STATUS_LINE_MANAGER_ID);
            partContext.putWidgetHandle(handle);
        }
    }

    /*
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(final Composite parent) {
        getShell().addShellListener(this);

        // ウィンドウのレンダリングを開始する
        WidgetHandle handle = ContextFactory.createWidgetHandle(parent);
        handle.setId(PartContext.SHELL_ID);
        partContext.putWidgetHandle(handle);

        windowComponent.render(handle, partContext);

        MethodBindingSupport.createListeners(partContext);

        // 画面初期表示時の、フォームから画面へのエクスポート処理を実施
        ValueBindingSupport.exportValue(partContext);
        ValueBindingSupport.exportSelection(partContext);

        return parent;
    }

    /**
     * アクションコンポーネントの初期化メソッドを呼び出します。<br />
     * 
     * @param argument
     *            ウィンドウへの引数
     */
    public void initActionComponent(final Object argument) {
        if (partActionComponent != null) {
            WidgetBinder.bindWidgets(partActionComponent, partContext);

            desc.setArgumentValue(partActionComponent, argument);
            desc.invokeInitializeMethod(partActionComponent);
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

    /**
     * ウィンドウIDを返します。<br />
     * 
     * @return ウィンドウID
     */
    public String getWindowId() {
        return windowComponent.getId();
    }

    /*
     * @see org.eclipse.swt.events.ShellListener#shellActivated(org.eclipse.swt.events.ShellEvent)
     */
    public void shellActivated(final ShellEvent e) {
        // Do nothing.
    }

    /*
     * @see org.eclipse.swt.events.ShellListener#shellClosed(org.eclipse.swt.events.ShellEvent)
     */
    public void shellClosed(final ShellEvent e) {
        this.windowManager.close(getWindowId());
    }

    /*
     * @see org.eclipse.swt.events.ShellListener#shellDeactivated(org.eclipse.swt.events.ShellEvent)
     */
    public void shellDeactivated(final ShellEvent e) {
        // Do nothing.
    }

    /*
     * @see org.eclipse.swt.events.ShellListener#shellDeiconified(org.eclipse.swt.events.ShellEvent)
     */
    public void shellDeiconified(final ShellEvent e) {
        // Do nothing.
    }

    /*
     * @see org.eclipse.swt.events.ShellListener#shellIconified(org.eclipse.swt.events.ShellEvent)
     */
    public void shellIconified(final ShellEvent e) {
        // Do nothing.
    }
}
