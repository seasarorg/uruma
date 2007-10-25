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
package org.seasar.uruma.binding.value.binder;

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.renderer.impl.AbstractGUITest;

/**
 * {@link TableViewerValueBinder} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
@Form(TableViewerValueBinderGUITest.class)
public class TableViewerValueBinderGUITest extends AbstractGUITest {
    @ExportValue
    public List<Contents> table;

    @InitializeMethod
    public void initialize() {
        table = new ArrayList<Contents>();
        Contents row1 = new Contents("aaa", "bbb", "ccc", "ddd");
        table.add(row1);
    }

    /**
     * @author y-komori
     * 
     */
    private static class Contents {
        public String column1;

        public String column2;

        public String column3;

        public String column4;

        /**
         * {@link Contents} を構築します。<br />
         * 
         * @param column1
         * @param column2
         * @param column3
         * @param column4
         */
        public Contents(final String column1, final String column2,
                final String column3, final String column4) {
            this.column1 = column1;
            this.column2 = column2;
            this.column3 = column3;
            this.column4 = column4;
        }
    }
}
