public class Position{
	public PageEntry p;
	public int wi; 
	public int position_AVL;

	public Position(PageEntry pe, int wordIndex){
		p=pe;
		wi= wordIndex;
	}
	public PageEntry getPageEntry(){
		return p;
	}
	public int getWordIndex(){
		return wi;
	}
}