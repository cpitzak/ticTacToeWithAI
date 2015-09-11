package com.eeprojects.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Node<E> {

	private E value;
	private Node<E> parent;
	private List<Node<E>> children;
	private Integer weight;

	public Node(Node<E> parent, E value) {
		this.parent = parent;
		this.value = value;
		children = new ArrayList<Node<E>>();
	}

	public Node<E> getParent() {
		return parent;
	}

	public List<Node<E>> getChildren() {
		return children;
	}

	public void addChild(Node<E> node) {
		children.add(node);
	}

	public E getValue() {
		return value;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
