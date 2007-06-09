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

/**
 * S2RCP の機能を利用する {@link IViewPart} の基底クラスです。<br />
 * <p>
 * S2RCP による {@link ViewPart} を作成するには、本クラスのサブクラスを作成してください。<br />
 * </p>
 * 
 * @author y-komori
 */
public abstract class S2RcpViewPart extends ViewPart {
    protected S2Container container;

    @Override
    public void init(IViewSite site, IMemento memento) throws PartInitException {
        super.init(site, memento);

        String pluginId = getSite().getPluginId();
        System.out.println("PluginID:" + pluginId);

        container = SingletonS2ContainerFactory.getContainer();

        String viewId = getSite().getId();
        System.out.println("ViewID:" + viewId);
        container.register(this, viewId);
    }

    @Override
    public void createPartControl(Composite parent) {

        ClassLoader loader = getClass().getClassLoader();
        System.out.println("S2RCP:" + loader);

        ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
        System.out.println("Thread:" + loader2);
    }

    @Override
    public void setFocus() {
        // TODO 自動生成されたメソッド・スタブ

    }
}
