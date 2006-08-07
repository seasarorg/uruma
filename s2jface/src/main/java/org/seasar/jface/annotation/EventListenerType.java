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
package org.seasar.jface.annotation;

import java.util.StringTokenizer;

import org.seasar.framework.util.StringUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * @author y-komori
 * 
 */
public enum EventListenerType {

    // ArmListener
    ARM,

    // ControlListener
    MOVE, RESIZE,

    // DisposeListener
    DISPOSE,

    // FocusListener
    FOCUS_IN, FOCUS_OUT,

    // HelpListener
    HELP,

    // KeyListener
    KEY_PRESSED, KEY_RELEASED,

    // MenuListener
    HIDE, SHOW,

    // ModifyListener
    MODIFY,

    // MouseListener
    MOUSE_DOUBLE_CLICK, MOUSE_DOWN, MOUSE_UP,

    // MouseMoveListener
    MOUSE_MOVE,

    // MouseTrackListener
    MOUSE_ENTER, MOUSE_EXIT, MOUSE_HOVER,

    // PaintListener
    PAINT,

    // SelectionListener
    DEFAULT_SELECTION, SELECTION,

    // ShellListener
    ACTIVATE, CLOSE, DEACTIVATE, DEICONIFY, ICONIFY,

    // TraverseListener
    TRAVERSE,

    // TreeListener
    COLLAPSE, EXPAND,

    // VerifyListener
    VERIFY;

    public String getName() {
        return toString().toLowerCase();
    }

    // TODO 面倒でもSWT.Xxxを直接参照した方がコンパイルエラーになってよいかも
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
