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

import static org.seasar.jface.renderer.info.SliderInfo.STYLE_PROP;
import static org.seasar.jface.renderer.info.SliderInfo.MAX_PROP;
import static org.seasar.jface.renderer.info.SliderInfo.MIN_PROP;
import static org.seasar.jface.renderer.info.SliderInfo.SELECTION_PROP;
import static org.seasar.jface.renderer.info.SliderInfo.INCREMENT_PROP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Slider;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.SliderInfo;

/**
 * <code>Slider</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class SliderRenderer extends AbstractControlRenderer<Slider> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        String style = controlComponent.getPropertyValue(STYLE_PROP);
        int styleValue = (style == null) ? SWT.HORIZONTAL : SWT.VERTICAL;
        return styleValue;
    }

    @Override
    protected void doRender(final Slider slider,
            final ControlComponent controlComponent) {
        addSlider(slider, controlComponent);
    }

    @Override
    protected Class<Slider> getControlType() {
        return Slider.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return SliderInfo.class;
    }

    public String getRendererName() {
        return "slider";
    }
    
    protected void addSlider(final Slider slider,
            final ControlComponent controlComponent) {
        String max = controlComponent.getPropertyValue(MAX_PROP);
        String min = controlComponent.getPropertyValue(MIN_PROP);
        String selection = controlComponent.getPropertyValue(SELECTION_PROP);
        selection = selection != null ? selection : min;
        String increment = controlComponent.getPropertyValue(INCREMENT_PROP);
        
        slider.setMaximum(IntegerConversionUtil.toPrimitiveInt(max));
        slider.setMinimum(IntegerConversionUtil.toPrimitiveInt(min));
        slider.setSelection(IntegerConversionUtil.toPrimitiveInt(selection));
        slider.setIncrement(IntegerConversionUtil.toPrimitiveInt(increment));
    }
}
