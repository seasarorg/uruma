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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Tree;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link Tree} を表すコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class TreeComponent extends CompositeComponent {
    /**
     * ヘッダの表示状態です。<br />
     */
    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    public String headerVisible;

    /**
     * ラインの表示状態です。<br />
     */
    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    public String linesVisible;

    /**
     * デフォルトでツリーを展開する階層です。<br />
     * 【例】2を指定した場合、常に2階層目まで展開されます。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String autoExpandLevel = "1";

    private List<TreeItemComponent> children = new ArrayList<TreeItemComponent>();

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
