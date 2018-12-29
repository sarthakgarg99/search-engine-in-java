import java.util.*;
import java.io.*;

public class SearchEngine{
	InvertedPageIndex SearchEngine;
	SearchEngine(){
		SearchEngine = new InvertedPageIndex();
	}
	public void performAction(String actionMessage){

			String s="";
		
			Vector<String> v=new Vector<String>();
			for(int i=0;i<actionMessage.length();i++){
				if(actionMessage.charAt(i)!=' '){
					s= s+ actionMessage.charAt(i);
				}
				else{
					v.add(s);
					s="";
				}
			}
			v.add(s);
			String query =v.get(0);
			String answer = "";
			switch(query){
				case "addPage":
					String page_name = v.get(1);
					PageEntry page_entry = new PageEntry(page_name);
					SearchEngine.addPage(page_entry);
					break;

				case "queryFindPagesWhichContainWord":
					String word = v.get(1).toLowerCase();
					String[] word_array= new String[1];
					
					
					if(!word.equals("")){
	
						if(word.equals("structures")||word.equals("stacks")||word.equals("applications")){
			
						word= word.substring(0,(s.length()-1));
						
					}	}
					// System.out.println(word);
					word_array[0]=word;

					MySet<PageEntry> myset = SearchEngine.getPagesWhichContainWord(word);
					
					if(!myset.isEmpty()){

					Node<PageEntry> temp = myset.l.head;
					String[] pn = new String[1000];
					float[] fl = new float[1000];


					int var =0;
					while(temp!=null){

						pn[var] = temp.data.page_name;

						fl[var] = temp.data.getRelevanceForOR(word_array,SearchEngine);
						
						temp=temp.next;
						var++;
					}
					for(int i=0;i<var;i++){
						for(int j=0;j<var -i-1;j++){
							if(fl[j]<fl[j+1]){
								float temp2 = fl[j+1];
								fl[j+1]=fl[j];
								fl[j]=temp2;
								String temp1 = pn[j+1];
								pn[j+1]=pn[j];
								pn[j]=temp1;
							}
						}
					}
					for(int i=0;i<var;i++){
						answer =answer+ pn[i]+", ";
					}

					System.out.println(answer.substring(0,answer.length()-2));
				}
				

				else System.out.println("No webpage contains word "+word);
					break;

				case "queryFindPositionsOfWordInAPage":
					

					String word1 = v.get(1).toLowerCase();
					if(word1.equals("structures")||word1.equals("stacks")||word1.equals("applications")){
			
			word1= word1.substring(0,(word1.length()-1));
			
		}
					String filename= v.get(2);
					if(!SearchEngine.isPage(filename)){
						answer = "Webpage " + filename + " not found";
						System.out.println(answer);
					}
					else{
						PageEntry hey = new PageEntry(filename);
					Node<WordEntry> temp3= hey.getPageIndex().we_list.head;
					while(temp3!=null){
						if(temp3.data.word.equals(word1)){
							MyLinkedList<Position> ll = temp3.data.getAllPositionsForThisWord();
							Node<Position> temp2 = ll.head;
							while(temp2!=null){
								answer=answer+ Integer.toString(temp2.data.wi) + ", ";

								temp2 = temp2.next;
							}


							break;
						}
						temp3=temp3.next;
					}
					if(answer.equals("")){
						System.out.println("Webpage "+filename +" does not contain word "+word1);
						break;
					}

				System.out.println(answer.substring(0,answer.length()-2));	
					}
					
				break;
				



				case "queryFindPagesWhichContainAllWords":
					String[] str_array= new String[v.size()-1];
					for(int i =0;i<str_array.length;i++){
						str_array[i]=v.get(i+1).toLowerCase();
					}


				MySet<PageEntry> myset2 = SearchEngine.getPagesWhichContainAllWords(str_array);

				if(!myset2.isEmpty()){
					ArrayList<SearchResult> al_AND =new ArrayList<SearchResult>();
					Node<PageEntry> temp11 = myset2.l.head;
					
					while(temp11 !=null){
						al_AND.add(new SearchResult(temp11.data, temp11.data.getRelevanceForOR(str_array,SearchEngine)));
					    temp11 =temp11.next;
					    
					}
					MySort m1 = new MySort();
					al_AND = m1.sortThisList(al_AND);
					for(int i=0;i<al_AND.size();i++){  
						answer = answer + al_AND.get(i).p.page_name +", ";  
					} 
					System.out.println(answer.substring(0,answer.length()-2));

					
				}
				else System.out.println("No webpage contains all words");
				break;	


				case "queryFindPagesWhichContainAnyOfTheseWords":
					String[] str_array1= new String[v.size()-1];
					for(int i =0;i<str_array1.length;i++){
						str_array1[i]=v.get(i+1).toLowerCase();
					}
                  

				MySet<PageEntry> myset3 = SearchEngine.getPagesWhichContainAnyOfTheseWords(str_array1);


				if(!myset3.isEmpty()){
					ArrayList<SearchResult> al_OR =new ArrayList<SearchResult>();
					Node<PageEntry> temp = myset3.l.head;
					
					while(temp !=null){
						al_OR.add(new SearchResult(temp.data, temp.data.getRelevanceForOR(str_array1, SearchEngine)));
					    temp =temp.next;
					    
					}
					MySort m = new MySort();
					al_OR = m.sortThisList(al_OR);
					for(SearchResult st:al_OR){  
						answer = answer + st.p.page_name+", ";  
					} 
					System.out.println(answer.substring(0,answer.length()-2)); 
				}
				else System.out.println("No webpage contains any of these webpages");
				break;

				case "queryFindPagesWhichContainPhrase":
					String[] str_array2= new String[v.size()-1];
					for(int i =0;i<str_array2.length;i++){
						str_array2[i]=v.get(i+1).toLowerCase();
					}


				MySet<PageEntry> myset4 = SearchEngine.getpagesWhichContainPhrase(str_array2);

				if(!myset4.isEmpty()){
					ArrayList<SearchResult> al_Phrase =new ArrayList<SearchResult>();
					Node<PageEntry> temp = myset4.l.head;
					
					while(temp !=null){
						al_Phrase.add(new SearchResult(temp.data, temp.data.getRelevanceForPhrase(str_array2)));
					    temp =temp.next;
					    
					}
					MySort m2 = new MySort();
					m2.sortThisList(al_Phrase);
					for(SearchResult st:al_Phrase){  
						answer = answer + st.p.page_name +", ";  
					} 
					System.out.println(answer.substring(0,answer.length()-2));
					}
					else System.out.println("No webpage contains this phrase");
				    break;


			
		}}

		//  public static void main(String args[]){
	
		// 	//try{
		// 	SearchEngine r = new SearchEngine();
		// 	r.performAction("addPage stack_cprogramming");
		// 	r.performAction("addPage stack_datastructure_wiki");
		// 	// r.performAction("addPage stack_cprogramming");
			
		// 	r.performAction("addPage stacklighting");

		// 	// r.performAction("queryFindPagesWhichContainWord stack");
		// 	// r.performAction("queryFindPositionsOfWordInAPage empty stack_datastructure_wiki");
		// 	r.performAction("addPage stackoverflow");
		// 	r.performAction("addPage stacklighting");
		// 	r.performAction("addPage stack_oracle");
		// 	r.performAction("addPage cmu_stack");

			 
		// 	// r.performAction("queryFindPositionsOfWordInAPage stack stack_datastructure_wiki");
		// 	r.performAction("queryFindPagesWhichContainPhrase data structure");
			






		// 	// r.performAction("queryFindPagesWhichContainWord delhi");
		// 	// r.performAction("queryFindPagesWhichContainWord stack");
		// 	// r.performAction("queryFindPagesWhichContainWord wikipedia");
		// 	// r.performAction("queryFindPositionsOfWordInAPage magazines stack_datastructure_wiki");
		// 	// r.performAction("queryFindPagesWhichContainWord allain");
		// 	// r.performAction("addPage stack_cprogramming");
		// 	// r.performAction("queryFindPagesWhichContainWord allain");
		// 	// r.performAction("queryFindPagesWhichContainWord C");
		// 	// r.performAction("queryFindPagesWhichContainWord C++");

		// 	// r.performAction("addPage stack_oracle");

		// 	// r.performAction("queryFindPagesWhichContainWord jdk");

		// 	// r.performAction("addPage stackoverflow");
		// 	// r.performAction("queryFindPagesWhichContainWord function");

		// 	// r.performAction("addPage stacklighting");
		// 	// r.performAction("addPage stackmagazine");
		// 	// r.performAction("queryFindPagesWhichContainWord magazines");




  //       //   }
		// //catch(Exception e){

		// //}	

		// } 
	}



