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
package org.seasar.uruma.renderer.impl;

import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.seasar.uruma.component.impl.ComboComponent;
import org.seasar.uruma.component.impl.SimpleItemComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.viewer.ContentsHolder;
import org.seasar.uruma.viewer.GenericLabelProvider;

/**
 * {@link ComboViewer} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class ComboViewerRenderer extends
        AbstractViewerRenderer<ComboComponent, ComboViewer, Combo> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#getDefaultStyle()
     */
    @Override
    protected int getDefaultStyle() {
        return SWT.SIMPLE;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#doRenderViewer(org.seasar.uruma.component.impl.CompositeComponent,
     *      org.eclipse.jface.viewers.Viewer)
     */
    @Override
    protected void doRenderViewer(final ComboComponent uiComponent,
            final ComboViewer viewer) {
        // setupItems(uiComponent, viewer);
    }

    @Override
    protected void doRenderAfter(final ComboViewer viewer,
            final ComboComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        setupItems(uiComponent, viewer);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<Combo> getWidgetType() {
        return Combo.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getViewerType()
     */
    @Override
    protected Class<ComboViewer> getViewerType() {
        return ComboViewer.class;
    }

    private void setupItems(final ComboComponent comboComponent,
            final ComboViewer viewer) {
        List<SimpleItemComponent> itemList = comboComponent.getItems();

        IContentProvider provider = viewer.getContentProvider();
        if (provider != null && provider instanceof ContentsHolder) {
            ContentsHolder holder = ContentsHolder.class.cast(provider);

            String[] contents = new String[itemList.size()];

            for (int i = 0; i < contents.length; i++) {
                contents[i] = itemList.get(i).getText();
            }

            holder.setContents(contents);
        } else {
            for (SimpleItemComponent simpleItemComponent : itemList) {
                viewer.add(simpleItemComponent.getText());
            }
        }
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getDefaultLabelProvider()
     */
    @Override
    protected IBaseLabelProvider getDefaultLabelProvider() {
        return new GenericLabelProvider();
    }
}
