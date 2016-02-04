package linkedlist;

public class Linkedlist<T> {
	protected Node<T> head;
	protected Node<T> tail;
	protected int size;

public Linkedlist() {
		// TODO Auto-generated constructor stub
		head = null;
		tail = null;
		size = 0;

	}

public void append(T value) {
	checkValue(value, "not allow to insert null value");	
		if (head == null) {
			head = tail = new Node<T>(value);
			size++;
		} else {
			Node<T> tmp = new Node<T>(value);
			tail.setNext(tmp);
			tail = tmp;
			size++;
		}
	}

public int findIndexOf(T value) {
	checkValue(value, "can't search for null value");
		Node<T> tmp = head;
		int counter = 0;
		while (tmp != null) {
			counter++;
			if (tmp.getValue().equals(value)) {
				return counter-1;
			}
			tmp = tmp.getNext();
		}
		return -1;
	}

public T findValueOf(int index) {
	checkIndex(index);
			Node<T> tmp = head;
			int counter = 0;
			while (counter <= index) {
				if (counter == index) {
					return tmp.getValue();
				} else {
					tmp = tmp.getNext();
					counter++;
				}
			}	
		return null;
	}
	
public boolean insert(T value,int index){
	checkIndex(index);
	checkValue(value, "not allow to insert null value");
	Node<T> node=new Node<T>(value);
	if (index > size) {
		return false;
	} 
	else {
		Node<T> tmp = head;
		if(index==0){
			
			node.setNext(head);
			head=node;
		}
		else if(index == size-1){
			node.setNext(tail);
			tail=node;
		}
		else
		{
		int counter = 1;
		while (counter <= index) {
			if (counter == index-1) {
				node.setNext(tmp.getNext());
				tmp.setNext(node);
				return true;	
			} 
			else {
				tmp = tmp.getNext();
				counter++;
				}
		}
		
		}
		return false;
	}
	
}

public boolean delete(int index){
	checkIndex(index);
		Node<T> tmp = head;
		int counter = 0;
		while (counter <= index) {
			if (counter == index-1) {
				Node<T> dTmp=tmp.getNext();
				if(tmp.getNext().getNext()==null)
					tail=tmp;
				tmp.setNext(tmp.getNext().getNext());
				dTmp=null;
				size--;
				return true;
			} else {
				tmp = tmp.getNext();
				counter++;
			}
		}
	return false;	
}
public void deleteValue(T value){
	checkValue(value, "there are no null value in linked list");
   
	Node<T> tmp=head;
	if(head.getValue().equals(value)){
		head=head.getNext();
		tmp=null;
		size--;
	}
	else{
	while(tmp.getNext()!=null){
		
		if(tmp.getNext().getValue().equals(value)){
			Node<T> preTmp=tmp.getNext();
			if(tmp.getNext().getNext()==null)
				tail=tmp;
			tmp.setNext(tmp.getNext().getNext());
			preTmp=null;
			size--;
			break;
		}
		else
			tmp=tmp.getNext();
	}
	}
}
//Mergesort For Linked Lists
/*public void sort(){
	Node<T> tmp1=head;
	Node<T> tmp2;
	T value=head.getValue();
	
}*/
private void checkValue(T value,String message){
	if(value == null){
		   throw new IllegalArgumentException(message);
	   }
	
}
private void checkIndex(int index) {
	//assert(index < 0 ||index >= size ):"index "+index+" out of rang ";
	if(index < 0 ||index >= size){
		throw new IndexOutOfBoundsException("index "+index+" out of rang");
	}
}
public int size() {
		return size;
	}

	public Node<T> getHead() {
		return head;
	}

	public Node<T> getTail() {
		return tail;
	}

	public void display(){
		Node<T> tmp=head;
		while (tmp!=null) {
			System.out.print(tmp.getValue()+" -> ");
			tmp=tmp.getNext();
		}
		System.out.println(" ");
	}
}
