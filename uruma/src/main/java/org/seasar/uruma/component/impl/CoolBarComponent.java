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

import org.eclipse.swt.widgets.CoolBar;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;

/**
 * {@link CoolBar} に対応するコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class CoolBarComponent extends CompositeComponent {

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN, setTiming = SetTiming.RENDER_AFTER)
    @FieldDescription("ロック状態")
    private String locked;

    @RenderingPolicy(conversionType = ConversionType.INT_ARRAY, setTiming = SetTiming.RENDER_AFTER)
    @FieldDescription("ラップ表示項目")
    private String wrapIndices;

    /**
     * ロック状態を取得します。<br />
     * 
     * @return ロック状態
     */
    public String getLocked() {
        return this.locked;
    }

    /**
     * ロック状態を設定します。<br />
     * 
     * @param locked
     *            ロック状態
     */
    public void setLocked(final String locked) {
        this.locked = locked;
    }

    /**
     * ラップ表示項目を取得します。<br />
     * 
     * @return ラップ表示項目
     */
    public String getWrapIndices() {
        return this.wrapIndices;
    }

    /**
     * ラップ表示項目を設定します。<br />
     * 
     * @param wrapIndices
     *            ラップ表示項目
     */
    public void setWrapIndices(final String wrapIndices) {
        this.wrapIndices = wrapIndices;
    }
}
