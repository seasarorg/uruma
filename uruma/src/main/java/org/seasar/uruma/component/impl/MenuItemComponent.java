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

import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * メニュー項目情報を保持するためのコンポーネントです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class MenuItemComponent extends AbstractItemComponent {

    @RenderingPolicy(conversionType = ConversionType.ACCELERATOR)
    @FieldDescription("アクセラレータ")
    private String accelerator;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("イネーブル状態")
    private String enabled;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("選択状態")
    private String selection;

    /**
     * アクセラレータを取得します。<br />
     * 
     * @return アクセラレータ
     */
    public String getAccelerator() {
        return this.accelerator;
    }

    /**
     * アクセラレータを設定します。<br />
     * 
     * @param accelerator
     *            アクセラレータ
     */
    public void setAccelerator(final String accelerator) {
        this.accelerator = accelerator;
    }

    /**
     * イネーブル状態を取得します。<br />
     * 
     * @return イネーブル状態
     */
    public String getEnabled() {
        return this.enabled;
    }

    /**
     * イネーブル状態を設定します。<br />
     * 
     * @param enabled
     *            イネーブル状態
     */
    public void setEnabled(final String enabled) {
        this.enabled = enabled;
    }

    /**
     * 選択状態を取得します。<br />
     * 
     * @return 選択状態
     */
    public String getSelection() {
        return this.selection;
    }

    /**
     * 選択状態を設定します。<br />
     * 
     * @param selection
     *            選択状態
     */
    public void setSelection(final String selection) {
        this.selection = selection;
    }
}
