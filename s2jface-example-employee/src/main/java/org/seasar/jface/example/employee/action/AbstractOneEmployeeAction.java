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
package org.seasar.jface.example.employee.action;

import org.eclipse.swt.widgets.Shell;
import org.seasar.jface.annotation.BindingValue;

public class AbstractOneEmployeeAction {

    protected Shell shell;

    @BindingValue
    private String empno;

    @BindingValue
    private String ename;

    @BindingValue
    private String job;

    @BindingValue
    private String mgr;

    @BindingValue
    private String hiredate;

    @BindingValue
    private String sal;

    @BindingValue
    private String comm;

    public String getComm() {
        return this.comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getEmpno() {
        return this.empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getHiredate() {
        return this.hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMgr() {
        return this.mgr;
    }

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public String getSal() {
        return this.sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

}
