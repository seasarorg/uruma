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

import static org.seasar.jface.component.impl.ControlComponent.ATTR_HEIGHT;
import static org.seasar.jface.component.impl.ControlComponent.ATTR_WIDTH;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * @author y-komori
 * 
 */
public class GridLayoutSupport extends
        AbstractLayoutSupport<GridLayout, GridData> {
    private static final String LAYOUT_NAME = "grid";

    public GridLayout createLayout() {
        return new GridLayout();
    }

    public Class<GridLayout> getLayoutType() {
        return GridLayout.class;
    }

    public GridData createLayoutData() {
        return new GridData();
    }

    public GridData createLayoutData(ControlComponent controlComponent) {
        GridData gridData = createLayoutData();

        Property widthProp = controlComponent.getProperty(ATTR_WIDTH);
        if ((widthProp != null) && widthProp.isValueExist()) {
            gridData.minimumWidth = widthProp.getIntValue();
        }

        Property heightProp = controlComponent.getProperty(ATTR_HEIGHT);
        if ((heightProp != null) && heightProp.isValueExist()) {
            gridData.minimumHeight = heightProp.getIntValue();
        }

        setupLayoutData(gridData, controlComponent);

        return gridData;
    }

    public String getLayoutName() {
        return LAYOUT_NAME;
    }

}
