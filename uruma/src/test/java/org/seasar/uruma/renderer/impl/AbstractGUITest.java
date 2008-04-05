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
package org.seasar.uruma.renderer.impl;

import org.eclipse.swt.widgets.Shell;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.PostOpenMethod;
import org.seasar.uruma.core.StandAloneUrumaStarter;

/**
 * レンダラのテストを行うための基底クラスです。</br>
 * <p>
 * 各レンダラのテストクラスは、本クラスを継承してください。</br>
 * </p>
 * 
 * @author y-komori
 */
public abstract class AbstractGUITest extends S2FrameworkTestCase {
    protected StandAloneUrumaStarter uruma;

    public Shell shell;

    protected boolean result;

    /*
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        uruma = StandAloneUrumaStarter.getInstance();
        S2Container container = SingletonS2ContainerFactory.getContainer();
        String componentName = StringUtil.decapitalize(getClass()
                .getSimpleName())
                + "Action";
        container.register(this, componentName);

        // クラス名と同名の dicon ファイルが存在すればインクルードする
        try {
            S2Container child = S2ContainerFactory
                    .create(convertPath(getClass().getSimpleName() + ".dicon"));
            container.include(child);
        } catch (ResourceNotFoundRuntimeException ex) {
            // do nothing.
        }

        result = false;
    }

    /*
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        StandAloneUrumaStarter.destroy();
    }

    /**
     * テスト画面を開きます。<br />
     */
    public void testRender() {
        String path = convertPath(getClass().getSimpleName() + ".xml");
        uruma.openWindow(path);
        assertTrue(path, result);
    }

    @PostOpenMethod
    public void postOpen() {
    }

    /**
     * 「OK」ボタンが押されたときに呼び出されるメソッドです。<br />
     */
    @EventListener(id = "okButton")
    public void okButtonAction() {
        shell.close();

        result = true;
    }

    /**
     * 「NG」ボタンが押されたときに呼び出されるメソッドです。<br />
     */
    @EventListener(id = "ngButton")
    public void ngButtonAction() {
        shell.close();
        result = false;
    }
}
