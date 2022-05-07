package LLS;

import java.util.NoSuchElementException;
/**
 * Class to implement item stack as a linked list.
 * @author Lileah Tunno
 */
public class LinkListStack<E> implements ItemStack<E> {
// Insert inner class Node<E>
	class Node<E> {
		E items;
		Node<E> next;

//Node contructor
		public Node(E item, Node<E> next) {
			super();
			this.items = item;
			this.next = next;
		}
	}

	private Node<E> head = null;
	public int noItems = 0;

	/**
	 * The reference to the first stack node. Data fields
	 */
	public LinkListStack() {
		this.head = new Node(null, null);
	}

	/**
	 * Push an item onto the stack
	 * @param item – item to be pushed
	 */
	@Override
	public E pushItem(E item) {
		Node ptr = new Node<>(item, head.next);
		ptr.next = head;
		head = ptr;
		noItems++;
		return item;
	}

	/**
	 * Pop an item of the stack
	 * @return the popped item
	 * @throw NoSuchElementException if stack is empty
	 */
	@Override
	public E popItem() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			E ptr = head.items;
			head = head.next;
			noItems--;
			return ptr;
		}
	}

	/**
	 * Return the item on the top of the stack without removing it
	 * @return the item on the top of the stack
	 * @throw NoSuchElementException if stack is empty
	 */
	@Override
	public E peekItem() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return head.items;
			// return(E) head.items;
		}
	}

	/**
	 * See whether the stack is empty.
	 * @return true if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		if (noItems == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Clear the stack, removes all items.
	 */
	@Override
	public void clear() {
		head = null;
	}
}