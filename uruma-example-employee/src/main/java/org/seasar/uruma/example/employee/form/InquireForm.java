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

import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportExportValue;

public class InquireForm {
	@ImportExportValue
	private String empno;

	@ImportExportValue
	private String ename;

	@ImportExportValue
	private String job;

	@ImportExportValue
	private String mgr;

	@ExportValue
	private String mname;

	@ImportExportValue
	private String hiredate;

	@ImportExportValue
	private String sal;

	@ImportExportValue
	private String comm;

	@ExportValue
	private String dname;

	public String getComm() {
		return comm;
	}

	public void setComm(final String comm) {
		this.comm = comm;
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

	public String getMname() {
		return mname;
	}

	public void setMname(final String mname) {
		this.mname = mname;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(final String sal) {
		this.sal = sal;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(final String dname) {
		this.dname = dname;
	}
}
