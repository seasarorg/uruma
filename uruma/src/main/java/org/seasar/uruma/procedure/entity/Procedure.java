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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.seasar.uruma.util.AssertionUtil;

/**
 * プロシジャ定義を表すクラスです。<br />
 * 本クラスはプロシジャ実行定義ファイルの procedure 要素に対応します。<br />
 * 
 * @author y-komori
 */
public class Procedure {
    private String name;

    private Map<String, String> paramMap = new HashMap<String, String>();

    /**
     * 実行するコマンド名称を取得します。<br />
     * 
     * @return 実行するコマンド名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 実行するコマンド名称を設定します。<br />
     * 
     * @param name
     *            実行するコマンド名称
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * コマンドのパラメータを設定します。<br />
     * 
     * @param name
     *            パラメータ名称
     * @param value
     *            パラメータ値
     */
    public void setParam(final String name, final String value) {
        AssertionUtil.assertNotNull("name", name);
        AssertionUtil.assertNotNull("value", value);
        paramMap.put(name, value);
    }

    /**
     * コマンドのパラメータを取得します。<br />
     * 
     * @param name
     *            パラメータ名称
     * @return パラメータ値。パラメータが存在しない場合は <code>null</code>。
     */
    public String getParam(final String name) {
        return paramMap.get(name);
    }

    /**
     * パラメータ名の一覧を取得します。<br />
     * 
     * @return パラメータ名の {@link Set} オブジェクト。
     */
    public Set<String> getParamNames() {
        return Collections.unmodifiableSet(paramMap.keySet());
    }
}
