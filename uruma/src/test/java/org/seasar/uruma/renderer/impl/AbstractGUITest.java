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

import java.io.File;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.seasar.eclipse.common.util.SWTUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.framework.util.ResourceUtil;
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
    // Maven2 のターゲットディレクトリ名
    private static final String TARGET_DIR = "target";

    // file プロトコル名
    private static final String FILE_PROTOCOL = "file";

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
        String componentName = StringUtil.decapitalize(getClass().getSimpleName()) + "Action";
        container.register(this, componentName);

        // クラス名と同名の dicon ファイルが存在すればインクルードする
        try {
            S2Container child = S2ContainerFactory.create(convertPath(getClass().getSimpleName()
                    + ".dicon"));
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

    /**
     * テスト画面オープン後に実行される処理です。<br />
     */
    @PostOpenMethod
    public void postOpen() {
        captureScreen();

        if (isAutoTestMode()) {
            try {
                autoOK();
            } catch (Exception ex) {
                ex.printStackTrace();
                fail(ex.getMessage());
            }
        }
    }

    /**
     * 画面キャプチャを行います。 キャプチャ先は、Maven2 の target ディレクトリ配下の capture です。 target
     * ディレクトリが見つからない場合、カレントディレクトリ配下になります。
     */
    protected void captureScreen() {
        String dir = System.getProperty("user.dir") + System.getProperty("file.separator");
        String classPath = getClass().getName().replace('.', '/') + ".class";
        URL url = ResourceUtil.getResourceNoException(classPath);
        if (url != null && FILE_PROTOCOL.equals(url.getProtocol())) {
            dir = url.getPath();
            int pos = dir.indexOf(TARGET_DIR);
            if (pos >= 0) {
                dir = dir.substring(0, pos + TARGET_DIR.length()) + "/";
            }
        }
        dir += "capture";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        String path = dir + "/" + getClass().getSimpleName() + ".png";
        SWTUtil.saveWindowImage(shell, path, SWT.IMAGE_PNG);
    }

    protected boolean isAutoTestMode() {
        return Boolean.getBoolean("autoTest");
    }

    protected void autoOK() throws Exception {
        okButtonAction();
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
