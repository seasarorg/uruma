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

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.seasar.jface.component.impl.ComboComponent;
import org.seasar.jface.component.impl.SimpleItemComponent;
import org.seasar.jface.exception.RenderException;
import org.seasar.jface.util.S2ContainerUtil;
import org.seasar.jface.viewer.ComboViewerAdapter;
import org.seasar.jface.viewer.GenericLabelProvider;
import org.seasar.jface.viewer.GenericTableLabelProvider;

/**
 * <code>Combo</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class ComboRenderer extends
        AbstractCompositeRenderer<ComboComponent, Combo> {

    private static final String LABEL_PROVIDER = "LabelProvider";

    @Override
    protected int getDefaultStyle() {
        return SWT.SIMPLE;
    }

    @Override
    protected void doRenderComposite(ComboComponent comboComponent, Combo combo) {
        ComboViewer viewer = new ComboViewer(combo);
        ComboViewerAdapter viewerAdapter = new ComboViewerAdapter(viewer);
        getContext().putViewerAdapter(combo, viewerAdapter);

        String id = comboComponent.getId();
        setupLabelProvider(viewer, id);

        addItems(comboComponent, combo);
    }

    private void setupLabelProvider(ComboViewer viewer, String id) {
        IBaseLabelProvider provider = null;
        if (id != null) {
            Object defined = S2ContainerUtil.getComponentNoException(id
                    + LABEL_PROVIDER);
            if (defined != null) {
                if (defined instanceof ILabelProvider) {
                    provider = (IBaseLabelProvider) defined;
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            provider, ILabelProvider.class.getName());
                }
            }
        }

        if (provider == null) {
            // ユーザー定義のLabelProviderが存在しない場合、
            // デフォルトのLabelProviderを設定する
            provider = new GenericLabelProvider();
        }
        viewer.setLabelProvider(provider);
    }

    private void addItems(ComboComponent controlComponent, Combo control) {
        for (SimpleItemComponent item : controlComponent.getItems()) {
            control.add(item.getText());
        }
    }

    @Override
    protected Class<Combo> getWidgetType() {
        return Combo.class;
    }
}
