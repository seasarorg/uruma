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

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.component.impl.CTabFolderComponent;
import org.seasar.jface.component.impl.GradientInfo;
import org.seasar.jface.component.impl.GradientItem;
import org.seasar.jface.renderer.RendererSupportUtil;

/**
 * <code>CTabFolder</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class CTabFolderRenderer extends
        AbstractCompositeRenderer<CTabFolderComponent, CTabFolder> {

    @Override
    protected void doRenderComposite(CTabFolderComponent controlComponent,
            CTabFolder control) {
        setSelectionBackground(controlComponent, control);
    }
    
    @Override
    protected void doRenderAfter(CTabFolder widget, CTabFolderComponent uiComponent, Widget parent, WindowContext context) {
        setSelection(uiComponent, widget);
    }

    private void setSelectionBackground(CTabFolderComponent controlComponent,
            CTabFolder control) {
        GradientInfo gradientInfo = controlComponent.getSelectionBackgroundGradient();
        if (gradientInfo != null) {
            List<GradientItem> gradientItems = gradientInfo.getGradientItems(); 
            Color[] colors = new Color[gradientItems.size() + 1];
            int[] percents = new int[gradientItems.size()];
            colors[0] = ((Color) RendererSupportUtil.convertValue(controlComponent, gradientInfo.getStartColor(), ConversionType.COLOR));
            int i = 0;
            for (GradientItem item : gradientItems) {
                colors[i+1] = ((Color) RendererSupportUtil.convertValue(controlComponent, item.getColor(), ConversionType.COLOR));
                percents[i] = ((Integer) RendererSupportUtil.convertValue(controlComponent, item.getPercent(), ConversionType.INT));
                i++;
            }
            Boolean vertical = (Boolean) RendererSupportUtil.convertValue(controlComponent, gradientInfo.getVertical(), ConversionType.BOOLEAN);
            control.setSelectionBackground(colors, percents, vertical);
        }
        else {
            String value = controlComponent.getSelectionBackground();
            if (value == null) {
                return;
            }
            Color color = (Color) RendererSupportUtil.convertValue(
                    controlComponent, value, ConversionType.COLOR);
            control.setSelectionBackground(color);
        }
    }

    private void setSelection(CTabFolderComponent controlComponent, CTabFolder control) {
        String value = controlComponent.getSelection();
        if (value == null) {
            return;
        }
        control.setSelection(Integer.parseInt(value));
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.BORDER;
    }

    @Override
    protected Class<CTabFolder> getWidgetType() {
        return CTabFolder.class;
    }
}
