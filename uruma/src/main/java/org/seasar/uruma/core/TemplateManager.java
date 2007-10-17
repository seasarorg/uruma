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
package org.seasar.uruma.core;

import org.seasar.uruma.component.Template;

/**
 * 画面定義テンプレートを管理するためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface TemplateManager {
    /**
     * 指定されたパスの画面定義 XML を読み込み、{@link Template} オブジェクトを返します。<br />
     * 
     * @param path
     *            画面定義 XML のパス
     * @return {@link Template} オブジェクト
     */
    public Template getTemplate(final String path);
}
