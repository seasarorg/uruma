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
package org.seasar.uruma.rcp.configuration;

import java.io.Writer;

/**
 * {@link ConfigurationElement} の内容を コンフィグレーション XML に書き出すためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ConfigurationWriter {
    /**
     * 開始タグを出力します。<br />
     * 
     * @param element
     *            要素名
     * @param writer
     *            出力先 {@link Writer} オブジェクト
     */
    public void writeStartTag(ConfigurationElement element, Writer writer);

    /**
     * インデント付きで開始タグを出力します。<br />
     * 
     * @param element
     *            要素名
     * @param writer
     *            出力先 {@link Writer} オブジェクト
     * @param level
     *            インデントレベル
     */
    public void writeStartTag(ConfigurationElement element, Writer writer,
            int level);

    /**
     * 終了タグを出力します。<br />
     * 
     * @param element
     *            要素名
     * @param writer
     *            出力先 {@link Writer} オブジェクト
     */
    public void writeEndTag(ConfigurationElement element, Writer writer);

    /**
     * インデント付きで終了タグを出力します。<br />
     * 
     * @param element
     *            要素名
     * @param writer
     *            出力先 {@link Writer} オブジェクト
     * @param level
     *            インデントレベル
     */
    public void writeEndTag(ConfigurationElement element, Writer writer,
            int level);

    /**
     * {@link ConfigurationWriter} がサポートする {@link ConfigurationElement}
     * のクラスオブジェクトを返します。<br />
     * 
     * @return {@link ConfigurationElement} サブクラスの {@link Class} オブジェクト
     */
    public Class<? extends ConfigurationElement> getSupportType();
}
