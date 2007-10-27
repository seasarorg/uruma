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
package org.seasar.uruma.example.employee.action;

import org.eclipse.swt.widgets.Shell;
import org.seasar.uruma.annotation.ArgumentValue;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.annotation.ReturnValue;
import org.seasar.uruma.example.employee.dto.DepartmentDto;
import org.seasar.uruma.example.employee.dto.EmployeeDto;
import org.seasar.uruma.example.employee.dxo.EmployeeEditFormDxo;
import org.seasar.uruma.example.employee.form.EmployeeEditForm;
import org.seasar.uruma.example.employee.logic.EmployeeLogic;

/**
 * @author bskuroneko
 */
public abstract class AbstractEditAction {
	public EmployeeLogic employeeLogic;

	public EmployeeEditFormDxo employeeEditFormDxo;

	public Shell shell;

	public EmployeeEditForm employeeEditForm;

	@ArgumentValue
	public EmployeeDto editEmployee;

	@ReturnValue
	public EmployeeDto result;

	@InitializeMethod
	public void initialize() {
		employeeEditForm.setDeptList(employeeLogic.getAllDepartments());
	}

	@EventListener(id = "ok")
	public void onOk() {
		DepartmentDto selectedDepartmentDto = employeeEditForm
				.getSelectedDepartmentDto();
		if (selectedDepartmentDto != null) {
			employeeEditForm.setDeptno(selectedDepartmentDto.getDeptno());
		}
		EmployeeDto employee = employeeEditFormDxo.convert(employeeEditForm);
		result = doUpdateOrInsert(employee);
		shell.close();
	}

	abstract protected EmployeeDto doUpdateOrInsert(EmployeeDto employeeDto);

	public void setEmployeeEditForm(final EmployeeEditForm employeeEditForm) {
		this.employeeEditForm = employeeEditForm;
	}
}
