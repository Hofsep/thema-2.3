package vraag1;

public interface Tree<E extends Comparable<E>> extends Iterable<E> {
	
	/* Return true if the element is in the tree*/
	public boolean search(E e);
	
	/* Insert element e into the binary search tree.
	 * Return true if the element is inserted successfully.*/
	public boolean insert(E e);
	
	/* Delete the specified element from the tree.
	 * Return true if the element is deleted succesfully. */
	public boolean delete(E e);
	
	/* Inorder traversal from the root*/
	public void inOrder();
	
	/* Inorder traversal from the root*/
	public void preOrder();
	
	/* Postorder traversal from the root*/
	public void postOrder();
	
	/* Get the number of nodes in the tree*/
	public int getSize();
	
	/* Return true if the tree is empty*/
	public boolean isEmpty();
	
	/* Return an iterator to traverse elements in the tree*/
	public java.util.Iterator<E> iterator();	

}
