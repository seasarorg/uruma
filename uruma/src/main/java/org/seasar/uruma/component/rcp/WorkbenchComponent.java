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
package org.seasar.uruma.component.rcp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.base.AbstractUIContainer;
import org.seasar.uruma.component.jface.MenuComponent;

/**
 * ワークベンチのためのコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class WorkbenchComponent extends AbstractUIContainer {
    /**
     * ID です。<br />
     */
    public String id;

    /**
     * ワークベンチウィンドウのタイトルです。
     */
    public String title;

    /**
     * ワークベンチウィンドウに表示するアイコンのイメージパスです。
     */
    public String image;

    /**
     * ワークベンチウィンドウのスタイルです。
     * 
     * @see SWT
     */
    public String style;

    /**
     * ワークベンチウィンドウの初期ウィンドウ幅です。
     */
    public String initWidth;

    /**
     * ワークベンチウィンドウの初期ウィンドウ高さです。
     */
    public String initHeight;

    /**
     * ワークベンチのメニューバーに表示する <code>menu</code> 要素の ID です。
     */
    public String menu;

    /**
     * ステータスラインの表示/非表示を指定します。
     */
    public String statusLine;

    /**
     * 最初に表示するパースペクティブの ID です。
     */
    public String initialPerspectiveId;

    /**
     * ウィンドウの位置・サイズの保存可否を指定します。
     */
    public String saveAndRestore;

    /**
     * ウィンドウの位置・サイズの保存可否を指定します。
     */
    public String menuBar;

    /**
     * クールバーの表示/非表示を指定します。
     */
    public String coolBar;

    /**
     * ファーストビューバーの表示/非表示を指定します。
     */
    public String fastViewBars;

    /**
     * パースぺクティブバーの表示/非表示を指定します。
     */
    public String perspectiveBar;

    /**
     * プログレスバーの表示/非表示を指定します。
     */
    public String progressIndicator;

    /**
     * 指定された ID を持つ {@link PerspectiveComponent} を検索して返します。<br />
     * 
     * @param rowId
     *            ID (画面定義 XML 上の ID)
     * @return {@link PerspectiveComponent} オブジェクト
     */
    public PerspectiveComponent findPerspective(final String rowId) {
        if (rowId == null) {
            return null;
        }

        for (UIElement element : children) {
            if (element instanceof PerspectiveComponent) {
                if (rowId.equals(((PerspectiveComponent) element).id)) {
                    return (PerspectiveComponent) element;
                }
            }
        }

        return null;
    }

    /**
     * {@link WorkbenchComponent} が保持する {@link PerspectiveComponent} のリストを返します。<br />
     * 
     * @return {@link PerspectiveComponent} のリスト
     */
    public List<PerspectiveComponent> getPerspectives() {
        return getElements(PerspectiveComponent.class);
    }

    /**
     * {@link WorkbenchComponent} が保持する {@link MenuComponent} のリストを返します。<br />
     * 
     * @return {@link MenuComponent} のリスト
     */
    public List<MenuComponent> getMenus() {
        return getElements(MenuComponent.class);
    }

    protected <E> List<E> getElements(final Class<E> clazz) {
        List<E> result = new ArrayList<E>(children.size());

        for (UIElement element : children) {
            if (clazz.isAssignableFrom(element.getClass())) {
                result.add(clazz.cast(element));
            }
        }

        return result;
    }
}
