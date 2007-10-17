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
package org.seasar.uruma.component.impl;

import org.eclipse.swt.custom.SashForm;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link SashForm} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class SashFormComponent extends CompositeComponent {

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("最大化されているコントロール")
    private String maximizedControlId;

    @RenderingPolicy(conversionType = ConversionType.SWT_CONST)
    @FieldDescription("分割方向")
    private String orientation;

    @RenderingPolicy(conversionType = ConversionType.INT_ARRAY, setTiming = SetTiming.RENDER_AFTER)
    @FieldDescription("内部に保持するウィジット")
    private String weights;

    /**
     * 最大化されているコントロールを取得します。<br />
     * 
     * @return 最大化されているコントロール
     */
    public String getMaximizedControlId() {
        return this.maximizedControlId;
    }

    /**
     * 最大化されているコントロールを設定します。<br />
     * 
     * @param maximizedControlId
     *            最大化されているコントロール
     */
    public void setMaximizedControlId(final String maximizedControlId) {
        this.maximizedControlId = maximizedControlId;
    }

    /**
     * 分割方向を取得します。<br />
     * 
     * @return 分割方向
     */
    public String getOrientation() {
        return this.orientation;
    }

    /**
     * 分割方向を設定します。<br />
     * 
     * @param orientation
     *            分割方向
     */
    public void setOrientation(final String orientation) {
        this.orientation = orientation;
    }

    /**
     * 内部に保持するウィジットを取得します。<br />
     * 
     * @return 内部に保持するウィジット
     */
    public String getWeights() {
        return this.weights;
    }

    /**
     * 内部に保持するウィジットを設定します。<br />
     * 
     * @param weights
     *            内部に保持するウィジット
     */
    public void setWeights(final String weights) {
        this.weights = weights;
    }

}
