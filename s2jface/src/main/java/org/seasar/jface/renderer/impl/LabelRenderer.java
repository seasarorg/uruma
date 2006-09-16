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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.seasar.jface.component.impl.LabelComponent;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.PathUtil;

/**
 * <code>Label</code> のレンダリングを行うクラスです。<br />
 * 
 * @author y-komori
 */
public class LabelRenderer extends
        AbstractControlRenderer<LabelComponent, Label> {

    @Override
    protected void doRender(LabelComponent controlComponent, Label control) {
        renderImage(control, controlComponent);
    }

    protected void renderImage(Label label, LabelComponent labelComponent) {
        String imgSrc = labelComponent.getImage();
        if (imgSrc != null) {
            Image image = ImageManager.getImage(imgSrc);
            if (image == null) {
                imgSrc = PathUtil.createPath(labelComponent.getBasePath(),
                        imgSrc);
                image = ImageManager.loadImage(imgSrc);
            }

            label.setImage(image);
        }
    }

    @Override
    protected Class<Label> getControlType() {
        return Label.class;
    }
}
