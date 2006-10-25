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
package org.seasar.jface.example.employee.action;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.seasar.jface.S2JFaceWindowManager;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.EventListenerType;

public class MainAction {

    private Shell shell;

    private S2JFaceWindowManager windowManager;

    @EventListener(id = "shell", type = EventListenerType.SHOW)
    public void onInit() {
        Display.getCurrent().asyncExec(new Runnable() {
            public void run() {
                searchEmployee();
            }
        });
    }

    @EventListener(id = { "menuSearch", "toolSearch" })
    public void searchEmployee() {
        List employees = (List) windowManager
                .openModal("org/seasar/jface/example/employee/search.xml");
        
        // TODO 結果をテーブルにバインディング
        System.out.println("検索結果: " + employees);
        int count = 0;
        if (employees != null) {
            count = employees.size();
        }
        System.out.println("結果件数: " + count);
    }

    @EventListener(id = { "menuRegist", "toolRegist" })
    public void registEmployee() {
        windowManager
                .openModal("org/seasar/jface/example/employee/regist.xml");
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
        windowManager.openModal("org/seasar/jface/example/employee/edit.xml");
        // TODO 結果を受け取って表示更新
    }

    @EventListener(id = { "menuInquire", "toolInquire" })
    public void inquireEmployee() {
        windowManager.openModal("org/seasar/jface/example/employee/inquire.xml");
        // TODO 結果を受け取って表示更新
    }

    @EventListener(id = "menuAbout")
    public void showAbout() {
        windowManager.openModal("org/seasar/jface/example/employee/about.xml");
    }

    @EventListener(id = "menuExit")
    public void exit() {
        shell.close();
    }

    public void setWindowManager(S2JFaceWindowManager windowManager) {
        this.windowManager = windowManager;
    }

}
