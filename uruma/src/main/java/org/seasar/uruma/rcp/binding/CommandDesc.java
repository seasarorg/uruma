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
package org.seasar.uruma.rcp.binding;

import org.seasar.uruma.util.AssertionUtil;

/**
 * コマンドの定義情報を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class CommandDesc {
    private String commandId;

    private String urumaId;

    /**
     * {@link CommandDesc} を構築します。<br />
     * 
     * @param commandId
     *            コマンド ID
     */
    public CommandDesc(final String commandId) {
        AssertionUtil.assertNotEmpty("commandId", commandId);
        this.commandId = commandId;
    }

    /**
     * {@link CommandDesc} を構築します。<br />
     * 
     * @param commandId
     *            コマンド ID
     * @param urumaId
     *            対応する Uruma 画面コンポーネントの ID
     */
    public CommandDesc(final String commandId, final String urumaId) {
        this(commandId);
        AssertionUtil.assertNotEmpty("urumaId", urumaId);
        this.urumaId = urumaId;
    }

    /**
     * コマンドID を返します。<br />
     * 
     * @return コマンドID
     */
    public String getCommandId() {
        return this.commandId;
    }

    /**
     * コマンドに対応する Uruma 画面コンポーネントの ID を返します。<br />
     * 
     * @return 画面コンポーネントの ID
     */
    public String getUrumaId() {
        return this.urumaId;
    }

    /**
     * コマンドに対応する Uruma 画面コンポーネントの ID を設定します。<br />
     * 
     * @param urumaId
     *            画面コンポーネントの ID
     */
    public void setUrumaId(final String urumaId) {
        this.urumaId = urumaId;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.commandId;
    }
}
