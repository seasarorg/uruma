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

import org.eclipse.swt.widgets.Shell;
import org.seasar.uruma.annotation.ApplicationContext;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.employee.dto.DepartmentDto;
import org.seasar.uruma.example.employee.dto.EmployeeDto;
import org.seasar.uruma.example.employee.dto.EmployeeSearchDto;
import org.seasar.uruma.example.employee.dxo.SearchFormDxo;
import org.seasar.uruma.example.employee.form.SearchForm;
import org.seasar.uruma.example.employee.logic.EmployeeLogic;

/**
 * @author bskuroneko
 */
public class SearchAction {
	public EmployeeLogic employeeLogic;

	public SearchFormDxo searchFormDxo;

	public Shell shell;

	public SearchForm searchForm;

	@ApplicationContext
	public List<EmployeeDto> selectedEmployees;

	@ApplicationContext
	public boolean edited;

	@InitializeMethod
	public void initialize() {
		searchForm.setDeptList(employeeLogic.getAllDepartments());
	}

	@EventListener(id = "ok")
	public void onOk() {
		DepartmentDto selectedDepartment = searchForm.getSelectedDepartment();
		if (selectedDepartment != null) {
			searchForm.setDeptno(selectedDepartment.getDeptno());
		}

		EmployeeSearchDto searchDto = searchFormDxo.convert(searchForm);
		selectedEmployees = employeeLogic.searchEmployeeDtoList(searchDto);
		edited = true;
		shell.close();
	}

	@EventListener(id = "cancel")
	public void onCancel() {
		shell.close();
	}
}
