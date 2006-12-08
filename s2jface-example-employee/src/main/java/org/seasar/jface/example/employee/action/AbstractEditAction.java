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

import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.annotation.ImportValue;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.annotation.ReturnValue;
import org.seasar.jface.example.employee.dto.DepartmentDto;
import org.seasar.jface.example.employee.dto.EmployeeDto;
import org.seasar.jface.example.employee.logic.EmployeeLogic;

/**
 * @author bskuroneko
 */
public abstract class AbstractEditAction extends AbstractOneEmployeeAction {
    protected EmployeeLogic employeeLogic;

    @ImportValue(id = "dept")
    private String dname;

    @ExportValue(id = "dept")
    private List<DepartmentDto> deptList;

    private Integer deptno;

    @ReturnValue
    private EmployeeDto result;

    @InitializeMethod
    public void initialize() {
        deptList = employeeLogic.getAllDepartments();
    }

    @EventListener(id = "ok")
    public void onOk() {
        bindDept();
        result = doInsertOrUpdate();
        shell.close();
    }

    protected abstract EmployeeDto doInsertOrUpdate();

    private void bindDept() {
        if (deptList == null) {
            return;
        }
        for (DepartmentDto dto : deptList) {
            if (dto.getDname().equals(dname)) {
                deptno = dto.getDeptno();
                break;
            }
        }
    }

    @EventListener(id = "cancel")
    public void onCancel() {
        shell.close();
    }

    public Integer getDeptno() {
        return this.deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return this.dname;
    }

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }
}
