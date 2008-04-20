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
package org.seasar.uruma.ui.dialogs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.util.MessageUtil;

/**
 * 例外スタックトレースを表示するためのダイアログです。<br />
 * 
 * @author y-komori
 */
public class UrumaErrorDialog extends IconAndMessageDialog implements
        UrumaConstants {
    protected static final int DETAILS_ITEM_COUNT = 10;

    protected static final String NESTING_INDENT = "  ";

    protected String title;

    protected Button detailsButton;

    protected TreeViewer detailTreeViewer;

    protected boolean detailsCreated;

    protected Throwable rootThrowable;

    protected List<Throwable> causes = new ArrayList<Throwable>();

    protected Clipboard clipboard;

    protected Image errorIcon;

    protected Image dialogIcon;

    /**
     * {@link UrumaErrorDialog} を構築します。<br />
     * 
     * @param parentShell
     *            親 {@link Shell} オブジェクト
     * @param title
     *            ダイアログタイトル
     * @param message
     *            メッセージ
     * @param ex
     *            例外オブジェクト
     */
    public UrumaErrorDialog(final Shell parentShell, final String title,
            final String message, final Throwable ex) {
        super(parentShell);
        this.title = (title != null ? title : "Error");
        this.message = (message != null ? message : "");
        this.rootThrowable = ex;
        getCause(ex);
        getImageIcons(parentShell);

        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    protected void getCause(final Throwable throwable) {
        Throwable cause = throwable.getCause();
        if ((cause != null) && !causes.contains(cause)) {
            causes.add(cause);
            getCause(cause);
        }
    }

    protected void getImageIcons(final Shell shell) {
        ClassLoader loader = getClass().getClassLoader();

        InputStream is = loader.getResourceAsStream("images/error.png");
        errorIcon = new Image(shell.getDisplay(), is);

        is = loader.getResourceAsStream("images/uruma16.png");
        dialogIcon = new Image(shell.getDisplay(), is);
    }

    /*
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(this.title);
        shell.setImage(dialogIcon);
    }

    /*
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        createMessageArea(parent);

        // create a composite with standard margins and spacing
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        layout.numColumns = 2;
        composite.setLayout(layout);
        GridData childData = new GridData(SWT.FILL, SWT.FILL, true, false);
        childData.horizontalSpan = 2;
        composite.setLayoutData(childData);
        composite.setFont(parent.getFont());

        return composite;
    }

    /*
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        // create OK and Details buttons
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
                true);
        createDetailsButton(parent);
    }

    protected void createDetailsButton(final Composite parent) {
        detailsButton = createButton(parent, IDialogConstants.DETAILS_ID,
                IDialogConstants.SHOW_DETAILS_LABEL, false);
    }

    protected void toggleDetailsArea() {
        Point windowSize = getShell().getSize();
        Point oldSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
        if (detailsCreated) {
            detailTreeViewer.getTree().dispose();
            detailsCreated = false;
            detailsButton.setText(IDialogConstants.SHOW_DETAILS_LABEL);
        } else {
            detailTreeViewer = createDetailsTreeViewer((Composite) getContents());
            detailTreeViewer.setInput("");
            detailsButton.setText(IDialogConstants.HIDE_DETAILS_LABEL);
            detailsCreated = true;
        }
        Point newSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
        getShell()
                .setSize(
                        new Point(windowSize.x, windowSize.y
                                + (newSize.y - oldSize.y)));
    }

    protected TreeViewer createDetailsTreeViewer(final Composite parent) {
        TreeViewer viewer = new TreeViewer(parent, SWT.SINGLE
                | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        Tree tree = viewer.getTree();

        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.heightHint = tree.getItemHeight() * DETAILS_ITEM_COUNT;
        data.horizontalSpan = 2;
        tree.setLayoutData(data);
        tree.setFont(parent.getFont());
        createContextMenu(tree);

        viewer.setContentProvider(new DetailsContentProvider());
        viewer.setLabelProvider(new DetailLabelProvider());
        viewer.setAutoExpandLevel(2);
        return viewer;
    }

    protected void createContextMenu(final Tree tree) {
        Menu copyMenu = new Menu(tree);

        MenuItem allCopyItem = new MenuItem(copyMenu, SWT.NONE);
        allCopyItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                copyToClipboard(rootThrowable);
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                widgetSelected(e);
            }
        });
        allCopyItem.setText(MessageUtil.getMessageWithBundleName(
                URUMA_MESSAGE_BASE, "COPY_ALL"));

        MenuItem copyItem = new MenuItem(copyMenu, SWT.NONE);
        copyItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                StructuredSelection selection = (StructuredSelection) detailTreeViewer
                        .getSelection();
                if (selection != null) {
                    Object selected = selection.getFirstElement();
                    copyToClipboard(selected);
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                widgetSelected(e);
            }
        });
        copyItem.setText(MessageUtil.getMessageWithBundleName(
                URUMA_MESSAGE_BASE, "COPY_SELECTION"));

        tree.setMenu(copyMenu);
    }

    private void copyToClipboard(final Object selected) {
        if (clipboard != null) {
            clipboard.dispose();
        }
        if (selected == null) {
            return;
        }

        StringBuffer statusBuffer = new StringBuffer(8192);
        if (selected instanceof Throwable) {
            populateCopyBuffer(statusBuffer, (Throwable) selected);
        } else if (selected instanceof StackTraceElement) {
            populateCopyBuffer(statusBuffer, (StackTraceElement) selected);
        }

        clipboard = new Clipboard(detailTreeViewer.getTree().getDisplay());
        clipboard.setContents(new Object[] { statusBuffer.toString() },
                new Transfer[] { TextTransfer.getInstance() });

    }

    protected void populateCopyBuffer(final StringBuffer buffer,
            final Throwable throwable) {
        buffer.append(throwable.getClass().getName());
        String message = throwable.getMessage();
        if (message != null) {
            buffer.append(" : ");
            buffer.append(message);
        }
        buffer.append("\n");

        StackTraceElement[] elements = throwable.getStackTrace();
        if (elements != null) {
            for (StackTraceElement stackTraceElement : elements) {
                populateCopyBuffer(buffer, stackTraceElement);
            }
        }

        Throwable cause = throwable.getCause();
        if (cause != null) {
            buffer.append("\n");
            populateCopyBuffer(buffer, cause);
        }
    }

    protected void populateCopyBuffer(final StringBuffer buffer,
            final StackTraceElement element) {
        buffer.append(NESTING_INDENT);
        buffer.append("at " + element + "\n");
    }

    /*
     * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(final int id) {
        if (id == IDialogConstants.DETAILS_ID) {
            toggleDetailsArea();
        } else {
            super.buttonPressed(id);
        }
    }

    /*
     * @see org.eclipse.jface.dialogs.Dialog#close()
     */
    @Override
    public boolean close() {
        if (clipboard != null) {
            clipboard.dispose();
        }
        if ((errorIcon != null) && !errorIcon.isDisposed()) {
            errorIcon.dispose();
        }
        if ((dialogIcon != null) && !dialogIcon.isDisposed()) {
            dialogIcon.dispose();
        }
        return super.close();
    }

    /*
     * @see org.eclipse.jface.dialogs.IconAndMessageDialog#getImage()
     */
    @Override
    protected Image getImage() {
        return getSystemImage(SWT.ICON_ERROR);
    }

    protected Image getSystemImage(final int id) {
        Shell shell = getShell();
        final Display display;
        if (shell == null) {
            shell = getParentShell();
        }
        if (shell == null) {
            display = Display.getCurrent();
        } else {
            display = shell.getDisplay();
        }

        final Image[] image = new Image[1];
        display.syncExec(new Runnable() {
            public void run() {
                image[0] = display.getSystemImage(id);
            }
        });

        return image[0];
    }

    /**
     * 例外表示ツリー用のコンテントプロバイダです。<br />
     * 
     * @author y-komori
     */
    class DetailsContentProvider implements ITreeContentProvider {
        /*
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren(final Object parentElement) {
            if (parentElement instanceof String) {
                return new Object[] { rootThrowable };
            } else if (parentElement == rootThrowable) {
                if (causes.size() > 0) {
                    return causes.toArray();
                } else {
                    return rootThrowable.getStackTrace();
                }
            } else if (parentElement instanceof Throwable) {
                return ((Throwable) parentElement).getStackTrace();
            } else {
                return null;
            }
        }

        /*
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent(final Object element) {
            return null;
        }

        /*
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren(final Object element) {
            if ((element instanceof String) || (element instanceof Throwable)) {
                return true;
            } else {
                return false;
            }
        }

        /*
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(final Object inputElement) {
            return getChildren(inputElement);
        }

        /*
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose() {
        }

        /*
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(final Viewer viewer, final Object oldInput,
                final Object newInput) {
        }
    }

    /**
     * 例外表示ツリー用のラベルプロバイダです。<br />
     * 
     * @author y-komori
     */
    class DetailLabelProvider implements ILabelProvider {
        /*
         * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
         */
        public Image getImage(final Object element) {
            if (element instanceof Throwable) {
                return errorIcon;
            } else {
                return null;
            }
        }

        /*
         * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
         */
        public String getText(final Object element) {
            if (element instanceof Throwable) {
                return element.getClass().getName() + " "
                        + ((Throwable) element).getMessage();
            } else if (element instanceof StackTraceElement) {
                StackTraceElement st = (StackTraceElement) element;
                String line = "at " + st.toString();
                return line;
            } else {
                return NULL_STRING;
            }
        }

        /*
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(final ILabelProviderListener listener) {
        }

        /*
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
        }

        /*
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty(final Object element,
                final String property) {
            return false;
        }

        /*
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(final ILabelProviderListener listener) {
        }
    }
}
