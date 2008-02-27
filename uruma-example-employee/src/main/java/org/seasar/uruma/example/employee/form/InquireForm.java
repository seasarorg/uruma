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

/**
 * 表示画面のためのフォームクラスです。<br />
 * 
 * @author y-komori
 */
public class InquireForm {
	@ImportExportValue
	public String empno;

	@ImportExportValue
	public String ename;

	@ImportExportValue
	public String job;

	@ImportExportValue
	public String mgr;

	@ExportValue
	public String mname;

	@ImportExportValue
	public String hiredate;

	@ImportExportValue
	public String sal;

	@ImportExportValue
	public String comm;

	@ExportValue
	public String dname;

}
