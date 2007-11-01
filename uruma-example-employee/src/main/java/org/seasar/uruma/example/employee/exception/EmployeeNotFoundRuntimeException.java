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
package org.seasar.uruma.example.employee.exception;

public class EmployeeNotFoundRuntimeException extends AppRuntimeException {

	private static final long serialVersionUID = 2463317432158127302L;

	private int empno;

	public EmployeeNotFoundRuntimeException(final int empno) {
		super("examples.jsf.EmployeeNotFound", new Object[] { String
				.valueOf(empno) });
		this.empno = empno;
	}

	public int getEmpno() {
		return empno;
	}
}