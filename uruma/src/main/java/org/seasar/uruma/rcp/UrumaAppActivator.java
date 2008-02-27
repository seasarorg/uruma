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
package org.seasar.uruma.rcp;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;

/**
 * Uruma アプリケーションのためのアクティベータです。<br />
 * 
 * @author y-komori
 */
public class UrumaAppActivator extends AbstractUIPlugin implements
        UrumaConstants, UrumaMessageCodes {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(UrumaAppActivator.class);

    /*
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public final void start(final BundleContext context) throws Exception {
        super.start(context);

        // ServiceReference ref = context.getServiceReference(UrumaService.class
        // .getName());
        //
        // if (ref != null) {
        // System.err.println("ServiceRef 取得成功!");
        //
        // UrumaService service = (UrumaService) context.getService(ref);
        // } else {
        // System.err.println("ServiceRef 取得失敗!");
        // }
    }

    /*
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public final void stop(final BundleContext context) throws Exception {
        super.stop(context);
    }
}
