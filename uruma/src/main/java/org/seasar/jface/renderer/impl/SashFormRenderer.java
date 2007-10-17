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
package org.seasar.jface.renderer.impl;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.impl.SashFormComponent;

/**
 * <code>SashForm</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class SashFormRenderer extends
        AbstractCompositeRenderer<SashFormComponent, SashForm> {

    @Override
    protected void doRenderComposite(SashFormComponent controlComponent,
            SashForm control) {
    }
    
    @Override
    protected void doRenderAfter(SashForm widget, SashFormComponent uiComponent, Widget parent, WindowContext context) {
        setMaximizedControl(widget, uiComponent);
    }

    private void setMaximizedControl(SashForm widget, SashFormComponent uiComponent) {
        String id = uiComponent.getMaximizedControlId();
        if (id == null) {
            return;
        }
        Control control = (Control) getContext().getComponent(id);
        widget.setMaximizedControl(control);
    }

    @Override
    protected Class<SashForm> getWidgetType() {
        return SashForm.class;
    }
}
