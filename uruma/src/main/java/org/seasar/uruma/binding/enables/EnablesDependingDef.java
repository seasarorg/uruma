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
package org.seasar.uruma.binding.enables;

import org.seasar.uruma.context.WidgetHandle;

/**
 * 他コンポーネントの選択状態によってイネーブル状態が変化するコントロールを表すクラスです。<br />
 * 
 * @author bskuroneko
 */
public class EnablesDependingDef {

    private WidgetHandle handle;

    private String targetId;

    private EnablesForType type;

    /**
     * {@link EnablesDependingDef} オブジェクトを構築します。<br />
     * <code>targetId</code> で指定した id を持つコンポーネントが、<code>type</code>
     * の表す状態になった場合、<code>handle</code> がイネーブルとなります。
     * 
     * @param handle
     *            イネーブル状態を変更するウィジットを保持する {@link WidgetHandle}
     * @param targetId
     *            監視するターゲットコンポーネントの id
     * @param type
     *            <code>handle</code> をイネーブルにする条件となるターゲットコンポーネントの状態
     */
    public EnablesDependingDef(final WidgetHandle handle,
            final String targetId, final EnablesForType type) {
        this.handle = handle;
        this.targetId = targetId;
        this.type = type;
    }

    /**
     * 依存先コンポーネントのIDを取得します。<br />
     * 
     * @return 依存先コンポーネントのID
     */
    public String getTargetId() {
        return this.targetId;
    }

    /**
     * イネーブル条件を取得します。<br />
     * 
     * @return イネーブル条件
     */
    public EnablesForType getType() {
        return this.type;
    }

    /**
     * {@link WidgetHandle} を取得します。<br />
     * 
     * @return {@link WidgetHandle} オブジェクト
     */
    public WidgetHandle getWidgetHandle() {
        return this.handle;
    }
}
