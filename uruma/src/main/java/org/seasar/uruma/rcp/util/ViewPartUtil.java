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
package org.seasar.uruma.rcp.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;
import org.seasar.uruma.core.UrumaConstants;

/**
 * {@link IViewPart} のためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class ViewPartUtil implements UrumaConstants {
    private ViewPartUtil() {

    }

    /**
     * 指定された <code>id</code> からプライマリ ID を取り出します。<br />
     * プライマリ ID とは、<code>:</code> よりも手前の部分です。<br />
     * 
     * @param id
     *            ID
     * @return プライマリID
     */
    public static String getPrimaryId(final String id) {
        if (id != null) {
            int pos = id.lastIndexOf(COLON);
            if (pos > -1) {
                return id.substring(0, pos);
            }
        }
        return id;
    }

    /**
     * 指定された <code>id</code> からセカンダリ ID を取り出します。<br />
     * セカンダリ ID とは、<code>:</code> よりも後ろの部分です。<br />
     * 
     * @param id
     *            ID
     * @return セカンダリ ID。セカンダリ ID が含まれない場合は <code>null</code>。
     */
    public static String getSecondaryId(final String id) {
        if (id != null) {
            int pos = id.lastIndexOf(COLON);
            if (pos > -1) {
                return id.substring(pos + 1);
            }
        }
        return null;
    }

    /**
     * プライマリ ID とセカンダリ ID を結合して返します。<br />
     * 
     * @param primaryId
     *            プライマリ ID
     * @param secondaryId
     *            セカンダリ ID
     * @return 結合した ID
     */
    public static String createFullId(final String primaryId,
            final String secondaryId) {
        if (secondaryId != null) {
            return primaryId + COLON + secondaryId;
        } else {
            return primaryId;
        }
    }

    /**
     * ビューを検索します。<br />
     * 
     * @param viewId
     *            ビューのID(RCP上のIDです)
     * @return 見つかったビューの {@link IViewDescriptor}。見つからない場合は <code>null</code>。
     */
    public static IViewDescriptor findViewDescriptor(final String viewId) {
        if (viewId == null) {
            return null;
        }

        String pId = getPrimaryId(viewId);
        IViewDescriptor[] descs = PlatformUI.getWorkbench().getViewRegistry()
                .getViews();
        for (int i = 0; i < descs.length; i++) {
            IViewDescriptor viewDescriptor = descs[i];
            if (pId.equals(viewDescriptor.getId())) {
                return viewDescriptor;
            }
        }
        return null;
    }

    /**
     * Uruma アプリケーションとして登録されたビューを検索します。<br />
     * マルチプルビューは一つのビューとして検索されます。<br />
     * 
     * @return 見つかったビューの {@link IViewDescriptor} リスト
     */
    public static List<IViewDescriptor> findUrumaAppViewDescs() {
        List<IViewDescriptor> result = new ArrayList<IViewDescriptor>();

        String prefix = UrumaServiceUtil.getService().getPluginId() + PERIOD;
        IViewDescriptor[] descs = PlatformUI.getWorkbench().getViewRegistry()
                .getViews();
        for (int i = 0; i < descs.length; i++) {
            IViewDescriptor desc = descs[i];
            if (desc.getId().startsWith(prefix)) {
                result.add(desc);
            }
        }
        return result;
    }

    /**
     * Uruma アプリケーションとして登録されたビューのうち、インスタンス化されているものを検索します。<br />
     * 
     * @return 見つかったビューの {@link IViewDescriptor} リスト
     */
    public static List<IViewReference> findUrumaAppViewRefs() {
        List<IViewReference> result = new ArrayList<IViewReference>();

        String prefix = UrumaServiceUtil.getService().getPluginId() + PERIOD;
        IWorkbenchPage page = getWorkbenchPage();
        if (page != null) {
            IViewReference[] refs = page.getViewReferences();
            for (int i = 0; i < refs.length; i++) {
                IViewReference ref = refs[i];
                if (ref.getId().startsWith(prefix)) {
                    result.add(ref);
                }
            }
        }

        return result;
    }

    /**
     * 指定されたビューの {@link IViewReference} を返します。<br />
     * 本メソッドの戻り値が <code>null</code> でなければ、指定されたビューは生成されています。
     * 
     * @param primaryId
     *            ビューの ID
     * @param secondaryId
     *            セカンダリ ID。指定しない場合は <code>null</code>
     * @return 指定されたビューの {@link IViewDescriptor}
     * @see IWorkbenchPage#findViewReference(String, String)
     */
    public static IViewReference findViewReference(final String primaryId,
            final String secondaryId) {
        IWorkbenchPage page = getWorkbenchPage();
        if (page != null) {
            return page.findViewReference(primaryId, secondaryId);
        } else {
            return null;
        }

    }

    /**
     * 指定された ID のビューをアクティブにします。<br />
     * ビューがまだ生成されていない場合は、生成します。<br />
     * 
     * @param id
     *            RCP のビュー ID
     */
    public static void acitivateView(final String id) {
        acitivateView(id, null);
    }

    /**
     * 指定された ID のビューをアクティブにします。<br />
     * ビューがまだ生成されていない場合は、生成します。<br />
     * 
     * @param primaryId
     *            RCP のビュー ID
     * @param secondaryId
     *            セカンダリ ID
     * @see IWorkbenchPage#showView(String, String, int)
     */
    public static void acitivateView(final String primaryId,
            final String secondaryId) {
        IWorkbenchPage page = getWorkbenchPage();
        if (page == null) {
            return;
        }

        try {
            int mode = IWorkbenchPage.VIEW_ACTIVATE;
            IViewReference ref = findViewReference(primaryId, secondaryId);
            if (ref == null) {
                mode = IWorkbenchPage.VIEW_CREATE;
            }
            page.showView(primaryId, secondaryId, mode);
        } catch (PartInitException ex) {
            // Do nothing
        }

    }

    /**
     * アクティブな {@link IWorkbenchPage} を返します。<br />
     * 
     * @return {@link IWorkbench} オブジェクト。取得できない場合は <code>null</code>。
     */
    public static IWorkbenchPage getWorkbenchPage() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        if (window != null) {
            return window.getActivePage();
        }
        return null;
    }
}
