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
package org.seasar.jface.component.impl;

import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.annotation.component.ComponentAttribute.SetTiming;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;

/**
 * @author bskuroneko
 * 
 */
public class SashFormComponent extends CompositeComponent {

    @ComponentAttribute(targetType = TargetType.NONE)
    private String maximizedControlId;

    @ComponentAttribute(conversionType = ConversionType.SWT_CONST)
    private String orientation;

    @ComponentAttribute(conversionType = ConversionType.INT_ARRAY, setTiming = SetTiming.RENDER_AFTER)
    private String weights;

    public String getMaximizedControlId() {
        return this.maximizedControlId;
    }

    public void setMaximizedControlId(String maximizedControlId) {
        this.maximizedControlId = maximizedControlId;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getWeights() {
        return this.weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

}
