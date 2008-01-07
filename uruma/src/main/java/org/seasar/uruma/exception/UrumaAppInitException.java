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
package org.seasar.uruma.exception;

import org.osgi.framework.Bundle;
import org.seasar.uruma.core.UrumaMessageCodes;

/**
 * Uruma アプリケーション初期化失敗時にスローされる例外です。
 * 
 * @author y-komori
 */
public class UrumaAppInitException extends UrumaRuntimeException implements
        UrumaMessageCodes {

    private static final long serialVersionUID = -3995331336671147648L;

    /**
     * {@link UrumaAppInitException} を構築します。<br />
     * 
     * @param bundle
     *            対象 {@link Bundle} オブジェクト
     */
    public UrumaAppInitException(final Bundle bundle) {
        super(URUMA_APP_STARTING_FAILED, bundle.getSymbolicName());
    }

    /**
     * {@link UrumaAppInitException} を構築します。<br />
     * 
     * @param bundle
     *            対象 {@link Bundle} オブジェクト
     * @param detail
     *            詳細原因
     */
    public UrumaAppInitException(final Bundle bundle, final String detail) {
        super(URUMA_APP_STARTING_FAILED, bundle.getSymbolicName(), detail);
    }

    /**
     * {@link UrumaAppInitException} を構築します。<br />
     * 
     * @param bundle
     *            対象 {@link Bundle} オブジェクト
     * @param cause
     *            原因となった例外オブジェクト
     * @param detail
     *            詳細原因
     */
    public UrumaAppInitException(final Bundle bundle, final Throwable cause,
            final String detail) {
        super(URUMA_APP_STARTING_FAILED, cause, bundle.getSymbolicName(),
                detail);
    }
}
