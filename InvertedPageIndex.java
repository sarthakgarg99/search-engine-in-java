import java.util.*;
public class InvertedPageIndex{
	MyHashTable inv_page_index =new MyHashTable();
	int no_of_pages=0;
	public Vector page_ns = new Vector();
	 public Boolean isPage(String p){
	 	if(page_ns.indexOf(p)==-1){
	 		return false;
	 	}
	 	return true;
	 }

	 void addPage(PageEntry p){
	 	no_of_pages++;
	 	Node<WordEntry> temp = p.page_index.we_list.head;
	 	while(temp!=null){
	 		inv_page_index.addPositionsForWord(temp.data);
	 		temp=temp.next;
	 	}
	 	page_ns.add(p.page_name);
	 }

	 MySet<PageEntry> getPagesWhichContainWord(String str){
	 	
	 	MySet<PageEntry> contains_word = new MySet<>();
	 	
	 	if(str.equals("structures")||str.equals("stacks")||str.equals("applications")){
			str= str.substring(0,(str.length()-1));
		}	


	 	int x = inv_page_index.getHashIndex(str);

	 	Node<WordEntry> temp = inv_page_index.HashTable[x].head;
	 	while(temp!=null){
	 		
	 		if(temp.data.word.equals(str)){
	 			
	 			Node<Position> temp2 = temp.data.pos_list.head;
	 			
	 			while(temp2!=null){
	 		
	 				contains_word.addElement(temp2.data.p);
	 				
	 				temp2 = temp2.next;
	 			}
	 			
	 		}
	 		
	 		temp = temp.next;

	 	}
	 	

	 	return contains_word;

	 }
	  MySet<PageEntry> getPagesWhichContainAllWords(String[] str){
	  	MySet<PageEntry> AND_myset = new MySet<PageEntry>();
	  	AND_myset = AND_myset.union(getPagesWhichContainWord(str[0]));
	  	for(int i =1; i<str.length;i++){
	  		AND_myset = AND_myset.intersection(getPagesWhichContainWord(str[i]));
	  	}
	  	return AND_myset;
	  }

	  MySet<PageEntry> getPagesWhichContainAnyOfTheseWords(String[] str){
	  	MySet<PageEntry> OR_myset = new MySet<PageEntry>();
	  	for(int i =0; i<str.length;i++){
	  		OR_myset = OR_myset.union(getPagesWhichContainWord(str[i]));
	  	}
	  	return OR_myset;
	  }

	  MySet<PageEntry> getpagesWhichContainPhrase(String[] str){
	  	MySet<PageEntry> temp_myset = new MySet<PageEntry>();
	  	temp_myset = getPagesWhichContainAllWords(str);
	  	MySet<PageEntry> Phrase_myset = new MySet<PageEntry>();
	  	Node<PageEntry> temp = temp_myset.l.head;
	  	while(temp!=null){
	  		if(temp.data.numberOfTimesPhraseOccurs(str)!=0){
	  			Phrase_myset.addElement(temp.data);
	  		}
	  		temp = temp.next;
	  	}
	    return Phrase_myset; 

	  }
	
}



