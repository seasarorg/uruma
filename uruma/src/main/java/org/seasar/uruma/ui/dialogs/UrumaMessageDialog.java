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

import org.eclipse.jface.dialogs.MessageDialog;
import org.seasar.uruma.util.ShellUtil;

/**
 * メッセージダイアログを表示するためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaMessageDialog {
    private UrumaMessageDialog() {

    }

    public static final void openInformation(final String title,
            final String message) {
        MessageDialog.openInformation(ShellUtil.getShell(), title, message);
    }

    public static final void openError(final String title, final String message) {
        MessageDialog.openError(ShellUtil.getShell(), title, message);
    }

    public static final boolean openConfirm(final String title,
            final String message) {
        return MessageDialog.openConfirm(ShellUtil.getShell(), title, message);
    }
}
