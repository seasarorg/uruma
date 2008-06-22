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

/**
 * メソッドの終了を通知するためのインタフェースです。<br />
 * 
 * @author y-komori
 */
public interface MethodCallback {
    /**
     * メソッドの終了を通知します。<br />
     * 
     * @param binding
     *            呼び出された {@link MethodBinding}
     * @param args
     *            呼び出されたメソッドに渡された引数
     * @return 通知先からの戻り値
     */
    public Object callback(MethodBinding binding, Object[] args);
}
