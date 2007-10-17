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

import org.eclipse.swt.widgets.TreeItem;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link TreeItem} を表すコンポーネント。<br />
 * 
 * @author y-komori
 */
public class TreeItemComponent extends AbstractUIComponent {
    // TreeItem には複数の setText() メソッドが存在するため、
    // 独自にレンダリングを行う
    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("テキスト")
    private String text;

    // TreeItem には複数の setImage() メソッドが存在するため、
    // 独自にレンダリングを行う
    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("イメージパス")
    private String image;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @FieldDescription("背景色")
    private String background;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("チェック状態")
    private boolean checked;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("展開状態")
    private boolean expanded;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("フォント高さ")
    private int fontHeight;

    @RenderingPolicy(conversionType = ConversionType.STRING)
    @FieldDescription("フォント名称")
    private String fontName;

    @RenderingPolicy(conversionType = ConversionType.STRING)
    @FieldDescription("フォントスタイル")
    private String fontStyle;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @FieldDescription("前景色")
    private String foreground;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("グレーアウト状態")
    private boolean grayed;

    @FieldDescription("親ツリー項目")
    private TreeItemComponent parent;

    private List<TreeItemComponent> children = new ArrayList<TreeItemComponent>();

    /*
     * @see org.seasar.uruma.component.impl.AbstractUIComponent#doRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRender(final WidgetHandle parent, final PartContext context) {
        renderChildren(getWidgetHandle(), context);
    }

    protected void renderChildren(final WidgetHandle parent,
            final PartContext context) {
        for (TreeItemComponent child : children) {
            child.render(parent, context);
        }
    }

    /**
     * イメージパスを取得します。<br />
     * 
     * @return イメージパス
     */
    public String getImage() {
        return this.image;
    }

    /**
     * イメージパスを設定します。<br />
     * 
     * @param image
     *            イメージパス
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * テキストを取得します。<br />
     * 
     * @return テキスト
     */
    public String getText() {
        return this.text;
    }

    /**
     * テキストを設定します。<br />
     * 
     * @param text
     *            テキスト
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * 背景色を取得します。<br />
     * 
     * @return 背景色
     */
    public String getBackground() {
        return this.background;
    }

    /**
     * 背景色を設定します。<br />
     * 
     * @param background
     *            背景色
     */
    public void setBackground(final String background) {
        this.background = background;
    }

    /**
     * チェック状態を取得します。<br />
     * 
     * @return チェック状態
     */
    public boolean getChecked() {
        return this.checked;
    }

    /**
     * チェック状態を設定します。<br />
     * 
     * @param checked
     *            チェック状態
     */
    public void setChecked(final boolean checked) {
        this.checked = checked;
    }

    /**
     * 展開状態を取得します。<br />
     * 
     * @return 展開状態
     */
    public boolean getExpanded() {
        return this.expanded;
    }

    /**
     * 展開状態を設定します。<br />
     * 
     * @param expanded
     *            展開状態
     */
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * フォント高さを取得します。<br />
     * 
     * @return フォント高さ
     */
    public int getFontHeight() {
        return this.fontHeight;
    }

    /**
     * フォント高さを設定します。<br />
     * 
     * @param fontHeight
     *            フォント高さ
     */
    public void setFontHeight(final int fontHeight) {
        this.fontHeight = fontHeight;
    }

    /**
     * フォント名称を取得します。<br />
     * 
     * @return フォント名称
     */
    public String getFontName() {
        return this.fontName;
    }

    /**
     * フォント名称を設定します。<br />
     * 
     * @param fontName
     *            フォント名称
     */
    public void setFontName(final String fontName) {
        this.fontName = fontName;
    }

    /**
     * フォントスタイルを取得します。<br />
     * 
     * @return フォントスタイル
     */
    public String getFontStyle() {
        return this.fontStyle;
    }

    /**
     * フォントスタイルを設定します。<br />
     * 
     * @param fontStyle
     *            フォントスタイル
     */
    public void setFontStyle(final String fontStyle) {
        this.fontStyle = fontStyle;
    }

    /**
     * 前景色を取得します。<br />
     * 
     * @return 前景色
     */
    public String getForeground() {
        return this.foreground;
    }

    /**
     * 前景色を設定します。<br />
     * 
     * @param foreground
     *            前景色
     */
    public void setForeground(final String foreground) {
        this.foreground = foreground;
    }

    /**
     * グレーアウト状態を取得します。<br />
     * 
     * @return グレーアウト状態
     */
    public boolean getGrayed() {
        return this.grayed;
    }

    /**
     * グレーアウト状態を設定します。<br />
     * 
     * @param grayed
     *            グレーアウト状態
     */
    public void setGrayed(final boolean grayed) {
        this.grayed = grayed;
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
