import java.util.*;
import java.io.*;
import java.lang.*;

public class PageEntry{
	public PageIndex page_index = new PageIndex();
	Vector<String> global;
	public String page_name;
	public int numberwithoutstop;

	public void global_insert(String str){
		String s = "";
		
		for(int i=0;i<str.length();i++){
				if(str.charAt(i)!=' ' && str.charAt(i)!='}'&&str.charAt(i)!='{' && str.charAt(i)!='['&&str.charAt(i)!=']'&&str.charAt(i)!='<'&&str.charAt(i)!='>'&&str.charAt(i)!='='&&str.charAt(i)!='('&&str.charAt(i)!=')'&&str.charAt(i)!='.'&&str.charAt(i)!=','&&str.charAt(i)!=';'&&str.charAt(i)!='\''&&str.charAt(i)!='"'&&str.charAt(i)!='?'&&str.charAt(i)!='#'&&str.charAt(i)!='!'&&str.charAt(i)!='-'&&str.charAt(i)!=':'){
					s= s+ Character.toLowerCase(str.charAt(i));
				}
				else{
					if(!s.equals("")){
						if(s.equals("structures")||s.equals("stacks")||s.equals("applications")){
			s= s.substring(0,(s.length()-1));
		}	
						global.add(s);
						s="";
					}
					
				}
			}
			
				if(!s.equals("")){
						if(s.equals("structures")||s.equals("stacks")||s.equals("applications")){
							s= s.substring(0,(s.length()-1));
						}	
				global.add(s);
			}
			
	}

	public Boolean check(String str){
		if(str.equals("a")||str.equals("an")||str.equals("they")||str.equals("the")||str.equals("these")||str.equals("this")||str.equals("for")||str.equals("is")||str.equals("are")||str.equals("was")||str.equals("of")||str.equals("or")||str.equals("and")||str.equals("does")||str.equals("will")||str.equals("whose"))
		return false;
		else return true;
	}

	public PageEntry(String PageName){
		try{
			 numberwithoutstop=0;
			 page_name = PageName;

			 global= new Vector<String>();
			 String fin = "webpages/"+ page_name;
			 
			 FileInputStream fstream = new FileInputStream(fin);
			  Scanner s = new Scanner (fstream);
			  while(s.hasNextLine()){
			  	String p = s.nextLine();
			  	global_insert(p);
			  }
			  int j=0;
			  int i=0;
			  for(j=0;j<global.size();j++){
			  	if(check(global.get(j))){
			  		numberwithoutstop++;
			  		i ++;
			  		Position pos = new Position(this,j+1);
			  		pos.position_AVL = i;
			  		page_index.addPositionForWord(global.get(j),pos);

			  	}
			  }
			

	    }
	    catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
    }

	public PageIndex getPageIndex(){
		return page_index;
	}
	
	public float getTermFrequency(String word){
		
		WordEntry ref = page_index.returnWordEntryFromString(word);
		if(ref == null){
			return 0;
		}
		Node<Position> temp2 = ref.pos_list.head;
	 		while(temp2!=null){
	 		temp2 =temp2.next;}
		if(ref==null){
			return 0;
		}
		float numerator = ref.pos_list.length();
		float denominator = numberwithoutstop;
		// return numerator/denominator;
		return numerator/denominator;
	}

	 /*public float getRelevanceForAND(String[] str){
		float relevanceAND=0;
		for(int i =0;i<str.length;i++){
			relevanceAND = relevanceAND + getTermFrequency(str[i]);
		}

		return relevanceAND;

	}*/
	public float getRelevanceForOR(String[] str, InvertedPageIndex inv_pi){
		float relevanceOR=0;
		float N = (float)inv_pi.no_of_pages;
		for(int i=0;i<str.length;i++){
			
			if(getTermFrequency(str[i])!=0){
				int den = (inv_pi.getPagesWhichContainWord(str[i]).l.length());
				float inverse_doc_freq = (float)java.lang.Math.log(N/den); 
				relevanceOR = relevanceOR + getTermFrequency(str[i])*(inverse_doc_freq);
				
			}
			
			}
			

		return relevanceOR;
	}

	public float getRelevanceForPhrase(String[] str){
		float relevancePhrase=0;
		float m = numberOfTimesPhraseOccurs(str);
		float W_P = numberwithoutstop;
		float k = str.length;

		relevancePhrase = (m/2)/((W_P)-((k-1)*(m/2)));
		return relevancePhrase;

	}

	public int numberOfTimesPhraseOccurs(String[] str){
		
		WordEntry[] corres_word_entry = new WordEntry[str.length];
		for(int i=0; i <str.length;i++){
			if(str[i].equals("structures")||str[i].equals("stacks")||str[i].equals("applications")){
			str[i]= str[i].substring(0,(str[i].length()-1));
		}	
			corres_word_entry[i] = page_index.returnWordEntryFromString(str[i]);
		}
		WordEntry f_word = corres_word_entry[0];
		f_word.tree.inOrder(f_word.tree.root);
		int counter =0;
		
		for(int i =0; i<f_word.tree.traversal.size() ;i++){
			int flag =0;

			for(int j=1;j<corres_word_entry.length;j++){
				if(corres_word_entry[j]==null){
					return 0;
				}
				if(!(corres_word_entry[j].tree.search(corres_word_entry[j].tree.root,(f_word.tree.traversal.get(i))+j))){
					flag++;
				}
			}
			if(flag==0){

			    
				counter++;

			}
		
		}
		return counter;
	}

	public static void main(String[] args){
		PageEntry raghav = new PageEntry("cmu_stack");
		String[] str = new String[2];
		str[0]="data";
		str[1]="structures";
		System.out.println(raghav.numberOfTimesPhraseOccurs(str));

	}
}