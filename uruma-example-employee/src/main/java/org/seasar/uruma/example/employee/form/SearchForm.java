/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.uruma.example.employee.form;

import java.util.List;

import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.ImportValue;
import org.seasar.uruma.example.employee.dto.DepartmentDto;

/**
 * @author y-komori
 * 
 */
public class SearchForm {
	@ImportValue
	private String empno;

	@ImportValue
	private String ename;

	@ImportValue
	private String job;

	@ImportValue
	private String mgr;

	@ImportValue
	private String mname;

	@ImportValue
	private String fromHiredate;

	@ImportValue
	private String toHiredate;

	@ImportValue
	private String fromSal;

	@ImportValue
	private String toSal;

	@ImportValue(id = "dept")
	private String deptName;

	@ExportValue(id = "dept")
	private List<DepartmentDto> deptList;

	@ImportSelection(id = "dept")
	private DepartmentDto selectedDepartment;

	private Integer deptno;

	public List<DepartmentDto> getDeptList() {
		return deptList;
	}

	public void setDeptList(final List<DepartmentDto> deptList) {
		this.deptList = deptList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(final String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(final Integer deptno) {
		this.deptno = deptno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(final String empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(final String ename) {
		this.ename = ename;
	}

	public String getFromHiredate() {
		return fromHiredate;
	}

	public void setFromHiredate(final String fromHiredate) {
		this.fromHiredate = fromHiredate;
	}

	public String getFromSal() {
		return fromSal;
	}

	public void setFromSal(final String fromSal) {
		this.fromSal = fromSal;
	}

	public String getJob() {
		return job;
	}

	public void setJob(final String job) {
		this.job = job;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(final String mgr) {
		this.mgr = mgr;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(final String mname) {
		this.mname = mname;
	}

	public String getToHiredate() {
		return toHiredate;
	}

	public void setToHiredate(final String toHiredate) {
		this.toHiredate = toHiredate;
	}

	public String getToSal() {
		return toSal;
	}

	public void setToSal(final String toSal) {
		this.toSal = toSal;
	}

	public DepartmentDto getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(final DepartmentDto selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}
}
