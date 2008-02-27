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

import org.eclipse.swt.widgets.Spinner;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link Spinner} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class SpinnerComponent extends CompositeComponent {
    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("最大値")
    private String maximum;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("最小値")
    private String minimum;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("現在値")
    private String selection;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("増分値")
    private String increment;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("ページ増分値")
    private String pageIncrement;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("小数位")
    private String digits;

    /**
     * 最大値を取得します。<br />
     * 
     * @return 最大値
     */
    public String getMaximum() {
        return this.maximum;
    }

    /**
     * 最大値を設定します。<br />
     * 
     * @param maximum
     *            最大値
     */
    public void setMaximum(final String maximum) {
        this.maximum = maximum;
    }

    /**
     * 最小値を取得します。<br />
     * 
     * @return 最小値
     */
    public String getMinimum() {
        return this.minimum;
    }

    /**
     * 最小値を設定します。<br />
     * 
     * @param minimum
     *            最小値
     */
    public void setMinimum(final String minimum) {
        this.minimum = minimum;
    }

    /**
     * 現在値を取得します。<br />
     * 
     * @return 現在値
     */
    public String getSelection() {
        return this.selection;
    }

    /**
     * 現在値を設定します。<br />
     * 
     * @param selection
     *            現在値
     */
    public void setSelection(final String selection) {
        this.selection = selection;
    }

    /**
     * 増分値を取得します。<br />
     * 
     * @return 増分値
     */
    public String getIncrement() {
        return this.increment;
    }

    /**
     * 増分値を設定します。<br />
     * 
     * @param increment
     *            増分値
     */
    public void setIncrement(final String increment) {
        this.increment = increment;
    }

    /**
     * ページ増分値を取得します。<br />
     * 
     * @return ページ増分値
     */
    public String getPageIncrement() {
        return this.pageIncrement;
    }

    /**
     * ページ増分値を設定します。<br />
     * 
     * @param pageIncrement
     *            ページ増分値
     */
    public void setPageIncrement(final String pageIncrement) {
        this.pageIncrement = pageIncrement;
    }

    /**
     * 小数位を取得します。<br />
     * 
     * @return 小数位
     */
    public String getDigits() {
        return this.digits;
    }

    /**
     * 小数位を設定します。<br />
     * 
     * @param digits
     *            小数位
     */
    public void setDigits(final String digits) {
        this.digits = digits;
    }
}
