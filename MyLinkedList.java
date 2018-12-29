public class MyLinkedList<T>{
	Node<T> head;


	MyLinkedList(){
		head = null;
		
	}

	public Boolean isEmpty(){
		if(head == null){
			return true;
		}
		else{
			return false;
		}
	}

	public void addFirst(T o){
		Node<T> v = new Node();
	
		(v.data)=o;
		v.next= head;
		head = v;
	}

	

	public void removeFirst()throws Exception{
		if(head==null){
			throw new Exception("List empty so can't remove");
		}
		head = head.next;
	}

	public void deleteNode(T o)throws Exception{
		if(head==null){
			throw new Exception("List empty so can't remove");
		}
		Node<T> temp = head;
		if((head.data).equals(o)){
			head= head.next;
			return;
		}
		while(temp.next!=null){
			if(temp.next.data.equals(o)){
				temp.next = temp.next.next;
				break;

			}
			temp=temp.next;
		}
		if(temp.next == null){
			throw new Exception("The object doesn't exist");
		}

	}
	public void removeLast()throws Exception{
		if(head==null){
			throw new Exception("List empty so can't remove");
		}
		Node<T> curr = head;
		while(curr.next.next != null){
			curr = curr.next;
		}
		curr.next= null;
	}

	public Boolean isMember(T o){
		

		Node<T> temp = head;
		while(temp!= null){
			if((temp.data).equals(o)){
				return true;}
			temp = temp.next;
		}
		return false;
	}

	public int length(){
		Node<T> temp = head;
		int counter=0;
		while(temp!=null){
			temp=temp.next;
			counter++;
		}

		return counter;
	}
	public void addLast(T o){
		Node<T> temp =head;
		Node<T> new_node = new Node<>();
		new_node.data = o;
		new_node.next = null;
		if(head==null){
			head = new_node;
		}
		else{

		while(temp.next!=null){
			temp = temp.next;
		}
		
		temp.next = new_node;
	}
		

	}

	
}


class Node<T>{
	public T data;
	public Node<T> next;

	public Node(T d, Node<T> n){
		data.equals(d);
		next = n;
	}


	public Node(){
		next=null;

	}
}
