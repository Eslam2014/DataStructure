package linkedlist;

public class Node <T>{
private T value;
private Node<T> next;

public Node(T value) {
	// TODO Auto-generated constructor stub
	this.value=value;
	this.next=null;
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
