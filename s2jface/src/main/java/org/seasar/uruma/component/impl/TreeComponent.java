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
package org.seasar.uruma.component.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Tree;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link Tree} を表すコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class TreeComponent extends CompositeComponent {
    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("ヘッダ表示状態")
    private boolean headerVisible;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("ライン表示状態")
    private boolean linesVisible;

    private List<TreeItemComponent> children = new ArrayList<TreeItemComponent>();

    /**
     * ヘッダ表示状態を取得します。<br />
     * 
     * @return ヘッダ表示状態
     */
    public boolean getHeaderVisible() {
        return this.headerVisible;
    }

    /**
     * ヘッダ表示状態を設定します。<br />
     * 
     * @param headerVisible
     *            ヘッダ表示状態
     */
    public void setHeaderVisible(final boolean headerVisible) {
        this.headerVisible = headerVisible;
    }

    /**
     * ライン表示状態を取得します。<br />
     * 
     * @return ライン表示状態
     */
    public boolean getLinesVisible() {
        return this.linesVisible;
    }

    /**
     * ライン表示状態を設定します。<br />
     * 
     * @param linesVisible
     *            ライン表示状態
     */
    public void setLinesVisible(final boolean linesVisible) {
        this.linesVisible = linesVisible;
    }

    /**
     * ツリー項目を追加します。<br />
     * 
     * @param child
     *            ツリー項目
     */
    public void addTreeItem(final TreeItemComponent child) {
        children.add(child);
    }

    /**
     * ツリー項目のリストを取得します。<br />
     * 
     * @return ツリー項目のリスト
     */
    public List<TreeItemComponent> getTreeItems() {
        return Collections.unmodifiableList(children);
    }
}
