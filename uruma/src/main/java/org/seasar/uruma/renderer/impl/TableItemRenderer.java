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
package org.seasar.uruma.renderer.impl;

import java.util.Iterator;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableItem;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.jface.TableCellComponent;
import org.seasar.uruma.component.jface.TableItemComponent;
import org.seasar.uruma.renderer.RendererSupportUtil;

/**
 * {@link TableItem} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TableItemRenderer extends AbstractWidgetRenderer<TableItemComponent, TableItem> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected void doRender(final TableItemComponent uiComponent, final TableItem widget) {
        setFont(uiComponent, widget);
        setImage(uiComponent, widget);
        setText(uiComponent, widget);
        renderCells(uiComponent, widget);
    }

    private void setImage(final TableItemComponent uiComponent, final TableItem widget) {
        if (uiComponent.image == null) {
            return;
        }
        Image image = getImage(uiComponent, uiComponent.image);
        widget.setImage(image);
    }

    private void setText(final TableItemComponent uiComponent, final TableItem widget) {
        String text = uiComponent.text;
        if (text == null) {
            return;
        }
        String convertedText = RendererSupportUtil.convertText(text);
        widget.setText(convertedText);
    }

    private void setFont(final TableItemComponent uiComponent, final TableItem widget) {
        if (uiComponent.fontName == null && uiComponent.fontStyle == null
                && uiComponent.fontHeight == null) {
            return;
        }
        Font font = RendererSupportUtil.getFont(widget.getFont(), uiComponent.fontName,
                uiComponent.fontStyle, uiComponent.fontHeight);
        widget.setFont(font);
    }

    private void renderCells(final TableItemComponent uiComponent, final TableItem widget) {
        int index = 0;
        for (Iterator<TableCellComponent> it = uiComponent.getTableCells().iterator(); it.hasNext(); index++) {
            TableCellComponent cell = it.next();
            renderCell(index, cell, uiComponent, widget);
        }
    }

    private void renderCell(final int index, final TableCellComponent cell,
            final TableItemComponent tableItemComponent, final TableItem tableItem) {
        setText(index, cell, tableItemComponent, tableItem);
        setBackground(index, cell, tableItemComponent, tableItem);
        setForeground(index, cell, tableItemComponent, tableItem);
        setImage(index, cell, tableItemComponent, tableItem);
        setFont(index, cell, tableItemComponent, tableItem);
    }

    private void setText(final int index, final TableCellComponent cell,
            final TableItemComponent tableItemComponent, final TableItem tableItem) {
        if (cell.text == null) {
            return;
        }
        String text = RendererSupportUtil.convertText(cell.text);
        tableItem.setText(index, text);
    }

    private void setBackground(final int index, final TableCellComponent cell,
            final TableItemComponent tableItemComponent, final TableItem tableItem) {
        if (cell.background == null) {
            return;
        }
        Color color = RendererSupportUtil.convertColor(cell.background);
        tableItem.setBackground(index, color);
    }

    private void setForeground(final int index, final TableCellComponent cell,
            final TableItemComponent tableItemComponent, final TableItem tableItem) {
        if (cell.foreground == null) {
            return;
        }
        Color color = RendererSupportUtil.convertColor(cell.foreground);
        tableItem.setForeground(index, color);
    }

    private void setImage(final int index, final TableCellComponent cell,
            final TableItemComponent tableItemComponent, final TableItem tableItem) {
        if (cell.image == null) {
            return;
        }
        Image image = getImage(tableItemComponent, cell.image);
        tableItem.setImage(index, image);
    }

    private void setFont(final int index, final TableCellComponent cell,
            final TableItemComponent tableItemComponent, final TableItem tableItem) {
        if (cell.fontName == null && cell.fontStyle == null && cell.fontHeight == null) {
            return;
        }
        Font defaultFont = tableItem.getFont(index);
        Font font = RendererSupportUtil.getFont(defaultFont, cell.fontName, cell.fontStyle,
                cell.fontHeight);
        tableItem.setFont(index, font);
    }

    private Image getImage(final UIComponent uiComponent, final String value) {
        return RendererSupportUtil.convertImage(value, uiComponent.getParentURL(),
                getResourceRegistry());
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<TableItem> getWidgetType() {
        return TableItem.class;
    }

}
