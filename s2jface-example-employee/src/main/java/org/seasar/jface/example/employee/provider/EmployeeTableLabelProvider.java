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
package org.seasar.jface.example.employee.provider;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Formatter;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import examples.jsf.dto.EmployeeDto;

/**
 * @author y-komori
 * 
 */
public class EmployeeTableLabelProvider implements ITableLabelProvider {

    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        EmployeeDto dto = (EmployeeDto) element;

        switch (columnIndex) {
        case 0:
            return dto.getEmpno().toString();

        case 1:
            String empName = dto.getEname();
            return empName != null ? empName : "";

        case 2:
            String job = dto.getJob();
            return job != null ? job : "";

        case 3:
            Integer mgr = dto.getMgr();
            return mgr != null ? mgr.toString() : "";

        case 4:
            Date hiredate = dto.getHiredate();
            Formatter formatter = new Formatter();
            formatter.format("%tY/%<tm/%<td", hiredate);
            return hiredate != null ? formatter.out().toString() : "";

        case 5:
            BigDecimal salary = dto.getSal();
            return salary != null ? salary.toString() : "";

        case 6:
            BigDecimal commission = dto.getComm();
            return commission != null ? commission.toString() : "";

        case 7:
            String deptName = dto.getDname();
            return deptName != null ? deptName : "";

        default:
            break;
        }
        return "---";
    }

    public void addListener(ILabelProviderListener listener) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
    }
}
