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
package org.seasar.uruma.rcp.configuration.impl;

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.util.AssertionUtil;

/**
 * <code>command</code> 要素のための {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori <a
 *         href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_menus.html#e.command">command</a>
 */
public class MenuCommandElement extends AbstractConfigurationElementContainer {
    /**
     * 要素名です。<br />
     */
    public static final String ELEMENT_NAME = "command";

    /**
     * プッシュメニューを表すスタイル文字列です。<br />
     */
    public static final String STYLE_PUSH = "push";

    /**
     * ラジオメニューを表すスタイル文字列です。<br />
     */
    public static final String STYLE_RADIO = "radio";

    /**
     * トグルメニューを表すスタイル文字列です。<br />
     */
    public static final String STYLE_TOGGLE = "toggle";

    /**
     * プルダウンメニューを表すスタイル文字列です。<br />
     */
    public static final String STYLE_PULLDOWN = "pulldown";

    /**
     * 対応するコマンドの ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String commandId;

    /**
     * コマンド ID です。<br />
     */
    @ConfigurationAttribute
    public String id;

    /**
     * ニーモニックです。<br />
     */
    @ConfigurationAttribute
    public String mnemonic;

    /**
     * アイコンのパスです。<br />
     */
    @ConfigurationAttribute
    public String icon;

    /**
     * ディスエーブル状態のアイコンパスです。<br />
     */
    @ConfigurationAttribute
    public String disabledIcon;

    /**
     * ホバー状態のアイコンパスです。<br />
     */
    @ConfigurationAttribute
    public String hoverIcon;

    /**
     * ラベルです。<br />
     */
    @ConfigurationAttribute
    public String label;

    /**
     * ツールチップテキストです。<br />
     */
    @ConfigurationAttribute
    public String tooltip;

    /**
     * ヘルプコンテクスト ID です。<br />
     */
    @ConfigurationAttribute
    public String helpContextId;

    /**
     * スタイルです。<br />
     */
    @ConfigurationAttribute
    public String style;

    /**
     * {@link MenuCommandElement} を構築します。<br />
     * 
     * @param commandId
     *            コマンド ID
     */
    public MenuCommandElement(final String commandId) {
        super();
        AssertionUtil.assertNotNull("commandId", commandId);
        this.commandId = commandId;
    }
}
