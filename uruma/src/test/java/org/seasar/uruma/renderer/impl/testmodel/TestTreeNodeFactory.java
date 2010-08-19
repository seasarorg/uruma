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
import java.util.List;

/**
 * {@link TestTreeNode} によるテスト用ツリー構造を生成するためのファクトリです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class TestTreeNodeFactory {
    public static TestTreeNode generate(final int columnNum) {
        TestTreeNode root = new TestTreeNode();
        generateLabel(root, "Item", columnNum);

        List<TestTreeNode> layer1 = generateLayer(root, "Item", 4, columnNum);

        List<TestTreeNode> layer2_1 = generateLayer(layer1.get(0), "Item-1", 3, columnNum);
        List<TestTreeNode> layer2_2 = generateLayer(layer1.get(1), "Item-2", 5, columnNum);
        List<TestTreeNode> layer2_3 = generateLayer(layer1.get(2), "Item-3", 2, columnNum);
        List<TestTreeNode> layer2_4 = generateLayer(layer1.get(3), "Item-4", 1, columnNum);

        List<TestTreeNode> layer3_1 = generateLayer(layer2_1.get(0), "Item-1-1", 3, columnNum);
        List<TestTreeNode> layer3_2 = generateLayer(layer2_2.get(1), "Item-2-2", 3, columnNum);
        List<TestTreeNode> layer3_3 = generateLayer(layer2_2.get(2), "Item-2-3", 3, columnNum);
        List<TestTreeNode> layer3_4 = generateLayer(layer2_2.get(3), "Item-2-4", 3, columnNum);

        List<TestTreeNode> layer4_1 = generateLayer(layer3_2.get(0), "Item-2-2-1", 10, columnNum);
        return root;
    }

    private static List<TestTreeNode> generateLayer(final TestTreeNode parent,
            final String labelPrefix, final int itemNum, final int columnNum) {
        List<TestTreeNode> result = new ArrayList<TestTreeNode>();
        for (int i = 1; i <= itemNum; i++) {
            TestTreeNode node = new TestTreeNode(parent);
            generateLabel(node, labelPrefix + "-" + i, columnNum);
            result.add(node);
        }
        return result;
    }

    private static void generateLabel(final TestTreeNode target, final String labelPrefix,
            final int columnNum) {
        for (int i = 1; i <= columnNum; i++) {
            target.addLabel(labelPrefix + ":" + i);
        }
    }
}
