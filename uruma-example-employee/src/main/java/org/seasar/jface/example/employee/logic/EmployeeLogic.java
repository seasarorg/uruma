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
package org.seasar.jface.example.employee.logic;

import java.util.List;

import org.seasar.jface.example.employee.dto.DepartmentDto;
import org.seasar.jface.example.employee.dto.EmployeeDto;
import org.seasar.jface.example.employee.dto.EmployeeSearchDto;
import org.seasar.jface.example.employee.entity.Employee;

/**
 * @author bskuroneko
 * @author y-komori
 */
public interface EmployeeLogic {

    public int getSearchCount(EmployeeSearchDto dto);

    public List<EmployeeDto> searchEmployeeDtoList(EmployeeSearchDto dto);

    public Employee getEmployee(Integer empno);

    public boolean existEmployee(Integer empno);

    public List<DepartmentDto> getAllDepartments();

    public String getDname(Integer deptno);

    public EmployeeDto insert(EmployeeDto employee);

    public EmployeeDto update(EmployeeDto employee);

    public void delete(Employee employee);
}
