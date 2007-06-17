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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.component.Template;
import org.seasar.jface.component.UICompositeComponent;
import org.seasar.jface.component.impl.ViewPartComponent;
import org.seasar.jface.exception.RenderException;
import org.seasar.jface.impl.WindowContextImpl;

/**
 * S2RCP の機能を利用する {@link IViewPart} の基底クラスです。<br />
 * <p>
 * S2RCP による {@link ViewPart} を作成するには、本クラスのサブクラスを作成してください。<br />
 * </p>
 * 
 * @author y-komori
 */
public class S2RcpViewPart extends ViewPart {
    protected S2Container container;

    /*
     * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite,
     *      org.eclipse.ui.IMemento)
     */
    @Override
    public void init(IViewSite site, IMemento memento) throws PartInitException {
        super.init(site, memento);

        String viewComponentName = getViewComponentName();
        container = SingletonS2ContainerFactory.getContainer();
        container.register(this, viewComponentName);
    }

    /*
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        S2RcpActivator plugin = (S2RcpActivator) container
                .getComponent(S2RcpActivator.PLUGIN);

        Thread currentThread = Thread.currentThread();
        ClassLoader originalLoader = currentThread.getContextClassLoader();

        currentThread.setContextClassLoader(getClass().getClassLoader());
        String templatePath = getTemplatePath();
        Template template = plugin.getTemplate(templatePath);
        currentThread.setContextClassLoader(originalLoader);

        UICompositeComponent rootComponent = template.getRootComponent();
        if (rootComponent instanceof ViewPartComponent) {
            ViewPartComponent viewPartComponent = (ViewPartComponent) rootComponent;
            viewPartComponent.render(parent, new WindowContextImpl());
        } else {
            throw new RenderException(RenderException.REQUIRED_VIEWPART_ERROR,
                    templatePath);
        }
    }

    /*
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
        // Do nothing.
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
        String viewId = getSite().getId();
        return StringUtil.decapitalize(StringUtil.substringToLast(viewId, "."));
    }

    protected String getTemplatePath() {
        return getViewComponentName() + ".xml";
    }
}
