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
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
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
@ComponentElement
public class ToolItemComponent extends AbstractItemComponent implements
        EnablesDependable {

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @ComponentAttribute
    @FieldDescription("選択不可時イメージパス")
    public String disabledImage;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("選択可能状態")
    public String enabled;

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @ComponentAttribute
    @FieldDescription("選択時イメージパス")
    public String hotImage;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("選択状態")
    public String selection;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @ComponentAttribute
    @FieldDescription("ツールチップテキスト")
    public String toolTipText;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("幅")
    public String width;

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
}
