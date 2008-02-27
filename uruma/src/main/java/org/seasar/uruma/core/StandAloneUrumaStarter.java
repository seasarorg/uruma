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

import java.util.MissingResourceException;

import org.eclipse.swt.widgets.Display;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.uruma.log.UrumaLogger;

/**
 * RCP を利用せずに単独でウィンドウを開くアプリケーションのためのスタートアップクラスです。<br />
 * 
 * @author y-komori
 */
public class StandAloneUrumaStarter implements UrumaMessageCodes,
        UrumaConstants {
    private final UrumaLogger logger = UrumaLogger
            .getLogger(StandAloneUrumaStarter.class);

    private static StandAloneUrumaStarter instance;

    protected S2Container container;

    private Display display;

    private String imageBundleName = DEFAULT_IMAGE_BUNDLE_PATH;

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
        logger.log(STAND_ALONE_URUMA_STARTER_INIT);
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
        display = Display.getCurrent();
        if (display == null) {
            display = new Display();
            setupImageManager(display);
        }
        try {

            UrumaWindowManager windowManager = (UrumaWindowManager) container
                    .getComponent(UrumaWindowManager.class);
            windowManager.openWindow(templatePath, true);
        } finally {
            try {
                dispose();
            } catch (Throwable ex) {
                logger.log(EXCEPTION_OCCURED_WITH_REASON, ex.getMessage());
            }
        }
    }

    protected void initS2Container() {
        S2Container urumaContainer = S2ContainerFactory
                .create(UrumaConstants.URUMA_DICON_PATH);
        String configPath = SingletonS2ContainerFactory.getConfigPath();

        try {
            container = S2ContainerFactory.create(configPath);
        } catch (ResourceNotFoundRuntimeException ex) {
            logger.log(DICON_FILE_NOT_FOUND, configPath);
            container = S2ContainerFactory.create();
        }
        container.include(urumaContainer);

        container.init();
        SingletonS2ContainerFactory.setContainer(container);

        ComponentUtil.setS2Container(container);
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

    protected void setupImageManager(final Display display) {
        ImageManager.init(display);
        logger.log(LOADING_IMAGE_BUNDLE, imageBundleName);

        try {
            ImageManager.loadImages(imageBundleName);
        } catch (MissingResourceException ex) {
            logger.log(IMAGE_DEF_BUNDLE_NOT_FOUND, imageBundleName);
        }
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
            instance.logger.log(STAND_ALONE_URUMA_STARTER_STOP);
            instance.dispose();
            instance = null;
        }
    }
}
