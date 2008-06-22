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
package org.seasar.uruma.jobs.impl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.seasar.uruma.jobs.ProgressMonitor;

/**
 * RCP 環境における {@link ProgressMonitor} の実装クラスです。<br />
 * 本クラスは {@link IProgressMonitor} をラップします。
 * 
 * @author y-komori
 */
public class RcpProgressMonitor implements ProgressMonitor {
    private IProgressMonitor monitor;

    /**
     * {@link RcpProgressMonitor} を構築します。<br />
     * 
     * @param monitor
     *            {@link IProgressMonitor} オブジェクト
     */
    public RcpProgressMonitor(final IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    /*
     * @see org.seasar.uruma.jobs.ProgressMonitor#beginTask(java.lang.String,
     *      int)
     */
    public void beginTask(final String name, final int totalWork) {
        monitor.beginTask(name, totalWork);
    }

    /*
     * @see org.seasar.uruma.jobs.ProgressMonitor#done()
     */
    public void done() {
        monitor.done();
    }

    /*
     * @see org.seasar.uruma.jobs.ProgressMonitor#isCanceled()
     */
    public boolean isCanceled() {
        return monitor.isCanceled();
    }

    /*
     * @see org.seasar.uruma.jobs.ProgressMonitor#setCanceled(boolean)
     */
    public void setCanceled(final boolean value) {
        monitor.setCanceled(value);
    }

    /*
     * @see org.seasar.uruma.jobs.ProgressMonitor#setTaskName(java.lang.String)
     */
    public void setTaskName(final String name) {
        monitor.setTaskName(name);
    }

    /*
     * @see org.seasar.uruma.jobs.ProgressMonitor#subTask(java.lang.String)
     */
    public void subTask(final String name) {
        monitor.setTaskName(name);
    }

    /*
     * @see org.seasar.uruma.jobs.ProgressMonitor#worked(int)
     */
    public void worked(final int work) {
        monitor.worked(work);
    }

}
