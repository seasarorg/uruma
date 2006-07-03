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

import static org.seasar.jface.renderer.info.LabelInfo.ALIGNMENT_PROP;
import static org.seasar.jface.renderer.info.LabelInfo.IMAGE_PROP;
import static org.seasar.jface.renderer.info.LabelInfo.TEXT_PROP;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.LabelInfo;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.PathUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * @author y-komori
 * 
 */
public class LabelRenderer extends AbstractControlRenderer<Label> {

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
        String text = controlComponent.getPropertyValue(TEXT_PROP);
        if (text != null) {
            text = text.replace("\\n", "\n");
            text = text.replace("\\t", "\t");
            if (text.startsWith("\"") && text.endsWith("\"")) {
                text = text.substring(1, text.length() - 1);
            }
            label.setText(text);
        }
    }

    protected void renderImage(Label label, ControlComponent controlComponent) {
        String imgSrc = controlComponent.getPropertyValue(IMAGE_PROP);
        if (imgSrc != null) {
            Image image = ImageManager.getImage(imgSrc);
            if (image == null) {
                imgSrc = PathUtil.createPath(controlComponent.getBasePath(),
                        imgSrc);
                image = ImageManager.loadImage(imgSrc);
            }

            label.setImage(image);
        }
    }

    protected void renderAlignment(Label label,
            ControlComponent controlComponent) {
        String alignment = controlComponent.getPropertyValue(ALIGNMENT_PROP);
        if (alignment != null) {
            label.setAlignment(SWTUtil.getSWTConstant(alignment));
        }
    }

    public String getRendererName() {
        return "label";
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return LabelInfo.class;
    }
}
