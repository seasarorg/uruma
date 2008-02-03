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
package org.seasar.uruma.binding.method.impl;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.seasar.uruma.binding.method.EventListenerDef;
import org.seasar.uruma.binding.method.GenericListener;
import org.seasar.uruma.binding.method.ListenerBinder;
import org.seasar.uruma.binding.method.MethodBinding;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link Viewer} のための {@link ListenerBinder} です。<br />
 * 
 * @author y-komori
 */
public class ViewerListenerBinder extends AbstractListenerBinder {
    /*
     * @see org.seasar.uruma.binding.method.ListenerBinder#bindListener(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext,
     *      org.seasar.uruma.binding.method.MethodBinding,
     *      org.seasar.uruma.binding.method.EventListenerDef)
     */
    public Class<?> bindListener(final WidgetHandle handle,
            final PartContext context, final MethodBinding binding,
            final EventListenerDef def) {
        Widget widget = handle.<Viewer> getCastWidget().getControl();

        Listener listener = new GenericListener(context, binding);
        widget.addListener(def.getType().getSWTEventType(), listener);
        return GenericListener.class;
    }

    /*
     * @see org.seasar.uruma.binding.method.ListenerBinder#getTargetCLass()
     */
    public Class<?> getTargetCLass() {
        return Viewer.class;
    }
}
