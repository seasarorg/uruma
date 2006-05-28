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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * @author y-komori
 * 
 */
public class ButtonRenderer extends AbstractControlRenderer<Button> {

    @Override
    protected int getStyle(ControlComponent uiComponent) {
        int style = super.getStyle(uiComponent);
        style = (style == SWT.NONE) ? SWT.PUSH : style;
        return style;
    }

    @Override
    protected void doRender(Button control, ControlComponent uiComponent) {
        Button button = (Button) control;
        button.setText(uiComponent.getText());
    }

    @Override
    protected Class<Button> getControlType() {
        return Button.class;
    }

    public String getRendererName() {
        return "button";
    }
}
