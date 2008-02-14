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
import org.seasar.uruma.component.jface.MenuItemComponent;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;

/**
 * <code>action</code> 要素のための {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori
 * @see <a
 *      href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_actionSets.html">ActionSets</a>
 */
public class ActionElement extends AbstractConfigurationElement {

    /**
     * {@link ActionElement} を構築します。<br />
     */
    public ActionElement(final MenuItemComponent menuItem) {
        super();
        id = menuItem.getId();
        label = menuItem.getText();
        icon = menuItem.getImage();
        disabledIcon = menuItem.disabledImage;
        hoverIcon = menuItem.hoverImage;
        tooltip = menuItem.description;
    }

    /**
     * ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String id;

    /**
     * 表示ラベルです。<br />
     */
    @ConfigurationAttribute(required = true)
    public String label;

    /**
     * 関連付けられたコマンドの ID です。<br />
     */
    @ConfigurationAttribute
    public String definitionId;

    /**
     * メニューバーの中のパスです。<br />
     */
    @ConfigurationAttribute
    public String menubarPath;

    /**
     * ツールバーの中のパスです。<br />
     */
    @ConfigurationAttribute
    public String toolbarPath;

    /**
     * アイコンのパスです。<br />
     */
    @ConfigurationAttribute
    public String icon;

    /**
     * ディスエーブル時のアイコンのパスです。<br />
     */
    @ConfigurationAttribute
    public String disabledIcon;

    /**
     * ホバー時のアイコンのパスです。<br />
     */
    @ConfigurationAttribute
    public String hoverIcon;

    /**
     * ツールチップ表示内容です。<br />
     */
    @ConfigurationAttribute
    public String tooltip;

    /**
     * ヘルプコンテクスト ID です。<br />
     */
    @ConfigurationAttribute
    public String helpContextId;

    /**
     * アクションのスタイルです。<br />
     * <code>push</code>、<code>radio</code>、<code>toggle</code>、<code>pulldown</code>
     * のいずれかを指定します。<br />
     * デフォルトは <code>push</code> です。<br />
     */
    @ConfigurationAttribute
    public String style = "push";

    /**
     * {@link #style} 属性が <code>radio</code> または <code>toggle</code>
     * の場合の初期状態です。<br />
     */
    @ConfigurationAttribute
    public boolean state;

    /**
     * アクションに対応するクラス名です。<br />
     */
    @ConfigurationAttribute(name = "class")
    public String clazz;

    /**
     * リターゲット・アクションにするかどうかを指定します。<br />
     */
    @ConfigurationAttribute
    public boolean retarget;

    /**
     * {@link #retarget} 属性が <code>true</code> の場合に、ラベルの更新を許可するかどうかを指定します。<br />
     */
    @ConfigurationAttribute
    public boolean allowLabelUpdate;

    /**
     * アクションを有効にするための条件を指定します。<br />
     */
    @ConfigurationAttribute
    public String enablesFor;

}
