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

import org.seasar.uruma.util.AssertionUtil;

/**
 * 一つの画面に対応するプロシジャ定義を表すクラスです。<br />
 * 本クラスはプロシジャ実行定義ファイルの procedures 要素に対応します。<br />
 * 
 * @author y-komori
 */
public class Procedures {
    private List<Sequence> sequences = new ArrayList<Sequence>();

    /**
     * プロシジャシーケンスを追加します。<br />
     * 
     * @param sequence
     *            プロシジャシーケンス
     */
    public void addSequence(final Sequence sequence) {
        AssertionUtil.assertNotNull("sequence", sequence);
        sequences.add(sequence);
    }

    /**
     * プロシジャシーケンスを削除します。<br />
     * 
     * @param sequence
     *            削除対象のプロシジャシーケンス
     */
    public void removeSequence(final Sequence sequence) {
        AssertionUtil.assertNotNull("sequence", sequence);
        sequences.remove(sequence);
    }

    /**
     * プロシジャシーケンスの一覧を取得します。<br />
     * 
     * @return プロシジャシーケンスの一覧
     */
    public List<Sequence> getSequences() {
        return Collections.unmodifiableList(sequences);
    }
}
