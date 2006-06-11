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

import static org.seasar.jface.renderer.info.SpinnerInfo.MAX_PROP;
import static org.seasar.jface.renderer.info.SpinnerInfo.MIN_PROP;
import static org.seasar.jface.renderer.info.SpinnerInfo.SELECTION_PROP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Spinner;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.SpinnerInfo;

/**
 * <code>Spinner</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class SpinnerRenderer extends AbstractControlRenderer<Spinner> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        int style = SWT.BORDER;
        return style;
    }

    @Override
    protected void doRender(final Spinner spinner,
            final ControlComponent controlComponent) {
        addSpinner(spinner, controlComponent);
    }

    @Override
    protected Class<Spinner> getControlType() {
        return Spinner.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return SpinnerInfo.class;
    }

    public String getRendererName() {
        return "spinner";
    }
    
    protected void addSpinner(final Spinner spinner,
            final ControlComponent controlComponent) {
        String max = controlComponent.getPropertyValue(MAX_PROP);
        String min = controlComponent.getPropertyValue(MIN_PROP);
        String selection = controlComponent.getPropertyValue(SELECTION_PROP);
        selection = selection != null ? selection : min;
        
        spinner.setMaximum(IntegerConversionUtil.toPrimitiveInt(max));
        spinner.setMinimum(IntegerConversionUtil.toPrimitiveInt(min));
        spinner.setSelection(IntegerConversionUtil.toPrimitiveInt(selection));
    }
}
