/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;

/**
 * @author bskuroneko
 */
public class TableItemComponent extends AbstractItemComponent {

    private List<TableCellComponent> tableCells = new ArrayList<TableCellComponent>();

    @ComponentAttribute(conversionType = ConversionType.COLOR)
    private String background;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String checked;

    // TODO フォント対応
    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontHeight;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontName;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontStyle;

    @ComponentAttribute(conversionType = ConversionType.COLOR)
    private String foreground;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String grayed;

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getChecked() {
        return this.checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getFontHeight() {
        return this.fontHeight;
    }

    public void setFontHeight(String fontHeight) {
        this.fontHeight = fontHeight;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontStyle() {
        return this.fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getForeground() {
        return this.foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public String getGrayed() {
        return this.grayed;
    }

    public void setGrayed(String grayed) {
        this.grayed = grayed;
    }
    
    public void addTableCell(TableCellComponent cell) {
        this.tableCells.add(cell);
    }

    public List<TableCellComponent> getTableCells() {
        return tableCells;
    }

}
