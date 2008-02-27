/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.example.employee.logic.impl;

import java.util.List;

import org.seasar.uruma.example.employee.dao.DepartmentDao;
import org.seasar.uruma.example.employee.dao.EmployeeDao;
import org.seasar.uruma.example.employee.dto.DepartmentDto;
import org.seasar.uruma.example.employee.dto.EmployeeDto;
import org.seasar.uruma.example.employee.dto.EmployeeSearchDto;
import org.seasar.uruma.example.employee.dxo.DepartmentDxo;
import org.seasar.uruma.example.employee.dxo.EmployeeDxo;
import org.seasar.uruma.example.employee.entity.Department;
import org.seasar.uruma.example.employee.entity.Employee;
import org.seasar.uruma.example.employee.exception.EmployeeAlreadyExistRuntimeException;
import org.seasar.uruma.example.employee.exception.EmployeeNotFoundRuntimeException;
import org.seasar.uruma.example.employee.logic.EmployeeLogic;

/**
 * {@link EmployeeLogic} の実装クラスです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class EmployeeLogicImpl implements EmployeeLogic {

	private EmployeeDao employeeDao;

	private DepartmentDao departmentDao;

	private EmployeeDxo employeeDxo;

	private DepartmentDxo departmentDxo;

	public void setDepartmentDao(final DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void setEmployeeDao(final EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setDepartmentDxo(final DepartmentDxo departmentDxo) {
		this.departmentDxo = departmentDxo;
	}

	public void setEmployeeDxo(final EmployeeDxo employeeDxo) {
		this.employeeDxo = employeeDxo;
	}

	public int getSearchCount(final EmployeeSearchDto dto) {
		return employeeDao.getSearchCount(dto);
	}

	public List<EmployeeDto> searchEmployeeDtoList(final EmployeeSearchDto dto) {
		List<Employee> employeeList = employeeDao.searchEmployeeList(dto);
		return employeeDxo.convert(employeeList);
	}

	public Employee getEmployee(final Integer empno) {
		return employeeDao.getEmployee(empno);
	}

	public List<DepartmentDto> getAllDepartments() {
		List<Department> departmentList = departmentDao.getAllDepartments();
		return departmentDxo.convert(departmentList);
	}

	public String getDname(final Integer deptno) {
		return departmentDao.getDname(deptno);
	}

	public EmployeeDto insert(final EmployeeDto dto) {
		if (existEmployee(dto.empno)) {
			throw new EmployeeAlreadyExistRuntimeException(dto.empno);
		}
		employeeDao.insert(employeeDxo.convert(dto));
		Employee employee = getEmployee(dto.empno);
		return employeeDxo.convert(employee);
	}

	public EmployeeDto update(final EmployeeDto dto) {
		if (!existEmployee(dto.empno)) {
			throw new EmployeeNotFoundRuntimeException(dto.empno);
		}
		employeeDao.update(employeeDxo.convert(dto));
		Employee employee = getEmployee(dto.empno);
		return employeeDxo.convert(employee);
	}

	public void delete(final Employee employee) {
		if (!existEmployee(employee.empno)) {
			throw new EmployeeNotFoundRuntimeException(employee.empno);
		}
		employeeDao.delete(employee);
	}

	public boolean existEmployee(final Integer empno) {
		return employeeDao.getEmployee(empno) != null;
	}
}
