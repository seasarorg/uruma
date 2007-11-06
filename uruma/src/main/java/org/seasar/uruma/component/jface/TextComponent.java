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

import org.eclipse.swt.widgets.Text;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link Text} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class TextComponent extends ControlComponent {

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @FieldDescription("テキスト")
    private String text;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("ダブルクリック許可状態")
    private String doubleClickEnabled;

    @RenderingPolicy(conversionType = ConversionType.CHAR)
    @FieldDescription("エコーキャラクタ")
    private String echoChar;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("編集可不可状態")
    private String editable;

    @RenderingPolicy(conversionType = ConversionType.SWT_CONST)
    @FieldDescription("文字方向")
    private String orientation;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("選択開始位置")
    private String selectionStart;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("選択終了位置")
    private String selectionEnd;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("タブ数")
    private String tabs;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("最大文字数")
    private String textLimit;

    @RenderingPolicy(conversionType = ConversionType.INT, setTiming = SetTiming.RENDER_AFTER)
    @FieldDescription("先頭表示位置")
    private String topIndex;

    /**
     * ダブルクリック許可状態を取得します。<br />
     * 
     * @return ダブルクリック許可状態
     */
    public String getDoubleClickEnabled() {
        return this.doubleClickEnabled;
    }

    /**
     * ダブルクリック許可状態を設定します。<br />
     * 
     * @param doubleClickEnabled
     *            ダブルクリック許可状態
     */
    public void setDoubleClickEnabled(final String doubleClickEnabled) {
        this.doubleClickEnabled = doubleClickEnabled;
    }

    /**
     * エコーキャラクタを取得します。<br />
     * 
     * @return エコーキャラクタ
     */
    public String getEchoChar() {
        return this.echoChar;
    }

    /**
     * エコーキャラクタを設定します。<br />
     * 
     * @param echoChar
     *            エコーキャラクタ
     */
    public void setEchoChar(final String echoChar) {
        this.echoChar = echoChar;
    }

    /**
     * 編集可不可状態を取得します。<br />
     * 
     * @return 編集可不可状態
     */
    public String getEditable() {
        return this.editable;
    }

    /**
     * 編集可不可状態を設定します。<br />
     * 
     * @param editable
     *            編集可不可状態
     */
    public void setEditable(final String editable) {
        this.editable = editable;
    }

    /**
     * 文字方向を取得します。<br />
     * 
     * @return 文字方向
     */
    public String getOrientation() {
        return this.orientation;
    }

    /**
     * 文字方向を設定します。<br />
     * 
     * @param orientation
     *            文字方向
     */
    public void setOrientation(final String orientation) {
        this.orientation = orientation;
    }

    /**
     * 選択終了位置を取得します。<br />
     * 
     * @return 選択終了位置
     */
    public String getSelectionEnd() {
        return this.selectionEnd;
    }

    /**
     * 選択終了位置を設定します。<br />
     * 
     * @param selectionEnd
     *            選択終了位置
     */
    public void setSelectionEnd(final String selectionEnd) {
        this.selectionEnd = selectionEnd;
    }

    /**
     * 選択開始位置を取得します。<br />
     * 
     * @return 選択開始位置
     */
    public String getSelectionStart() {
        return this.selectionStart;
    }

    /**
     * 選択開始位置を設定します。<br />
     * 
     * @param selectionStart
     *            選択開始位置
     */
    public void setSelectionStart(final String selectionStart) {
        this.selectionStart = selectionStart;
    }

    /**
     * タブ数を取得します。<br />
     * 
     * @return タブ数
     */
    public String getTabs() {
        return this.tabs;
    }

    /**
     * タブ数を設定します。<br />
     * 
     * @param tabs
     *            タブ数
     */
    public void setTabs(final String tabs) {
        this.tabs = tabs;
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
     * 最大文字数を取得します。<br />
     * 
     * @return 最大文字数
     */
    public String getTextLimit() {
        return this.textLimit;
    }

    /**
     * 最大文字数を設定します。<br />
     * 
     * @param textLimit
     *            最大文字数
     */
    public void setTextLimit(final String textLimit) {
        this.textLimit = textLimit;
    }

    /**
     * 先頭表示位置を取得します。<br />
     * 
     * @return 先頭表示位置
     */
    public String getTopIndex() {
        return this.topIndex;
    }

    /**
     * 先頭表示位置を設定します。<br />
     * 
     * @param topIndex
     *            先頭表示位置
     */
    public void setTopIndex(final String topIndex) {
        this.topIndex = topIndex;
    }
}
