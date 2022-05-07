package DLL;
/**
 * This Double Linked list will be able to get nodes to create a circular double linked list
 * Get, append, insert, remove, get number of items
 * is able to make the list clear and empty as well
 * @author Lileah Tunno
 *
 * @param <E>
 */
public class DoubleLinkedList <E> implements ItemList <E> {

	private class Node {
		E item;
		Node next;
		Node prev;
		
	/**
	 * 
	 * @param item
	 * @param next
	 * @param prev
	 */
		public Node(E item, Node next, Node prev) {
			super();
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}
/**
 * makes head and noitems empty to start
 */
	private Node head = null;
	private int noItems = 0;
	
	
	public DoubleLinkedList() {
		head = new Node(null, null, null);
		head.next = head;
		head.prev = head;
	}
	
	/**
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if (index < 0 || index >= noItems) {
			throw new IndexOutOfBoundsException();
		}
		Node ptr = head.next;
		while (index-- > 0) {
			ptr = ptr.next;
		}
		return ptr;
	}
	
    /**
     * Append an item to the end of the list
     * @param item – item to be appended
     */
	@Override
	public void appendItem(E item) {
		Node ptr = head.prev;
		ptr.next = ptr.next.prev = new Node(item, ptr.next, ptr);
		noItems++;
	}	

    /**
     * Insert an item at a specified index position
     * @param item – item to be inserted
     * @param index – index position where to insert the item
     * @throw IndexOutOfBoundsException if index is < 0 or > no of items
     */
	@Override
	public void insertItem(int index, E item) {
		if (index == getNoItems()) {
			appendItem(item);
		} else {
			Node ptr = getNode(index);
			ptr.prev.next = ptr.prev.next.prev = new Node(item, ptr.prev.next, ptr);
			noItems++;
		}
	}

	/**
     * Return an item at a specified index
     * @param index – index of the item to return
     * @return the item at the specified index
     * @throw IndexOutOfBoundsException if index is < 0 or >= no of items
     */
	@Override
	public E getItem(int index) {
		if (index < 0 && index >= noItems) {
			throw new IndexOutOfBoundsException();
		} 
		Node ptr = getNode(index);
		E item = ptr.item;
		return item;
	}
	
	/**
     * Remove an item at a specified index
     * @param index – index of the item to be removed
     * @return the removed item
     * @throw IndexOutOfBoundsException if index is < 0 or >= no of items
     */
	@Override
	public E removeItem(int index) {
		Node ptr = getNode (index);
		ptr.prev.next = ptr.next;
		ptr.next.prev = ptr.prev;
		noItems--;
		return ptr.item;
	}

	/**
	 * Return the number of items currently in the list
	 * @return the number of items in the list
	 */
	@Override
	public int getNoItems() {
		return noItems;
	}
	
	/**
	 * Determine if the list is empty
	 * @return true if the list is empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		if (noItems == 0) {
			return true;
		} 
			return false;
	}

	/**
	 * Clear the list.  The list becomes empty
	 */
	@Override
	public void clear() {
		head = head.next;
		head = head.prev;
		noItems = 0;
	}
}