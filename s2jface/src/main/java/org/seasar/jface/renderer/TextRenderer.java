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
import org.eclipse.swt.widgets.Text;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.jface.component.impl.ControlComponent;

public class TextRenderer extends AbstractControlRenderer<Text> {

    @Override
    protected Class<Text> getControlType() {
        return Text.class;
    }

    @Override
    protected void doRender(Text control, ControlComponent uiComponent) {
        control.setText(uiComponent.getText());

        String textLimit = uiComponent.getAttribute("textLimit");
        if (textLimit != null) {
            control.setTextLimit(IntegerConversionUtil
                    .toPrimitiveInt(textLimit));
        }

        String editable = uiComponent.getAttribute("editable");
        if (editable != null) {
            control.setEditable(BooleanConversionUtil
                    .toPrimitiveBoolean(editable));
        }
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.SINGLE | SWT.BORDER;
    }

}
