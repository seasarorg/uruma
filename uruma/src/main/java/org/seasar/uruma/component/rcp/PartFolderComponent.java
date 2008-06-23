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

import org.seasar.uruma.component.base.AbstractUIElementContainer;

/**
 * <code>partFolder</code> 要素を表すコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class PartFolderComponent extends AbstractUIElementContainer {
    /**
     * フォルダの ID を表します。<br />
     */
    public String id;

    /**
     * パースペクティブ内の配置位置を表します。<br />
     */
    public String position;

    /**
     * パースペクティブ内に占める割合(%)を表します。<br />
     */
    public String ratio;
}
