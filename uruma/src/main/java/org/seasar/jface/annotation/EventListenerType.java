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
package org.seasar.jface.annotation;

import java.util.StringTokenizer;

import org.seasar.eclipse.common.util.SWTUtil;
import org.seasar.framework.util.StringUtil;

/**
 * @author y-komori
 * @author bskuroneko
 * 
 */
public enum EventListenerType {

    // Key
    KEY_DOWN, KEY_UP,

    // Mouse
    MOUSE_DOWN, MOUSE_UP, MOUSE_MOVE, MOUSE_ENTER, MOUSE_EXIT, MOUSE_DOUBLE_CLICK, MOUSE_HOVER,

    // Paint
    PAINT,

    // Controll
    MOVE, RESIZE,

    // Dispose
    DISPOSE,

    // Selection
    SELECTION, DEFAULT_SELECTION,

    // Focus
    FOCUS_IN, FOCUS_OUT,

    // Tree
    EXPAND, COLLAPSE,

    // Shell
    ICONIFY, DEICONIFY, CLOSE, ACTIVATE, DEACTIVATE,

    // Menu or Controll's visible change
    SHOW, HIDE,

    // Modify
    MODIFY,

    // Verify
    VERIFY,

    // Help
    HELP,

    // Arm
    ARM,

    // Traverse
    TRAVERSE,

    // Others
    DRAG_DETECT, HARD_KEY_DOWN, HARD_KEY_UP, MENU_DETECT, SET_DATA, MOUSE_WHEEL;

    public String getName() {
        return capitalizeConstantName(toString());
    }

    public int getSWTEventType() {
        String swtConstantName = capitalizeConstantName(toString());
        return SWTUtil.getSWTConstant(swtConstantName);
    }

    // TODO 適切なクラスに移動する
    private String capitalizeConstantName(String string) {
        StringTokenizer st = new StringTokenizer(string, "_");
        StringBuilder builder = new StringBuilder("");
        while (st.hasMoreTokens()) {
            builder.append(StringUtil.capitalize(st.nextToken().toLowerCase()));
        }
        return builder.toString();
    }
}
