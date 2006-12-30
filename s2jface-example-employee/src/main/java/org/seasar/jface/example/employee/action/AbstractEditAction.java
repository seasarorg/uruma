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

import org.eclipse.swt.widgets.Shell;
import org.seasar.jface.annotation.ArgumentValue;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.annotation.ReturnValue;
import org.seasar.jface.example.employee.dto.DepartmentDto;
import org.seasar.jface.example.employee.dto.EmployeeDto;
import org.seasar.jface.example.employee.dxo.EmployeeEditFormDxo;
import org.seasar.jface.example.employee.form.EmployeeEditForm;
import org.seasar.jface.example.employee.logic.EmployeeLogic;

/**
 * @author bskuroneko
 */
public abstract class AbstractEditAction {

    protected Shell shell;

    protected EmployeeLogic employeeLogic;

    protected EmployeeEditForm employeeEditForm;

    protected EmployeeEditFormDxo employeeEditFormDxo;

    @ArgumentValue
    protected EmployeeDto editEmployee;

    @ReturnValue
    private EmployeeDto result;

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

    public void setEmployeeEditFormDxo(EmployeeEditFormDxo employeeEditFormDxo) {
        this.employeeEditFormDxo = employeeEditFormDxo;
    }

    public void setEmployeeEditForm(EmployeeEditForm employeeEditForm) {
        this.employeeEditForm = employeeEditForm;
    }

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }
}
