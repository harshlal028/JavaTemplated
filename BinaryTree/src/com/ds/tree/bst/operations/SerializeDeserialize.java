package com.ds.tree.bst.operations;

/**
 * Demonstration of Serialization-Deserialization of a BST (not any tree)
 * @author harsh
 *
 */
public class SerializeDeserialize {
	
	/* Function to serialize a BST */
	public static String serialize(Node root) {
    	return preorder(root);
    }
    
    /* This only works for a BST for general tree use BFS */
    public static String preorder(Node root)
    {
    	String sep = ":";
    	if(root == null)
    	{
    		return "";
    	}
    	return sep + root.data + sep + preorder(root.left) + preorder(root.right);
    }

    // Function to deserialize a tree
    public static Node deserialize(String data) {
    	String sep = ":";
    	String[] nodes = data.split(sep);
    	Node root = null;
    	for(int i=0; i<nodes.length; i++)
    	{
    		if(nodes[i].length()!= 0)
    			root = addNode(root, Integer.parseInt(nodes[i]));
    	}
        return root;
    }
    
    // utility to add nodes to a BST
    public static Node addNode(Node root, int val)
    {
    	if(null == root)
    	{
    		return new Node(val);
    	}
    	else if(val > root.data)
    	{
    		root.right = addNode(root.right, val);
    	}
    	else if(val <= root.data)
    	{
    		root.left = addNode(root.left, val);
    	}
    	return root;
    }
    
    // Utility to check if the trees are same.
    public static boolean sameTree(Node root1, Node root2)
    {
    	if(root1 == null && root2 == null)
    	{
    		return true;
    	}
    	else if(root1 != null && root2 != null)
    	{
    		return (root1.data == root2.data && sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right));
    	}
    	else
    	{
    		return(false);
    	}
    }
    
    public static void main(String[] args) {
		
    	Node n25 = new Node(25);
		Node n12 = new Node(12);
		Node n50 = new Node(50);
		Node n5 = new Node(5);
		Node n21 = new Node(21);
		Node n30 = new Node(30);
		Node n90 = new Node(90);
		Node n28 = new Node(28);
		Node n35 = new Node(35);
		
		n25.left =  n12;
		n25.right = n50;
		n12.left =  n5;
		n12.right = n21;
		n50.left =  n30;
		n50.right = n90;
		n30.left =  n28;
		n30.right = n35;
		
		String strTree = serialize(n25);
		System.out.println(strTree);
		System.out.println(sameTree(n25, deserialize(strTree)));
	}

}
