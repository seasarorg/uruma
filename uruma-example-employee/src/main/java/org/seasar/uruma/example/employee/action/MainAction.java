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
package org.seasar.uruma.example.employee.action;

import java.util.List;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Shell;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.uruma.annotation.ApplicationContext;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.core.UrumaWindowManager;
import org.seasar.uruma.example.employee.dto.EmployeeDto;
import org.seasar.uruma.example.employee.dto.EmployeeSearchDto;
import org.seasar.uruma.example.employee.entity.Employee;
import org.seasar.uruma.example.employee.logic.EmployeeLogic;

/**
 * メイン画面のためのアクションクラスです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
@Form(MainAction.class)
public class MainAction {
	public UrumaWindowManager windowManager;

	public EmployeeLogic employeeLogic;

	public Shell shell;

	@Binding(bindingType = BindingType.MAY)
	public IStatusLineManager statusLineManager;

	public TableViewer employeeTable;

	@ExportValue(id = "employeeTable")
	public List<EmployeeDto> employees;

	@ImportSelection(id = "employeeTable")
	@ApplicationContext
	public List<EmployeeDto> selectedEmployees;

	@ApplicationContext
	public boolean edited;

	@InitializeMethod
	public void initialize() {
		employees = employeeLogic
				.searchEmployeeDtoList(new EmployeeSearchDto());
	}

	@EventListener(id = { "menuSearch", "toolSearch" })
	public void searchEmployee() {
		edited = false;
		windowManager.openDialog(
				"org/seasar/uruma/example/employee/search.xml", this);
		if (edited) {
			employees = selectedEmployees;
			employeeTable.refresh();
		}
	}

	@EventListener(id = { "menuRegist", "toolRegist" })
	public void registEmployee() {
		edited = false;
		windowManager.openDialog(
				"org/seasar/uruma/example/employee/regist.xml", this);
		if (edited) {
			employees.add(selectedEmployees.get(selectedEmployees.size() - 1));
		}
	}

	@EventListener(id = { "menuDelete", "toolDelete" })
	public void deleteEmployee() {
		boolean result = MessageDialog.openConfirm(shell, "削除確認",
				"選択された従業員情報を削除しますか？");
		if (result) {
			try {
				for (EmployeeDto dto : selectedEmployees) {
					Employee employee = employeeLogic.getEmployee(dto
							.getEmpno());
					employeeLogic.delete(employee);
					statusLineManager.setMessage(null);
				}
			} finally {
				initialize();
			}
		}
	}

	@EventListener(id = { "menuEdit", "toolEdit" })
	public void editEmployee() {
		edited = false;
		EmployeeDto employee = selectedEmployees.get(0);
		windowManager.openDialog("org/seasar/uruma/example/employee/edit.xml",
				this);
		if (edited) {
			employeeTable.refresh(employee);
		}
	}

	@EventListener(id = { "menuInquire", "toolInquire" })
	public void inquireEmployee() {
		windowManager.openDialog(
				"org/seasar/uruma/example/employee/inquire.xml", this);
	}

	@EventListener(id = "menuAbout")
	public void showAbout() {
		windowManager.openDialog("org/seasar/uruma/example/employee/about.xml",
				this);
	}

	@EventListener(id = "menuExit")
	public void exit() {
		shell.close();
	}

	@EventListener(id = "employeeTable")
	public void onTableSelect() {
		int selCount = employeeTable.getTable().getSelectionCount();
		statusLineManager.setMessage(selCount + "個の項目が選択されています");
	}
}
