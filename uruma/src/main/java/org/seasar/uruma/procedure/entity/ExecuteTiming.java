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
package org.seasar.uruma.procedure.entity;

import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.annotation.PostOpenMethod;

/**
 * プロシジャ実行タイミングを表す列挙型です。<br />
 * 
 * @author y-komori
 */
public enum ExecuteTiming {
    /**
     * type 属性で指定された GUI イベント発生時に実行します。<br />
     */
    EVENT,

    /**
     * {@link InitializeMethod} のタイミングで実行します。<br />
     */
    INITIALIZE,

    /**
     * {@link PostOpenMethod} のタイミングで実行します。<br />
     */
    POST_OPEN,

    /**
     * {@link PreCloseMethod} のタイミングで実行します。<br />
     */
    PRE_CLOSE;
}
