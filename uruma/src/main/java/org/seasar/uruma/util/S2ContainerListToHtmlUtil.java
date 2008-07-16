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
package org.seasar.uruma.util;

import java.lang.reflect.Field;

import org.seasar.framework.aop.impl.PointcutImpl;
import org.seasar.framework.container.ArgDef;
import org.seasar.framework.container.ArgDefAware;
import org.seasar.framework.container.AspectDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ContainerNotRegisteredRuntimeException;
import org.seasar.framework.container.Expression;
import org.seasar.framework.container.MethodDef;
import org.seasar.framework.container.PropertyDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.impl.ArgDefImpl;
import org.seasar.framework.container.util.SmartDeployUtil;
import org.seasar.framework.env.Env;
import org.seasar.framework.util.StringUtil;

/**
 * S2ContainerにレジストされているオブジェクトをHTMLに出力するユーティリティです。<br />
 * 
 * @author y.sugigami
 */
public class S2ContainerListToHtmlUtil {

    /**
     * 初期化パラメータの設定パスのキーです。
     */
    public static final String CONFIG_PATH_KEY = "configPath";

    /**
     * a 初期化パラメータのデバッグのキーです。
     */
    public static final String DEBUG_KEY = "debug";

    /**
     * queryStringのコマンドのキーです。
     */
    public static final String COMMAND = "command";

    /**
     * queryStringの再起動のキーです。
     */
    public static final String RESTART = "restart";

    /**
     * queryStringの一覧のキーです。
     */
    public static final String LIST = "list";

    /**
     * パスです。
     */
    public static final String PATH = "path";

    private static final String MODE_BEGIN = "<strong><font color='#DC143C'>";

    private static final String MODE_END = "</font></strong>";

    /**
     * {@link S2Container}の中身({@link ComponentDef})を表示します。<br />
     * 
     * @param container
     *            {@link S2Container} オブジェクト
     * @param path
     *            dicon ファイルのパス
     * @return 表示内容
     */
    public static String list(final S2Container container, final String path) {
        StringBuffer sb = new StringBuffer();
        if (container == null) {
            sb.append("S2Container[" + escape(path) + "] is not found.");
            return sb.toString();
        }
        sb
                .append("<html><head><title>Seasar2 Component List</title></head><body>");
        try {
            sb.append("<h1>S2Container</h1>");
            sb.append(printSmartDeploy(container));
            sb.append("<ul>");
            try {
                sb.append("<li>path : <code>" + escape(container.getPath())
                        + "</code></li>");
                final String nameSpace = container.getNamespace();
                if (!StringUtil.isEmpty(nameSpace)) {
                    sb.append("<li>namespace : <code>" + escape(nameSpace)
                            + "</code></li>");
                }
                final String envValue = Env.getValue();
                if (!StringUtil.isEmpty(envValue)) {
                    sb.append("<li>env : <code>" + escape(envValue)
                            + "</code></li>");
                }
            } finally {
                sb.append("</ul>");
            }
            sb.append(listComponent(container));

            sb.append(listInclude(container, path));

        } finally {
            sb.append("</body></html>");
        }
        return sb.toString();
    }

    /**
     * {@link S2Container}の中身({@link ComponentDef})を表示します。<br />
     * 
     * @param path
     *            dicon ファイルのパス
     * @return 表示内容
     */
    public static String list(final String path) {
        final S2Container container = getContainer(path);
        return list(container, path);
    }

    /**
     * {@link S2Container}を返します。<br />
     * 
     * @param path
     *            dicon ファイルのパス
     * @return {@link S2Container}
     */
    protected static S2Container getContainer(final String path) {
        final S2Container root = SingletonS2ContainerFactory.getContainer();
        try {
            return StringUtil.isEmpty(path) ? root : root.getDescendant(path);
        } catch (final ContainerNotRegisteredRuntimeException e) {
            return null;
        }
    }

    private static StringBuffer printSmartDeploy(final S2Container container) {
        StringBuffer sb = new StringBuffer();
        sb.append("<p>S2Container is working under ");
        try {
            if (SmartDeployUtil.isHotdeployMode(container)) {
                sb.append(MODE_BEGIN + "HOT deploy" + MODE_END);
            } else if (SmartDeployUtil.isWarmdeployMode(container)) {
                sb.append(MODE_BEGIN + "WARM deploy" + MODE_END);
            } else if (SmartDeployUtil.isCooldeployMode(container)) {
                sb.append(MODE_BEGIN + "COOL deploy" + MODE_END);
            } else {
                sb.append("normal");
            }
        } finally {
            sb.append(" mode.</p>");
        }
        return sb;
    }

    private static StringBuffer listInclude(final S2Container container,
            final String path) {
        StringBuffer sb = new StringBuffer();
        if (container.getChildSize() == 0) {
            return sb;
        }
        sb.append("<h2>Includes</h2>");
        sb.append("<p><ul>");
        try {
            for (int i = 0; i < container.getChildSize(); ++i) {
                final S2Container child = container.getChild(i);
                final String childPath = child.getPath();
                sb.append("<li><h2 style='list-style-type: circle'><code>"
                        + childPath + "</code></h2></li>");

                sb.append(listComponent(child));
            }
        } finally {
            sb.append("</ul></p>");
        }
        return sb;
    }

    private static StringBuffer listComponent(final S2Container container) {
        StringBuffer sb = new StringBuffer();
        if (container.getComponentDefSize() == 0) {
            return sb;
        }
        sb.append("<h2>Components</h2>");
        sb.append("<p><ul>");
        try {
            for (int i = 0; i < container.getComponentDefSize(); ++i) {
                final ComponentDef cd = container.getComponentDef(i);
                sb.append(printComponent(cd));
            }
        } finally {
            sb.append("</ul></p>");
        }
        return sb;
    }

    private static StringBuffer printComponent(final ComponentDef cd) {
        StringBuffer sb = new StringBuffer();
        final String name = cd.getComponentName();
        final Class<?> clazz = cd.getComponentClass();
        sb.append("<li style='list-style-type: square'><code><strong>"
                + (name != null ? escape(name) : "-") + " ["
                + (clazz != null ? escape(clazz.getName()) : "-")
                + "]</strong></code>");
        sb.append("<ul>");
        sb.append("<li style='list-style-type: circle'>instance : <code>"
                + escape(cd.getInstanceDef().getName()) + "</code></li>");
        sb.append("<li style='list-style-type: circle'>autoBinding : <code>"
                + escape(cd.getAutoBindingDef().getName()) + "</code></li>");

        Expression expression = cd.getExpression();
        final String expr = (expression != null) ? expression.toString() : "";
        if (!StringUtil.isEmpty(expr)) {
            sb.append("<li style='list-style-type: circle'>ognl : <code>"
                    + escape(expr) + "</code></li>");
        }

        sb.append(printArg(cd));
        sb.append(printAspect(cd));
        sb.append(printProperty(cd));
        sb.append(printInitMethod(cd));
        sb.append(printDestroyMethod(cd));

        try {
            final Object component = cd.getComponent();
            sb
                    .append("<li style='list-style-type: circle'>toString : <pre style='border-style: solid; border-width: 1'>"
                            + escape(component.toString()) + "</pre></li>");
        } catch (final Exception ignore) {
        }
        sb.append("</ul>");
        return sb;
    }

    private static StringBuffer printArg(final ArgDefAware cd) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cd.getArgDefSize(); ++i) {
            sb.append("<li style='list-style-type: circle'>arg<ul>");
            final ArgDef ad = cd.getArgDef(i);

            Expression expression = ad.getExpression();
            final String expr = (expression != null) ? expression.toString()
                    : "";
            if (!StringUtil.isEmpty(expr)) {
                sb.append("<li style='list-style-type: circle'>ognl : <code>"
                        + escape(expr) + "</code></li>");
            }

            final ComponentDef child = getChildComponentDef(ad);
            if (child != null) {
                sb.append(printComponent(child));
            }

            sb.append("</ul></li>");
        }
        return sb;
    }

    private static StringBuffer printAspect(final ComponentDef cd) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cd.getAspectDefSize(); ++i) {
            sb.append("<li style='list-style-type: circle'>aspect<ul>");
            final AspectDef ad = cd.getAspectDef(i);
            final PointcutImpl pc = (PointcutImpl) ad.getPointcut();
            if (pc != null) {
                final String[] pointCuts = pc.getMethodNames();
                if (pointCuts != null && pointCuts.length > 0) {
                    sb
                            .append("<li style='list-style-type: circle'>pointcut<ul>");
                    for (int j = 0; j < pointCuts.length; ++j) {
                        sb.append("<li style='list-style-type: circle'><code>"
                                + escape(pointCuts[j]) + "</code></li>");
                    }
                    sb.append("</ul></li>");
                }
            }

            Expression expression = ad.getExpression();
            final String expr = (expression != null) ? expression.toString()
                    : "";
            if (!StringUtil.isEmpty(expr)) {
                sb.append("<li style='list-style-type: circle'>ognl : <code>"
                        + escape(expr) + "</code></li>");
            }

            final ComponentDef child = getChildComponentDef(ad);
            if (child != null) {
                sb.append(printComponent(child));
            }

            sb.append("</ul></li>");
        }
        return sb;
    }

    private static StringBuffer printProperty(final ComponentDef cd) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cd.getPropertyDefSize(); ++i) {
            sb.append("<li style='list-style-type: circle'>property<ul>");
            final PropertyDef pd = cd.getPropertyDef(i);
            sb.append("<li style='list-style-type: circle'>name : <code>"
                    + escape(pd.getPropertyName()) + "</code></li>");

            Expression expression = pd.getExpression();
            final String expr = (expression != null) ? expression.toString()
                    : "";
            if (!StringUtil.isEmpty(expr)) {
                sb.append("<li style='list-style-type: circle'>ognl : <code>"
                        + escape(expr) + "</code></li>");
            }

            final ComponentDef child = getChildComponentDef(pd);
            if (child != null) {
                sb.append(printComponent(child));
            }

            sb.append("</ul></li>");
        }
        return sb;
    }

    private static StringBuffer printInitMethod(final ComponentDef cd) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cd.getInitMethodDefSize(); ++i) {
            sb.append("<li style='list-style-type: circle'>initMethod<ul>");
            sb.append(printMethod(cd.getInitMethodDef(i)));
            sb.append("</ul></li>");
        }
        return sb;
    }

    private static StringBuffer printDestroyMethod(final ComponentDef cd) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cd.getDestroyMethodDefSize(); ++i) {
            sb.append("<li style='list-style-type: circle'>destroyMethod<ul>");
            sb.append(printMethod(cd.getDestroyMethodDef(i)));
            sb.append("</ul></li>");
        }
        return sb;
    }

    private static StringBuffer printMethod(final MethodDef md) {
        StringBuffer sb = new StringBuffer();
        sb.append("<li style='list-style-type: circle'>name : <code>"
                + escape(md.getMethodName()) + "</code></li>");

        Expression expression = md.getExpression();
        final String expr = (expression != null) ? expression.toString() : "";
        if (!StringUtil.isEmpty(expr)) {
            sb.append("<li style='list-style-type: circle'>ognl : <code>"
                    + escape(expr) + "</code></li>");
        }

        final ComponentDef child = getChildComponentDef(md);
        if (child != null) {
            sb.append(printComponent(child));
        }
        return sb;
    }

    private static ComponentDef getChildComponentDef(final Object o) {
        try {
            final Field f = ArgDefImpl.class
                    .getDeclaredField("childComponentDef");
            f.setAccessible(true);
            return (ComponentDef) f.get(o);
        } catch (final Exception e) {
            return null;
        }
    }

    private static String escape(final String text) {
        if (text == null) {
            return "null";
        }
        final StringBuffer buf = new StringBuffer(text.length() * 4);
        for (int i = 0; i < text.length(); ++i) {
            final char ch = text.charAt(i);
            switch (ch) {
            case '<':
                buf.append("&lt;");
                break;
            case '>':
                buf.append("&gt;");
                break;
            case '&':
                buf.append("&amp;");
                break;
            case '"':
                buf.append("&quot;");
                break;
            default:
                buf.append(ch);
                break;
            }
        }
        return new String(buf);
    }
}
