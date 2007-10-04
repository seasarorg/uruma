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
package org.seasar.uruma.component;

import org.eclipse.swt.widgets.Control;

/**
 * {@link Control} に関する情報を保持するインターフェースです。<br />
 * 
 * @author bskuroneko
 */
public interface UIControlComponent extends UIComponent {
    /**
     * {@link LayoutDataInfo} オブジェクトを取得します。<br />
     * 
     * @return {@link LayoutDataInfo} オブジェクト
     */
    public LayoutDataInfo getLayoutDataInfo();

    /**
     * {@link LayoutDataInfo} オブジェクトを設定します。<br />
     * 
     * @param layoutDataInfo
     *            {@link LayoutDataInfo} オブジェクト
     */
    public void setLayoutDataInfo(LayoutDataInfo layoutDataInfo);
}
