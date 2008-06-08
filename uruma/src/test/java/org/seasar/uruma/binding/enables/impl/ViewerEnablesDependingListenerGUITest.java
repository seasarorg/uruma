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
package org.seasar.uruma.binding.enables.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.renderer.impl.AbstractGUITest;

/**
 * {@link ViewerEnablesDependingListener} のためのテストクラスです。<br />
 * 
 * @author bskuroneko
 */
public class ViewerEnablesDependingListenerGUITest extends AbstractGUITest {

    private int nextIndex = 0;

    @ExportValue(id = "table")
    public List<TableBean> tableBeans;

    @InitializeMethod
    public void initialize() {
        tableBeans = new ArrayList<TableBean>();
        for (int i = 0; i < 10; i++) {
            addItem();
        }
    }

    @EventListener(id = "addItemButton")
    public void addItem() {
        TableBean bean = new TableBean();
        bean.setColumn1("data" + nextIndex);
        bean.setColumn2(String.valueOf(nextIndex));
        tableBeans.add(bean);
        nextIndex++;
    }

    @EventListener(id = "removeAllButton")
    public void removeAll() {
        tableBeans.clear();
    }

    @EventListener(id = "nullButton")
    public void nullOperation() {
    }

    public static class TableBean {
        private String column1;

        private String column2;

        public String getColumn1() {
            return this.column1;
        }

        public void setColumn1(final String column1) {
            this.column1 = column1;
        }

        public String getColumn2() {
            return this.column2;
        }

        public void setColumn2(final String column2) {
            this.column2 = column2;
        }
    }
}
