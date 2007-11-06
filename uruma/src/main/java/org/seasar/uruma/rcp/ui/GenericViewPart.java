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
package org.seasar.uruma.rcp.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * 汎用的な {@link ViewPart} クラスです。<br />
 * 
 * @author y-komori
 */
public class GenericViewPart extends ViewPart {

    @Override
    public void createPartControl(final Composite parent) {
        System.err.println(getSite().getPluginId());
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void setFocus() {
        // TODO 自動生成されたメソッド・スタブ

    }

}
