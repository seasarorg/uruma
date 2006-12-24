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

import org.seasar.jface.annotation.ArgumentValue;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.example.employee.dto.DepartmentDto;
import org.seasar.jface.example.employee.dto.EmployeeDto;
import org.seasar.jface.example.employee.dxo.EditActionDxo;

/**
 * @author bskuroneko
 * 
 */
public class EditAction extends AbstractEditAction {

    private EditActionDxo editActionDxo;

    @ArgumentValue
    private EmployeeDto editEmployee;

    @Override
    @InitializeMethod
    public void initialize() {
        super.initialize();
        editActionDxo.convert(editEmployee, this);
        selectedDepartmentDto = new DepartmentDto();
        selectedDepartmentDto.setDeptno(getDeptno());
    }

    @Override
    protected EmployeeDto doInsertOrUpdate() {
        editActionDxo.convert(this, editEmployee);
        return employeeLogic.update(editEmployee);
    }

    public void setEditActionDxo(EditActionDxo editActionDxo) {
        this.editActionDxo = editActionDxo;
    }

}
