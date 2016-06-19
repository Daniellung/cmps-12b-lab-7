// Daniel Lung
// dlung
// 12M
// lab7
// 5/30/16
// Creates binary tree of Dictionary with methods
// Dictionary.java
public class Dictionary implements DictionaryInterface{
	
	private class Node{
		Node left = null;
		Node right = null;
		String key = "";
		String value = "";
	 }
    Node root;
    int numPairs;
    public Dictionary(){
	    root = new Node();
	    numPairs = 0;
    }
   public Node findKey(Node R, String k){
	   if(R==null || k.compareTo(R.key)==0)
		   return R;
	   if(k.compareTo(R.key)<0){
		   return findKey(R.left, k);
	   }
	   else
		   return findKey(R.right, k);
   }
   
   public Node findParent(Node N, Node R){
	   Node P = null;
	   if(N!=R){
		   P = R;
		   while(P.left!=N && P.right !=N){
			   if(N.key.compareTo(P.key)<0)
				   P = P.left;
			   else
				   P = P.right;
		   }
	   }
	   return P;
   }
	public Node findLeftmost(Node R){
		Node L = R;
		if(L!=null){
			for(int x = 0; L.left != null; L = L.left);
		}
		return L;
	}
	//
	public void printInOrder(Node R){
		if(R!=null){
			printInOrder(R.left);
			if(R.key.compareTo("")!=0) System.out.println(R.key + " " + R.value);
			printInOrder(R.right);
		}
	}
	
	public void deleteAll(Node N){
		if(N!=null){
			deleteAll(N.left);
			deleteAll(N.right);
			N = null;
		}
	}
	
   // isEmpty()
   // pre: none
   // returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
	   return(numPairs==0);
   }

   // size()
   // pre: none
   // returns the number of entries in this Dictionary
   public int size(){
	   return numPairs;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
	public String lookup(String key){
		Node N;
		N = findKey(root, key);
		return (N == null ? null : N.value);
	}

   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{
	   Node N, A, B;
	   if(findKey(root, key)!=null){
		throw new DuplicateKeyException(
            "Dictionary Error: cannot insert() on duplicate key: " + key);
	   }
	   N = new Node();
	   N.key = key;
	   N.value = value;
	   B = null;
	   A = root;
	   while(A!=null){
		   B = A;
		   if(key.compareTo(A.key)<0) A = A.left;
		   else A = A.right;
	   }
	   if(B == null) root = N;
	   else if(key.compareTo(B.key)<0) B.left = N;
	   else B.right = N;
	   numPairs++;
   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
	   Node N, P, S;
	   N = findKey(root, key);
	   if(N==null){
		   throw new KeyNotFoundException(
			   "Dictionary Error: cannot delete() on non-existent key:" + key);
	   }
	   if(N.left == null && N.right== null){
		   if(N == root){
			   root = null;
		   }
			else{
				P = findParent(N, root);
				if(P.right == N) P.right = null;
				else P.left = null;
			}
	   }
	   else if(N.right == null){
		   if(N == root){
			   root = N.left;
		   }else{
			   P = findParent(N, root);
			   if(P.right == N) P.right = N.left;
			   else P.left = N.left;
		   }
	   }
	   else if(N.left == null){
		   if(N == root){
			   root = N.right;
		   }else{
			   P = findParent(N, root);
			   if(P.right == N) P.right = N.right;
			   else P.left = N.right;
		   }
		}else{
			S = findLeftmost(N.right);
			N.key = S.key;
			N.value = S.value;
			P = findParent(S, N);
			if(P.right == S) P.right = S.right;
			else P.left = S.right;
		}
		numPairs--;
   }

   // makeEmpty()
   // pre: none
	public void makeEmpty(){
		deleteAll(root);
		root = null;
		numPairs = 0;
	}

   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString(){
	   printInOrder(root);
	   return "";
   }
}