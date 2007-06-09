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
package org.seasar.jface.renderer;

import java.util.HashMap;
import java.util.Map;

import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.ButtonComponent;
import org.seasar.jface.component.impl.CTabFolderComponent;
import org.seasar.jface.component.impl.CTabItemComponent;
import org.seasar.jface.component.impl.CanvasComponent;
import org.seasar.jface.component.impl.ComboComponent;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.CoolBarComponent;
import org.seasar.jface.component.impl.CoolItemComponent;
import org.seasar.jface.component.impl.GroupComponent;
import org.seasar.jface.component.impl.LabelComponent;
import org.seasar.jface.component.impl.LinkComponent;
import org.seasar.jface.component.impl.MenuComponent;
import org.seasar.jface.component.impl.MenuItemComponent;
import org.seasar.jface.component.impl.ProgressBarComponent;
import org.seasar.jface.component.impl.SashFormComponent;
import org.seasar.jface.component.impl.ScaleComponent;
import org.seasar.jface.component.impl.SliderComponent;
import org.seasar.jface.component.impl.SpinnerComponent;
import org.seasar.jface.component.impl.TabFolderComponent;
import org.seasar.jface.component.impl.TabItemComponent;
import org.seasar.jface.component.impl.TableColumnComponent;
import org.seasar.jface.component.impl.TableComponent;
import org.seasar.jface.component.impl.TableItemComponent;
import org.seasar.jface.component.impl.TextComponent;
import org.seasar.jface.component.impl.ToolBarComponent;
import org.seasar.jface.component.impl.ToolItemComponent;
import org.seasar.jface.component.impl.TreeComponent;
import org.seasar.jface.component.impl.TreeItemComponent;
import org.seasar.jface.component.impl.ViewPartComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.renderer.impl.ButtonRenderer;
import org.seasar.jface.renderer.impl.CTabFolderRenderer;
import org.seasar.jface.renderer.impl.CTabItemRenderer;
import org.seasar.jface.renderer.impl.CanvasRenderer;
import org.seasar.jface.renderer.impl.ComboRenderer;
import org.seasar.jface.renderer.impl.CompositeRenderer;
import org.seasar.jface.renderer.impl.CoolBarRenderer;
import org.seasar.jface.renderer.impl.CoolItemRenderer;
import org.seasar.jface.renderer.impl.GroupRenderer;
import org.seasar.jface.renderer.impl.LabelRenderer;
import org.seasar.jface.renderer.impl.LinkRenderer;
import org.seasar.jface.renderer.impl.MenuItemRenderer;
import org.seasar.jface.renderer.impl.MenuRenderer;
import org.seasar.jface.renderer.impl.ProgressBarRenderer;
import org.seasar.jface.renderer.impl.SashFormRenderer;
import org.seasar.jface.renderer.impl.ScaleRenderer;
import org.seasar.jface.renderer.impl.SliderRenderer;
import org.seasar.jface.renderer.impl.SpinnerRenderer;
import org.seasar.jface.renderer.impl.TabFolderRenderer;
import org.seasar.jface.renderer.impl.TabItemRenderer;
import org.seasar.jface.renderer.impl.TableColumnRenderer;
import org.seasar.jface.renderer.impl.TableItemRenderer;
import org.seasar.jface.renderer.impl.TableRenderer;
import org.seasar.jface.renderer.impl.TextRenderer;
import org.seasar.jface.renderer.impl.ToolBarRenderer;
import org.seasar.jface.renderer.impl.ToolItemRenderer;
import org.seasar.jface.renderer.impl.TreeItemRenderer;
import org.seasar.jface.renderer.impl.TreeRenderer;
import org.seasar.jface.renderer.impl.ViewPartRenderer;
import org.seasar.jface.renderer.impl.WindowRenderer;
import org.seasar.jface.util.AssertionUtil;

/**
 * レンダラを取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class RendererFactrory {
    private static final Map<Class<? extends UIComponent>, Renderer> rendererMap = new HashMap<Class<? extends UIComponent>, Renderer>();

    static {
        addRenderer(WindowComponent.class, new WindowRenderer());
        addRenderer(ViewPartComponent.class, new ViewPartRenderer());

        addRenderer(CompositeComponent.class, new CompositeRenderer());
        addRenderer(ComboComponent.class, new ComboRenderer());
        addRenderer(TabFolderComponent.class, new TabFolderRenderer());
        addRenderer(TabItemComponent.class, new TabItemRenderer());
        addRenderer(TableComponent.class, new TableRenderer());
        addRenderer(TableColumnComponent.class, new TableColumnRenderer());
        addRenderer(TableItemComponent.class, new TableItemRenderer());
        addRenderer(SpinnerComponent.class, new SpinnerRenderer());
        addRenderer(CTabFolderComponent.class, new CTabFolderRenderer());
        addRenderer(CTabItemComponent.class, new CTabItemRenderer());
        addRenderer(GroupComponent.class, new GroupRenderer());
        addRenderer(ToolBarComponent.class, new ToolBarRenderer());
        addRenderer(ToolItemComponent.class, new ToolItemRenderer());
        addRenderer(CoolBarComponent.class, new CoolBarRenderer());
        addRenderer(CoolItemComponent.class, new CoolItemRenderer());
        addRenderer(CanvasComponent.class, new CanvasRenderer());
        addRenderer(SashFormComponent.class, new SashFormRenderer());

        addRenderer(TreeComponent.class, new TreeRenderer());
        addRenderer(TreeItemComponent.class, new TreeItemRenderer());

        addRenderer(ButtonComponent.class, new ButtonRenderer());
        addRenderer(LabelComponent.class, new LabelRenderer());
        addRenderer(TextComponent.class, new TextRenderer());
        addRenderer(ProgressBarComponent.class, new ProgressBarRenderer());
        addRenderer(ScaleComponent.class, new ScaleRenderer());
        addRenderer(SliderComponent.class, new SliderRenderer());
        addRenderer(LinkComponent.class, new LinkRenderer());

        addRenderer(MenuComponent.class, new MenuRenderer());
        addRenderer(MenuItemComponent.class, new MenuItemRenderer());
    }

    /**
     * <code>UIComponent</code> クラスをキーにして、レンダラを取得します。</br>
     * 
     * @param uiComponentClass
     *            レンダラに対応する {@link UIComponent} クラス
     * @return レンダラオブジェクト
     * @throws NotFoundException
     *             レンダラが見つからなかった場合
     */
    public static Renderer getRenderer(
            final Class<? extends UIComponent> uiComponentClass) {
        Renderer renderer = rendererMap.get(uiComponentClass);
        if (renderer != null) {
            return renderer;
        } else {
            throw new NotFoundException(NotFoundException.RENDERER,
                    uiComponentClass.getName());
        }
    }

    /**
     * <code>UIComponent</code> クラスをキーとして、レンダラを登録します。</br>
     * 
     * @param uiComponentClass
     *            <code>UIComponent</code> クラス
     * @param renderer
     *            レンダラオブジェクト
     */
    public static void addRenderer(
            final Class<? extends UIComponent> uiComponentClass,
            final Renderer renderer) {
        AssertionUtil.assertNotNull("uiComponentClass", uiComponentClass);
        AssertionUtil.assertNotNull("renderer", renderer);
        rendererMap.put(uiComponentClass, renderer);
    }
}
