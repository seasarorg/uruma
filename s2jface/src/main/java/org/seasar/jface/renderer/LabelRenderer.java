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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.PathUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * @author y-komori
 * 
 */
public class LabelRenderer extends AbstractControlRenderer<Label> {

    @Override
    protected void doRender(Label label, ControlComponent uiComponent) {
        String text = uiComponent.getText();
        if (text != null) {
            label.setText(text);
        }

        String imgSrc = uiComponent.getAttribute("image");
        if (imgSrc != null) {
            Image image = ImageManager.getInstance().getImage(imgSrc);
            if (image == null) {
                imgSrc = PathUtil.createPath(uiComponent.getBasePath(), imgSrc);
                image = ImageManager.getInstance().getImage(imgSrc,
                        label.getDisplay());
            }

            label.setImage(image);
        }

        String alignment = uiComponent.getAttribute("alignment");
        if (alignment != null) {
            label.setAlignment(SWTUtil.getSWTConstant(alignment));
        }
    }

    @Override
    protected Class<Label> getControlType() {
        return Label.class;
    }
}
