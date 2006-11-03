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

import org.seasar.jface.annotation.ArgumentValue;
import org.seasar.jface.annotation.BindType;
import org.seasar.jface.annotation.BindingValue;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.example.employee.dxo.InquireActionDxo;

import examples.jsf.dto.EmployeeDto;

/**
 * @author bskuroneko
 * 
 */
public class InquireAction extends AbstractOneEmployeeAction {

    private InquireActionDxo inquireActionDxo;

    @BindingValue(type = BindType.Export)
    private String dname;

    @ArgumentValue
    private EmployeeDto inquireEmployee;

    @InitializeMethod
    public void initialize() {
        inquireActionDxo.convert(inquireEmployee, this);
    }

    @EventListener(id = "ok")
    public void onOk() {
        shell.close();
    }

    public void setInquireActionDxo(InquireActionDxo inquireActionDxo) {
        this.inquireActionDxo = inquireActionDxo;
    }

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

}
