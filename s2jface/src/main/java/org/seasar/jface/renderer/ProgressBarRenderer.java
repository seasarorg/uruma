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

import static org.seasar.jface.renderer.info.ProgressBarInfo.MAX_PROP;
import static org.seasar.jface.renderer.info.ProgressBarInfo.MIN_PROP;
import static org.seasar.jface.renderer.info.ProgressBarInfo.SELECTION_PROP;

import org.eclipse.swt.widgets.ProgressBar;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.ProgressBarInfo;

/**
 * @author bskuroneko
 *
 */
public class ProgressBarRenderer extends AbstractControlRenderer<ProgressBar> {

    @Override
    protected void doRender(ProgressBar progressBar, ControlComponent controlComponent) {
        addProgressBar(progressBar, controlComponent);
    }

    @Override
    protected Class<ProgressBar> getControlType() {
        return ProgressBar.class;
    }

    public String getRendererName() {
        return "progressBar";
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return ProgressBarInfo.class;
    }

    protected void addProgressBar(ProgressBar progressBar, ControlComponent controlComponent) {
        String max = controlComponent.getPropertyValue(MAX_PROP);
        String min = controlComponent.getPropertyValue(MIN_PROP);
        String selection = controlComponent.getPropertyValue(SELECTION_PROP);
        selection = selection != null ? selection : min;
        
        progressBar.setMaximum(IntegerConversionUtil.toPrimitiveInt(max));
        progressBar.setMinimum(IntegerConversionUtil.toPrimitiveInt(min));
        progressBar.setSelection(IntegerConversionUtil.toPrimitiveInt(selection));
    }

}
