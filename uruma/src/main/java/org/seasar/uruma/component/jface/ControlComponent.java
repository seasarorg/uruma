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
package org.seasar.uruma.component.jface;

import org.eclipse.swt.widgets.Control;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.UIControlComponent;
import org.seasar.uruma.component.base.AbstractUIComponent;

/**
 * {@link Control} を表す基底コンポーネントクラスです。<br />
 * 
 * @author y-komori
 */
public abstract class ControlComponent extends AbstractUIComponent implements
        UIControlComponent, EnablesDependable {
    private LayoutDataInfo layoutDataInfo;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @ComponentAttribute
    @FieldDescription("背景色")
    public String background;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("背景イメージパス")
    public String backgroundImage;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("イネーブル状態")
    public String enabled;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("フォント高さ")
    public String fontHeight;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("フォント名称")
    public String fontName;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("フォントスタイル")
    public String fontStyle;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @ComponentAttribute
    @FieldDescription("前景色")
    public String foreground;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("高さ")
    public String height;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @ComponentAttribute
    @FieldDescription("ツールチップテキスト")
    public String toolTipText;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("可視状態")
    public String visible;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("幅")
    public String width;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("X座標")
    public String x;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("Y座標")
    public String y;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("メニューのID")
    public String menu;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("依存先コンポーネントのID")
    public String enablesDependingId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("依存方法")
    public String enablesFor;

    /*
     * @see org.seasar.uruma.component.EnablesDependable#getEnablesDependingId()
     */
    public String getEnablesDependingId() {
        return this.enablesDependingId;
    }

    /**
     * 依存先コンポーネントの ID を設定します。<br />
     * 
     * @param enablesDependingId
     *            依存先コンポーネントの ID
     */
    public void setEnablesDependingId(final String enablesDependingId) {
        this.enablesDependingId = enablesDependingId;
    }

    /*
     * @see org.seasar.uruma.component.EnablesDependable#getEnablesForType()
     */
    public String getEnablesFor() {
        return this.enablesFor;
    }

    /**
     * 依存方法を設定します。<br />
     * 
     * @param enablesFor
     *            依存方法
     */
    public void setEnablesFor(final String enablesFor) {
        this.enablesFor = enablesFor;
    }

    /*
     * @see org.seasar.uruma.component.UIControlComponent#getLayoutDataInfo()
     */
    public LayoutDataInfo getLayoutDataInfo() {
        return this.layoutDataInfo;
    }

    /*
     * @see org.seasar.uruma.component.UIControlComponent#setLayoutDataInfo(org.seasar.uruma.component.LayoutDataInfo)
     */
    public void setLayoutDataInfo(final LayoutDataInfo layoutDataInfo) {
        this.layoutDataInfo = layoutDataInfo;
    }
}
