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
package org.seasar.uruma.ui.dialogs;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.util.MessageUtil;
import org.seasar.uruma.util.ShellUtil;

/**
 * メッセージダイアログを表示するためのユーティリティクラスです。<br />
 * 各ダイアログで表示するメッセージは {@link UrumaConstants#USER_MESSAGE_BASE} リソースバンドルから取得します。<br />
 * その際、タイトルは、<i>リソースキー</i><code>_TITLE</code> から、メッセージは <i>リソースキー</i><code>_MSG</code>
 * から取得します。<br />
 * 
 * @author y-komori
 */
public class UrumaMessageDialog {
    private static final String TITLE_SUFFIX = "_TITLE";

    private static final String MESSAGE_SUFFIX = "_MSG";

    private UrumaMessageDialog() {

    }

    /**
     * インフォメーションダイアログを表示します。<br />
     * 
     * @param key
     *            リソースキー
     */
    public static final void openInformation(final String key) {
        MessageDialog.openInformation(ShellUtil.getShell(), getTitle(key),
                getMessage(key));
    }

    /**
     * エラーダイアログを表示します。<br />
     * 
     * @param key
     *            リソースキー
     */
    public static final void openError(final String key) {
        MessageDialog.openError(ShellUtil.getShell(), getTitle(key),
                getMessage(key));
    }

    /**
     * 確認ダイアログを表示します。<br />
     * 
     * @param key
     *            リソースキー
     * @return OKボタンが押された場合、<code>true</code>。キャンセルボタンが押された場合、<code>false</code>
     */
    public static final boolean openConfirm(final String key) {
        return MessageDialog.openConfirm(ShellUtil.getShell(), getTitle(key),
                getMessage(key));
    }

    /**
     * 入力ダイアログを表示します。<br />
     * 
     * @param key
     *            リソースキー
     * @param initialText
     *            初期表示文字列
     * @return 入力結果。キャンセルされた場合は <code>null</code>
     */
    public static String openInput(final String key, final String initialText) {
        InputDialog dialog = new InputDialog(ShellUtil.getShell(),
                getTitle(key), getMessage(key), initialText, null);
        if (dialog.open() == Window.OK) {
            return dialog.getValue();
        } else {
            return null;
        }
    }

    /**
     * 入力ダイアログを表示します。<br />
     * 
     * @param key
     *            リソースキー
     * @return 入力結果。キャンセルされた場合は <code>null</code>
     */
    public static String openInput(final String key) {
        return openInput(key, UrumaConstants.NULL_STRING);
    }

    protected static String getTitle(final String bundleKey) {
        return MessageUtil.getMessage(bundleKey + TITLE_SUFFIX);
    }

    protected static String getMessage(final String bundleKey) {
        return MessageUtil.getMessage(bundleKey + MESSAGE_SUFFIX);
    }
}
