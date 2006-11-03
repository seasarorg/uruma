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

import org.eclipse.swt.widgets.Shell;
import org.seasar.jface.annotation.BindType;
import org.seasar.jface.annotation.BindingValue;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.annotation.ReturnValue;
import org.seasar.jface.example.employee.dxo.SearchActionDxo;
import org.seasar.jface.example.employee.service.EmployeeService;

import examples.jsf.dto.DepartmentDto;
import examples.jsf.dto.EmployeeSearchDto;

public class SearchAction {

    private EmployeeService employeeService;

    private SearchActionDxo dxo;

    private Shell shell;

    @BindingValue(type = BindType.Import)
    private String empno;

    @BindingValue(type = BindType.Import)
    private String ename;

    @BindingValue(type = BindType.Import)
    private String job;

    @BindingValue(type = BindType.Import)
    private String mgr;

    @BindingValue(type = BindType.Import)
    private String fromHiredate;

    @BindingValue(type = BindType.Import)
    private String toHiredate;

    @BindingValue(type = BindType.Import)
    private String fromSal;

    @BindingValue(type = BindType.Import)
    private String toSal;

    @BindingValue(type = BindType.Import, id = "dept")
    private String deptName;

    @BindingValue(type = BindType.Export, id = "dept", label = "dname")
    private List<DepartmentDto> deptList;

    private Integer deptno;

    @ReturnValue
    private List searchResult = null;

    @InitializeMethod
    public void initialize() {
        deptList = employeeService.getAllDepartments();
    }

    @EventListener(id = "ok")
    public void onOk() {
        bindDept();

        EmployeeSearchDto searchDto = dxo.convert(this);
        searchResult = employeeService.searchEmployeeDtoList(searchDto);
        shell.close();
    }

    @EventListener(id = "cancel")
    public void onCancel() {
        searchResult = null;
        shell.close();
    }

    private void bindDept() {
        if (deptList == null) {
            return;
        }
        for (DepartmentDto dto : deptList) {
            if (dto.getDname().equals(deptName)) {
                deptno = dto.getDeptno();
                break;
            }
        }
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setDxo(SearchActionDxo dxo) {
        this.dxo = dxo;
    }

    public String getEmpno() {
        return this.empno;
    }

    public String getEname() {
        return this.ename;
    }

    public String getFromHiredate() {
        return this.fromHiredate;
    }

    public String getFromSal() {
        return this.fromSal;
    }

    public String getJob() {
        return this.job;
    }

    public String getMgr() {
        return this.mgr;
    }

    public String getToHiredate() {
        return this.toHiredate;
    }

    public String getToSal() {
        return this.toSal;
    }

    public Integer getDeptno() {
        return this.deptno;
    }

}
