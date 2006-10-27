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
package org.seasar.jface.example.employee.service.impl;

import java.util.List;

import org.seasar.jface.example.employee.service.EmployeeService;

import examples.jsf.dto.DepartmentDto;
import examples.jsf.dto.EmployeeDto;
import examples.jsf.exception.EmployeeAlreadyExistRuntimeException;
import examples.jsf.exception.EmployeeNotFoundRuntimeException;
import examples.jsf.logic.EmployeeLogic;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeLogic employeeLogic;

    public void delete(EmployeeDto dto) {
        if (!employeeLogic.existEmployee(dto.getEmpno())) {
            throw new EmployeeNotFoundRuntimeException(dto.getEmpno());
        }
        employeeLogic.delete(dto);
    }

    public void insert(EmployeeDto dto) {
        if (employeeLogic.existEmployee(dto.getEmpno())) {
            throw new EmployeeAlreadyExistRuntimeException(dto.getEmpno());
        }
        employeeLogic.insert(dto);
    }

    public void update(EmployeeDto dto) {
        if (!employeeLogic.existEmployee(dto.getEmpno())) {
            throw new EmployeeNotFoundRuntimeException(dto.getEmpno());
        }
        employeeLogic.update(dto);
    }

    public List<DepartmentDto> getAllDepartments() {
        return employeeLogic.getAllDepartments();
    }

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

}
