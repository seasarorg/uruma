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
package org.seasar.uruma.component.jface;

import org.eclipse.swt.widgets.CoolItem;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.base.AbstractUIContainerItemComponent;

/**
 * {@link CoolItem} に対応するコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class CoolItemComponent extends AbstractUIContainerItemComponent
        implements EnablesDependable {
    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("依存先コンポーネントのID")
    private String enablesDependingId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("依存方法")
    private String enablesForType;

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
        return this.enablesForType;
    }

    /**
     * 依存方法を設定します。<br />
     * 
     * @param enablesForType
     *            依存方法
     */
    public void setEnablesForType(final String enablesForType) {
        this.enablesForType = enablesForType;
    }

}
