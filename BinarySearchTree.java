// A binary search tree structure guarantees that for any given node, all nodes to the left contain 
// values less than or equal to the value in the given node and all nodes to the right contain 
// values greater than that in the given node

// N nodes in a BST typically require logN levels
// This makes insertions, deletions, and searches very efficient (O(logN)) on average because these
// actions require a level-by-level descent for a BST containing N nodes
// An inorder traversal of a BST will always return the BST's values in sorted order

public class BinarySearchTree<E extends Comparable<E>> {

	private static class TreeNode<E> {

		public E data;
		public TreeNode<E> left;
		public TreeNode<E> right;

		public TreeNode(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

	}

	private TreeNode<E> overallRoot;

	public BinarySearchTree() {
		overallRoot = null;
	}

	public void add(E value) {
		overallRoot = add(overallRoot, value);
	}

	private TreeNode<E> add(TreeNode<E> root, E value) {
		if (root == null) {
			root = new TreeNode<E>(value);
		} else if (root.data.compareTo(value) >= 0) {
			root.left = add(root.left, value);
		} else {
			root.right = add(root.right, value);
		}
		return root;
	}

	public boolean contains(E value) {
		return contains(value, overallRoot);
	}
	
	private boolean contains(E value, TreeNode<E> root) {
		if (root == null) {
			return false;
		} else {
			int compare = value.compareTo(root.data);
			if (compare == 0) {
				return true;
			} else if (compare < 0) {
				return contains(value, root.left);
			} else {
				return contains(value, root.right);
			}
		}
	}
	
	public void print() {
		printInorder(overallRoot);
	}
	
	private void printInorder(TreeNode<E> root) {
		if (root != null) {
			printInorder(root.left);
			System.out.print(" " + root.data);
			printInorder(root.right);
		}
	}
	
	public void printSideways() {
		System.out.println();
		printSideways(overallRoot, 1);
	}
	
	private void printSideways(TreeNode<E> root, int level) {
		if (root != null) {
			printSideways(root.right, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			System.out.println(root.data);
			printSideways(root.left, level + 1);
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree myBST = new BinarySearchTree();
		myBST.add("Lisa");
		myBST.add("Bart");
		myBST.add("Marge");
		myBST.add("Homer");
		myBST.add("Maggie");
		myBST.add("Flanders");
		myBST.add("Smithers");
		myBST.add("Milhouse");
		myBST.print();
		myBST.printSideways();
		System.out.println(myBST.contains("Homer"));
		System.out.println(myBST.contains("Martin"));
	}
}
