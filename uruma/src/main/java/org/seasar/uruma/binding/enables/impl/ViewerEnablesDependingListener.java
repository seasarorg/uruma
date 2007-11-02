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
package org.seasar.uruma.binding.enables.impl;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Widget;
import org.seasar.uruma.binding.enables.EnablesDependingListener;
import org.seasar.uruma.binding.enables.EnablesForType;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.exception.EnablesDependingException;
import org.seasar.uruma.exception.UnsupportedClassException;

/**
 * {@link Viewer} に対する {@link EnablesDependingListener} です。<br />
 * 
 * @author y-komori
 */
public class ViewerEnablesDependingListener extends EnablesDependingListener {
    protected StructuredViewer viewer;

    /**
     * {@link ViewerEnablesDependingListener} を構築します。<br />
     * 
     * @param target
     *            ターゲットの {@link WidgetHandle}
     * @param enabled
     *            イネーブル状態を変更するウィジットの {@link WidgetHandle}
     * @param type
     *            選択条件を表す {@link EnablesForType}
     */
    public ViewerEnablesDependingListener(final WidgetHandle target,
            final WidgetHandle enabled, final EnablesForType type) {
        super(target, enabled, type);

        if (target.getWidget() instanceof Viewer) {
            this.viewer = target.<StructuredViewer> getCastWidget();
        } else {
            throw new UnsupportedClassException(target.getWidgetClass(),
                    StructuredViewer.class);
        }
    }

    /*
     * @see org.seasar.uruma.binding.enables.EnablesDependingListener#setupListener()
     */
    @Override
    protected void setupListener() {
        ISelectionChangedListener listener = new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                updateEnableState();
            }
        };
        viewer.addSelectionChangedListener(listener);
    }

    /*
     * @see org.seasar.uruma.binding.enables.EnablesDependingListener#updateEnableState()
     */
    @Override
    protected void updateEnableState() {
        if (!viewer.getControl().isDisposed()) {
            Object enabledWidget = enabled.getWidget();
            if (enabledWidget instanceof Widget
                    && ((Widget) enabledWidget).isDisposed()) {
                return;
            }
            boolean enableState = resolveEnabledState();
            enabledProperty.setValue(enabledWidget, Boolean
                    .valueOf(enableState));
        }
    }

    protected boolean resolveEnabledState() {
        boolean result = false;
        int selectionCount = ((StructuredSelection) viewer.getSelection())
                .size();
        if (type == EnablesForType.SELECTION) {
            result = (selectionCount > 0);
        } else if (type == EnablesForType.SINGLE) {
            result = (selectionCount == 1);
        } else if (type == EnablesForType.PAIR) {
            result = (selectionCount == 2);
        } else if (type == EnablesForType.NONE) {
            result = (selectionCount == 0);
        } else if (type == EnablesForType.MULTI) {
            result = (selectionCount >= 2);
        } else {
            throw new EnablesDependingException(viewer.getClass(), type);
        }
        return result;
    }
}
