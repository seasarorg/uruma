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
package org.seasar.uruma.example.employee.form;

import java.util.List;

import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.example.employee.dto.DepartmentDto;

public class EmployeeEditForm {
	@ImportExportValue
	private String empno;

	@ImportExportValue
	private String ename;

	@ImportExportValue
	private String job;

	@ImportExportValue
	private String mgr;

	@ImportExportValue
	private String hiredate;

	@ImportExportValue
	private String sal;

	@ImportExportValue
	private String comm;

	@ExportValue(id = "dept")
	private List<DepartmentDto> deptList;

	@ExportSelection(id = "dept")
	@ImportSelection(id = "dept")
	private DepartmentDto selectedDepartmentDto;

	private Integer deptno;

	private int versionNo;

	public String getComm() {
		return comm;
	}

	public void setComm(final String comm) {
		this.comm = comm;
	}

	public List<DepartmentDto> getDeptList() {
		return deptList;
	}

	public void setDeptList(final List<DepartmentDto> deptList) {
		this.deptList = deptList;
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

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(final String hiredate) {
		this.hiredate = hiredate;
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

	public String getSal() {
		return sal;
	}

	public void setSal(final String sal) {
		this.sal = sal;
	}

	public DepartmentDto getSelectedDepartmentDto() {
		return selectedDepartmentDto;
	}

	public void setSelectedDepartmentDto(
			final DepartmentDto selectedDepartmentDto) {
		this.selectedDepartmentDto = selectedDepartmentDto;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(final int versionNo) {
		this.versionNo = versionNo;
	}
}
