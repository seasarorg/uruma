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

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.seasar.uruma.annotation.ApplicationContext;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.employee.dto.EmployeeDto;
import org.seasar.uruma.example.employee.dxo.InquireFormDxo;
import org.seasar.uruma.example.employee.entity.Employee;
import org.seasar.uruma.example.employee.form.InquireForm;
import org.seasar.uruma.example.employee.logic.EmployeeLogic;

/**
 * @author bskuroneko
 */
public class InquireAction {
	public Shell shell;

	public InquireForm inquireForm;

	public InquireFormDxo inquireFormDxo;

	public EmployeeLogic employeeLogic;

	@ApplicationContext
	public List<EmployeeDto> selectedEmployees;

	@InitializeMethod
	public void initialize() {
		Employee employee = employeeLogic.getEmployee(selectedEmployees.get(0)
				.getEmpno());
		inquireFormDxo.convert(employee, inquireForm);
	}

	@EventListener(id = "ok")
	public void onOk() {
		shell.close();
	}
}
