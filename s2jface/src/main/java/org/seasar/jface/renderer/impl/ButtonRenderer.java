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
import org.eclipse.swt.widgets.Button;
import org.seasar.jface.component.impl.ButtonComponent;
import org.seasar.jface.util.ImageManager;

/**
 * <code>Button</code> のレンダリングを行うクラスです。<br />
 * 
 * @author y-komori
 */
public class ButtonRenderer extends
        AbstractControlRenderer<ButtonComponent, Button> {

    @Override
    protected int getDefaultStyle() {
        return SWT.PUSH;
    }

    @Override
    protected void doRender(ButtonComponent controlComponent, Button control) {
        setImage(controlComponent, control);
    }

    protected void setImage(ButtonComponent buttonComponent, Button button) {
        String image = buttonComponent.getImage();
        if (image != null) {
            button.setImage(ImageManager.loadImage(image));
        }
    }

    @Override
    protected Class<Button> getControlType() {
        return Button.class;
    }
}
