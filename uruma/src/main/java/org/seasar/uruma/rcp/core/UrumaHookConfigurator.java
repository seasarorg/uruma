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
package org.seasar.uruma.rcp.core;

import org.eclipse.osgi.baseadaptor.HookConfigurator;
import org.eclipse.osgi.baseadaptor.HookRegistry;
import org.seasar.uruma.core.UrumaConstants;

/**
 * OSGi フレームワークに対するフックの準備を行うためのクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaHookConfigurator implements HookConfigurator, UrumaConstants {
    private static boolean DEBUG;

    static {
        if (Boolean.getBoolean(URUMA_EXTENSION_DEBUG)) {
            DEBUG = true;
        }
    }

    /*
     * @see org.eclipse.osgi.baseadaptor.HookConfigurator#addHooks(org.eclipse.osgi.baseadaptor.HookRegistry)
     */
    public void addHooks(final HookRegistry hookRegistry) {
        if (DEBUG) {
            hookRegistry.addWatcher(new UrumaBundleWatcher());
        }
    }
}
