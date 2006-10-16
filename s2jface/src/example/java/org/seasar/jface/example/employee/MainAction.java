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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
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

    @EventListener(id = "menuRegist")
    public void onMenuRegist() {
        registEmployee();
    }

    @EventListener(id = "toolRegist")
    public void onToolRegist() {
        registEmployee();
    }

    @EventListener(id = "menuDelete")
    public void onMenuDelete() {
        deleteEmployee();
    }

    @EventListener(id = "toolDelete")
    public void onToolDelete() {
        deleteEmployee();
    }

    @EventListener(id = "menuSearch")
    public void onMenuSearch() {
        searchEmployee();
    }

    @EventListener(id = "toolSearch")
    public void onToolSearch() {
        searchEmployee();
    }

    @EventListener(id = "menuEdit")
    public void onMenuEdit() {
        editEmployee();
    }

    @EventListener(id = "toolEdit")
    public void onToolEdit() {
        editEmployee();
    }

    @EventListener(id = "menuExit")
    public void onMenuExit() {
        exit();
    }

    @EventListener(id = "employeeTable", type=EventListenerType.SELECTION)
    public void onEmployeeTableSelectionChange() {
        boolean selected = employeeTable.getSelectionCount() > 0;
        menuEdit.setEnabled(selected);
        menuDelete.setEnabled(selected);
        menuInquire.setEnabled(selected);
        
        toolEdit.setEnabled(selected);
        toolDelete.setEnabled(selected);
        toolInquire.setEnabled(selected);
    }

    private void registEmployee() {
        windowManager
                .open("org/seasar/jface/example/employee/regist.xml", true);
        // TODO 戻り値判定
        // TODO 登録実行（必要なら砂時計表示）
    }

    private void editEmployee() {
        // TODO 編集実装
    }

    private void deleteEmployee() {
        MessageBox dialog = new MessageBox(shell, SWT.OK | SWT.CANCEL);
        dialog.setMessage("選択された従業員情報を削除しますか？");
        dialog.setText("削除確認");
        int result = dialog.open();
        if (result == SWT.OK) {
            // TODO 削除実行
        }
    }

    private void searchEmployee() {
        windowManager
                .open("org/seasar/jface/example/employee/search.xml", true);
        // TODO 戻り値判定
        // TODO search実行（必要なら砂時計表示）
    }

    private void exit() {
        shell.close();
    }

    public void setWindowManager(S2JFaceWindowManager windowManager) {
        this.windowManager = windowManager;
    }

}
