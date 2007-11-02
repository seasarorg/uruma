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
package org.seasar.uruma.exception;

import org.seasar.uruma.core.UrumaMessageCodes;

/**
 * XML 定義上でコンポーネント ID が重複定義されたときにスローされる例外です。</br>
 * 
 * @author y-komori
 */
public class DuplicateComponentIdException extends UrumaRuntimeException {

    private static final long serialVersionUID = -4312621272322050182L;

    /**
     * {@link DuplicateComponentIdException} を構築します。<br />
     * 
     * @param duplicatedId
     *            重複したコンポーネント ID
     */
    public DuplicateComponentIdException(final String duplicatedId) {
        super(UrumaMessageCodes.DUPLICATE_COMPONENT_ID, duplicatedId);
    }
}
