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
package org.seasar.jface;

import org.eclipse.swt.widgets.Display;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author y-komori
 * 
 */
public class S2JFace {
    private static S2JFace instance;
    
    protected S2Container container;

    private Display display;

    private String imageBundleName = "s2JFaceImages";

    public static void main(String[] args) {
        if (args.length >= 1) {
            String templatePath = args[0];
            S2JFace s2JFace = S2JFace.getInstance();
            s2JFace.openWindow(templatePath);
        } else {
            System.err.println("[Error] 第1引数に初期画面のテンプレートパスを指定してください.");
        }
    }
    
    /**
     * 本クラスのインスタンスを取得します。<br />
     * 
     * @return S2JFace クラスのインスタンス。
     */
    public synchronized static S2JFace getInstance()
    {
        if(instance == null){
            instance = new S2JFace();
        }
        return instance;
    }
    
    private S2JFace() {
        initS2Container();
    }

    /**
     * Dicon ファイルのパスを設定します。<br />
     * デフォルトでは、<code>app.dicon</code> が使用されます。<br />
     * 本メソッドは、S2JFace{@link #getInstance()} を最初に呼び出す前に呼び出してください。
     * 
     * @param configPath Dicon ファイルのパス
     */
    public static void setConfigPath(final String configPath) {
        SingletonS2ContainerFactory.setConfigPath(configPath);
    }

    /**
     * 指定された画面定義XMLを読み込み、画面を表示します。<br />
     * 
     * @param templatePath 画面定義XMLのパス
     */
    public void openWindow(final String templatePath) {
        openWindow(templatePath, null);
    }

    /**
     * 指定された画面定義XMLを読み込み、引数を指定して画面を表示します。<br />
     * 
     * @param templatePath 画面定義XMLのパス
     * @param argument 引数オブジェクト
     */
    public void openWindow(final String templatePath, Object argument) {
        display = Display.getCurrent();
        if (display == null) {
            display = new Display();
        }
        try {
            setupImageManager();

            S2JFaceWindowManager windowManager = (S2JFaceWindowManager) container
                    .getComponent(S2JFaceWindowManager.class);
            windowManager.openModal(templatePath, argument);
        } finally {
            try {
                dispose();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected void initS2Container() {
        S2Container s2JFaceContainer = S2ContainerFactory
                .create(S2JFaceConstants.S2JFACE_DICON_PATH);
        String configPath = SingletonS2ContainerFactory.getConfigPath();
        container = S2ContainerFactory.create(configPath);
        container.include(s2JFaceContainer);

        container.init();
        SingletonS2ContainerFactory.setContainer(container);
    }

    /**
     * イメージ設定用リソースバンドル名を指定します。<br />
     * デフォルトでは、<code>s2JFaceImages</code> が使用されます。
     * 
     * @param imageBundleName リソースバンドル名
     */
    public void setImageBundleName(final String imageBundleName) {
        this.imageBundleName = imageBundleName;
    }

    protected void setupImageManager() {
        ImageManager.loadImages(imageBundleName);
    }

    protected void dispose() {
        ImageManager.dispose();
        if(display != null){
            display.dispose();
            display = null;
        }
    }
    
    /**
     * S2JFace のインスタンスを破棄します。
     */
    public static void destroy() {
        if(instance != null){
            instance.dispose();
            instance = null;
        }
    }
}
