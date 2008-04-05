/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.seasar.framework.container.annotation.tiger.AutoBindingType;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.binding.context.ApplicationContextBinder;
import org.seasar.uruma.binding.enables.EnablesDependingListenerSupport;
import org.seasar.uruma.binding.method.MethodBindingSupport;
import org.seasar.uruma.binding.method.WindowCloseListener;
import org.seasar.uruma.binding.value.ValueBindingSupport;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.jface.WindowComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.ComponentUtil;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.core.UrumaWindowManager;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.desc.PartActionDescFactory;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.renderer.impl.WindowRenderer;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link Template} オブジェクトを元にして画面描画を行う、{@link ApplicationWindow} です。
 * 
 * @author y-komori
 */
@Component(autoBinding = AutoBindingType.NONE)
public class UrumaApplicationWindow extends ApplicationWindow implements
        UrumaConstants, UrumaMessageCodes {
    private UrumaWindowManager windowManager;

    private WindowComponent windowComponent;

    private WindowContext windowContext;

    private PartContext partContext;

    private PartActionDesc desc;

    private Object partActionComponent;

    private List<WindowCloseListener> closeListeners;

    private boolean block;

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
        component.preRender(null, windowContext);

        setupActionComponent();
        ComponentUtil.setupFormComponent(partContext, windowComponent.getId());

        setupMenuBar();
        setupShellStyle(component, modal);
        setupStatusLine();

        // パートアクションの @Initialize メソッドを呼び出す
        ComponentUtil
                .invokeInitMethodOnAction(partActionComponent, partContext);
    }

    protected void setupActionComponent() {
        partActionComponent = ComponentUtil.setupPartAction(partContext,
                windowComponent.getId());
        if (partActionComponent != null) {
            this.desc = PartActionDescFactory
                    .getPartActionDesc(partActionComponent.getClass());
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
        if (StringUtil.isNotBlank(windowComponent.menu)) {
            addMenuBar();
        }
    }

    /*
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    @Override
    protected MenuManager createMenuManager() {
        String menuId = windowComponent.menu;

        WidgetHandle handle = partContext.getWindowContext().getWidgetHandle(
                menuId);
        if (handle != null) {
            if (handle.instanceOf(MenuManager.class)) {
                return handle.<MenuManager> getCastWidget();
            } else {
                throw new RenderException(UNSUPPORTED_TYPE_ERROR, menuId,
                        MenuManager.class.getName());
            }
        } else {
            throw new NotFoundException(UICOMPONENT_NOT_FOUND, menuId);
        }
    }

    protected void setupStatusLine() {
        String statusLine = windowComponent.statusLine;
        if ("true".equals(statusLine)) {
            addStatusLine();
            WidgetHandle handle = ContextFactory
                    .createWidgetHandle(getStatusLineManager());
            handle.setId(STATUS_LINE_MANAGER_CID);
            partContext.putWidgetHandle(handle);
        }
    }

    /*
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(final Composite parent) {
        // ウィンドウのレンダリングを開始する
        WidgetHandle windowHandle = ContextFactory.createWidgetHandle(this);
        windowHandle.setId(WINDOW_CID);
        partContext.putWidgetHandle(windowHandle);

        WidgetHandle shellHandle = ContextFactory.createWidgetHandle(parent);
        shellHandle.setId(SHELL_CID);
        partContext.putWidgetHandle(shellHandle);

        windowComponent.render(shellHandle, partContext);

        MethodBindingSupport.createListeners(partContext);

        // 画面初期表示時の、フォームから画面へのエクスポート処理を実施
        ValueBindingSupport.exportValue(partContext);
        ValueBindingSupport.exportSelection(partContext);

        EnablesDependingListenerSupport
                .setupEnableDependingListeners(windowContext);

        return parent;
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
     * ウィンドウIDを返します。<br />
     * 
     * @return ウィンドウID
     */
    public String getWindowId() {
        return windowComponent.getId();
    }

    /**
     * {@link WindowCloseListener} を追加します。<br />
     * 
     * @param listener
     *            {@link WindowCloseListener} オブジェクト
     */
    public void addWindowCloseListener(final WindowCloseListener listener) {
        AssertionUtil.assertNotNull("listener", listener);

        if (closeListeners == null) {
            closeListeners = new ArrayList<WindowCloseListener>();
        }
        closeListeners.add(listener);
    }

    /*
     * @see org.eclipse.jface.window.Window#open()
     */
    @Override
    public int open() {
        super.open();

        ComponentUtil.invokePostOpenMethodOnAction(partActionComponent,
                partContext);

        if (block) {
            Shell shell = getShell();
            Display display;
            if (shell == null) {
                display = Display.getCurrent();
            } else {
                display = shell.getDisplay();
            }

            while (shell != null && !shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
                // TODO ExceptionHandlerによる例外処理を行う
            }
            display.update();
        }

        return getReturnCode();
    }

    /*
     * @see org.eclipse.jface.window.ApplicationWindow#close()
     */
    @Override
    public boolean close() {
        // ウィンドウをクローズしてよいか確認する
        boolean canClose = true;
        if (closeListeners != null) {
            for (WindowCloseListener listener : closeListeners) {
                canClose &= listener.canWindowClose(this);
            }
        }

        if (canClose && super.close()) {
            // ApplicationContext へのエクスポート処理
            if (partActionComponent != null) {
                ApplicationContextBinder.exportObjects(partActionComponent,
                        desc.getApplicationContextDefList(), windowContext
                                .getApplicationContext());
            }

            if (closeListeners != null) {
                closeListeners.clear();
                closeListeners = null;
            }

            this.windowManager.close(getWindowId());

            return true;
        } else {
            return false;
        }
    }

    /*
     * @see org.eclipse.jface.window.Window#setBlockOnOpen(boolean)
     */
    @Override
    public void setBlockOnOpen(final boolean shouldBlock) {
        this.block = shouldBlock;
    }
}
