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
package org.seasar.jface.example.employee;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolItem;
import org.seasar.jface.S2JFaceWindowManager;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.EventListenerType;

public class MainAction {

    private Shell shell;

    private S2JFaceWindowManager windowManager;

    private MenuItem menuDelete;

    private MenuItem menuEdit;

    private MenuItem menuInquire;

    private ToolItem toolDelete;

    private ToolItem toolEdit;

    private ToolItem toolInquire;

    private Table employeeTable;

    @EventListener(id = "shell", type = EventListenerType.SHOW)
    public void onInit() {
        searchEmployee();
    }

    @EventListener(id = { "menuSearch", "toolSearch" })
    public void searchEmployee() {
        windowManager
                .open("org/seasar/jface/example/employee/search.xml", true);
        // TODO 結果を受け取って表示更新
    }

    @EventListener(id = { "menuRegist", "toolRegist" })
    public void registEmployee() {
        windowManager
                .open("org/seasar/jface/example/employee/regist.xml", true);
        // TODO 結果を受け取って表示更新
    }

    @EventListener(id = { "menuDelete", "toolDelete" })
    public void deleteEmployee() {
        boolean result = MessageDialog.openConfirm(shell, "削除確認",
                "選択された従業員情報を削除しますか？");
        if (result) {
            // TODO 削除実行
        }
    }

    @EventListener(id = { "menuEdit", "toolEdit" })
    public void editEmployee() {
        windowManager.open("org/seasar/jface/example/employee/edit.xml", true);
        // TODO 結果を受け取って表示更新
    }

    @EventListener(id = { "menuInquire", "toolInquire" })
    public void inquireEmployee() {
        windowManager.open("org/seasar/jface/example/employee/inquire.xml", true);
        // TODO 結果を受け取って表示更新
    }

    @EventListener(id = "menuAbout")
    public void showAbout() {
        windowManager.open("org/seasar/jface/example/employee/about.xml", true);
    }

    @EventListener(id = "menuExit")
    public void exit() {
        shell.close();
    }

    @EventListener(id = "employeeTable")
    public void onEmployeeTableSelectionChange() {
        boolean multiSelected = employeeTable.getSelectionCount() > 0;
        boolean oneSelected = employeeTable.getSelectionCount() == 1;
        
        menuEdit.setEnabled(oneSelected);
        menuDelete.setEnabled(multiSelected);
        menuInquire.setEnabled(oneSelected);

        toolEdit.setEnabled(oneSelected);
        toolDelete.setEnabled(multiSelected);
        toolInquire.setEnabled(oneSelected);
    }

    public void setWindowManager(S2JFaceWindowManager windowManager) {
        this.windowManager = windowManager;
    }

}
