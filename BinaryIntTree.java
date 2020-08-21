// An example binary tree with preorder, inorder, and postorder traversals
// Binary trees are typically constructed for a specific purpose, so there's no one-size-fits-all
// implementation
// This binary tree stores ints and has a structure with an easily-definable pattern (root n, 
// left 2n, right 2n+1)

// Leaf Node -> Node with null left and right pointers
// Branch Node -> Node with at least one non-null pointer
// Depth -> Node's distance from the root node
// Height -> Maximum depth of the binary tree - 1

public class BinaryIntTree {

	class IntTreeNode {

		public int data;
		public IntTreeNode left;
		public IntTreeNode right;

		public IntTreeNode(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

	}

	private IntTreeNode overallRoot;

	public BinaryIntTree(int max) {
		if (max < 0) {
			throw new IllegalArgumentException("Input cannot be negative.");
		} else {
			overallRoot = buildTree(1, max);
		}
	}

	private IntTreeNode buildTree(int n, int max) {
		if (n > max) {
			return null;
		} else {
			IntTreeNode left = buildTree(2 * n, max);
			IntTreeNode right = buildTree((2 * n) + 1, max);
			return new IntTreeNode(n, left, right);
		}
	}

	public void printPreorder() {
		System.out.print("preorder traversal:");
		printPreorder(overallRoot);
		System.out.println();
	}

	private void printPreorder(IntTreeNode root) {
		if (root != null) {
			System.out.print(" " + root.data);
			printPreorder(root.left);
			printPreorder(root.right);
		}
	}

	public void printInorder() {
		System.out.print("inorder traversal:");
		printInorder(overallRoot);
		System.out.println();
	}

	private void printInorder(IntTreeNode root) {
		if (root != null) {
			printInorder(root.left);
			System.out.print(" " + root.data);
			printInorder(root.right);
		}
	}

	public void printPostorder() {
		System.out.print("postorder traversal:");
		printPostorder(overallRoot);
		System.out.println();
	}

	private void printPostorder(IntTreeNode root) {
		if (root != null) {
			printPostorder(root.left);
			printPostorder(root.right);
			System.out.print(" " + root.data);
		}
	}

	public void printSideways() {
		printSideways(overallRoot, 0);
	}

	private void printSideways(IntTreeNode root, int level) {
		if (root != null) {
			printSideways(root.right, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			System.out.println(root.data);
			printSideways(root.left, level + 1);
		}
	}
	
	public int sum() {
		return sum(overallRoot);
	}
	
	private int sum(IntTreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return root.data + sum(root.left) + sum(root.right);
		}
	}
	
	public int maxDepth() {
		return maxDepth(overallRoot);
	}
	
	private int maxDepth(IntTreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
		}
	}
	
	public int countLeaves() {
		return countLeaves(overallRoot);
	}
	
	private int countLeaves(IntTreeNode root) {
		if (root == null) {
			return 0;
		} else if (root.left == null && root.right == null) {
			return 1;
		} else {
			return countLeaves(root.left) + countLeaves(root.right);
		}
	}

	public static void main(String[] args) {
		BinaryIntTree exampleTree = new BinaryIntTree(10);
		exampleTree.printSideways();
		exampleTree.printPreorder();
		exampleTree.printInorder();
		exampleTree.printPostorder();
	}
}
