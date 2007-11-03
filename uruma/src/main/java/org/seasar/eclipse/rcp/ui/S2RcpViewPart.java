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
package org.seasar.eclipse.rcp.ui;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.SelectionListener;
import org.seasar.uruma.binding.method.SingleParamTypeMethodBinding;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.component.impl.ViewPartComponent;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.rcp.UrumaActivator;
import org.seasar.uruma.util.AnnotationUtil;
import org.seasar.uruma.util.S2ContainerUtil;

/**
 * S2RCP の機能を利用する {@link IViewPart} の基底クラスです。<br />
 * <p>
 * S2RCP による {@link ViewPart} を作成するには、本クラスのサブクラスを作成してください。<br />
 * 本クラスは {@link S2RcpViewPart#init(IViewSite, IMemento)} メソッドにおいて、自インスタンスを
 * {@link S2RcpViewPart#getViewComponentName()} の戻り値をコンポーネント名として、{@link S2Container}
 * へ登録します。<br />
 * </p>
 * <p>
 * 当該 {@link IViewPart} の中で使用されている {@link Viewer} が一つしか存在しない場合、その {@link Viewer}
 * を自動的に {@link ISelectionProvider} として {@link IWorkbenchPartSite} へ登録します。<br />
 * {@link Viewer} が複数存在する場合、自動登録は行いません。<br />
 * </p>
 * 
 * @author y-komori
 */
public class S2RcpViewPart extends ViewPart {
    protected S2Container container;

    protected WindowContext windowContext;

    private String viewComponentName;

    /*
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(final Composite parent) {
        UrumaActivator plugin = (UrumaActivator) container
                .getComponent(UrumaConstants.URUMA_PLUGIN_S2NAME);

        Thread currentThread = Thread.currentThread();
        ClassLoader originalLoader = currentThread.getContextClassLoader();

        currentThread.setContextClassLoader(getClass().getClassLoader());
        String templatePath = getTemplatePath();
        Template template = plugin.getTemplate(templatePath);
        currentThread.setContextClassLoader(originalLoader);

        UIContainer rootComponent = template.getRootComponent();
        if (rootComponent instanceof ViewPartComponent) {
            ViewPartComponent viewPartComponent = (ViewPartComponent) rootComponent;
            // TODO 要修正
            // viewPartComponent.render(parent, windowContext);
        } else {
            throw new RenderException(
                    UrumaMessageCodes.REQUIRED_VIEWPART_ERROR, templatePath);
        }

        prepareSelectionProvider();

        // TODO 他のViewPartでのレンダリング結果もバインドできるようにする。
        // TODO 要修正
        // WidgetBinder.bindWidgets(this, windowContext);

        setupSelectionListeners();
    }

    protected String getTemplatePath() {
        return getViewComponentName() + ".xml";
    }

    /**
     * ViewID からコンポーネント名を取得します。<br />
     * <p>
     * コンポーネント名は ViewID のうち、最後に登場するピリオドより後ろの文字列を取り出し、先頭の文字を小文字にしたものになります。<br />
     * 【例】<br />
     * <code>org.seasar.eclipse.rcp.TestView</code> の場合、<code>testView</code>
     * がコンポーネント名となります。
     * </p>
     * 
     * @return コンポーネント名
     */
    protected String getViewComponentName() {
        return viewComponentName;
    }

    /*
     * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite,
     *      org.eclipse.ui.IMemento)
     */
    @Override
    public void init(final IViewSite site, final IMemento memento)
            throws PartInitException {
        super.init(site, memento);

        initViewComponentName();

        container = SingletonS2ContainerFactory.getContainer();

        container.register(this, viewComponentName);

        S2ContainerUtil.injectDependency(this, container);
    }

    private void initViewComponentName() {
        String viewId = getSite().getId();
        viewComponentName = StringUtil.decapitalize(StringUtil.substringToLast(
                viewId, "."));
    }

    private void prepareSelectionProvider() {
        // TODO 要修正
        // List<Viewer> viewers = windowContext.getViewerComponents();
        // if (viewers.size() == 1) {
        // Viewer viewer = viewers.get(0);
        // getSite().setSelectionProvider(viewer);
        // }
    }

    /*
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
        // Do nothing.
    }

    private void setupSelectionListeners() {
        List<Method> listenerMethods = AnnotationUtil.getAnnotatedMethods(
                getClass(), SelectionListener.class);

        for (Method method : listenerMethods) {
            if (Modifier.isPublic(method.getModifiers())) {
                SelectionListener annotation = method
                        .getAnnotation(SelectionListener.class);
                boolean nullSelection = annotation.nullSelection();
                String providerPartId = annotation.value();

                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length <= 1) {
                    SingleParamTypeMethodBinding methodBinding = new SingleParamTypeMethodBinding(
                            this, method);

                    GenericSelectionListener listener;
                    if (nullSelection) {
                        listener = new NullGenericSelectionListener(
                                windowContext, methodBinding);
                    } else {
                        listener = new GenericSelectionListener(windowContext,
                                methodBinding);
                    }

                    if (StringUtil.isEmpty(providerPartId)) {
                        getSite().getPage().addSelectionListener(listener);
                    } else {
                        getSite().getPage().addSelectionListener(
                                providerPartId, listener);
                    }
                }
            }
        }
    }

    /**
     * {@link WindowContext} オブジェクトを設定します。<br />
     * 
     * @param windowContext
     *            {@link WindowContext} オブジェクト
     */
    public void setWindowContext(final WindowContext windowContext) {
        this.windowContext = windowContext;
    }
}
