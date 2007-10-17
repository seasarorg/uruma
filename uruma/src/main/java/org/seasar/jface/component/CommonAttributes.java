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
package org.seasar.jface.component;

/**
 * 一括指定のための属性を保持するためのコンポーネントです。<br />
 * 
 * @author y-komori
 */
public interface CommonAttributes extends UIElement {

    public String getBackGround();

    public void setBackGround(String backGround);

    public String getFontHeight();

    public void setFontHeight(String fontHeight);

    public String getFontName();

    public void setFontName(String fontName);

    public String getFontStyle();

    public void setFontStyle(String fontStyle);

    public String getForeGround();

    public void setForeGround(String foreGround);

    public String getHeight();

    public void setHeight(String height);

    public String getWidth();

    public void setWidth(String width);

}
