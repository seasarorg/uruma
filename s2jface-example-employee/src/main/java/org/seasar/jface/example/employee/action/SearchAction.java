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
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.annotation.ReturnValue;
import org.seasar.jface.example.employee.dto.DepartmentDto;
import org.seasar.jface.example.employee.dto.EmployeeSearchDto;
import org.seasar.jface.example.employee.dxo.SearchFormDxo;
import org.seasar.jface.example.employee.form.SearchForm;
import org.seasar.jface.example.employee.logic.EmployeeLogic;

/**
 * @author bskuroneko
 */
public class SearchAction {

    private EmployeeLogic employeeLogic;

    private SearchFormDxo searchFormDxo;

    private Shell shell;

    private SearchForm searchForm;

    @ReturnValue
    private List searchResult = null;

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
        searchResult = employeeLogic.searchEmployeeDtoList(searchDto);
        shell.close();
    }

    @EventListener(id = "cancel")
    public void onCancel() {
        searchResult = null;
        shell.close();
    }

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public void setSearchFormDxo(SearchFormDxo searchFormDxo) {
        this.searchFormDxo = searchFormDxo;
    }

    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }
}
