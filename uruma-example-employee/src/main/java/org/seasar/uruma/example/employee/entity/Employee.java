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
package org.seasar.uruma.example.employee.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Relation;

@Bean(table = "EMP")
public class Employee implements Serializable {

	private static final long serialVersionUID = 5593337544677032847L;

	public Integer empno;

	public String ename;

	public String job;

	public Integer mgr;

	public java.util.Date hiredate;

	public BigDecimal sal;

	public BigDecimal comm;

	public Integer deptno;

	public int versionNo;

	@Relation(relationNo = 0, relationKey = "deptno")
	public Department department;

	@Relation(relationNo = 1, relationKey = "mgr:empno")
	public Employee manager;

	public Employee() {
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Employee)) {
			return false;
		}
		Employee castOther = (Employee) other;
		return empno == castOther.empno;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		setupToString(buf);
		buf.append("]");
		return buf.toString();
	}

	protected void setupToString(final StringBuffer buf) {
		buf.append(empno).append(", ");
		buf.append(ename).append(", ");
		buf.append(job).append(", ");
		buf.append(mgr).append(", ");
		buf.append(hiredate).append(", ");
		buf.append(sal).append(", ");
		buf.append(comm).append(", ");
		buf.append(deptno).append(", ");
		buf.append(versionNo);
	}

	@Override
	public int hashCode() {
		if (empno != null) {
			return empno.intValue();
		}
		return 0;
	}
}
