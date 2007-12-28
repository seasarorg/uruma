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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Uruma のための {@link BundleActivator} です。<br />
 * 
 * @author y-komori
 */
public class CoreActivator implements BundleActivator {

    public void start(final BundleContext context) throws Exception {
        System.err.println("UrumaCoreActivaterStart");
        context.addBundleListener(new UrumaBundleListener());
    }

    public void stop(final BundleContext context) throws Exception {
        System.err.println("UrumaCoreActivaterStop");
    }
}
