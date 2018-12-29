public class WordEntry{
	public String word;
	public MyLinkedList<Position> pos_list = new MyLinkedList();;
	public AVLTree tree= new AVLTree();
	public WordEntry(String s){
		word =s;
		
	}
	public void addPosition(Position position){
		pos_list.addLast(position);
		tree.insert(position);

	}
	public void addPositions(MyLinkedList<Position> positions){
		
		Node<Position> temp2 = positions.head;
		while(temp2!=null){
			
			pos_list.addLast(temp2.data);
			temp2 = temp2.next;
		}
	}

	public MyLinkedList<Position> getAllPositionsForThisWord(){
		return pos_list;
	}
	
}