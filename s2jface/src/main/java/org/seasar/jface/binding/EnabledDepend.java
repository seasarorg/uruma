/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.binding;

import org.eclipse.swt.widgets.Widget;

/**
 * 他のウィジットのイベントに応じてイベントのイネーブル状態を変更するウィジットを表すクラスです。<br />
 * 
 * @author bskuroneko
 */
public class EnabledDepend {

    private Widget widget;

    private String targetId;

    private EnabledDependType type;

    /**
     * {@link EnabledDepend} オブジェクトを構築します。<br />
     * <code>targetId</code> で指定した id を持つコンポーネントが、<code>type</code>
     * の表す状態になった場合、<code>widget</code> がイネーブルとなります。
     * 
     * @param widget
     *            イネーブル状態を変更するウィジット
     * @param targetId
     *            監視するターゲットコンポーネントの id
     * @param type
     *            <code>widget</code> をイネーブルにする条件となるターゲットコンポーネントの状態
     */
    public EnabledDepend(Widget widget, String targetId, EnabledDependType type) {
        this.widget = widget;
        this.targetId = targetId;
        this.type = type;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public EnabledDependType getType() {
        return this.type;
    }

    public Widget getWidget() {
        return this.widget;
    }
}
