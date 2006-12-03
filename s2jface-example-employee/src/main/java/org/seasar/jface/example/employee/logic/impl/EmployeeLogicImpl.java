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
import org.seasar.jface.example.employee.dto.EmployeeSearchDto;
import org.seasar.jface.example.employee.entity.Department;
import org.seasar.jface.example.employee.entity.Employee;
import org.seasar.jface.example.employee.logic.EmployeeLogic;

public class EmployeeLogicImpl implements EmployeeLogic {

    private EmployeeDao employeeDao;

    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public int getSearchCount(EmployeeSearchDto dto) {
        return employeeDao.getSearchCount(dto);
    }

    public List<Employee> searchEmployeeList(EmployeeSearchDto dto) {
        return employeeDao.searchEmployeeList(dto);
    }

    public Employee getEmployee(Integer empno) {
        return employeeDao.getEmployee(empno);
    }

    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    public String getDname(Integer deptno) {
        return departmentDao.getDname(deptno);
    }

    public void insert(Employee employee) {
        employeeDao.insert(employee);
    }

    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

    public boolean existEmployee(Integer empno) {
        return employeeDao.getEmployee(empno) != null;
    }
}
