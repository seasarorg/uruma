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

/**
 * レイアウトに関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 * 
 * @param <LAYOUT_DATA_INFO>
 *            関連するレイアウトデータクラス
 */
public interface LayoutInfo<LAYOUT_DATA_INFO extends LayoutDataInfo> extends
        UIElement {
    /**
     * 引き継ぎ用の <code>LayoutDataInfo</code> を設定します。<br />
     * <p>
     * 引き継ぎ用の <code>LayoutDataInfo</code> とは、本 <code>LayoutInfo</code> を設定した
     * <code>Composite</code> の子に対して自動的に設定される <code>LayoutDataInfo</code>
     * のことを表します。<br />
     * </p>
     * 
     * @param layoutDataInfo
     *            <code>LayoutDataInfo</code> オブジェクト
     */
    public void setCommonLayoutDataInfo(LAYOUT_DATA_INFO layoutDataInfo);

    /**
     * 引き継ぎ用の <code>LayoutDataInfo</code> を取得します。<br />
     * 
     * @return <code>LayoutDataInfo</code> オブジェクト
     */
    public LAYOUT_DATA_INFO getCommonLayoutDataInfo();
}
