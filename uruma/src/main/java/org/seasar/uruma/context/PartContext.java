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
package org.seasar.uruma.context;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;
import org.seasar.uruma.desc.FormDesc;
import org.seasar.uruma.desc.PartActionDesc;

/**
 * ウィンドウパートに関する情報を保持するクラスのための、インターフェースです。<br />
 * ウインドウパートは、 {@link ViewPart}、{@link EditorPart}、{@link DialogPage}など、ウィンドウやダイアログを構成する独立したパートを表します。
 * 
 * @author y-komori
 */
public interface PartContext extends WidgetHolder {
    /**
     * 自ウィンドウのShellオブジェクトが登録されるIDです。
     */
    public static final String SHELL_ID = "shell";

    /**
     * メニューバー用の {@link IMenuManager} が登録される ID です。<br />
     */
    public static final String MENU_BAR_MANAGER_ID = "menuBarManager";

    /**
     * ID の設定されていない <code>menu</code> 要素にデフォルトで割り当てられる ID です。<br />
     */
    public static final String DEFAULT_MENU_ID = "defaultMenu";

    /**
     * {@link IStatusLineManager} が登録される ID です。<br />
     */
    public static final String STATUS_LINE_MANAGER_ID = "statusLineManager";

    /**
     * ウィンドウパートの名称を返します。<br />
     * 
     * @return ウィンドウパートの名称
     */
    public String getName();

    /**
     * {@link PartActionDesc} オブジェクトを取得します。<br />
     * 
     * @return {@link PartActionDesc} オブジェクト
     */
    public PartActionDesc getPartActionDesc();

    /**
     * パートアクションクラスのオブジェクトを取得します。<br />
     * 
     * @return パートアクションクラスのオブジェクト
     */
    public Object getPartActionObject();

    /**
     * {@link FormDesc} オブジェクトを取得します。<br />
     * 
     * @return {@link FormDesc} オブジェクト
     */
    public FormDesc getFormDesc();

    /**
     * フォームクラスのオブジェクトを取得します。<br />
     * 
     * @return フォームクラスのオブジェクト
     */
    public Object getFormObject();

    /**
     * {@link PartActionDesc} オブジェクトを設定します。<br />
     * 
     * @param desc
     *            {@link PartActionDesc} オブジェクト
     */
    public void setPartActionDesc(PartActionDesc desc);

    /**
     * パートアクションクラスのオブジェクトを設定します。<br />
     * 
     * @param object
     *            パートアクションクラスのオブジェクト
     */
    public void setPartActionObject(Object object);

    /**
     * {@link FormDesc} オブジェクトを設定します。<br />
     * 
     * @param desc
     *            {@link FormDesc} オブジェクト
     */
    public void setFormDesc(FormDesc desc);

    /**
     * フォームクラスのオブジェクトを設定します。<br />
     * 
     * @param object
     *            フォームクラスのオブジェクト
     */
    public void setFormObject(Object object);

    /**
     * 親 {@link WindowContext} を返します。<br />
     * 
     * @return {@link WindowContext} オブジェクト
     */
    public WindowContext getWindowContext();
}
