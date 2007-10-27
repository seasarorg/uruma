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
package org.seasar.uruma.example.employee.dao;

import java.util.List;

import org.seasar.uruma.example.employee.dto.EmployeeSearchDto;
import org.seasar.uruma.example.employee.entity.Employee;

public interface EmployeeDao {

    public Class BEAN = Employee.class;

    public String searchEmployeeList_ARGS = "dto";

    public List<Employee> searchEmployeeList(EmployeeSearchDto dto);

    public String getSearchCount_ARGS = "dto";

    public int getSearchCount(EmployeeSearchDto dto);

    public String getEmployee_ARGS = "empno";

    public Employee getEmployee(Integer empno);

    public void insert(Employee employee);

    public void update(Employee employee);

    public void delete(Employee employee);
}
