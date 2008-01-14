/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.rcp.autoregister;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InitMethodDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.factory.AnnotationHandler;
import org.seasar.framework.container.factory.AnnotationHandlerFactory;
import org.seasar.framework.container.impl.ArgDefImpl;
import org.seasar.framework.container.impl.InitMethodDefImpl;
import org.seasar.framework.container.impl.PropertyDefImpl;

/**
 * {@link UrumaAppAutoRegister} を {@link S2Container} へ登録するための
 * {@link ComponentDef} オブジェクトを生成するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaAppAutoRegisterBuilder {
    /**
     * {@link UrumaAppAutoRegister} のための {@link ComponentDef} オブジェクトを生成します。<br />
     * 
     * @param refClassName
     *            リファレンスクラス名称
     * @param basePackage
     *            ベースパッケージ名
     * @return {@link UrumaAppAutoRegister} の {@link ComponentDef} オブジェクト
     * @throws ClassNotFoundException
     */
    public static ComponentDef build(final String refClassName,
            final String basePackage) throws ClassNotFoundException {
        AnnotationHandler handler = AnnotationHandlerFactory
                .getAnnotationHandler();
        ComponentDef cd = handler.createComponentDef(
                UrumaAppAutoRegister.class, InstanceDefFactory.SINGLETON,
                AutoBindingDefFactory.AUTO, false);
        
        cd.addPropertyDef(new PropertyDefImpl("instanceDef",
                InstanceDefFactory.PROTOTYPE));
        cd.addInitMethodDef(ctreateAddReferenceClassDef(refClassName));
        cd.addInitMethodDef(createAddClassPatternDef(basePackage, ".*Action"));
        cd.addInitMethodDef(createAddClassPatternDef(basePackage,
                ".*ContentProvider"));
        cd.addInitMethodDef(createAddClassPatternDef(basePackage,
                ".*LabelProvider"));
        cd.addInitMethodDef(createAddClassPatternDef(basePackage, ".*Sorter"));
        
        handler.appendInitMethod(cd);
        handler.appendDestroyMethod(cd);
        handler.appendAspect(cd);
        handler.appendInterType(cd);
        handler.appendDI(cd);

        return cd;
    }

    protected static InitMethodDef ctreateAddReferenceClassDef(
            final String referenceClassName) throws ClassNotFoundException {
        InitMethodDef def = new InitMethodDefImpl("addReferenceClass");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> refClass = loader.loadClass(referenceClassName);
        def.addArgDef(new ArgDefImpl(refClass));
        return def;
    }

    protected static InitMethodDef createAddClassPatternDef(
            final String packageName, final String shortClass) {
        InitMethodDef def = new InitMethodDefImpl("addClassPattern");
        def.addArgDef(new ArgDefImpl(packageName));
        def.addArgDef(new ArgDefImpl(shortClass));
        return def;
    }
}
