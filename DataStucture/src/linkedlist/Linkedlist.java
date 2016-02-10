package linkedlist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Linkedlist<T> implements List<T> {

	static class Node<T> {
		private T value;
		private Node<T> next;

		public Node(T value) {
			this.value = value;
			this.next = null;
		}

		public T getValue() {
			return value;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public Linkedlist() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean add(T value) {
		checkValue(value, "not allow to insert null value");

		if (head == null) {
			head = tail = new Node<T>(value);
			size++;
			return true;
		}
		Node<T> tmp = new Node<T>(value);
		tail.next = tmp;
		tail = tmp;
		size++;
		return true;
	}

	@Override
	public void add(int index, T element) {
		if (index != size)
			checkIndex(index);
		checkValue(element, "not allow to insert null value");

		Node<T> node = new Node<T>(element);
		if (index == 0) {
			node.next = head;
			head = node;

		}
		Node<T> tmp = getPrevNode(index);
		node.next = tmp.next;
		tmp.next = node;

	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(Object o) {

		if (o == null || checkObjectType(o))
			return false;

		return indexOf(o) != -1;
	}

	private boolean checkObjectType(Object o) {
		if (o instanceof Linkedlist<?>) {
			return false;
		}
		return true;
	}

	@Override
	public int indexOf(Object o) {
		if (o == null || !checkObjectType(o))
			return -1;

		Node<T> tmp = head;
		int counter = 0;
		while (tmp != null) {
			if (tmp.value.equals(o)) {
				return counter;
			}
			counter++;
			tmp = tmp.next;
		}
		return -1;
	}

	@Override
	public Iterator<T> iterator() {

		Iterator<T> it = new Iterator<T>() {
			Node<T> tmp = head;

			@Override
			public boolean hasNext() {
				if (tmp != null)
					return true;
				return false;
			}

			@Override
			public T next() {
				if (tmp != null) {
					T value = tmp.value;
					tmp = tmp.next;
					return value;
				}
				return null;
			}
		};
		return it;
	}

	@Override
	public Object[] toArray() {
		Node<T> tmp = head;
		Object[] objectLinkedlist = new Object[size];
		int index = 0;
		while (tmp != null) {
			objectLinkedlist[index] = tmp;
			tmp = tmp.next;
			index++;
		}
		return objectLinkedlist;

	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {

		T[] arrayLinkedlist = a.length < size ? Arrays.copyOf(a, size) : a;

		@SuppressWarnings("unchecked")
		Node<T> tmp = (Node<T>) head;
		int index = 0;
		while (tmp != null) {
			arrayLinkedlist[index] = tmp.value;
			tmp = tmp.next;
			index++;
		}
		return arrayLinkedlist;
	}

	@Override
	public T get(int index) {

		checkIndex(index);
		Node<T> tmp = head;

		for (int counter = 0; counter <= index; counter++) {
			tmp = tmp.next;
		}
		return tmp.value;

	}

	@Override
	public T set(int index, T element) {

		if (index != size)
			checkIndex(index);
		checkValue(element, "not allow to insert null value");

		Node<T> node = new Node<T>(element);
		if (index == 0) {
			head = node;
			return null;
		}

		Node<T> tmp = getPrevNode(index);

		node.next = tmp.next.next;
		tmp.next = node;
		return tmp.value;
	}

	@Override
	public boolean remove(Object o) {
		if (o != null || head == null || checkObjectType(o))
			return false;

		@SuppressWarnings("unchecked")
		T value = (T) o;
		if (head.value.equals(value)) {
			if (size == 1) {
				head = tail = null;
			}
			head = head.next;
			size--;
			return true;
		}

		Node<T> tmp = head;
		while (tmp.next != null) {
			if (tmp.next.value.equals(value)) {
				Node<T> preTmp = tmp.next;
				if (preTmp.next == null)
					tail = tmp;
				tmp.next = preTmp.next;
				preTmp = null;
				size--;
				return true;

			} else
				tmp = tmp.next;
		}
		return false;

	}

	@Override
	public T remove(int index) {
		checkIndex(index);
		T value;
		if (index == 0) {
			value = head.value;
			head = tail = null;
			size--;
			return value;
		}

		Node<T> tmp = getPrevNode(index);
		Node<T> dTmp = tmp.next;
		value = dTmp.value;
		if (dTmp.next == null)
			tail = tmp;
		tmp.next = dTmp.next;
		dTmp = null;
		size--;
		return value;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (c == null) {
			return false;
		}
		for (T t : c) {
			add(t);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if (index != size) {
			checkIndex(index);
		}
		if (c == null) {
			return false;
		}

		int count = index;
		for (T t : c) {
			add(count, t);
			count++;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null) {
			return false;
		}

		boolean result = true;
		for (Object t : c) {
			result &= remove(t);
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		Node<T> tmp=head;
		while(tmp!=null){
			head=tmp.next;
			tmp=null;
			tmp=head;
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	private void checkValue(T value, String message) {
		if (value == null) {
			throw new IllegalArgumentException(message);
		}

	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder linkedlist = new StringBuilder();
		Node<T> tmp = head;
		while (tmp != null) {
			linkedlist.append(tmp.value + " -> ");
			tmp = tmp.next;
		}
		return linkedlist.toString();
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index " + index + " out of range");
		}
	}

	
	private Node<T> getPrevNode(int index) {
		Node<T> tmp = head;
		for (int counter = 0; counter < index; counter++) {
			tmp = tmp.next;
		}
		return tmp;
	}
}
