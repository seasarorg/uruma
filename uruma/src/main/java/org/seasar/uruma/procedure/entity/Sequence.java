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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.util.AssertionUtil;

/**
 * プロシジャの実行シーケンス情報を保持するクラスです。<br />
 * 本クラスはプロシジャ実行定義ファイルの sequence 要素に対応します。<br />
 * 
 * @author y-komori
 */
public class Sequence {
    private List<Procedure> procedures = new ArrayList<Procedure>();

    private List<String> ids = new ArrayList<String>();

    private EventListenerType type = EventListenerType.SELECTION;

    private ExecuteTiming timing;

    /**
     * プロシジャ実行対象 GUI コンポーネントの ID 一覧を取得します。<br />
     * 
     * @return プロシジャ実行対象 GUI コンポーネントの ID リスト
     */
    public List<String> getIds() {
        return Collections.unmodifiableList(ids);
    }

    /**
     * プロシジャ実行対象 GUI コンポーネントの ID を追加します。<br />
     * 
     * @param id
     *            プロシジャ実行対象 GUI コンポーネントの ID
     */
    public void addId(final String id) {
        AssertionUtil.assertNotNull("id", id);
        this.ids.add(id);
    }

    /**
     * プロシジャ実行対象 GUI コンポーネントの ID を削除します。<br />
     * 
     * @param id
     *            削除対象の ID
     */
    public void removeId(final String id) {
        AssertionUtil.assertNotNull("id", id);
        this.ids.remove(id);
    }

    /**
     * プロシジャ実行対象イベントの種類を取得します。<br />
     * 
     * @return プロシジャ実行対象イベントの種類
     */
    public EventListenerType getType() {
        return this.type;
    }

    /**
     * プロシジャ実行対象イベントの種類を設定します。<br />
     * 
     * @param type
     *            プロシジャ実行対象イベントの種類
     */
    public void setType(final EventListenerType type) {
        this.type = type;
    }

    /**
     * プロシジャ実行タイミングを取得します。<br />
     * 
     * @return プロシジャ実行タイミング
     */
    public ExecuteTiming getTiming() {
        return this.timing;
    }

    /**
     * プロシジャ実行タイミングを設定します。<br />
     * 
     * @param timing
     *            プロシジャ実行タイミング
     */
    public void setTiming(final ExecuteTiming timing) {
        this.timing = timing;
    }

    /**
     * プロシジャ定義を追加します。<br />
     * 
     * @param procedure
     *            プロシジャ定義
     */
    public void addProcedure(final Procedure procedure) {
        AssertionUtil.assertNotNull("procedure", procedure);
        procedures.add(procedure);
    }

    /**
     * プロシジャ定義を削除します。<br />
     * 
     * @param procedure
     *            プロシジャ定義
     */
    public void removeProcedure(final Procedure procedure) {
        AssertionUtil.assertNotNull("procedure", procedure);
        procedures.remove(procedure);
    }

    /**
     * プロシジャ定義の一覧を取得します。<br />
     * 
     * @return プロシジャ定義のリスト
     */
    public List<Procedure> getProcedures() {
        return Collections.unmodifiableList(procedures);
    }
}
