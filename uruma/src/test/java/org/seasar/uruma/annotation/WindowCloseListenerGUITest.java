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
package org.seasar.uruma.annotation;

import org.eclipse.jface.dialogs.MessageDialog;
import org.seasar.uruma.binding.method.WindowCloseListener;
import org.seasar.uruma.renderer.impl.AbstractGUITest;
import org.seasar.uruma.util.ShellUtil;

/**
 * {@link WindowCloseListener} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class WindowCloseListenerGUITest extends AbstractGUITest {

    @EventListener(id = "window", type = EventListenerType.WINDOW_CLOSING)
    public boolean handleClose() {
        return MessageDialog.openConfirm(ShellUtil.getShell(), "Confirm",
                "Are you okay?");
    }

    @EventListener(id = "shell", type = EventListenerType.CLOSE)
    public void onClose() {
        result = true;
    }
}
