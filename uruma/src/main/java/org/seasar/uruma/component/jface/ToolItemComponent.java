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

import org.eclipse.swt.widgets.ToolItem;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.base.AbstractItemComponent;

/**
 * {@link ToolItem} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class ToolItemComponent extends AbstractItemComponent implements
        EnablesDependable {

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @FieldDescription("選択不可時イメージパス")
    private String disabledImage;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("選択可能状態")
    private String enabled;

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @FieldDescription("選択時イメージパス")
    private String hotImage;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("選択状態")
    private String selection;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @FieldDescription("ツールチップテキスト")
    private String toolTipText;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("幅")
    private String width;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("依存先コンポーネントのID")
    private String enablesDependingId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("依存方法")
    private String enablesFor;

    /**
     * 選択不可時イメージパスを取得します。<br />
     * 
     * @return 選択不可時イメージパス
     */
    public String getDisabledImage() {
        return this.disabledImage;
    }

    /**
     * 選択不可時イメージパスを設定します。<br />
     * 
     * @param disabledImage
     *            選択不可時イメージパス
     */
    public void setDisabledImage(final String disabledImage) {
        this.disabledImage = disabledImage;
    }

    /**
     * 選択可能状態を取得します。<br />
     * 
     * @return 選択可能状態
     */
    public String getEnabled() {
        return this.enabled;
    }

    /**
     * 選択可能状態を設定します。<br />
     * 
     * @param enabled
     *            選択可能状態
     */
    public void setEnabled(final String enabled) {
        this.enabled = enabled;
    }

    /**
     * 選択時イメージパスを取得します。<br />
     * 
     * @return 選択時イメージパス
     */
    public String getHotImage() {
        return this.hotImage;
    }

    /**
     * 選択時イメージパスを設定します。<br />
     * 
     * @param hotImage
     *            選択時イメージパス
     */
    public void setHotImage(final String hotImage) {
        this.hotImage = hotImage;
    }

    /**
     * 選択状態を取得します。<br />
     * 
     * @return 選択状態
     */
    public String getSelection() {
        return this.selection;
    }

    /**
     * 選択状態を設定します。<br />
     * 
     * @param selection
     *            選択状態
     */
    public void setSelection(final String selection) {
        this.selection = selection;
    }

    /**
     * ツールチップテキストを取得します。<br />
     * 
     * @return ツールチップテキスト
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * ツールチップテキストを設定します。<br />
     * 
     * @param toolTipText
     *            ツールチップテキスト
     */
    public void setToolTipText(final String toolTipText) {
        this.toolTipText = toolTipText;
    }

    /**
     * 幅を取得します。<br />
     * 
     * @return 幅
     */
    public String getWidth() {
        return this.width;
    }

    /**
     * 幅を設定します。<br />
     * 
     * @param width
     *            幅
     */
    public void setWidth(final String width) {
        this.width = width;
    }

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
}
