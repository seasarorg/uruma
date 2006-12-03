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
package org.seasar.jface.example.employee.logic.impl;

import java.util.List;

import org.seasar.jface.example.employee.dao.DepartmentDao;
import org.seasar.jface.example.employee.dao.EmployeeDao;
import org.seasar.jface.example.employee.dto.DepartmentDto;
import org.seasar.jface.example.employee.dto.EmployeeDto;
import org.seasar.jface.example.employee.dto.EmployeeSearchDto;
import org.seasar.jface.example.employee.dxo.DepartmentDxo;
import org.seasar.jface.example.employee.dxo.EmployeeDxo;
import org.seasar.jface.example.employee.entity.Department;
import org.seasar.jface.example.employee.entity.Employee;
import org.seasar.jface.example.employee.exception.EmployeeAlreadyExistRuntimeException;
import org.seasar.jface.example.employee.exception.EmployeeNotFoundRuntimeException;
import org.seasar.jface.example.employee.logic.EmployeeLogic;

/**
 * @author bskuroneko
 * @author y-komori
 */
public class EmployeeLogicImpl implements EmployeeLogic {

    private EmployeeDao employeeDao;

    private DepartmentDao departmentDao;

    private EmployeeDxo employeeDxo;

    private DepartmentDxo departmentDxo;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDepartmentDxo(DepartmentDxo departmentDxo) {
        this.departmentDxo = departmentDxo;
    }

    public void setEmployeeDxo(EmployeeDxo employeeDxo) {
        this.employeeDxo = employeeDxo;
    }

    public int getSearchCount(EmployeeSearchDto dto) {
        return employeeDao.getSearchCount(dto);
    }

    public List<EmployeeDto> searchEmployeeDtoList(EmployeeSearchDto dto) {
        List<Employee> employeeList = employeeDao.searchEmployeeList(dto);
        return employeeDxo.convert(employeeList);
    }

    public Employee getEmployee(Integer empno) {
        return employeeDao.getEmployee(empno);
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departmentList = departmentDao.getAllDepartments();
        return departmentDxo.convert(departmentList);
    }

    public String getDname(Integer deptno) {
        return departmentDao.getDname(deptno);
    }

    public EmployeeDto insert(EmployeeDto dto) {
        if (existEmployee(dto.getEmpno())) {
            throw new EmployeeAlreadyExistRuntimeException(dto.getEmpno());
        }
        employeeDao.insert(employeeDxo.convert(dto));
        Employee employee = getEmployee(dto.getEmpno());
        return employeeDxo.convert(employee);
    }

    public EmployeeDto update(EmployeeDto dto) {
        if (!existEmployee(dto.getEmpno())) {
            throw new EmployeeNotFoundRuntimeException(dto.getEmpno());
        }
        employeeDao.update(employeeDxo.convert(dto));
        Employee employee = getEmployee(dto.getEmpno());
        return employeeDxo.convert(employee);
    }

    public void delete(EmployeeDto dto) {
        if (!existEmployee(dto.getEmpno())) {
            throw new EmployeeNotFoundRuntimeException(dto.getEmpno());
        }
        Employee employee = new Employee();
        employee.setEmpno(dto.getEmpno());
        employeeDao.delete(employee);
    }

    public boolean existEmployee(Integer empno) {
        return employeeDao.getEmployee(empno) != null;
    }
}
