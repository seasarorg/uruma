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
package org.seasar.uruma.component.factory.desc;

/**
 * GUI コンポーネントを管理するためのインタフェースです。<br />
 * 
 * @author y-komori
 */
public interface ComponentRegistry {
    /**
     * コンポーネント定義ファイルの拡張子( {@value} )です。<br />
     * 拡張子は Uruma Component Library Descriptor の略です。
     */
    public static final String DESC_EXT = "ucld";

    /**
     * コンテクストクラスローダからコンポーネント定義ファイル(UCLDファイル)を読み込み、登録します。<br />
     */
    public void registComponents();

    /**
     * 指定されたクラスローダからコンポーネント定義ファイル(UCLDファイル)を読み込み、登録します。<br />
     * /components ディレクトリ配下を再帰的に検索し、見つかった拡張子 ucld のファイルを読み込みます。
     * 
     * @param loader
     *        クラスローダ
     */
    public void registComponents(final ClassLoader loader);
}
