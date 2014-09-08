package binary.search.tree;

public class TreeNode<T extends Comparable<T>> {
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;
	private TreeNode<T> parent;
	
	public TreeNode(T data){
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}


	
	
}
