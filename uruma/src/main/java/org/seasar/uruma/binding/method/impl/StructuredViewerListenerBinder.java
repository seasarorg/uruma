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

import org.eclipse.jface.viewers.StructuredViewer;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.binding.method.EventListenerDef;
import org.seasar.uruma.binding.method.GenericDoubleClickListener;
import org.seasar.uruma.binding.method.GenericSelectionChangedListener;
import org.seasar.uruma.binding.method.ListenerBinder;
import org.seasar.uruma.binding.method.MethodBinding;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link StructuredViewer} のための {@link ListenerBinder} です。<br />
 * 
 * @author y-komori
 */
public class StructuredViewerListenerBinder implements ListenerBinder {
    private static final EventListenerType[] SUPPORT_TYPES = new EventListenerType[] {
            EventListenerType.SELECTION, EventListenerType.MOUSE_DOUBLE_CLICK };

    /*
     * @see org.seasar.uruma.binding.method.ListenerBinder#bindListener(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext,
     *      org.seasar.uruma.binding.method.MethodBinding,
     *      org.seasar.uruma.binding.method.EventListenerDef)
     */
    public Class<?> bindListener(final WidgetHandle handle,
            final PartContext context, final MethodBinding binding,
            final EventListenerDef def) {
        StructuredViewer viewer = handle.<StructuredViewer> getCastWidget();
        if (def.getType() == EventListenerType.SELECTION) {
            GenericSelectionChangedListener listener = new GenericSelectionChangedListener(
                    context, binding);
            viewer.addSelectionChangedListener(listener);
            return GenericSelectionChangedListener.class;
        } else if (def.getType() == EventListenerType.MOUSE_DOUBLE_CLICK) {
            GenericDoubleClickListener listener = new GenericDoubleClickListener(
                    context, binding);
            viewer.addDoubleClickListener(listener);
            return GenericDoubleClickListener.class;
        }
        return null;
    }

    /*
     * @see org.seasar.uruma.binding.method.ListenerBinder#getEventTypes()
     */
    public EventListenerType[] getEventTypes() {
        return SUPPORT_TYPES;
    }

    /*
     * @see org.seasar.uruma.binding.method.ListenerBinder#getTargetCLass()
     */
    public Class<?> getTargetCLass() {
        return StructuredViewer.class;
    }

}
