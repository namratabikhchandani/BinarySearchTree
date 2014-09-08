package binary.search.tree;

import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>>{
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;
	
	private TreeNode<T> root;
	private int size;
	
	public TreeNode<T> getRoot(){
		return this.root;
	}
	
	public void setRoot(TreeNode<T> root){
		this.root = root;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Inserts the node with value equal to the parameter specified and returns the updated tree's root.
	 * @param item
	 * @return
	 */
	public void insert(T item){
		
		//error check
		if(item == null){
			throw new NullPointerException("Item to be inserted cannot be null");
		}
		TreeNode<T> parent = null;
		TreeNode<T> current = root;
		TreeNode<T> newNode = new TreeNode<T>(item);
		size++;
		while(current!=null){
			parent = current;
			if(item.compareTo(current.getData())==SMALLER){
				current=current.getLeft();
			}
			else{
				current=current.getRight();
			}
		}
		newNode.setParent(parent);
		if(parent == null ){
			//root empty
			root = newNode;
		}
		else{
			if(item.compareTo(parent.getData())==SMALLER){
				parent.setLeft(newNode);
			}
			else{
				parent.setRight(newNode);
			}
		}
	}
	
	/**
	 * searches the BST for requested item, if found returns the node.
	 * If the item is not found in the tree it throws NoSuchElementException.
	 * If the tree root or requested item is null, it throws NullPointerException.
	 * @param item
	 * @return
	 */
	public TreeNode<T> searchBST(T item){
		//error check
		if(item == null){
			throw new NullPointerException("Item to be inserted cannot be null");
		}
		
		if(root==null){
			throw new NullPointerException("Empty Tree. Cannot find item.");
		}
		
		TreeNode<T> current = root;
		while(current!=null){
			if(item.compareTo(current.getData())==EQUAL){
				return current;
			}
			else if(item.compareTo(current.getData())==SMALLER){
				current=current.getLeft();
			}
			else{
				current=current.getRight();
			}
		}
		throw new NoSuchElementException(item + "cannot be found in the tree");
	}
	
	/**
	 * returns the leftmost element of the tree
	 * @return
	 */
	public TreeNode<T> treeMinimum(TreeNode<T> root){
		if(root==null){
			throw new NullPointerException("Minimum cannot be found. Root is null");
		}
		TreeNode<T> current = root;
		while(current.getLeft()!=null){
			current=current.getLeft();
		}
		return current;
	}
	
	/**
	 * returns the rightmost element of the tree
	 * @return
	 */
	public TreeNode<T> treeMaximum(TreeNode<T> root){
		if(root==null){
			throw new NullPointerException("Minimum cannot be found. Root is null");
		}
		TreeNode<T> current = root;
		while(current.getRight()!=null){
			current=current.getRight();
		}
		return current;
	}
	
	/**
	 * returns the successor of the requested item as it would appear in the inorder traversal of the tree.
	 * @param item
	 * @return
	 */
	public TreeNode<T> inorderSuccessor(T item){
		//error check
		if(item == null){
			throw new NullPointerException("Item to be inserted cannot be null");
		}
		TreeNode<T> node = searchBST(item);
		if(node.getRight()!=null){
			return treeMinimum(node.getRight());
		}
		TreeNode<T> parent = node.getParent();
		while(parent!=null && node == parent.getRight()){
			node = parent;
			parent = node.getParent();
		}
		return parent;
	}
	
	/**
	 * returns the predecessor of the requested item as it would appear in the inorder traversal of the tree.
	 * @param item
	 * @return
	 */
	public TreeNode<T> inorderPredecessor(T item){
		//error check
		if(item == null){
			throw new NullPointerException("Item to be inserted cannot be null");
		}
		TreeNode<T> node = searchBST(item);
		if(node.getLeft()!=null){
			return treeMaximum(node.getLeft());
		}
		TreeNode<T> parent = node.getParent();
		while(parent!=null && node == parent.getLeft()){
			node = parent;
			parent = node.getParent();
		}
		return parent;
	}
	
	/**
	 * deletes the node with data equal to the requested item
	 * @param item
	 */
	public void delete(T item){
		//error check
		if(item == null){
			throw new NullPointerException("Item to be inserted cannot be null");
		}
		
		TreeNode<T> deleteThisNode = searchBST(item);
		TreeNode<T> tempNode = null;
		if(deleteThisNode.getLeft() == null || deleteThisNode.getRight()==null){
			tempNode = deleteThisNode;
		}
		else{
			tempNode = inorderSuccessor(item);
		}
		TreeNode<T> child = null;
		if(tempNode.getLeft()!=null){
			child = tempNode.getLeft();
		}
		else{
			child = tempNode.getRight();
		}
		if(child!=null){
			child.setParent(tempNode.getParent());
		}
		if(tempNode.getParent() == null){
			root = child;
		}
		else{
			if(tempNode.equals(tempNode.getParent().getLeft())){
				tempNode.getParent().setLeft(child);
			}
			else{
				tempNode.getParent().setRight(child);
			}
		}
		if(!tempNode.equals(deleteThisNode)){
			deleteThisNode.setData(tempNode.getData());
			tempNode.setLeft(deleteThisNode.getLeft());
			tempNode.setRight(deleteThisNode.getRight());
		}
		size--;
	}
	
	/**
	 * Performs inorder tree walk given the root of the tree
	 * @param root
	 */
	public void inorder(TreeNode<T> root){
		if(root!=null){
			inorder(root.getLeft());
			System.out.print(root.getData() + " ");
			inorder(root.getRight());
		}
	}
}
