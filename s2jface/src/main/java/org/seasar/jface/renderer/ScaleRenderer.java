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

import static org.seasar.jface.renderer.info.ScaleInfo.INCREMENT_PROP;
import static org.seasar.jface.renderer.info.ScaleInfo.MAX_PROP;
import static org.seasar.jface.renderer.info.ScaleInfo.MIN_PROP;
import static org.seasar.jface.renderer.info.ScaleInfo.SELECTION_PROP;

import org.eclipse.swt.widgets.Scale;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.ScaleInfo;

/**
 * <code>Scale</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class ScaleRenderer extends AbstractControlRenderer<Scale> {

    @Override
    protected void doRender(final Scale scale,
            final ControlComponent controlComponent) {
        addScale(scale, controlComponent);
    }

    @Override
    protected Class<Scale> getControlType() {
        return Scale.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return ScaleInfo.class;
    }

    public String getRendererName() {
        return "scale";
    }
    
    protected void addScale(final Scale scale,
            final ControlComponent controlComponent) {
        String max = controlComponent.getPropertyValue(MAX_PROP);
        String min = controlComponent.getPropertyValue(MIN_PROP);
        String selection = controlComponent.getPropertyValue(SELECTION_PROP);
        selection = selection != null ? selection : min;
        String increment = controlComponent.getPropertyValue(INCREMENT_PROP);
        
        scale.setMaximum(IntegerConversionUtil.toPrimitiveInt(max));
        scale.setMinimum(IntegerConversionUtil.toPrimitiveInt(min));
        scale.setSelection(IntegerConversionUtil.toPrimitiveInt(selection));
        scale.setIncrement(IntegerConversionUtil.toPrimitiveInt(increment));
    }
}
