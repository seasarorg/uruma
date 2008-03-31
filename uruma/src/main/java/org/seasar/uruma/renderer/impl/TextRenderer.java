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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.seasar.uruma.component.jface.TextComponent;

/**
 * {@link Text} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TextRenderer extends AbstractControlRenderer<TextComponent, Text> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractControlRenderer#doRenderControl(org.seasar.uruma.component.jface.ControlComponent,
     *      org.eclipse.swt.widgets.Control)
     */
    @Override
    protected void doRenderControl(final TextComponent controlComponent,
            final Text control) {
        setSelection(controlComponent, control);
    }

    private void setSelection(final TextComponent controlComponent,
            final Text control) {
        if (controlComponent.selectionStart == null
                && controlComponent.selectionEnd == null) {
            return;
        }

        int start = 0;
        if (controlComponent.selectionStart != null) {
            start = Integer.parseInt(controlComponent.selectionStart);
        }

        if (controlComponent.selectionEnd == null) {
            control.setSelection(start);
        } else {
            int end = Integer.parseInt(controlComponent.selectionEnd);
            control.setSelection(start, end);
        }
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<Text> getWidgetType() {
        return Text.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#getDefaultStyle()
     */
    @Override
    protected int getDefaultStyle() {
        return SWT.SINGLE | SWT.BORDER;
    }

}
