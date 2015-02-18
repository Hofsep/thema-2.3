package vraag1;

import java.util.Stack;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {

	protected TreeNode<E> root;
	protected int size = 0;
	
	/** Create a default binary search tree */
	public BST(){
	}
	
	/** Create a binary search tree from an array of object */
	public BST(E[] objects){
		for(int i = 0; i < objects.length; i++)
			insert(objects[i]);
	}
	
	
	
	@Override /** Return true if the element is in the tree */
	public boolean search(E e) {
		TreeNode<E> current = root;
		
		while(current != null){
			if(e.compareTo(current.element)<0)
			{
				current = current.left;
			}
			else if(e.compareTo(current.element)>0)
			{
				current = current.right;
			}
			else //element matches current.element
				return true; //element is found
		}
		
			
		return false;
	}

	@Override 
	/**
	 * Insert element e into the binary search tree.
	 * Return true if the element is inserted successfully.
	 */
	public boolean insert(E e) {
		if(root == null)
			root = createNewNode(e);
		else{
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current !=null)
				if (e.compareTo(current.element)<0)
				{
					parent = current;
					current = current.left;
				}
				else if(e.compareTo(current.element)>0){
					parent = current;
					current = current.right;
				}
				else
					return false; // Duplicate node not inserted
			
			//Create the new node and attach it to the parent node
			if(e.compareTo(parent.element)<0)
			{
				parent.left = createNewNode(e);
			}
			else
				parent.right = createNewNode(e);
		}
		size++;	
		return true;
	}
	
	protected TreeNode<E> createNewNode(E e)
	{
		return new TreeNode<E>(e);
	}
	
	@Override
	public void inorder()
	{
		inorder(root);
	}
	/**
	 * Inorder traversal from a subtree // NON-RECURSIVE 
	 * @param root
	 */
	protected void inorder(TreeNode<E> root)
	{
		TreeNode<E> current = root;
		Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
		while(current != null || stack != null)
		{
			if(current !=null)
			{
				stack.push(current);
				current = current.left;
				System.out.println(current.element + "");
			}
			else
			{
				current = stack.pop();
				System.out.println(current.element + "");
				if(current !=null)
				{
					current = current.right;
					System.out.println(current.element + "");
				}
			}
		}
	}
	
	
	@Override
	public boolean delete(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}