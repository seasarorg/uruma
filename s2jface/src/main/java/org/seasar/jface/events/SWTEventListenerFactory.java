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
package org.seasar.jface.events;

import org.eclipse.swt.internal.SWTEventListener;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.EventListenerType;
import org.seasar.jface.binding.MethodBinding;
import org.seasar.jface.exception.NotFoundException;

/**
 * @author y-komori
 * 
 */
public class SWTEventListenerFactory {
    public static SWTEventListener getListener(EventListenerType type,
            WindowContext context, MethodBinding methodBinding) {
        SWTEventListener listener = null;
        switch (type) {
        case SELECTION:
            listener = getSelectionListener(context, methodBinding);
            break;

        default:
            throw new NotFoundException(NotFoundException.SWT_EVENT_LISTENER,
                    type.toString());
        }

        return listener;
    }

    protected static S2JFaceSelectionListener getSelectionListener(
            WindowContext context, MethodBinding methodBinding) {
        return new S2JFaceSelectionListener(context, methodBinding);
    }
}
