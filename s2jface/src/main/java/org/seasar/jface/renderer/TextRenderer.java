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
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * @author y-komori
 * 
 */
public class TextRenderer extends AbstractControlRenderer<Text> {
    public static final String ATTR_TEXT = "text";

    public static final String ATTR_TEXT_LIMIT = "textLimit";

    public static final String ATTR_EDITABLE = "editable";

    public static final String ATTR_ECHO_CHAR = "echoChar";

    public static final String ATTR_TABS = "tabs";

    @Override
    protected Class<Text> getControlType() {
        return Text.class;
    }

    @Override
    protected void doRender(Text control, ControlComponent controlComponent) {
        renderText(control, controlComponent);
        renderTextLimit(control, controlComponent);
        renderEditable(control, controlComponent);
        renderEchoChar(control, controlComponent);
        renderTabs(control, controlComponent);
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.SINGLE | SWT.BORDER;
    }

    protected void renderText(Text text, ControlComponent controlComponent) {
        text.setText(controlComponent.getPropertyValue(ATTR_TEXT));
    }

    protected void renderTextLimit(Text text, ControlComponent controlComponent) {
        String textLimit = controlComponent.getPropertyValue(ATTR_TEXT_LIMIT);
        if (textLimit != null) {
            text.setTextLimit(IntegerConversionUtil.toPrimitiveInt(textLimit));
        }
    }

    protected void renderEditable(Text text, ControlComponent controlComponent) {
        String editable = controlComponent.getPropertyValue(ATTR_EDITABLE);
        if (editable != null) {
            text
                    .setEditable(BooleanConversionUtil
                            .toPrimitiveBoolean(editable));
        }
    }

    protected void renderEchoChar(Text text, ControlComponent controlComponent) {
        String echoChar = controlComponent.getPropertyValue(ATTR_ECHO_CHAR);
        if (echoChar != null && !StringUtil.isEmpty(echoChar)) {
            text.setEchoChar(echoChar.charAt(0));
        }
    }

    protected void renderTabs(Text text, ControlComponent controlComponent) {
        String tabs = controlComponent.getPropertyValue(ATTR_TABS);
        if (tabs != null) {
            text.setTabs(IntegerConversionUtil.toPrimitiveInt(tabs));
        }
    }

    public String getRendererName() {
        return "text";
    }
}
