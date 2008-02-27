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
package org.seasar.uruma.renderer.impl;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Control;
import org.seasar.uruma.component.jface.SashFormComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link SashForm} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class SashFormRenderer extends
        AbstractCompositeRenderer<SashFormComponent, SashForm> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractCompositeRenderer#doRenderComposite(org.seasar.uruma.component.jface.CompositeComponent,
     *      org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void doRenderComposite(final SashFormComponent controlComponent,
            final SashForm control) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRenderAfter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRenderAfter(final SashForm widget,
            final SashFormComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        setMaximizedControl(widget, uiComponent);
    }

    private void setMaximizedControl(final SashForm widget,
            final SashFormComponent uiComponent) {
        String id = uiComponent.getMaximizedControlId();
        if (id == null) {
            return;
        }

        WidgetHandle handle = getContext().getWidgetHandle(id);
        if (handle != null) {
            if (handle.instanceOf(Control.class)) {
                Control control = handle.<Control> getCastWidget();
                widget.setMaximizedControl(control);
            }
        }
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<SashForm> getWidgetType() {
        return SashForm.class;
    }
}
