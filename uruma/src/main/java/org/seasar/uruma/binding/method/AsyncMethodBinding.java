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
import org.eclipse.core.runtime.jobs.Job;
import org.seasar.framework.util.MethodUtil;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;

/**
 * メソッドを非同期に実行するための {@link MethodBinding} です。<br />
 * 
 * @author y-komori
 */
public class AsyncMethodBinding extends MethodBinding {
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
        if (logger.isInfoEnabled()) {
            logger.log(UrumaMessageCodes.START_METHOD_CALL, UrumaLogger
                    .getObjectDescription(target), method.getName(), args);
        }

        Object[] filteredArgs = args;
        for (ArgumentsFilter filter : argumentsFilterList) {
            filteredArgs = filter.filter(filteredArgs);
        }

        Object result = MethodUtil.invoke(method, target, filteredArgs);

        if (logger.isInfoEnabled()) {
            logger.log(UrumaMessageCodes.END_METHOD_CALL, UrumaLogger
                    .getObjectDescription(target), method.getName(), result);
        }

        return result;
    }

    class AsyncJob extends Job {
        public AsyncJob() {
            super("");
        }

        public AsyncJob(final String name) {
            super(name);
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            // TODO 自動生成されたメソッド・スタブ
            return null;
        }
    }
}
