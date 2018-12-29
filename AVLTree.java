import java.util.*;

class AVLNode{
	public AVLNode left, right;
	public int height;
	public Position data;

	public AVLNode(Position p){
		left=null;
		right=null;
		height=0;
		data=p;
	}


}

public class AVLTree{
	public AVLNode root;
	public ArrayList<Integer> traversal;

	public AVLTree(){
		root=null;
		traversal=new ArrayList<Integer>();
	}

	public int getHeight(AVLNode t){
		if(t!=null){
			return t.height;
		}
		else{
			return (-1);
		}
	}

	public int max(int a, int b){
		if(a>b){
			return a;
		}
		else{
			return b;
		}
	}

	public void insert(Position p){
		root=insert(p,root);
		
	}

	public AVLNode insert(Position p, AVLNode t){
		if(t==null){
			t=new AVLNode(p);
		}

		else if(p.position_AVL < t.data.position_AVL){
			t.left = insert(p, t.left);
			if(getHeight(t.left)-getHeight(t.right) == 2){
				if(p.position_AVL<t.left.data.position_AVL){
					t = rotateWithLeftChild(t);

				}
				else{
					t=doubleWithLeftChild(t);
				}
			}
		}

		else if(p.position_AVL > t.data.position_AVL){
			t.right = insert(p, t.right);
			if(getHeight(t.right)-getHeight(t.left) == 2){
				if(p.position_AVL>t.right.data.position_AVL){
					t = rotateWithRightChild(t);

				}
				else{
					t=doubleWithRightChild(t);
				}
			}
		}

		else{
			t.height = max (getHeight(t.left), getHeight(t.right)) + 1;
			
		}
		return t;

	}

	public AVLNode rotateWithLeftChild(AVLNode k2){
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
		k2.height = max( getHeight( k2.left ), getHeight( k2.right ) ) + 1;
		k1.height = max( getHeight( k1.left ), k2.height ) + 1;
		return k1;

    }

    public AVLNode rotateWithRightChild(AVLNode k1){
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
		k1.height = max( getHeight( k1.left ), getHeight( k1.right ) ) + 1;
		k2.height = max( getHeight( k2.right ), k1.height ) + 1;
		return k2;

    }

    public AVLNode doubleWithLeftChild(AVLNode k3){
    	k3.left = rotateWithRightChild(k3.left);
    	return rotateWithLeftChild(k3);
    }

    public AVLNode doubleWithRightChild(AVLNode k3){
    	k3.right = rotateWithLeftChild(k3.right);
    	return rotateWithRightChild(k3);
    }

    public Boolean search(AVLNode r, int i){
    	Boolean b = false;
    	while((r!=null) && !b){
    		int rval = r.data.position_AVL;
    		if(i<rval){
    			r=r.left;
    		}
    		else if(i>rval){
    			r=r.right;
    		}
    		else{
    			b=true;
    			break;
    		}
    		b = search(r, i);
    	}
    	return b;
    }

    public void inOrder(AVLNode r){
    	
    	if(r!=null){
    		inOrder(r.left);
    		traversal.add(r.data.position_AVL);
    		inOrder(r.right);

    	}
    
    }

 

    

    






}