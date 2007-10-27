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
package org.seasar.uruma.example.employee.action;

import javax.annotation.Resource;

import org.eclipse.swt.widgets.Shell;
import org.seasar.framework.container.annotation.tiger.InterType;
import org.seasar.framework.container.annotation.tiger.Property;
import org.seasar.framework.container.annotation.tiger.PropertyType;
import org.seasar.jface.annotation.ArgumentValue;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.uruma.example.employee.dto.EmployeeDto;
import org.seasar.uruma.example.employee.dxo.InquireFormDxo;
import org.seasar.uruma.example.employee.form.InquireForm;

/**
 * @author bskuroneko
 */
public class InquireAction {
    private Shell shell;
    
    private InquireForm inquireForm;
    
    @Resource
    private InquireFormDxo inquireFormDxo;

    @ArgumentValue
    private EmployeeDto inquireEmployee;

    @InitializeMethod
    public void initialize() {
        inquireFormDxo.convert(inquireEmployee, inquireForm);
    }

    @EventListener(id = "ok")
    public void onOk() {
        shell.close();
    }
    
    public void setInquireForm(InquireForm inquireForm) {
        this.inquireForm = inquireForm;
    }
}
