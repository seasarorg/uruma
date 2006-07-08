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
package org.seasar.jface.layout;

import org.eclipse.swt.layout.FillLayout;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * @author y-komori
 * 
 */
public class FillLayoutSupport extends
        AbstractLayoutSupport<FillLayout, Object> {
    private static final String LAYOUT_NAME = "fill";

    public FillLayout createLayout() {
        return new FillLayout();
    }

    public Class<FillLayout> getLayoutType() {
        return FillLayout.class;
    }

    public Object createLayoutData() {
        return null;
    }

    public Object createLayoutData(ControlComponent controlComponent) {
        return null;
    }

    public String getLayoutName() {
        return LAYOUT_NAME;
    }
}
