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
package org.seasar.jface.renderer.impl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.impl.CoolBarComponent;

/**
 * <code>CoolBar</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class CoolBarRenderer extends
        AbstractCompositeRenderer<CoolBarComponent, CoolBar> {

    @Override
    protected void doRenderComposite(CoolBarComponent controlComponent,
            CoolBar control) {
    }

    @Override
    protected void doRenderAfter(CoolBar widget, CoolBarComponent uiComponent,
            Widget parent, WindowContext context) {
        setChildlenSize(widget, uiComponent);
        setResizeListener(widget, uiComponent);
    }

    private void setChildlenSize(CoolBar widget, CoolBarComponent uiComponent) {
        for (CoolItem item : widget.getItems()) {
            Control control = item.getControl();
            if (control == null) {
                continue;
            }

            Point controlSize = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            Point itemSize = item.computeSize(controlSize.x, controlSize.y);

            Point minimumSize = controlSize;
            if (control instanceof ToolBar) {
                ToolBar bar = (ToolBar) control;
                if (bar.getItemCount() > 0) {
                    ToolItem toolItem = bar.getItem(0);
                    minimumSize = new Point(toolItem.getBounds().width,
                            toolItem.getBounds().height);
                }
            }
            item.setMinimumSize(minimumSize);
            item.setPreferredSize(itemSize);
            item.setSize(itemSize);
        }
    }

    private void setResizeListener(final CoolBar widget, CoolBarComponent uiComponent) {
        widget.addControlListener(new ControlListener() {
            public void controlMoved(ControlEvent event) {
            }

            public void controlResized(ControlEvent event) {
                widget.getParent().layout();
            }
        });
    }

    @Override
    protected Class<CoolBar> getWidgetType() {
        return CoolBar.class;
    }
}
