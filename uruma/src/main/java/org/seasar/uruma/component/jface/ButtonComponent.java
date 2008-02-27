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

import org.eclipse.swt.widgets.Button;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link Button} に対応するコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class ButtonComponent extends ControlComponent {
    @RenderingPolicy(conversionType = ConversionType.SWT_CONST)
    @FieldDescription("アライメント")
    private String alignment;

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @FieldDescription("イメージパス")
    private String image;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("選択状態")
    private String selection;

    @RenderingPolicy(conversionType = ConversionType.STRING)
    @FieldDescription("テキスト")
    private String text;

    /**
     * アライメントを取得します。<br />
     * 
     * @return アライメント
     */
    public String getAlignment() {
        return this.alignment;
    }

    /**
     * アライメントを設定します。<br />
     * 
     * @param alignment
     *            アライメント
     */
    public void setAlignment(final String alignment) {
        this.alignment = alignment;
    }

    /**
     * イメージパスを取得します。<br />
     * 
     * @return イメージパス
     */
    public String getImage() {
        return this.image;
    }

    /**
     * イメージパスを設定します。<br />
     * 
     * @param image
     *            イメージパス
     */
    public void setImage(final String image) {
        this.image = image;
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
}
