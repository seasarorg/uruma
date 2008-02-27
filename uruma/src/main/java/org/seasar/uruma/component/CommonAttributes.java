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
package org.seasar.uruma.component;

/**
 * 一括指定のための属性を保持するためのコンポーネントです。<br />
 * 
 * @author y-komori
 */
public interface CommonAttributes extends UIElement {

    /**
     * 背景色を取得します。<br />
     * 
     * @return 背景色
     */
    public String getBackGround();

    /**
     * 背景色を設定します。<br />
     * 
     * @param backGround
     *            背景色
     */
    public void setBackGround(String backGround);

    /**
     * フォントの高さを取得します。<br />
     * 
     * @return フォントの高さ
     */
    public String getFontHeight();

    /**
     * フォントの高さを設定します。<br />
     * 
     * @param fontHeight
     *            フォントの高さ
     */
    public void setFontHeight(String fontHeight);

    /**
     * フォント名称を取得します。<br />
     * 
     * @return フォント名称
     */
    public String getFontName();

    /**
     * フォント名称を設定します。<br />
     * 
     * @param fontName
     *            フォント名称
     */
    public void setFontName(String fontName);

    /**
     * フォントスタイルを取得します。<br />
     * 
     * @return フォントスタイル
     */
    public String getFontStyle();

    /**
     * フォントスタイルを設定します。<br />
     * 
     * @param fontStyle
     *            フォントスタイル
     */
    public void setFontStyle(String fontStyle);

    /**
     * 前景色を取得します。<br />
     * 
     * @return 前景色
     */
    public String getForeGround();

    /**
     * 前景色を設定します。<br />
     * 
     * @param foreGround
     *            前景色
     */
    public void setForeGround(String foreGround);

    /**
     * コンポーネントの高さを取得します。<br />
     * 
     * @return コンポーネントの高さ
     */
    public String getHeight();

    /**
     * コンポーネントの高さを設定します。<br />
     * 
     * @param height
     *            コンポーネントの高さ
     */
    public void setHeight(String height);

    /**
     * コンポーネントの幅を取得します。<br />
     * 
     * @return コンポーネントの幅
     */
    public String getWidth();

    /**
     * コンポーネントの幅を設定します。<br />
     * 
     * @param width
     *            コンポーネントの幅
     */
    public void setWidth(String width);

}
