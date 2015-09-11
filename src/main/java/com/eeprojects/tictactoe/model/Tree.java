package com.eeprojects.tictactoe.model;

public class Tree<E> {

	private Node<E> root;

	public Tree(Node<E> root) {
		this.root = root;
	}

	public Node<E> getRoot() {
		return root;
	}

	//---------------------------------
	// code from internet below
	//---------------------------------
	/**
	 * Print a representation of this BST on System.out.
	 */
	public void print() {
		printHelper(root, "");
	}

	/**
	 * Print the BST rooted at root, with indent preceding all output lines. The
	 * nodes are printed in in-order.
	 *
	 * @param root
	 *            The root of the tree to be printed.
	 * @param indent
	 *            The string to go before output lines.
	 */
	private void printHelper(Node<E> root, String indent) {
		if (root == null) {
			System.out.println(indent + "null");
			return;
		}

		// Pick a pretty indent.
		String newIndent;
		if (indent.equals("")) {
			newIndent = ".. ";
		} else {
			newIndent = "..." + indent;
		}

		System.out.println(indent + root.getValue());
		for (Node<E> node : root.getChildren()) {
			printHelper(node, newIndent);
		}
	}
	//------------------------------------

}
