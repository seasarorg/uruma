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

import org.seasar.uruma.annotation.ApplicationContext;
import org.seasar.uruma.ui.UrumaApplicationWindow;

/**
 * ウィンドウを管理するクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 * @author bskuroneko
 */
public interface UrumaWindowManager {
    /**
     * 新しいウィンドウを開きます。<br />
     * 
     * @param templatePath
     *            テンプレートパス
     * @param modal
     *            モーダル属性
     * @return 生成したウィンドウ
     */
    public UrumaApplicationWindow openWindow(final String templatePath,
            final boolean modal);

    /**
     * 指定されたパスの画面定義 XML を読み込み、ダイアログを開きます。<br />
     * ダイアログをオープンする前に、<code>parentAction</code> から {@link ApplicationContext}
     * へ値がエクスポートされます。<br />
     * 
     * @param templatePath
     *            画面定義 XML のパス
     * @param parentAction
     *            呼び出し元アクションクラスの
     * @return リターンコード
     */
    public int openDialog(String templatePath, Object parentAction);

    /**
     * <code>windowId</code> で指定されたIDのウィンドウを閉じます。<br />
     * 
     * @param windowId
     *            ウィンドウのID
     */
    public void close(String windowId);
}
