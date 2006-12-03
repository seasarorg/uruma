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

import org.seasar.jface.example.employee.dto.DepartmentDto;
import org.seasar.jface.example.employee.dto.EmployeeDto;
import org.seasar.jface.example.employee.dto.EmployeeSearchDto;
import org.seasar.jface.example.employee.dxo.DepartmentDxo;
import org.seasar.jface.example.employee.dxo.EmployeeDxo;
import org.seasar.jface.example.employee.entity.Employee;
import org.seasar.jface.example.employee.exception.EmployeeAlreadyExistRuntimeException;
import org.seasar.jface.example.employee.exception.EmployeeNotFoundRuntimeException;
import org.seasar.jface.example.employee.logic.EmployeeLogic;
import org.seasar.jface.example.employee.service.EmployeeService;

/**
 * @author bskuroneko
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeLogic employeeLogic;

    private EmployeeDxo employeeDxo;

    private DepartmentDxo departmentDxo;

    public void delete(EmployeeDto dto) {
        if (!employeeLogic.existEmployee(dto.getEmpno())) {
            throw new EmployeeNotFoundRuntimeException(dto.getEmpno());
        }
        Employee employee = new Employee();
        employee.setEmpno(dto.getEmpno());
        employeeLogic.delete(employee);
    }

    public EmployeeDto insert(EmployeeDto dto) {
        if (employeeLogic.existEmployee(dto.getEmpno())) {
            throw new EmployeeAlreadyExistRuntimeException(dto.getEmpno());
        }
        employeeLogic.insert(employeeDxo.convert(dto));
        Employee employee = employeeLogic.getEmployee(dto.getEmpno());
        return employeeDxo.convert(employee);
    }

    public EmployeeDto update(EmployeeDto dto) {
        if (!employeeLogic.existEmployee(dto.getEmpno())) {
            throw new EmployeeNotFoundRuntimeException(dto.getEmpno());
        }
        employeeLogic.update(employeeDxo.convert(dto));
        Employee employee = employeeLogic.getEmployee(dto.getEmpno());
        return employeeDxo.convert(employee);
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentDxo.convert(employeeLogic.getAllDepartments());
    }

    public List<EmployeeDto> searchEmployeeDtoList(EmployeeSearchDto dto) {
        List<Employee> employeeList = employeeLogic.searchEmployeeList(dto);
        return employeeDxo.convert(employeeList);
    }

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public void setEmployeeDxo(EmployeeDxo employeeDxo) {
        this.employeeDxo = employeeDxo;
    }

    public void setDepartmentDxo(DepartmentDxo departmentDxo) {
        this.departmentDxo = departmentDxo;
    }
}
