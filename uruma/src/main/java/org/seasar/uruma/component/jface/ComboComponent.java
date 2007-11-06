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
package org.seasar.uruma.component.jface;

import org.eclipse.swt.widgets.Combo;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link Combo} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class ComboComponent extends SimpleItemCompositeComponent {

    @RenderingPolicy(conversionType = ConversionType.SWT_CONST)
    @FieldDescription("表示方向")
    private String orientation;

    @RenderingPolicy(conversionType = ConversionType.STRING)
    @FieldDescription("テキスト")
    private String text;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("最大入力文字数")
    private String textLimit;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("ドロップダウンリストへの表示項目数")
    private String visibleItemCount;

    /**
     * 表示方向を取得します。<br />
     * 
     * @return 表示方向
     */
    public String getOrientation() {
        return this.orientation;
    }

    /**
     * 表示方向を設定します。<br />
     * 
     * @param orientation
     *            表示方向
     */
    public void setOrientation(final String orientation) {
        this.orientation = orientation;
    }

    /**
     * テキストを取得します。<br />
     * 
     * @return テキスト
     */
    public String getText() {
        return this.text;
    }

    /**
     * テキストを設定します。<br />
     * 
     * @param text
     *            テキスト
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * 最大入力文字数を取得します。<br />
     * 
     * @return 最大入力文字数
     */
    public String getTextLimit() {
        return this.textLimit;
    }

    /**
     * 最大入力文字数を設定します。<br />
     * 
     * @param textLimit
     *            最大入力文字数
     */
    public void setTextLimit(final String textLimit) {
        this.textLimit = textLimit;
    }

    /**
     * ドロップダウンリストへの表示項目数を取得します。<br />
     * 
     * @return ドロップダウンリストへの表示項目数
     */
    public String getVisibleItemCount() {
        return this.visibleItemCount;
    }

    /**
     * ドロップダウンリストへの表示項目数を設定します。<br />
     * 
     * @param visibleItemCount
     *            ドロップダウンリストへの表示項目数
     */
    public void setVisibleItemCount(final String visibleItemCount) {
        this.visibleItemCount = visibleItemCount;
    }

}
