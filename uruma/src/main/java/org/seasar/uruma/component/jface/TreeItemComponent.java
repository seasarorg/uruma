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

import org.eclipse.swt.widgets.TreeItem;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.base.AbstractUIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link TreeItem} を表すコンポーネント。<br />
 * 
 * @author y-komori
 */
@ComponentElement
public class TreeItemComponent extends AbstractUIComponent {
    /**
     * テキストです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("テキスト")
    // TreeItem には複数の setText() メソッドが存在するため、
    // 独自にレンダリングを行う
    public String text;

    /**
     * イメージパスです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("イメージ")
    // TreeItem には複数の setImage() メソッドが存在するため、
    // 独自にレンダリングを行う
    public String image;

    /**
     * 背景色です。
     */
    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @ComponentAttribute
    @FieldDescription("背景色")
    public String background;

    /**
     * チェック状態です。
     */
    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("チェック状態")
    public String checked;

    /**
     * 展開状態です。
     */
    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("展開状態")
    public String expanded;

    /**
     * フォント高さです。
     */
    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("フォント高さ")
    public String fontHeight;

    /**
     * フォント名称です。
     */
    @RenderingPolicy(conversionType = ConversionType.STRING)
    @ComponentAttribute
    @FieldDescription("フォント名称")
    public String fontName;

    /**
     * フォントスタイルです。
     */
    @RenderingPolicy(conversionType = ConversionType.STRING)
    @ComponentAttribute
    @FieldDescription("フォントスタイル")
    public String fontStyle;

    /**
     * 前景色です。
     */
    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @ComponentAttribute
    @FieldDescription("前景色")
    public String foreground;

    /**
     * グレーアウト状態です。
     */
    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("グレーアウト状態")
    public String grayed;

    private TreeItemComponent parent;

    private List<TreeItemComponent> children = new ArrayList<TreeItemComponent>();

    /*
     * @see org.seasar.uruma.component.jface.AbstractUIComponent#doRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRender(final WidgetHandle parent, final PartContext context) {
        renderChildren(context.getWidgetHandle(getId()), context);
    }

    protected void renderChildren(final WidgetHandle parent,
            final PartContext context) {
        for (TreeItemComponent child : children) {
            child.render(parent, context);
        }
    }

    /**
     * 親ツリー項目を取得します。<br />
     * 
     * @return 親ツリー項目
     */
    public TreeItemComponent getParentTreeItem() {
        return this.parent;
    }

    /**
     * 親ツリー項目を設定します。<br />
     * 
     * @param parent
     *            親ツリー項目
     */
    public void setParentTreeItem(final TreeItemComponent parent) {
        this.parent = parent;
    }

    /**
     * 子ツリー項目を追加します。<br />
     * 
     * @param treeItem
     *            子ツリー項目
     */
    public void addTreeItem(final TreeItemComponent treeItem) {
        children.add(treeItem);
    }

    /**
     * 子ツリー項目のリストを取得します。<br />
     * 
     * @return 子ツリー項目のリスト
     */
    public List<TreeItemComponent> getTreeItems() {
        return Collections.unmodifiableList(this.children);
    }
}
