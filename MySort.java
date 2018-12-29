import java.util.*;  
import java.io.*;  

public class MySort{
	

	public ArrayList<SearchResult> sortThisList(ArrayList<SearchResult> listOfSortableEntries){
		
		
		Collections.sort(listOfSortableEntries);
		return listOfSortableEntries;
	}
}