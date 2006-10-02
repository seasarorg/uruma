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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.component.impl.TableComponent;
import org.seasar.jface.renderer.RendererSupportUtil;

/**
 * <code>Table</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TableRenderer extends
        AbstractCompositeRenderer<TableComponent, Table> {

    @Override
    protected void doRenderComposite(TableComponent compositeComponent, Table composite) {
    }
    
    @Override
    protected void doRenderAfter(Table widget, TableComponent uiComponent, Widget parent, WindowContext context) {
        String selection = uiComponent.getSelection();
        if (selection != null) {
            widget.setSelection((int[]) RendererSupportUtil.convertValue(uiComponent, selection, ConversionType.INT_ARRAY));
        }
    }
    
    @Override
    protected Class<Table> getWidgetType() {
        return Table.class;
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.BORDER;
    }
}
