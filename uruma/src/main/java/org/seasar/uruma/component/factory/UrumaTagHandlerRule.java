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
package org.seasar.uruma.component.factory;

import org.seasar.framework.xml.TagHandlerRule;

/**
 * Uruma の画面定義XMLをパースするためのタグハンドラを保持するインタフェースです。<br />
 * 
 * @author y-komori
 */
public interface UrumaTagHandlerRule {
    /**
     * タグハンドラを追加します。<br />
     * 
     * @param tagHandler
     *            タグハンドラ
     */
    public void addTagHandler(UrumaTagHandler tagHandler);

    /**
     * タグハンドラを追加します。<br />
     * 
     * @param path
     *            パス
     * @param tagHandler
     *            タグハンドラ
     */
    public void addTagHandler(String path, UrumaTagHandler tagHandler);

    /**
     * {@link TagHandlerRule} オブジェクトを返します。<br />
     * 
     * @return {@link TagHandlerRule} オブジェクト
     */
    public TagHandlerRule getTagHandlerRule();
}
