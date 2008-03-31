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
import java.util.List;

import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * グラデーション色に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
@ComponentElement
public class GradientInfo extends AbstractUIElement {
    @ComponentAttribute
    @FieldDescription("グラデーション方向")
    public String vertical;

    @ComponentAttribute
    @FieldDescription("開始色")
    public String startColor;

    private List<GradientItem> items = new ArrayList<GradientItem>();

    /**
     * {@link GradientItem} を追加します。<br />
     * 
     * @param item
     *            {@link GradientItem} オブジェクト
     */
    public void addGradientItem(final GradientItem item) {
        items.add(item);
    }

    /**
     * {@link GradientItem} のリストを取得します。<br />
     * 
     * @return {@link GradientItem} のリスト
     */
    public List<GradientItem> getGradientItems() {
        return items;
    }
}
