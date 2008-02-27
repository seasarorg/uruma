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

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table = "DEPT")
public class Department implements Serializable {

	private static final long serialVersionUID = -1118005104108335819L;

	public int deptno;

	public String dname;

	public String loc;

	public int versionNo;

	public Department() {
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Department))
			return false;
		Department castOther = (Department) other;
		return this.deptno == castOther.deptno;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		buf.append(deptno).append(", ");
		buf.append(dname).append(", ");
		buf.append(loc).append(", ");
		buf.append(versionNo).append("]");
		return buf.toString();
	}

	@Override
	public int hashCode() {
		return this.deptno;
	}
}
