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
package org.seasar.jface.renderer.impl;

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableItem;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.component.impl.TableCellComponent;
import org.seasar.jface.component.impl.TableItemComponent;
import org.seasar.jface.renderer.RendererSupportUtil;
import org.seasar.jface.util.FontManager;
import org.seasar.jface.util.SWTUtil;

/**
 * <code>TableItem</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TableItemRenderer extends
        AbstractWidgetRenderer<TableItemComponent, TableItem> {

    @Override
    protected void doRender(TableItemComponent uiComponent, TableItem widget) {
        renderCells(uiComponent, widget);
    }

    private void renderCells(TableItemComponent uiComponent, TableItem widget) {
        int index = 0;
        for (Iterator<TableCellComponent> it = uiComponent.getTableCells()
                .iterator(); it.hasNext(); index++) {
            TableCellComponent cell = it.next();
            renderCell(index, cell, uiComponent, widget);
        }
    }

    private void renderCell(int index, TableCellComponent cell,
            TableItemComponent tableItemComponent, TableItem tableItem) {
        setText(index, cell, tableItemComponent, tableItem);
        setBackground(index, cell, tableItemComponent, tableItem);
        setForeground(index, cell, tableItemComponent, tableItem);
        setImage(index, cell, tableItemComponent, tableItem);
        setFont(index, cell, tableItemComponent, tableItem);
    }

    private void setText(int index, TableCellComponent cell, TableItemComponent tableItemComponent, TableItem tableItem) {
        if (cell.getText() == null) {
            return;
        }
        String text = (String) RendererSupportUtil.convertValue(tableItemComponent, cell.getText(), ConversionType.TEXT);
        tableItem.setText(index, text);
    }

    private void setBackground(int index, TableCellComponent cell, TableItemComponent tableItemComponent, TableItem tableItem) {
        if (cell.getBackground() == null) {
            return;
        }
        Color color = (Color) RendererSupportUtil.convertValue(tableItemComponent, cell.getBackground(), ConversionType.COLOR);
        tableItem.setBackground(index, color);
    }

    private void setForeground(int index, TableCellComponent cell, TableItemComponent tableItemComponent, TableItem tableItem) {
        if (cell.getForeground() == null) {
            return;
        }
        Color color = (Color) RendererSupportUtil.convertValue(tableItemComponent, cell.getForeground(), ConversionType.COLOR);
        tableItem.setForeground(index, color);
    }

    private void setImage(int index, TableCellComponent cell, TableItemComponent tableItemComponent, TableItem tableItem) {
        if (cell.getImage() == null) {
            return;
        }
        Image image = (Image) RendererSupportUtil.convertValue(tableItemComponent, cell.getImage(), ConversionType.IMAGE);
        tableItem.setImage(index, image);
    }

    private void setFont(int index, TableCellComponent cell, TableItemComponent tableItemComponent, TableItem tableItem) {
        FontData fontData = tableItem.getFont(index).getFontData()[0];

        String fontName = cell.getFontName();
        if (fontName == null) {
            fontName = fontData.getName();
        }

        String fontStyle = cell.getFontStyle();
        int style;
        if (fontStyle != null) {
            style = SWTUtil.getStyle(fontStyle);
            style = (style == SWT.NONE) ? SWT.NORMAL : style;
        } else {
            style = fontData.getStyle();
        }

        String heightStr = cell.getFontHeight();
        int height;
        if (heightStr != null) {
            height = Integer.parseInt(heightStr);
        } else {
            height = fontData.getHeight();

        }

        tableItem.setFont(index, FontManager.get(fontName, height, style));
    }

    @Override
    protected Class<TableItem> getWidgetType() {
        return TableItem.class;
    }

}
