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

import org.eclipse.swt.widgets.TabFolder;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link TabFolder} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class TabFolderComponent extends CompositeComponent {

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("現在位置")
    private String selection;

    /**
     * 現在位置を取得します。<br />
     * 
     * @return 現在位置
     */
    public String getSelection() {
        return this.selection;
    }

    /**
     * 現在位置を設定します。<br />
     * 
     * @param selection
     *            現在位置
     */
    public void setSelection(final String selection) {
        this.selection = selection;
    }
}
