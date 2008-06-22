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
package org.seasar.uruma.binding.method;

import java.lang.reflect.Method;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.seasar.framework.util.MethodUtil;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.desc.PartActionDescFactory;
import org.seasar.uruma.jobs.ProgressMonitor;
import org.seasar.uruma.jobs.impl.RcpProgressMonitor;
import org.seasar.uruma.log.UrumaLogger;

/**
 * メソッドを非同期に実行するための {@link MethodBinding} です。<br />
 * 本メソッドでは、実行対象オブジェクトに {@link ProgressMonitor} 型のプロパティが存在する場合、
 * {@link ProgressMonitor} オブジェクトをインジェクションします。<br />
 * また、{@link Display#getCurrent()} によって {@link Display} オブジェクトが取得できる場合には、
 * {@link MethodCallback#callback(MethodBinding, Object[], Object)} メソッドの呼び出しを
 * UI スレッド側で実行します。
 * 
 * @author y-komori
 */
public class AsyncMethodBinding extends MethodBinding implements
        UrumaMessageCodes {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(AsyncMethodBinding.class);

    /**
     * {@link AsyncMethodBinding} を構築します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param method
     *            ターゲットメソッド
     * @param callback
     *            コールバックオブジェクト
     */
    AsyncMethodBinding(final Object target, final Method method,
            final MethodCallback callback) {
        super(target, method, callback);
    }

    /*
     * @see org.seasar.uruma.binding.method.MethodBinding#invoke(java.lang.Object[])
     */
    @Override
    public Object invoke(final Object[] args) {
        Object[] filteredArgs = args;
        for (ArgumentsFilter filter : argumentsFilterList) {
            filteredArgs = filter.filter(filteredArgs);
        }

        AsyncJob job = new AsyncJob(this, filteredArgs, Display.getCurrent());
        job.setUser(true);
        job.schedule();

        if (logger.isInfoEnabled()) {
            logger.log(ASYNC_METHOD_SCHEDULED, UrumaLogger
                    .getObjectDescription(target), method.getName(), args);
        }

        return null;
    }

    protected void injectProgressMonitor(final Object target,
            final IProgressMonitor monitor) {
        PartActionDesc desc = PartActionDescFactory.getPartActionDesc(target
                .getClass());

        ProgressMonitor uMonitor = new RcpProgressMonitor(monitor);
        desc.injectProgressMonitor(target, uMonitor);
    }

    class AsyncJob extends Job {
        private MethodBinding binding;

        private Object[] args;

        private Object returnValue;

        private Display display;

        AsyncJob(final MethodBinding binding, final Object[] args,
                final Display display) {
            super("");
            this.binding = binding;
            this.args = args;
            this.display = display;
        }

        /*
         * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            if (logger.isInfoEnabled()) {
                logger.log(ASYNC_METHOD_START, UrumaLogger
                        .getObjectDescription(target), method.getName(), args);
            }

            try {
                injectProgressMonitor(target, monitor);
                returnValue = MethodUtil.invoke(binding.getMethod(), binding
                        .getTarget(), args);

                if (callback != null) {
                    if (display != null) {
                        display.asyncExec(new Runnable() {
                            public void run() {
                                returnValue = callback.callback(binding, args,
                                        returnValue);
                            }
                        });
                    } else {
                        returnValue = callback.callback(binding, args,
                                returnValue);
                    }
                }
            } catch (Throwable ex) {
                logger.log(EXCEPTION_OCCURED_INVOKING_METHOD, ex, binding
                        .toString());
            } finally {
                if (logger.isInfoEnabled()) {
                    String desc = UrumaLogger.getObjectDescription(target);
                    if (!monitor.isCanceled()) {
                        logger.log(ASYNC_METHOD_END, desc, method.getName(),
                                returnValue);
                    } else {
                        logger.log(ASYNC_METHOD_CANCELED, desc, method
                                .getName(), returnValue);
                    }
                }
            }

            if (!monitor.isCanceled()) {
                return Status.OK_STATUS;
            } else {
                return Status.CANCEL_STATUS;
            }
        }
    }
}
