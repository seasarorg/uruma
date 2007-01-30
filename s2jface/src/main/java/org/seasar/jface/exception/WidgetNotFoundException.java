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
package org.seasar.jface.exception;

/**
 * アノテーションで指定された id が画面定義XML上に存在しない場合に生成される例外です。<br />
 * 
 * @author y-komori
 */
public class WidgetNotFoundException extends S2JFaceRuntimeException {

    private static final long serialVersionUID = -2902562365702560574L;

    public static final String ERROR_CODE = "EJFC0105";

    public WidgetNotFoundException(String id, String className) {
        super(ERROR_CODE, id, className);
    }
}
