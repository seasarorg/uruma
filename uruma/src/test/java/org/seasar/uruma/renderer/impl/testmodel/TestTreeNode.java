/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package org.seasar.uruma.renderer.impl.testmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * テスト用のツリー構造を表すモデルです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class TestTreeNode {
    private TestTreeNode parent;

    private List<TestTreeNode> children = new ArrayList<TestTreeNode>();

    private List<String> labels = new ArrayList<String>();

    /**
     * {@link TestTreeNode} を構築します。<br />
     */
    public TestTreeNode() {
        super();
    }

    /**
     * {@link TestTreeNode} を構築します。<br />
     */
    public TestTreeNode(final TestTreeNode parent) {
        this();
        parent.addChild(this);
    }

    /**
     * 親を設定します。<br />
     * 
     * @param parent
     *        親 {@link TestTreeNode}
     */
    public void setParent(final TestTreeNode parent) {
        if (parent != null) {
            this.parent = parent;
        } else {
            throw new IllegalArgumentException("parent can't be bull");
        }
    }

    /**
     * 親を返します。<br />
     * 
     * @return 親 {@link TestTreeNode}、存在しない場合は {@code <code>null</code>}
     */
    public TestTreeNode getParent() {
        return this.parent;
    }

    /**
     * 子ノードを持つかどうかを返します。<br />
     * 
     * @return 子ノードを持つ場合、{@code true}
     */
    public boolean hasChildren() {
        return children.size() > 0;
    }

    /**
     * すべての子ノードを返します。<br />
     * 
     * @return すべての子ノード
     */
    public List<TestTreeNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /**
     * 子ノードを追加します。<br />
     * 
     * @param child
     *        子ノード
     */
    public void addChild(final TestTreeNode child) {
        if (child != null) {
            children.add(child);
        } else {
            throw new IllegalArgumentException("child can't be null");
        }
    }

    /**
     * ラベルを追加します。<br />
     * 
     * @param label
     *        ラベル
     */
    public void addLabel(final String label) {
        if (label != null) {
            labels.add(label);
        } else {
            throw new IllegalArgumentException("label can't be null");
        }
    }

    /**
     * 複数のラベルを同時に追加します。<br />
     * 
     * @param labels
     *        ラベルの配列
     */
    public void addLabel(final String... labels) {
        if (labels != null) {
            for (String label : labels) {
                addLabel(label);
            }
        } else {
            throw new IllegalArgumentException("labels can't be null");
        }
    }

    /**
     * ラベルの数を返します。<br />
     * 
     * @return ラベルの数
     */
    public int getLabelCount() {
        return labels.size();
    }

    /**
     * 指定されたラベルを返します。<br />
     * 
     * @param index
     *        0から始まるラベルのインデックス
     * @return ラベル
     */
    public String getLabel(final int index) {
        return labels.get(index);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        for (String label : labels) {
            buf.append(label);
            buf.append(" ");
        }
        buf.append("}");
        return buf.toString();
    }
}
