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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Color;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.component.jface.CTabFolderComponent;
import org.seasar.uruma.component.jface.GradientInfo;
import org.seasar.uruma.component.jface.GradientItem;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.renderer.RendererSupportUtil;

/**
 * {@link CTabFolder} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class CTabFolderRenderer extends
        AbstractCompositeRenderer<CTabFolderComponent, CTabFolder> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractCompositeRenderer#doRenderComposite(org.seasar.uruma.component.jface.CompositeComponent,
     *      org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void doRenderComposite(
            final CTabFolderComponent controlComponent, final CTabFolder control) {
        setSelectionBackground(controlComponent, control);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRenderAfter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRenderAfter(final CTabFolder widget,
            final CTabFolderComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        setSelection(uiComponent, widget);
    }

    private void setSelectionBackground(
            final CTabFolderComponent controlComponent, final CTabFolder control) {
        GradientInfo gradientInfo = controlComponent
                .getSelectionBackgroundGradient();
        if (gradientInfo != null) {
            List<GradientItem> gradientItems = gradientInfo.getGradientItems();
            Color[] colors = new Color[gradientItems.size() + 1];
            int[] percents = new int[gradientItems.size()];
            colors[0] = ((Color) RendererSupportUtil.convertValue(
                    controlComponent, gradientInfo.getStartColor(),
                    ConversionType.COLOR));
            int i = 0;
            for (GradientItem item : gradientItems) {
                colors[i + 1] = ((Color) RendererSupportUtil
                        .convertValue(controlComponent, item.getColor(),
                                ConversionType.COLOR));
                percents[i] = ((Integer) RendererSupportUtil
                        .convertValue(controlComponent, item.getPercent(),
                                ConversionType.INT));
                i++;
            }
            Boolean vertical = (Boolean) RendererSupportUtil.convertValue(
                    controlComponent, gradientInfo.getVertical(),
                    ConversionType.BOOLEAN);
            control.setSelectionBackground(colors, percents, vertical);
        } else {
            String value = controlComponent.getSelectionBackground();
            if (value == null) {
                return;
            }
            Color color = (Color) RendererSupportUtil.convertValue(
                    controlComponent, value, ConversionType.COLOR);
            control.setSelectionBackground(color);
        }
    }

    private void setSelection(final CTabFolderComponent controlComponent,
            final CTabFolder control) {
        String value = controlComponent.getSelection();
        if (value == null) {
            return;
        }
        control.setSelection(Integer.parseInt(value));
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#getDefaultStyle()
     */
    @Override
    protected int getDefaultStyle() {
        return SWT.BORDER;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<CTabFolder> getWidgetType() {
        return CTabFolder.class;
    }
}
