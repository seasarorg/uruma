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

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

/**
 * @author y-komori
 * 
 */
public class UrumaBundleListener implements BundleListener {

    /*
     * @see org.osgi.framework.BundleListener#bundleChanged(org.osgi.framework.BundleEvent)
     */
    public void bundleChanged(final BundleEvent event) {
        int eventType = event.getType();

        switch (eventType) {
        case BundleEvent.RESOLVED:
            resolvedBundle(event);
            break;
        case BundleEvent.STARTING:
            startingBundle(event);
            break;
        case BundleEvent.STARTED:
            startedBundle(event);
            break;
        case BundleEvent.STOPPING:
            stoppingBundle(event);
            break;
        case BundleEvent.STOPPED:
            stoppedBundle(event);
            break;
        default:
        }
    }

    protected void resolvedBundle(final BundleEvent event) {
        System.err.println("Resolved : " + event.getBundle().getSymbolicName());
    }

    protected void startingBundle(final BundleEvent event) {
        System.err.println("Starting : " + event.getBundle().getSymbolicName());
    }

    protected void startedBundle(final BundleEvent event) {
        System.err.println("Started : " + event.getBundle().getSymbolicName());
    }

    protected void stoppingBundle(final BundleEvent event) {
        // Do nothing.
    }

    protected void stoppedBundle(final BundleEvent event) {
        // Do nothing.
    }
}
