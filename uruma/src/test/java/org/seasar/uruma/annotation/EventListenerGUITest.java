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
package org.seasar.uruma.annotation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.renderer.impl.AbstractGUITest;

/**
 * {@link EventListener} のためのテストクラスです。<br />
 * 
 * @author bskuroneko
 */
public class EventListenerGUITest extends AbstractGUITest {

    private SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");

    public Text eventLog;

    // Button
    @EventListener(id = "button", type = EventListenerType.SELECTION)
    public void onButtonSelection() {
        logEvent("buttonSelection NONE ARGUMENTS");
    }

    @EventListener(id = "button", type = EventListenerType.SELECTION)
    public void onButtonSelection(final Event event) {
        logEvent("buttonSelection EVENT", event);
    }

    @EventListener(id = "button", type = EventListenerType.SELECTION)
    public void onButtonSelection(final SelectionEvent selectionEvent) {
        logEvent("buttonSelection SELECTION EVENT", selectionEvent);
    }

    @EventListener(id = "button", type = EventListenerType.ACTIVATE)
    public void onButtonActivate(final Event event) {
        logEvent("buttonActivate", event);
    }

    @EventListener(id = "button", type = EventListenerType.DEACTIVATE)
    public void onButtonDeactivate(final Event event) {
        logEvent("buttonDeactivate", event);
    }

    @EventListener(id = "button", type = EventListenerType.DISPOSE)
    public void onButtonDispose(final Event event) {
        logEvent("buttonDispose", event);
    }

    @EventListener(id = "button", type = EventListenerType.HELP)
    public void onButtonHelp(final Event event) {
        logEvent("buttonHelp", event);
    }

    @EventListener(id = "button", type = EventListenerType.FOCUS_IN)
    public void onButtonFocusIn(final Event event) {
        logEvent("buttonFocusIn", event);
    }

    @EventListener(id = "button", type = EventListenerType.FOCUS_OUT)
    public void onButtonFocusOut(final Event event) {
        logEvent("buttonFocusOut", event);
    }

    @EventListener(id = "button", type = EventListenerType.TRAVERSE)
    public void onButtonTraverse(final Event event) {
        logEvent("buttonTraverse", event);
    }

    @EventListener(id = "button", type = EventListenerType.MOUSE_DOWN)
    public void onButtonMouseDown(final Event event) {
        logEvent("buttonMouseDown", event);
    }

    @EventListener(id = "button", type = EventListenerType.MOUSE_UP)
    public void onButtonMouseUp(final Event event) {
        logEvent("buttonMouseUp", event);
    }

    @EventListener(id = "button", type = EventListenerType.MOUSE_DOUBLE_CLICK)
    public void onButtonMouseDoubleClick(final Event event) {
        logEvent("buttonMouseDoubleClick", event);
    }

    // Shell
    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.SHOW)
    public void onShellShow(final Event event) {
        logEvent("shellShow", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.CLOSE)
    public void onShellClose(final Event event) {
        logEvent("shellClose", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.ACTIVATE)
    public void onShellActivate(final Event event) {
        logEvent("shellActivate", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.DEACTIVATE)
    public void onShellDeactivate(final Event event) {
        logEvent("shellDeactivate", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.ICONIFY)
    public void onShellIconify(final Event event) {
        logEvent("shellIconify", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.DEICONIFY)
    public void onShellDeiconify(final Event event) {
        logEvent("shellDeiconify", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.DISPOSE)
    public void onShellDispose(final Event event) {
        logEvent("shellDispose", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.MOVE)
    public void onShellMove(final Event event) {
        logEvent("shellMove", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.RESIZE)
    public void onShellResize(final Event event) {
        logEvent("shellResize", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.HELP)
    public void onShellHelp(final Event event) {
        logEvent("shellHelp", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.FOCUS_IN)
    public void onShellFocusIn(final Event event) {
        logEvent("shellFocusIn", event);
    }

    @EventListener(id = UrumaConstants.SHELL_CID, type = EventListenerType.FOCUS_OUT)
    public void onShellFocusOut(final Event event) {
        logEvent("shellFocusOut", event);
    }

    // Mouse
    @EventListener(id = "mouseLabel", type = EventListenerType.MOUSE_ENTER)
    public void onLabelMouseEnter(final Event event) {
        logEvent("labelMouseEnter", event);
    }

    @EventListener(id = "mouseLabel", type = EventListenerType.MOUSE_EXIT)
    public void onLabelMouseExit(final Event event) {
        logEvent("labelMouseExit", event);
    }

    @EventListener(id = "mouseLabel", type = EventListenerType.MOUSE_DOWN)
    public void onLabelMouseDown(final Event event) {
        logEvent("labelMouseDown", event);
    }

    @EventListener(id = "mouseLabel", type = EventListenerType.MOUSE_UP)
    public void onLabelMouseUp(final Event event) {
        logEvent("labelMouseUp", event);
    }

    @EventListener(id = "mouseLabel", type = EventListenerType.MOUSE_HOVER)
    public void onLabelMouseHover(final Event event) {
        logEvent("labelMouseHover", event);
    }

    @EventListener(id = "mouseLabel", type = EventListenerType.MOUSE_DOUBLE_CLICK)
    public void onLabelMouseDoubleClick(final Event event) {
        logEvent("labelMouseDoubleClick", event);
    }

    @EventListener(id = "mouseLabel", type = EventListenerType.MOUSE_MOVE)
    public void onLabelMouseMove(final Event event) {
        logEvent("labelMouseMove", event);
    }

    // text
    @EventListener(id = "text", type = EventListenerType.KEY_DOWN)
    public void onTextKeyDown(final Event event) {
        logEvent("textKeyDown", event);
    }

    @EventListener(id = "text", type = EventListenerType.KEY_UP)
    public void onTextKeyUp(final Event event) {
        logEvent("textKeyUp", event);
    }

    @EventListener(id = "text", type = EventListenerType.MODIFY)
    public void onTextModify(final Event event) {
        logEvent("textModify", event);
    }

    @EventListener(id = "text", type = EventListenerType.VERIFY)
    public void onTextVerify(final Event event) {
        logEvent("textVerify", event);
    }

    @EventListener(id = "text", type = EventListenerType.MOUSE_WHEEL)
    public void onTextMouseWheel(final Event event) {
        logEvent("textMouseWheel", event);
    }

    // logger
    private void logEvent(final String message, final Object event) {
        logEvent(message + " : " + event.toString());
    }

    private void logEvent(final String message) {
        String dateString = format.format(new Date());
        StringBuilder log = new StringBuilder(dateString);
        log.append(" ");
        log.append(message);

        if (!eventLog.isDisposed()) {
            eventLog.append(log.toString());
            eventLog.append(Text.DELIMITER);
        }
        System.out.println(log.toString());
    }
}
