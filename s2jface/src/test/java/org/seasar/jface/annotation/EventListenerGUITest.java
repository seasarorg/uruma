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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.seasar.jface.WindowContext;
import org.seasar.jface.renderer.impl.AbstractGUITest;

/**
 * @author bskuroneko
 * 
 */
public class EventListenerGUITest extends AbstractGUITest {
    
    private SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");
    
    private Text eventLog;

//  Button
    @EventListener(id="button", type=EventListenerType.SELECTION)
    public void onButtonSelection() {
        logEvent("buttonSelection NONE ARGUMENTS");
    }

    @EventListener(id="button", type=EventListenerType.SELECTION)
    public void onButtonSelection(Event event) {
        logEvent("buttonSelection EVENT", event);
    }

    @EventListener(id="button", type=EventListenerType.SELECTION)
    public void onButtonSelection(SelectionEvent selectionEvent) {
        logEvent("buttonSelection SELECTION EVENT", selectionEvent);
    }

    @EventListener(id="button", type=EventListenerType.ACTIVATE)
    public void onButtonActivate(Event event) {
        logEvent("buttonActivate", event);
    }
    
    @EventListener(id="button", type=EventListenerType.DEACTIVATE)
    public void onButtonDeactivate(Event event) {
        logEvent("buttonDeactivate", event);
    }
    
    @EventListener(id="button", type=EventListenerType.DISPOSE)
    public void onButtonDispose(Event event) {
        logEvent("buttonDispose", event);
    }

    @EventListener(id="button", type=EventListenerType.HELP)
    public void onButtonHelp(Event event) {
        logEvent("buttonHelp", event);
    }

    @EventListener(id="button", type=EventListenerType.FOCUS_IN)
    public void onButtonFocusIn(Event event) {
        logEvent("buttonFocusIn", event);
    }

    @EventListener(id="button", type=EventListenerType.FOCUS_OUT)
    public void onButtonFocusOut(Event event) {
        logEvent("buttonFocusOut", event);
    }

    @EventListener(id="button", type=EventListenerType.TRAVERSE)
    public void onButtonTraverse(Event event) {
        logEvent("buttonTraverse", event);
    }

    @EventListener(id="button", type=EventListenerType.MOUSE_DOWN)
    public void onButtonMouseDown(Event event) {
        logEvent("buttonMouseDown", event);
    }

    @EventListener(id="button", type=EventListenerType.MOUSE_UP)
    public void onButtonMouseUp(Event event) {
        logEvent("buttonMouseUp", event);
    }

    @EventListener(id="button", type=EventListenerType.MOUSE_DOUBLE_CLICK)
    public void onButtonMouseDoubleClick(Event event) {
        logEvent("buttonMouseDoubleClick", event);
    }

// Shell
    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.SHOW)
    public void onShellShow(Event event) {
        logEvent("shellShow", event);
    }
    
    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.CLOSE)
    public void onShellClose(Event event) {
        logEvent("shellClose", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.ACTIVATE)
    public void onShellActivate(Event event) {
        logEvent("shellActivate", event);
    }
    
    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.DEACTIVATE)
    public void onShellDeactivate(Event event) {
        logEvent("shellDeactivate", event);
    }
    
    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.ICONIFY)
    public void onShellIconify(Event event) {
        logEvent("shellIconify", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.DEICONIFY)
    public void onShellDeiconify(Event event) {
        logEvent("shellDeiconify", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.DISPOSE)
    public void onShellDispose(Event event) {
        logEvent("shellDispose", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.MOVE)
    public void onShellMove(Event event) {
        logEvent("shellMove", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.RESIZE)
    public void onShellResize(Event event) {
        logEvent("shellResize", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.HELP)
    public void onShellHelp(Event event) {
        logEvent("shellHelp", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.FOCUS_IN)
    public void onShellFocusIn(Event event) {
        logEvent("shellFocusIn", event);
    }

    @EventListener(id=WindowContext.SHELL_ID, type=EventListenerType.FOCUS_OUT)
    public void onShellFocusOut(Event event) {
        logEvent("shellFocusOut", event);
    }

// Mouse
    @EventListener(id="mouseLabel", type=EventListenerType.MOUSE_ENTER)
    public void onLabelMouseEnter(Event event) {
        logEvent("labelMouseEnter", event);
    }

    @EventListener(id="mouseLabel", type=EventListenerType.MOUSE_EXIT)
    public void onLabelMouseExit(Event event) {
        logEvent("labelMouseExit", event);
    }

    @EventListener(id="mouseLabel", type=EventListenerType.MOUSE_DOWN)
    public void onLabelMouseDown(Event event) {
        logEvent("labelMouseDown", event);
    }

    @EventListener(id="mouseLabel", type=EventListenerType.MOUSE_UP)
    public void onLabelMouseUp(Event event) {
        logEvent("labelMouseUp", event);
    }

    @EventListener(id="mouseLabel", type=EventListenerType.MOUSE_HOVER)
    public void onLabelMouseHover(Event event) {
        logEvent("labelMouseHover", event);
    }

    @EventListener(id="mouseLabel", type=EventListenerType.MOUSE_DOUBLE_CLICK)
    public void onLabelMouseDoubleClick(Event event) {
        logEvent("labelMouseDoubleClick", event);
    }

    @EventListener(id="mouseLabel", type=EventListenerType.MOUSE_MOVE)
    public void onLabelMouseMove(Event event) {
        logEvent("labelMouseMove", event);
    }

// text
    @EventListener(id="text", type=EventListenerType.KEY_DOWN)
    public void onTextKeyDown(Event event) {
        logEvent("textKeyDown", event);
    }

    @EventListener(id="text", type=EventListenerType.KEY_UP)
    public void onTextKeyUp(Event event) {
        logEvent("textKeyUp", event);
    }

    @EventListener(id="text", type=EventListenerType.MODIFY)
    public void onTextModify(Event event) {
        logEvent("textModify", event);
    }

    @EventListener(id="text", type=EventListenerType.VERIFY)
    public void onTextVerify(Event event) {
        logEvent("textVerify", event);
    }
    
    @EventListener(id="text", type=EventListenerType.MOUSE_WHEEL)
    public void onTextMouseWheel(Event event) {
        logEvent("textMouseWheel", event);
    }
    
// logger
    private void logEvent(String message, Object event) {
        logEvent(message + " : " + event.toString());
    }
    
    private void logEvent(String message) {
        String dateString = format.format(new Date());
        StringBuilder log = new StringBuilder(dateString);
        log.append(" ");
        log.append(message);
        
        if (! eventLog.isDisposed()) {
            eventLog.append(log.toString());
            eventLog.append(Text.DELIMITER);
        }
        System.out.println(log.toString());
    }
}
