public class PageIndex{
	public MyLinkedList<WordEntry> we_list;
	PageIndex(){
		we_list = new MyLinkedList();
	}

	public void addPositionForWord(String str, Position p){
		Node<WordEntry> temp = we_list.head;
       	int counter =0;
		while(temp!=null){
			if((temp.data.word).equals(str)){
				
				temp.data.addPosition(p);
				counter ++;
				
			}
			temp =temp.next;
		}
		 
		if(temp==null && counter ==0){
		WordEntry we = new WordEntry(str);
		we.pos_list.addLast(p);
		we.tree.insert(p);
		we_list.addLast(we);
	   }
		
	}


	 public MyLinkedList<WordEntry> getWordEntries(){
	 	return we_list;
	 }
	 public WordEntry returnWordEntryFromString(String str){
	 	Node<WordEntry> temp = we_list.head;
	 	int flag =0;
	 	while(temp!=null){
	 		
	 		if(temp.data.word.equals(str)){
	 			flag++;
	 			break;
	 		}
	 		temp = temp.next;
	 	}
	 	


	 	


	 	if(flag!=0){
	 		
	 		// WordEntry we = new WordEntry(str);
	 		// Node<Position> temp3 = temp.data.pos_list.head;
	 		// if(temp3!=null){
	 		// 	String tempname = temp3.data.p.page_name;
	 		// 	while(temp3 != null){
	 		// 		if(temp3.data.p.page_name.equals(tempname))
	 		// 		{
	 		// 			we.addPosition(temp3.data);
	 		// 		}
	 		// 		temp3 = temp3.next;
	 		// 	}	
	 		// 	return we;
	 		// }
	 		return temp.data;
	 	}
	 	else return null;
	 }






}