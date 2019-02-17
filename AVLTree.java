// Jonty Shah
// CSE 373 AD 
// TA: Chloe Lathe
// Homework Extra Credit
// 06/01/17

import java.util.Iterator;
import java.util.*;

public class AVLTree implements StringTree{
	
	private class AVLNode{
		//Do not change these variable names
		private String key;
		private String value;
		private AVLNode left;
		private AVLNode right;
      private int size;
      private int height;
		
      private AVLNode(String key, String value, AVLNode left, AVLNode right, int height, int size){
         this.key = key;
         this.value = value;
         this.left = left;
         this.right = right;
         this.height = height;
         this.size = size;
      }
      
      private AVLNode(String key, String value){
         this(key, value, null, null, 0, 1);
      }
         
  }
		//Place any additional fields you need here
			
		//TODO implement the node class here
	
	
	//Use this variable as your root
	private AVLNode root;
   public AVLTree(){
      root = null;
   }
	//You may use any additional fields here as you see fit
	
	public void makeEmpty() {
		// TODO Remove all elements from the AVL tree.
      root = null;
	}
	
	public int size() {
		// TODO Return the number of elements currently in the tree.
		return size(root);
	}
   
   private int size(AVLNode temp){
    if(temp == null){
       return 0;
     }
      return temp.size;
   }
   
   private int height(AVLNode temp){
    if(temp == null){
      return -1;
     } 
     return temp.height;
   }
   
   // TODO Insert the <key,value> pair into the AVLTree
   // Throw an IllegalArgumentException if the client attempts to insert a duplicate key
	public void insert(String key, String value) {
      if(key == null || value == null) {
         throw new IllegalArgumentException();
      }
      AVLNode temp = findHelper(key,root);
      if(temp != null) {
         throw new IllegalArgumentException();
      }
      root =  insertHelper(root, key , value);
		
	}
   
   private AVLNode insertHelper(AVLNode temp, String key, String value){
      if(temp == null){
        return new AVLNode(key, value); 
      } 
      int compared = compare(temp.key, key);
      if(compared == 1){
         temp.left = insertHelper(temp.left, key, value);
      } else {
         temp.right = insertHelper(temp.right, key, value);
      } 
         
      temp.size = 1 + size(temp.left) + size(temp.right);
      temp.height = 1 + Math.max(height(temp.left), height(temp.right));
      return balanceTree(temp);     
   }
   
   private AVLNode balanceTree(AVLNode temp){
      if(getBalance(temp) > 1){
         if(getBalance(temp.left) < 0){
            temp.left = rotateLeft(temp.left);
         } 
         temp = rotateRight(temp);
         
      } else if(getBalance(temp) < -1){
         if(getBalance(temp.right) > 0){
            temp.right = rotateRight(temp.right);
         } 
         temp = rotateLeft(temp);     
      }
      return temp;
   }
   
   private int getBalance(AVLNode temp){
     return height(temp.left) - height(temp.right); 
   }
   
   private AVLNode rotateLeft(AVLNode temp){
      AVLNode right = temp.right;
      temp.right = right.left;
      right.left = temp;
      right.size = temp.size;
      increase(temp, right);
      return right;  
   }
   
    private AVLNode rotateRight(AVLNode temp){
      AVLNode left = temp.left;
      temp.left = left.right;
      left.right = temp;
      left.size = temp.size;
      increase(temp, left);
      return left;  
   }
   
   private void increase(AVLNode temp, AVLNode right){
      temp.size = 1 + size(temp.left) + size(temp.right);
      temp.height = 1 + Math.max(height(temp.left), height(temp.right));
      right.height = 1 + Math.max(height(right.left), height(right.right));
   }
   
   // TODO Return the value affiliated with the String key.
	// Throw an ObjectNotFoundException if the key is not in the AVLTree
	public String find(String key) {
      if(key == null){
         throw new IllegalArgumentException();
      }
      AVLNode temp = findHelper(key, root);
      if(temp == null){
         throw new NoSuchElementException();
      }
		return temp.value;
	}
      
   private AVLNode findHelper(String key, AVLNode temp){
      if(temp == null){
         return null;
      }
      int compared = compare(temp.key, key);
      if(compared == 1){
         return findHelper(key, temp.left);
      } else if(compared == -1){
         return findHelper(key, temp.right);
      }
      return temp;
   
   }
   
     private int compare(String a, String b){
        if(a.compareTo(b) > 0){
         return 1;
        } else if(a.compareTo(b) < 0){
         return -1;
        } 
         return 0;  
     }

   // TODO Only complete this section if you wish to attempt the 10 points EC
	// This function should return a BFSIterator: Starter code provided below
	public Iterator<String> getBFSIterator() {
		return new BFSIterator(root);
	}
	

	private class BFSIterator implements Iterator<String>{
      private AVLNode root;
      private ListQueue visited;
      
      public BFSIterator(AVLNode root){
         this.root = root;
         this.visited = new ListQueue();
         if(root != null) {
           for(int i = 0; i < root.height + 1; i++) {
               getAll(root, i);
           }
         }     
      }
      
		public boolean hasNext() {
			// TODO Return true if the iterator has another value to return
         if(root == null){
            return false;
         }  
			return !visited.isEmpty(); 
		}
      
      // TODO Return the next value in a BFS traversal of the tree
		// It should start at the root and iterate through left children before right
		public String next() {
         if(!hasNext()){
            throw new NullPointerException();
         }
         return visited.dequeue();
		}
      
      private void getAll(AVLNode temp, int height){
         if(temp != null){
            if(height == 0){
               visited.enqueue(temp.key,temp.value);
            }  if(height > 0) {
               getAll(temp.left, height - 1);
               getAll(temp.right, height - 1);
            }
         }
      }                          
   }
}
