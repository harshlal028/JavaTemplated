package com.ds.tree.operations;

public class BinaryTree {

	Node root;

	/**
	 * Creates an empty binary tree -- a null root pointer.
	 */
	public BinaryTree() {
		this.root = null;
	}

	/**
	 * Returns true if the given target is in the binary tree. Uses a recursive
	 * helper.
	 */
	public boolean lookup(int data) {
		return (lookup(root, data));
	}

	/**
	 * Recursive lookup -- given a node, recur down searching for the given data.
	 */
	private boolean lookup(Node node, int data) {
		if (node == null) {
			return (false);
		}
		if (data == node.data) {
			return (true);
		} else if (data < node.data) {
			return (lookup(node.left, data));
		} else {
			return (lookup(node.right, data));
		}
	}

	/**
	 * Inserts the given data into the binary tree. Uses a recursive helper.
	 */
	public void insert(int data) {
		root = insert(root, data);
	}

	/**
	 * Recursive insert -- given a node pointer, recur down and insert the given
	 * data into the tree. Returns the new node pointer (the standard way to
	 * communicate a changed pointer back to the caller).
	 */
	private Node insert(Node node, int data) {
		if (node == null) {
			node = new Node(data);
		} else {
			if (data <= node.data) {
				node.left = insert(node.left, data);
			} else {
				node.right = insert(node.right, data);
			}
		}
		return (node); // in any case, return the new pointer to the caller
	}

	/**
	 * 1) Nested static class doesn't need reference of Outer class but non static
	 * nested class or Inner class requires Outer class reference. You can not
	 * create instance of Inner class without creating instance of Outer class. This
	 * is by far most important thing to consider while making a nested class static
	 * or non static.
	 * 
	 * 2) static class is actually static member of class and can be used in static
	 * context e.g. static method or static block of Outer class.
	 * 
	 * 3) Another difference between static and non static nested class is that you
	 * can not access non static members e.g. method and field into nested static
	 * class directly. If you do you will get error like "non static member can not
	 * be used in static context". While Inner class can access both static and non
	 * static member of Outer class.
	 * 
	 * @author harsh
	 *
	 */
	private static class Node {
		Node left;
		Node right;
		int data;

		Node(int newData) {
			left = null;
			right = null;
			data = newData;
		}
	}
}
