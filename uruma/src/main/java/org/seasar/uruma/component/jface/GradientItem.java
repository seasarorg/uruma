/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.component.jface;

import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * グラデーション項目を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class GradientItem extends AbstractUIElement {

    @FieldDescription("色")
    private String color;

    @FieldDescription("割合")
    private String percent;

    /**
     * 色を取得します。<br />
     * 
     * @return 色
     */
    public String getColor() {
        return this.color;
    }

    /**
     * 色を設定します。<br />
     * 
     * @param color
     *            色
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * 割合を取得します。<br />
     * 
     * @return 割合
     */
    public String getPercent() {
        return this.percent;
    }

    /**
     * 割合を設定します。<br />
     * 
     * @param percent
     *            割合
     */
    public void setPercent(final String percent) {
        this.percent = percent;
    }

}
