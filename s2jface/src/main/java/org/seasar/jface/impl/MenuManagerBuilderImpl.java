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
package org.seasar.jface.impl;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.log.Logger;
import org.seasar.jface.MenuManagerBuilder;
import org.seasar.jface.binding.DummyAction;
import org.seasar.jface.component.Menu;
import org.seasar.jface.component.MenuItem;
import org.seasar.jface.component.MenuItemType;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.S2ContainerUtil;

/**
 * @author y-komori
 * 
 */
public class MenuManagerBuilderImpl implements MenuManagerBuilder {
    private static Logger logger = Logger
            .getLogger(MenuManagerBuilderImpl.class);

    /*
     * @see org.seasar.jface.MenuManagerBuilder#createMenuManager(org.seasar.jface.component.Menu)
     */
    public MenuManager createMenuManager(final Menu menu) {
        MenuManager menuBar = new MenuManager(menu.getText());
        createMenu(menu, menuBar);
        return menuBar;
    }

    /**
     * <code>Menu</code> オブジェクトの情報をを元にして、<code>MenuManager</code>
     * へメニュー項目を追加します。<br />
     * 
     * @param parentMenu
     *            <code>Menu</code> オブジェクト
     * @param parentManager
     *            メニュー項目を追加する <code>MenuManager</code> オブジェクト
     */
    protected void createMenu(final Menu parentMenu,
            final MenuManager parentManager) {
        setVisible(parentManager, parentMenu);

        for (MenuItem item : parentMenu) {
            if (item.getType() == MenuItemType.MENU) {
                // サブメニューの生成
                MenuManager menuManager = new MenuManager(item.getText());
                parentManager.add(menuManager);
                createMenu((Menu) item, menuManager);
            } else if (item.getType() == MenuItemType.SEPARATOR) {
                // セパレータの生成
                parentManager.add(new Separator());
            } else {
                // メニュー項目の生成
                createItem(item, parentManager);
            }
        }
    }

    /**
     * 引数で渡された <code>MenuItem</code> の <code>id</code> に対応したコンポーネントを
     * <code>S2Container</code> から検索し、メニュー項目へバンドします。<br />
     * <p>
     * コンポーネント名は [<code>id</code>Action] という名前で検索します。
     * </p>
     * <dl>
     * <dt>【例】
     * <dd> id が "fileMenu" の場合、"fileMenuAction" という名称のコンポーネントを検索します。
     * </dl>
     * 
     * @param item
     *            <code>MenuItem</code> オブジェクト
     * @param parentManager
     *            親 <code>MenuManager</code>
     */
    protected void createItem(final MenuItem item,
            final MenuManager parentManager) {
        String id = item.getId();
        IAction action = null;
        if (id != null) {
            // id が指定されている場合はコンテナからコンポーネントの取得を試みる
            String componentName = id + "Action";
            // TODO コンポーネントが見つからない場合、どのメニューに対して見つからないのか分かりやすいようにする
            ComponentDef cd = S2ContainerUtil.getComponentDef(componentName);
            action = checkAction(cd.getComponent(), cd.getComponentName(), item);
        } else {
            // id が指定されていない場合はバインドできないため、ダミーアクションを登録
            action = createDummyAction(item);
            logIdNotFound(item);
        }

        // 各種属性の設定
        setText(action, item);
        setDescription(action, item);
        setImage(action, item);
        setDisableImage(action, item);
        setHoverImage(action, item);
        setToolTipText(action, item);
        setEnabled(action, item);
        MenuItemType type = item.getType();
        if ((type == MenuItemType.CHECK) || (type == MenuItemType.RADIO)) {
            setChecked(action, item);
        }

        // メニューへの登録を行う
        parentManager.add(action);
    }

    /**
     * 引数で渡されたコンポーネントが引数で渡された <code>MenuItem</code> の <code>type</code>
     * に合致した <code>IAction</code> の実装クラスであることをチェックします。<br />
     * <p>
     * 以下の場合はエラーログを出力し、ダミーアクションを返します。
     * <ul>
     * <li>コンポーネントが <code>IAction</code> のサブクラスでない場合.
     * <li><code>MenuItemType</code> と <code>IAction</code> のスタイルが異なる場合.
     * </ul>
     * </p>
     * 
     * @param component
     *            チェック対象コンポーネント
     * @param componentName
     *            チェック対象コンポーネントの名称
     * @param menuItem
     *            <code>MenuItem</code> オブジェクト
     * @return 正しい場合は <code>IAction</code> にキャストされたコンポーネント。誤っている場合は
     *         <code>DummyAction</code>
     * @see MenuItemType
     * @see DummyAction
     */
    protected IAction checkAction(final Object component,
            final String componentName, final MenuItem menuItem) {
        if (component == null) {
            // コンポーネントが存在しないため、仮のアクションを登録する
            logComponentNotFound(componentName, menuItem);
            return createDummyAction(menuItem);
        }

        if (!(component instanceof IAction)) {
            // コンポーネントの型が異なるため、仮のアクションを登録する
            // TODO 将来はIActionでラップしてアノテーションされたメソッドを呼び出せるようにする。
            logComponentNotFound(componentName, menuItem);
            return createDummyAction(menuItem);
        }

        IAction action = (IAction) component;
        int actionStyle = action.getStyle();
        switch (menuItem.getType()) {
        case ACTION:
            if (actionStyle == IAction.AS_PUSH_BUTTON) {
                return action;
            } else {
                break;
            }

        case RADIO:
            if (actionStyle == IAction.AS_RADIO_BUTTON) {
                return action;
            } else {
                break;
            }

        case CHECK:
            if (actionStyle == IAction.AS_CHECK_BOX) {
            } else {
                break;
            }

        default:
            break;
        }

        logActionTypeMissMatch(componentName, menuItem);
        return createDummyAction(menuItem);
    }

    /**
     * <code>MenuItem</code> に対応するダミーアクションを生成します。<br />
     * 
     * @param menuItem
     *            <code>MenuItem</code> オブジェクト
     * @return 生成されたダミーアクション
     */
    protected DummyAction createDummyAction(final MenuItem menuItem) {
        return new DummyAction(menuItem.getText(), MenuItemType
                .getActionStyle(menuItem.getType()));
    }

    /**
     * コンポーネントが見つからず、仮のアクションを登録する際のログを出力します。<br />
     * 
     * @param componentName
     *            コンポーネント名
     * @param menuItem
     *            メニューアイテム
     */
    protected void logComponentNotFound(final String componentName,
            final MenuItem menuItem) {
        logger.log("WJFC0201", new Object[] { menuItem.getId(), componentName,
                menuItem.getSourceLocation() });
    }

    /**
     * メニューアイテムにIDが指定されていないため、ダミーアクションを登録する際のログを出力します。<br />
     * 
     * @param menuItem
     *            メニューアイテム
     */
    protected void logIdNotFound(final MenuItem menuItem) {
        logger.log("WJFC0202", new Object[] { menuItem.getSourceLocation() });
    }

    /**
     * メニュー項目と<code>IAction</code>コンポーネントの型が不一致の際のログを出力します。<br />
     * 
     * @param componentName
     *            コンポーネント名
     * @param menuItem
     *            メニューアイテム
     */
    protected void logActionTypeMissMatch(final String componentName,
            final MenuItem menuItem) {
        logger.log("EJFC0203", new Object[] { menuItem.getType(),
                componentName, menuItem.getSourceLocation() });
    }

    /**
     * <code>MenuManager</code> に対して可視状態を設定します。<br />
     * 
     * @param menuManager
     *            <code>MenuManager</code> オブジェクト
     * @param menu
     *            <code>Menu</code> オブジェクト
     */
    protected void setVisible(final MenuManager menuManager, final Menu menu) {
        Boolean isVisible = menu.isVisible();
        if (isVisible != null) {
            menuManager.setVisible(isVisible.booleanValue());
        }
    }

    /**
     * <code>IAction</code> に対してテキストを設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setText(final IAction action, final MenuItem item) {
        String text = item.getText();
        if (text != null) {
            action.setText(text);
        }
    }

    /**
     * <code>IAction</code> に対して説明テキストを設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setDescription(final IAction action, final MenuItem item) {
        String description = item.getDescription();
        if (description != null) {
            action.setDescription(description);
        }
    }

    /**
     * <code>IAction</code> に対してイメージを設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setImage(final IAction action, final MenuItem item) {
        String imgPath = item.getImage();
        if (imgPath != null) {
            ImageDescriptor imgDesc = ImageManager.loadImageDescriptor(imgPath);
            if (imgDesc != null) {
                action.setImageDescriptor(imgDesc);
            }
        }
    }

    /**
     * <code>IAction</code> に対してディスエイブル時のイメージを設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setDisableImage(final IAction action, final MenuItem item) {
        // TODO 動作未確認。ActionContributionItemを利用する必要あり
        String imgPath = item.getDisabledImage();
        if (imgPath != null) {
            ImageDescriptor imgDesc = ImageManager.loadImageDescriptor(imgPath);
            if (imgDesc != null) {
                action.setDisabledImageDescriptor(imgDesc);
            }
        }
    }

    /**
     * <code>IAction</code> に対してホバー時のイメージを設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setHoverImage(final IAction action, final MenuItem item) {
        // TODO 動作未確認。ActionContributionItemを利用する必要あり
        String imgPath = item.getHoverImage();
        if (imgPath != null) {
            ImageDescriptor imgDesc = ImageManager.loadImageDescriptor(imgPath);
            if (imgDesc != null) {
                action.setHoverImageDescriptor(imgDesc);
            }
        }
    }

    /**
     * <code>IAction</code> に対してツールチップテキストを設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setToolTipText(final IAction action, final MenuItem item) {
        String toolTipText = item.getToolTipText();
        if (toolTipText != null) {
            action.setToolTipText(toolTipText);
        }
    }

    /**
     * <code>IAction</code> に対してイネーブル状態を設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setEnabled(final IAction action, final MenuItem item) {
        Boolean isEnabled = item.isEnabled();
        if (isEnabled != null) {
            action.setEnabled(isEnabled.booleanValue());
        }
    }

    /**
     * <code>IAction</code> に対してチェック状態を設定します。<br />
     * 
     * @param action
     *            <code>IAction</code> オブジェクト
     * @param item
     *            <code>MenuItem</code> オブジェクト
     */
    protected void setChecked(final IAction action, final MenuItem item) {
        Boolean isChecked = item.isChecked();
        if (isChecked != null) {
            action.setChecked(isChecked.booleanValue());
        }
    }
}
