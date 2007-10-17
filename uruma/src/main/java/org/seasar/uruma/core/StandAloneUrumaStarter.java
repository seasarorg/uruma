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
package org.seasar.uruma.core;

import org.eclipse.swt.widgets.Display;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * RCP を利用せずに単独でウィンドウを開くアプリケーションのためのスタートアップクラスです。<br />
 * 
 * @author y-komori
 */
public class StandAloneUrumaStarter {
    private static StandAloneUrumaStarter instance;

    protected S2Container container;

    private Display display;

    private String imageBundleName = UrumaConstants.DEFAULT_IMAGE_BUNDLE_PATH;

    /**
     * アプリケーションを開始します。<br />
     * 
     * @param args
     *            コマンドライン引数
     */
    public static void main(final String[] args) {
        if (args.length >= 1) {
            String templatePath = args[0];
            StandAloneUrumaStarter starter = StandAloneUrumaStarter
                    .getInstance();
            starter.openWindow(templatePath);
        } else {
            System.err.println("[Error] 第1引数に初期画面のテンプレートパスを指定してください.");
        }
    }

    /**
     * 本クラスのインスタンスを取得します。<br />
     * 
     * @return 本クラスのインスタンス
     */
    public synchronized static StandAloneUrumaStarter getInstance() {
        if (instance == null) {
            instance = new StandAloneUrumaStarter();
        }
        return instance;
    }

    private StandAloneUrumaStarter() {
        initS2Container();
    }

    /**
     * dicon ファイルのパスを設定します。<br />
     * デフォルトでは、<code>app.dicon</code> が使用されます。<br />
     * 本メソッドは、 {@link StandAloneUrumaStarter#getInstance() getInstance()}
     * メソッドを最初に呼び出す前に呼び出してください。
     * 
     * @param configPath
     *            Dicon ファイルのパス
     */
    public static void setConfigPath(final String configPath) {
        SingletonS2ContainerFactory.setConfigPath(configPath);
    }

    /**
     * 指定された画面定義 XML を読み込み、画面を表示します。<br />
     * 
     * @param templatePath
     *            画面定義XMLのパス
     */
    public void openWindow(final String templatePath) {
        openWindow(templatePath, null);
    }

    /**
     * 指定された画面定義 XML を読み込み、引数を指定して画面を表示します。<br />
     * 
     * @param templatePath
     *            画面定義XMLのパス
     * @param argument
     *            引数オブジェクト
     */
    public void openWindow(final String templatePath, final Object argument) {
        display = Display.getCurrent();
        if (display == null) {
            display = new Display();
        }
        try {
            setupImageManager();

            UrumaWindowManager windowManager = (UrumaWindowManager) container
                    .getComponent(UrumaWindowManager.class);
            windowManager.openModal(templatePath, argument);
        } finally {
            try {
                dispose();
            } catch (Throwable ex) {
                // TODO 例外をログ出力する
                ex.printStackTrace();
            }
        }
    }

    protected void initS2Container() {
        S2Container urumaContainer = S2ContainerFactory
                .create(UrumaConstants.URUMA_DICON_PATH);
        String configPath = SingletonS2ContainerFactory.getConfigPath();
        container = S2ContainerFactory.create(configPath);
        container.include(urumaContainer);

        container.init();
        SingletonS2ContainerFactory.setContainer(container);
    }

    /**
     * イメージ設定用リソースバンドル名を指定します。<br />
     * デフォルトでは、 <code>urumaImages</code> が使用されます。
     * 
     * @param imageBundleName
     *            リソースバンドル名
     */
    public void setImageBundleName(final String imageBundleName) {
        this.imageBundleName = imageBundleName;
    }

    protected void setupImageManager() {
        ImageManager.loadImages(imageBundleName);
    }

    protected void dispose() {
        ImageManager.dispose();
        if (display != null) {
            display.dispose();
            display = null;
        }
    }

    /**
     * 本クラスのインスタンスを破棄します。
     */
    public static void destroy() {
        if (instance != null) {
            instance.dispose();
            instance = null;
        }
    }

}
