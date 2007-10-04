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

import java.util.List;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;
import org.seasar.uruma.desc.FormClassDesc;
import org.seasar.uruma.desc.PartClassDesc;

/**
 * ウィンドウパートに関する情報を保持するクラスのための、インターフェースです。<br />
 * ウインドウパートは、 {@link ViewPart}、{@link EditorPart}、{@link DialogPage}など、ウィンドウやダイアログを構成する独立したパートを表します。
 * 
 * @author y-komori
 */
public interface PartContext {
    /**
     * 自ウィンドウのShellオブジェクトが登録されるIDです。
     */
    public static final String SHELL_ID = "shell";

    /**
     * {@link PartContext} が保持するすべての {@link WidgetHandle} をリストで返します。
     * 
     * @return {@link WidgetHandle} のリスト
     */
    public List<WidgetHandle> getWidgetHandleList();

    /**
     * <code>handleId</code> で指定された ID を持つ {@link WidgetHandle} を返します。<br />
     * 
     * @param handleId
     * @return {@link WidgetHandle} オブジェクト。見つからなかった場合は、<code>null</code>。
     */
    public WidgetHandle getWidgetHandle(String handleId);

    /**
     * {@link WidgetHandle} を登録します。<br />
     * 
     * @param handle
     *            {@link WidgetHandle} オブジェクト
     */
    public void putWidgetHandle(WidgetHandle handle);

    /**
     * {@link PartClassDesc} オブジェクトを取得します。<br />
     * 
     * @return {@link PartClassDesc} オブジェクト
     */
    public PartClassDesc getPartClassDesc();

    /**
     * パートクラスのインスタンスを取得します。<br />
     * 
     * @return パートクラスのインスタンス
     */
    public Object getPartClassInstance();

    /**
     * {@link FormClassDesc} オブジェクトを取得します。<br />
     * 
     * @return {@link FormClassDesc} オブジェクト
     */
    public FormClassDesc getFormClassDesc();

    /**
     * フォームクラスのインスタンスを取得します。<br />
     * 
     * @return フォームクラスのインスタンス
     */
    public Object getFormClassInstance();

    /**
     * {@link PartClassDesc} オブジェクトを設定します。<br />
     * 
     * @param desc
     *            {@link PartClassDesc} オブジェクト
     */
    public void setPartClassDesc(PartClassDesc desc);

    /**
     * パートクラスのインスタンスを設定します。<br />
     * 
     * @param instance
     *            パートクラスのインスタンス
     */
    public void setPartClassInstance(Object instance);

    /**
     * {@link FormClassDesc} オブジェクトを設定します。<br />
     * 
     * @param desc
     *            {@link FormClassDesc} オブジェクト
     */
    public void setFormClassDesc(FormClassDesc desc);

    /**
     * フォームクラスのインスタンスを設定します。<br />
     * 
     * @param instance
     *            フォームクラスのインスタンス
     */
    public void setFormClassObject(Object instance);
}
