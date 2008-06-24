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
package org.seasar.uruma.rcp.ui;

import org.eclipse.ui.IViewPart;

/**
 * パートアクションクラスから参照するビューパートのためのインタフェースです。<br />
 * 
 * @author y-komori
 */
public interface UrumaViewPart extends IViewPart {
    /**
     * タブに表示されるパート名称を設定します。<br />
     */
    public void setPartName(String name);

    /**
     * パートの ID を取得します。<br />
     * 
     * @return パートの ID
     */
    public String getId();

    /**
     * RCP 上のパートの ID を取得します。<br />
     * 
     * @return RCP 上のパートの ID
     */
    public String getRcpId();

    /**
     * パートのセカンダリ ID を取得します。<br />
     * 
     * @return セカンダリ ID。設定されていない場合は <code>null</code>。
     */
    public String getSecondaryId();

}
