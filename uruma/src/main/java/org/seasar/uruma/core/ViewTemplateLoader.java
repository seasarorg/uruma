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
package org.seasar.uruma.core;

import java.io.IOException;

/**
 * ビューテンプレートの一括登録を行うためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ViewTemplateLoader {
    /**
     * ビューを定義したテンプレートの一括登録を実行します。<br />
     * 
     * @throws IOException
     *             テンプレートの読み込みに失敗した場合
     */
    public void loadViewTemplates() throws IOException;
}
