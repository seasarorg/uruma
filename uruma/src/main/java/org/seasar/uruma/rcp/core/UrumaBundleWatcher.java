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

import org.eclipse.osgi.framework.adaptor.BundleWatcher;
import org.osgi.framework.Bundle;

/**
 * バンドルのライフサイクルを監視するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaBundleWatcher implements BundleWatcher {
    // private static final UrumaLogger logger = UrumaLogger
    // .getLogger(UrumaBundleWatcher.class);

    public void watchBundle(final Bundle bundle, final int type) {
        switch (type) {
        case START_INSTALLING:
            System.err.println("StartInstalling : " + bundle.getSymbolicName());
            // logger.info("StartInstalling : " + bundle.getSymbolicName());
            break;
        case END_INSTALLING:
            System.err.println("EndInstalling : " + bundle.getSymbolicName());
            // logger.info("EndInstalling : " + bundle.getSymbolicName());
            break;
        case START_ACTIVATION:
            System.err.println("StartActivation : " + bundle.getSymbolicName());
            // logger.info("StartActivation : " + bundle.getSymbolicName());
            break;
        case END_ACTIVATION:
            System.err.println("EndActivation : " + bundle.getSymbolicName());
            // logger.info("EndActivation : " + bundle.getSymbolicName());
            break;
        default:
            break;
        }
    }
}
