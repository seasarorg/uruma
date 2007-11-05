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
 * ID の重複した画面定義テンプレートを読み込んだ際にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class DuplicateIdTemplateException extends UrumaRuntimeException {

    private static final long serialVersionUID = -6119117309034159506L;

    /**
     * {@link DuplicateIdTemplateException} を構築します。<br />
     * 
     * @param id
     *            ID
     * @param path
     *            テンプレートのパス
     */
    public DuplicateIdTemplateException(final String id, final String path) {
        super(UrumaMessageCodes.DUPLICATE_ID_TEMPLATE, id, path);
    }
}
