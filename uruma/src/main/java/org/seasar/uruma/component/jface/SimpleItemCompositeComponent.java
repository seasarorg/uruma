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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bskuroneko
 */
public class SimpleItemCompositeComponent extends CompositeComponent {
    // TODO 必要？要確認
    private List<SimpleItemComponent> items = new ArrayList<SimpleItemComponent>();

    /**
     * 項目を追加します。<br />
     * 
     * @param itemComponent
     *            項目
     */
    public void addItem(final SimpleItemComponent itemComponent) {
        items.add(itemComponent);
    }

    /**
     * 項目のリストを取得します。<br />
     * 
     * @return 項目のリスト
     */
    public List<SimpleItemComponent> getItems() {
        return Collections.unmodifiableList(items);
    }
}
