public class MySet<T>{
	public MyLinkedList<T> l;
    
	public MySet(){
		l= new MyLinkedList();
	}
	public Boolean isEmpty(){
		
		return l.isEmpty();
	}

	public Boolean isMember(T o){
		return l.isMember(o);
	}

	public void addElement(T o){
		if(l.isMember(o)){
			//System.out.println("This object already exists");
		}
		else l.addLast(o);
	}
	public void Delete(T o)throws Exception{
		l.deleteNode(o);
	}
	public MySet<T> union(MySet<T> a){
		MySet<T> union_set = new MySet<>();
		Node<T> temp = l.head;
		while(temp!=null){
				
			union_set.addElement(temp.data);
			
			temp = temp.next;
     
		}
		
		Node<T> temp2 = a.l.head;
		while(temp2!=null){
			if(!(union_set.isMember(temp2.data))){	
				union_set.addElement(temp2.data);
			}
			temp2 = temp2.next;
     
		}
     	return union_set;
	}



	public MySet intersection(MySet<T> a){
		MySet inter_set = new MySet();
		Node<T> temp = l.head;
		while(temp!=null){
			if(a.isMember(temp.data)){
				inter_set.addElement(temp.data);
			}
			temp=temp.next;
		}
		return inter_set;
		
	}

 	
 		
 		

 	}





