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
package org.seasar.uruma.util;

import org.eclipse.swt.widgets.Shell;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.UrumaActivator;

/**
 * {@link Shell} に関するユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class ShellUtil {
    public static Shell getShell() {
        UrumaActivator activator = UrumaActivator.getInstance();
        if (activator != null) {
            return activator.getWorkbench().getActiveWorkbenchWindow()
                    .getShell();
        } else {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            ApplicationContext context = (ApplicationContext) container
                    .getComponent(ApplicationContext.class);
            WindowContext wContext = context.getWindowContexts().iterator()
                    .next();
            WidgetHandle handle = wContext
                    .getWidgetHandle(UrumaConstants.SHELL_CID);
            return handle.<Shell> getCastWidget();
        }
    }
}
