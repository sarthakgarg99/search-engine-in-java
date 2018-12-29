public class MyHashTable{
	int size =101;
	public MyLinkedList<WordEntry>[] HashTable = new MyLinkedList[size];
	int HashCode(String str){
		int h=0;
		for(int i=0;i<str.length();i++){
			h= h+ str.charAt(i);
		}
		return h;
	}
	 int getHashIndex(String str){
		return HashCode(str)%101;

	}
	void addPositionsForWord(WordEntry w){

		int x = getHashIndex(w.word);
		if(HashTable[x]==null){
			MyLinkedList<WordEntry> new_list = new MyLinkedList();
			HashTable[x] = new_list;

		}
		int flag=0;
		Node<WordEntry> temp = HashTable[x].head;
		while(temp!=null){
			if((temp.data.word).equals(w.word)){
				
				temp.data.addPositions(w.pos_list);
				flag++;
			}
			temp=temp.next;
		}
		if(temp==null && flag ==0){
			/*Node<WordEntry> new_node = new Node<>();
			new_node.data = w;
			new_node.next = null;
			Node<WordEntry> temp2 = HashTable[x].head; 
			while(temp2.next!=null){
				temp2 = temp2.next;
			}
			temp2.next = new_node;*/
			WordEntry we = new WordEntry(w.word);
			we.addPositions(w.pos_list);
			HashTable[x].addLast(we);
			
		}


	}




}