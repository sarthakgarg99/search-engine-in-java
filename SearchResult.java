import java.util.*;  
import java.io.*;  


public class SearchResult implements Comparable<SearchResult>{
	public PageEntry p;
	public float r;
	public SearchResult(PageEntry p, float r){
		this.p =p;
		this.r =r;
	}
	public PageEntry getPageEntry(){
		return p;
	}
	 public float getRelevance(){
	 	return r;
	 }
	 public int compareTo(SearchResult otherObject){
	 	if(r>otherObject.r){
	 		return -1;
	 	}
	 	else if(r<otherObject.r){
	 		return 1;
	 	}	
	 	else{
	 		return 0;
	 	} 	
	 		

     }
}