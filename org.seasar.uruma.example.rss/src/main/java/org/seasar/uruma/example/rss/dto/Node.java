package org.seasar.uruma.example.rss.dto;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private T target;
	
	private T parent;
	
	private List<Node<T>> Children = new ArrayList<Node<T>>();
	
	public void setTarget(T target) {
		this.target = target;
	}
	
	public T getTarget() {
		return this.target;
	}

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public List<Node<T>> getChildren() {
		return Children;
	}

	public void addChild(Node<T> child) {
		this.Children.add(child);
	}
	
	public boolean hasChildren() {
		if ( ! Children.isEmpty()) {
			return true;
		}
		return false;
	}
	
}
