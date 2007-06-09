/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.seasar.jface.component.impl.TextComponent;

/**
 * <code>Text</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TextRenderer extends
        AbstractControlRenderer<TextComponent, Text> {

    @Override
    protected void doRenderControl(TextComponent controlComponent, Text control) {
        setSelection(controlComponent, control);
    }
    
    private void setSelection(TextComponent controlComponent, Text control) {
        if (controlComponent.getSelectionStart() == null && controlComponent.getSelectionEnd() == null) {
            return;
        }
        
        int start = 0;
        if (controlComponent.getSelectionStart() != null) {
            start = Integer.parseInt(controlComponent.getSelectionStart());
        }
        
        if (controlComponent.getSelectionEnd() == null) {
            control.setSelection(start);
        }
        else {
            int end = Integer.parseInt(controlComponent.getSelectionEnd());
            control.setSelection(start, end);
        }
    }

    @Override
    protected Class<Text> getWidgetType() {
        return Text.class;
    }
    
    @Override
    protected int getDefaultStyle() {
        return SWT.SINGLE | SWT.BORDER;
    }

}
