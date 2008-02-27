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
package org.seasar.uruma.annotation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.seasar.eclipse.common.util.SWTUtil;
import org.seasar.uruma.binding.method.WindowCloseListener;

/**
 * {@link EventListener} の種別を表す列挙型です。<br />
 * 
 * @author y-komori
 * @author bskuroneko
 */
public enum EventListenerType {

    // Key
    /**
     * @see SWT#KeyDown
     */
    KEY_DOWN,
    /**
     * @see SWT#KeyUp
     */
    KEY_UP,

    // Mouse
    /**
     * @see SWT#MouseDown
     */
    MOUSE_DOWN,

    /**
     * @see SWT#MouseUp
     */
    MOUSE_UP,

    /**
     * @see SWT#MouseMove
     */
    MOUSE_MOVE,

    /**
     * @see SWT#MouseEnter
     */
    MOUSE_ENTER,

    /**
     * @see SWT#MouseExit
     */
    MOUSE_EXIT,

    /**
     * @see SWT#MouseDoubleClick
     */
    MOUSE_DOUBLE_CLICK,

    /**
     * @see SWT#MouseHover
     */
    MOUSE_HOVER,

    // Paint
    /**
     * @see SWT#Paint
     */
    PAINT,

    // Control
    /**
     * @see SWT#Move
     */
    MOVE,

    /**
     * @see SWT#Resize
     */
    RESIZE,

    // Dispose
    /**
     * @see SWT#Dispose
     */
    DISPOSE,

    // Selection
    /**
     * @see SWT#Selection
     */
    SELECTION,

    /**
     * @see SWT#DefaultSelection
     */
    DEFAULT_SELECTION,

    // Focus
    /**
     * @see SWT#FocusIn
     */
    FOCUS_IN,

    /**
     * @see SWT#FocusOut
     */
    FOCUS_OUT,

    // Tree
    /**
     * @see SWT#Expand
     */
    EXPAND,

    /**
     * @see SWT#Collapse
     */
    COLLAPSE,

    // Shell
    /**
     * @see SWT#Iconify
     */
    ICONIFY,

    /**
     * @see SWT#Deiconify
     */
    DEICONIFY,

    /**
     * @see SWT#Close
     */
    CLOSE,

    /**
     * @see SWT#Activate
     */
    ACTIVATE,

    /**
     * @see SWT#Deactivate
     */
    DEACTIVATE,

    // Menu or Controll's visible change
    /**
     * @see SWT#Show
     */
    SHOW,

    /**
     * @see SWT#Hide
     */
    HIDE,

    // Modify
    /**
     * @see SWT#Modify
     */
    MODIFY,

    // Verify
    /**
     * @see SWT#Verify
     */
    VERIFY,

    // Help
    /**
     * @see SWT#Help
     */
    HELP,

    // Arm
    /**
     * @see SWT#Arm
     */
    ARM,

    // Traverse
    /**
     * @see SWT#Traverse
     */
    TRAVERSE,

    // Others
    /**
     * @see SWT#DragDetect
     */
    DRAG_DETECT,

    /**
     * @see SWT#HardKeyDown
     */
    HARD_KEY_DOWN,

    /**
     * @see SWT#HardKeyDown
     */
    HARD_KEY_UP,

    /**
     * @see SWT#MenuDetect
     */
    MENU_DETECT,

    /**
     * @see SWT#SetData
     */
    SET_DATA,

    /**
     * @see SWT#MouseWheel
     */
    MOUSE_WHEEL,

    /**
     * ウィンドウクローズ直前に呼び出されるイベントです。<br />
     * 
     * @see WindowCloseListener
     */
    WINDOW_CLOSING(false);

    private static Map<String, Integer> constantMap = new HashMap<String, Integer>();

    static {
        EventListenerType[] values = EventListenerType.values();
        for (int i = 0; i < values.length; i++) {
            constantMap.put(values[i].toString(), SWTUtil
                    .getSWTConstant(values[i].getName()));
        }
    }

    private boolean isSwtEvent = true;

    private EventListenerType() {
    }

    private EventListenerType(final boolean isSwtEvent) {
        this.isSwtEvent = isSwtEvent;
    }

    /**
     * 名称を取得します。<br />
     * 
     * @return 名称
     */
    public String getName() {
        return SWTUtil.convertConstantName(toString());
    }

    /**
     * {@link SWT} クラスで定義されたイベント型を取得します。<br />
     * 
     * @return イベント型
     */
    public int getSWTEventType() {
        return constantMap.get(toString());
    }

    /**
     * {@link SWT} クラスで定義されたイベントかどうかを返します。<br />
     * 
     * @return SWT クラスで定義されたイベントの場合は <code>true</code>、そうでない場合は
     *         <code>false</code>。
     */
    public boolean isSWTEvent() {
        return this.isSwtEvent;
    }
}
