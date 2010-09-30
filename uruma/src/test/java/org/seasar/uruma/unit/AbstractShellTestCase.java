/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package org.seasar.uruma.unit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * {@link SWT} の {@link Display} や {@link Shell} オブジェクトを使用するテストケースのための基底クラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class AbstractShellTestCase {
    protected Display display;

    protected Shell shell;

    private boolean useShell;

    protected AbstractShellTestCase() {
        this(false);
    }

    protected AbstractShellTestCase(final boolean useShell) {
        super();
        this.useShell = useShell;
    }

    protected void before() throws Exception {
        display = Display.getCurrent();
        if (display == null) {
            display = new Display();
        }

        if (useShell) {
            shell = display.getActiveShell();
            if (shell == null) {
                shell = new Shell(display);
            }
        }
    }

    protected void after() throws Exception {
        if (useShell) {
            if (shell != null) {
                shell.dispose();
            }
        }

        if (display != null) {
            display.dispose();
        }
    }
}
