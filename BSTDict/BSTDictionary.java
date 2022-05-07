package BSTDict;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A binary search tree that holds info on the keys and value of how many
 * declared by Dictionary <K, V> key and value
 * subtrees/dictionaries are known as Right and Left
 * @author Lileah Tunno
 */

public class BSTDictionary<K, V> implements Dictionary<K, V> {

	// constructors, Left/BSTdic<k,v>, Right/BSTdic<k,v>, nokey/int, value/v, key/k

	private K key;
	private V value;
	private int noKeys;
	private BSTDictionary<K, V> Right;
	private BSTDictionary<K, V> Left;

	public BSTDictionary() {
	}

	/**
	 * Put a key together with its associated value into the dictionary. If the key
	 * already exists then the new value replaces the current value associated with
	 * the key. Values can be retrieved using the get method.
	 * 
	 * @param key   the key
	 * @param value the new value
	 * @return the original value if the key already exists in the dictionary,
	 *         otherwise null.
	 *         BST.this Inside of oot use BST use push left Pop pushleft bst.left
	 *         Stack<BSTDIC<K>>
	 */
	@Override
	public V put(K key, V value) {
		if (isEmpty()) {
			this.key = key;
			this.value = value;
			noKeys = 1;
			Right = new BSTDictionary<K, V>();
			Left = new BSTDictionary<K, V>();
			return null; // return 0? -1?
		}
		// comkey = compare key
		int comkey = ((Comparable) key).compareTo(this.key);
		if (comkey == 0) {
			V vresult = this.value;
			this.value = value;
			return vresult;
		} else if (comkey < 0) {
			V vresult = Left.put(key, value);
			if (vresult == null) {
				noKeys++;
			}
			return vresult;
			// vresult = value result after returned
		} else {
			V vresult = Right.put(key, value);
			if (vresult == null) {
				noKeys++;
			}
			return vresult;
		}
	}

	/**
	 * Get the current value associated with a given key.
	 * @param key the key
	 * @return the current value associated with the key in the dictionary if found,
	 *         otherwise null.
	 */
	@Override
	public V get(K key) {
		if (isEmpty()) {
			return null;
		}
		int comkey = ((Comparable) key).compareTo(this.key);
		if (comkey == 0) {
			return value;
		} else if (comkey < 0) {
			return Left.get(key);
		}
		return Right.get(key);
	}

	/**
	 * Reference from binary search delete method in book
	 * Remove the key and its associated value associated from the dictionary. The
	 * value associated with the key is returned. If the key does not exist in the
	 * dictionary then null is returned.
	 * 
	 * @param key the key
	 * @return the value associated with the removed key in the dictionary. If the
	 *         key did not exist then null.
	 *         Replace X to keep tree by using largest to right, smallest if left
	 */
	@Override
	public V remove(K key) {
		if (isEmpty()) {
			return null;
		}
		// value removed from left, removed key
		int comkey = ((Comparable) key).compareTo(this.key);
		if (comkey < 0) {
			V vDummy = Left.remove(key);
			if (vDummy != null) {
				noKeys--;
			}
			return vDummy;
		} else if (comkey > 0) {
			V vDummy = Right.remove(key);
			if (vDummy != null) {
				noKeys--;
			}
			return vDummy;
		}
		// value removed from right, removed key
		V value = this.value;
		if (Right.isEmpty()) {
			this.key = Left.key;
			this.value = Left.value;
			this.Right = Left.Right;
			this.Left = Left.Left;
			this.noKeys = Left.noKeys;

		} else if (Left.isEmpty()) {
			this.key = Right.key;
			this.value = Right.value;
			this.Right = Right.Right;
			this.Left = Right.Left;
			this.noKeys = Right.noKeys;

		} else {
			BSTDictionary<K, V> valDummy = Right.largestChild();
			this.key = valDummy.key;
			this.value = valDummy.value;
			noKeys--;
			return Right.remove(this.key);
		}
		return value;
	}

	/**
	 * based Largest child off of book This methods finds the right most value by
	 * pushing all the way left
	 * @param stack
	 */
	private BSTDictionary<K, V> largestChild() {
		if (Left.isEmpty()) {
			return this;
		} else {
			return Left.largestChild();
		}
	}

	/**
	 * Create an Iterator to iterate over the keys of the dictionary.
	 * @return an Iterator to iterator over the keys.
	 */
	@Override
	public Iterator<K> keys() {
		return new IteratorKey();
	}

	/**
	 * Test if the dictionary is empty
	 * @return true if the dictionary is empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		if (Right == null && Left == null && key == null && value == null && noKeys == 0) {
			// if(isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Get the number of keys in the dictionary
	 * @return the number of keys in the dictionary
	 */
	@Override
	public int noKeys() {
		return noKeys;
	}

	/**
	 * The iterator method returns the key in order traveral
	 * must not copy keys into data structure holding all keys
	 */
	private class IteratorKey implements Iterator<K> {
		Stack<BSTDictionary<K, V>> stack = new Stack<BSTDictionary<K, V>>();
		IteratorKey() {
			pushLeftStack(stack);
		}
		
		/**
		 * based off of isLeaf in book for boolean type hasNext tests to see whether
		 * tree's iterator has any keys in the stack.
		 * 
		 * @return true if there are keys and false if empty Note to self ! - is not
		 */
		public boolean hasNext() {
			if (!stack.isEmpty()) {
				return true;
			}
			return false;
		}

		/**
		 * based off of getLeftSubtree in book for k type/reference
		 * @throw NoSuchElementException if no (!) hasNext
		 * @return right of subtree root
		 */
		public K next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			BSTDictionary<K, V> dummy = stack.pop();
			dummy.Right.pushLeftStack(stack);
			return dummy.key;
		}
	}

	/**
	 * pushes stack with dictionary to the left
	 * @param of Stack BSTDic K,V stack
	 */
	private void pushLeftStack(Stack<BSTDictionary<K, V>> stack) {
		if (!isEmpty()) {
			stack.push(this);
			Left.pushLeftStack(stack);
		}
	}
}