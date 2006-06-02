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
    public static final String ATTR_TEXT = "text";

    public static final String ATTR_IMAGE = "image";

    public static final String ATTR_ALIGNMENT = "alignment";

    @Override
    protected void doRender(Label label, ControlComponent controlComponent) {
        renderText(label, controlComponent);
        renderImage(label, controlComponent);
        renderAlignment(label, controlComponent);
    }

    @Override
    protected Class<Label> getControlType() {
        return Label.class;
    }

    protected void renderText(Label label, ControlComponent controlComponent) {
        String text = controlComponent.getPropertyValue(ATTR_TEXT);
        if (text != null) {
            text = text.replace("\\n", "\n");
            text = text.replace("\\t", "\t");
            label.setText(text);
        }
    }

    protected void renderImage(Label label, ControlComponent controlComponent) {
        String imgSrc = controlComponent.getPropertyValue(ATTR_IMAGE);
        if (imgSrc != null) {
            Image image = ImageManager.getImage(imgSrc);
            if (image == null) {
                imgSrc = PathUtil.createPath(controlComponent.getBasePath(),
                        imgSrc);
                image = ImageManager.getImage(imgSrc);
            }

            label.setImage(image);
        }
    }

    protected void renderAlignment(Label label,
            ControlComponent controlComponent) {
        String alignment = controlComponent.getPropertyValue(ATTR_ALIGNMENT);
        if (alignment != null) {
            label.setAlignment(SWTUtil.getSWTConstant(alignment));
        }
    }

    public String getRendererName() {
        return "label";
    }
}
