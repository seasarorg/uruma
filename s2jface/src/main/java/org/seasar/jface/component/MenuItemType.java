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
package org.seasar.jface.component;

import org.eclipse.jface.action.IAction;
import org.seasar.framework.util.StringUtil;

/**
 * メニュー項目の種類を表す列挙型です。<br />
 * 
 * @author y-komori
 */
public enum MenuItemType {
    MENU, ACTION, SEPARATOR, RADIO, CHECK, NONE;

    public static final String MENU_STRING = "menu";

    public static final String ACTION_STRING = "action";

    public static final String SEPARATOR_STRING = "separator";

    public static final String RADIO_STRING = "radio";

    public static final String CHECK_STRING = "check";

    /**
     * 文字列から <code>MenuItemType</code> の値を返します。<br />
     * <p>
     * 文字列は大文字、小文字のどちらでも構いません。
     * </p>
     * 
     * @param name
     *            文字列
     * @return <code>MenuItemType</code> の値
     */
    public static MenuItemType getMenuItemType(String name) {
        if (StringUtil.equalsIgnoreCase(name, MENU_STRING)) {
            return MENU;
        } else if (StringUtil.equalsIgnoreCase(name, ACTION_STRING)) {
            return ACTION;
        } else if (StringUtil.equalsIgnoreCase(name, SEPARATOR_STRING)) {
            return SEPARATOR;
        } else if (StringUtil.equalsIgnoreCase(name, RADIO_STRING)) {
            return RADIO;
        } else if (StringUtil.equalsIgnoreCase(name, CHECK_STRING)) {
            return CHECK;
        }
        return NONE;
    }

    /**
     * <code>MenuItemType</code> の値にしたがって、対応する <code>IAction</code> の
     * <code>style</code> 値を返します。
     * 
     * @param type
     *            <code>MenuItemType</code>
     * @return <code>IAction</code> の <code>style</code> 値
     */
    public static int getActionStyle(MenuItemType type) {
        switch (type) {
        case MENU:
            return IAction.AS_DROP_DOWN_MENU;

        case ACTION:
            return IAction.AS_PUSH_BUTTON;

        case RADIO:
            return IAction.AS_RADIO_BUTTON;

        case CHECK:
            return IAction.AS_CHECK_BOX;

        default:
            return IAction.AS_UNSPECIFIED;
        }
    }
}
