package org.seasar.uruma.example.rss.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.seasar.uruma.example.rss.dto.Node;


/**
 * @author y-sugigami
 */
public class FeedTreeContentProvider implements ITreeContentProvider {

	public Object[] getChildren(final Object parentElement) {
		Node node = (Node) parentElement;
		if (node.hasChildren()) {
			return node.getChildren().toArray();
		} 
		return new Object[] {};
	}

	public Object getParent(final Object element) {
		Node node = (Node) element;
		return node.getParent();
	}

	public boolean hasChildren(final Object element) {
		Node node = (Node) element;
		return node.hasChildren();
	}

	public Object[] getElements(final Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
	}

}
