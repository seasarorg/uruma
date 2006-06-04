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
package org.seasar.jface.renderer;

import static org.seasar.jface.renderer.info.ButtonInfo.SELECTION_PROP;
import static org.seasar.jface.renderer.info.ButtonInfo.TEXT_PROP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ButtonInfo;
import org.seasar.jface.renderer.info.ComponentInfo;

/**
 * @author y-komori
 * 
 */
public class ButtonRenderer extends AbstractControlRenderer<Button> {

    @Override
    protected int getStyle(final ControlComponent uiComponent) {
        int style = super.getStyle(uiComponent);
        style = (style == SWT.NONE) ? SWT.PUSH : style;
        return style;
    }

    @Override
    protected void doRender(final Button button,
            final ControlComponent controlComponent) {
        renderText(button, controlComponent);
        renderSelection(button, controlComponent);
    }

    @Override
    protected Class<Button> getControlType() {
        return Button.class;
    }

    protected void renderText(final Button button,
            final ControlComponent controlComponent) {
        String text = controlComponent.getPropertyValue(TEXT_PROP);
        if (text != null) {
            button.setText(text);
        }
    }

    protected void renderSelection(final Button button,
            final ControlComponent controlComponent) {
        Property selection = controlComponent.getProperty(SELECTION_PROP);
        if (selection != null) {
            button.setSelection(selection.getBooleanValue());
        }
    }

    public String getRendererName() {
        return "button";
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return ButtonInfo.class;
    }
}
