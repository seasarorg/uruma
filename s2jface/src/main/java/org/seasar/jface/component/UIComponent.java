/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.component;

import java.util.Collection;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.renderer.Renderer;

/**
 * @author y-komori
 * 
 */
public interface UIComponent extends Iterable<UIComponent> {
    public void setId(String id);

    public String getId();

    /**
     * 親コンポーネントをセットします。</br> このとき、親コンポーネントの持つプロパティのうち、<code>Inheritance</code>
     * が <code>CHILD</code> または <code>DESCENDANT</code>
     * になっているものを自コンポーネントのプロパティに引き継ぎます。</br> その際、以下のように <code>Inheritance</code>
     * を書き換えます。</br>
     * <dl>
     * <dt>CHILDの場合
     * <dd>NONEに書き換える
     * <dt>DESCENDANTの場合
     * <dd>書き換えない(DESCENDANTのまま)
     * </dl>
     * 
     * @param parent
     *            親コンポーネント
     */
    public void setParent(UIComponent parent);

    public UIComponent getParent();

    public void addChild(UIComponent child);

    public UIComponent getChild(int index);

    public int getChildLength();

    public void setRenderer(Renderer renderer);

    public Renderer getRenderer();

    public void setRendererType(String type);

    public String getRendererType();

    public Widget getWidget();

    public String getBasePath();

    public void setBasePath(String basePath);

    public void render(Composite parent, WindowContext context);

    /**
     * プロパティを追加します。</br> 既に同名のプロパティが存在する場合、追加したプロパティに置き換えられます。
     * 
     * @param property
     *            プロパティ
     */
    public void addProperty(Property property);

    public Property getProperty(String name);

    public String getPropertyValue(String name);

    public Collection<Property> getProperties();

}
