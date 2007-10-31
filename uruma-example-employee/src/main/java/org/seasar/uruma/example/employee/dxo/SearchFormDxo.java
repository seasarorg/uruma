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
package org.seasar.uruma.example.employee.dxo;

import org.seasar.extension.dxo.annotation.DatePattern;
import org.seasar.uruma.example.employee.dto.EmployeeSearchDto;
import org.seasar.uruma.example.employee.form.SearchForm;

/**
 * @author bskuroneko
 * 
 */
public interface SearchFormDxo {

    @DatePattern("yyyy/MM/dd")
    EmployeeSearchDto convert(SearchForm searchForm);

}
