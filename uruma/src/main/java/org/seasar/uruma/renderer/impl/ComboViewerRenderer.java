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

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.SWT;
import org.seasar.uruma.component.impl.ComboComponent;
import org.seasar.uruma.component.impl.SimpleItemComponent;
import org.seasar.uruma.viewer.GenericLabelProvider;

/**
 * {@link ComboViewer} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class ComboViewerRenderer extends
        AbstractViewerRenderer<ComboComponent, ComboViewer> {

    @Override
    protected int getDefaultStyle() {
        return SWT.SIMPLE;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      org.eclipse.jface.viewers.Viewer)
     */
    @Override
    protected void doRender(final ComboComponent uiComponent,
            final ComboViewer viewer) {
        setupItems(uiComponent, viewer);
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
        for (SimpleItemComponent item : comboComponent.getItems()) {
            viewer.add(item.getText());
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
