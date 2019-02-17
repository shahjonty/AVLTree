// Jonty Shah
// CSE 373 AD 
// TA: Chloe Lathe
// Homework Extra Credit
// 06/01/17

// This class is a Linked list implementation of a Queue.
public class ListQueue {
   // This is a private node class.
	private class Node{
      private String key;
      private String value; 
      private Node next;
      
      // a node class constructor, intializes
      // the data and next pointer. Takes in a 
      // string and node as a parameter.
      private Node(String key,String value, Node next) {
         this.key = key;
         this.value = value;
         this.next = next;
	   }
      
      // takes in a string as a parameter. It is constructor
      // that intializes a node with the given data.
      private Node(String key,String value) {
         this(key,value, null);
      } 
  }
	
	private Node front; // creates a node variable
   private Node back; // creates a node variable.
	
   // This is a constructor for ListQueue class.
   // It intializes the front and back pointer to
   // the queue.
	public ListQueue(){
      front = null;
      back = front;     
	}
	
   // This method takes a string as a parameter.
   // It adds the string passed in as parameter 
   // at the back of the queue.
	public void enqueue(String key,String value) {
      if(front == null) {
        front = new Node(key,value);
        back = front;
      } else {
        back.next = new Node(key,value);
        back = back.next;
      }
	}
   
   // This method returns a string type value.
   // This method removes and return the element from 
   // front of the queue.
	public String dequeue(){
      if(front == null) {
         return null;
      } else {
         String temp = front.key;
         if(front != back) {
           front = front.next;
         } else{
            front = null;
            back = null;
         }
         return temp;
      }
	}
	
   // This method returns a string type value. 
   // The method returns the first element in the queue.
   // and if the queue is empty then it returns null.
	public String front(){
      if(front == null) {
		   return null;
      } else {
         return front.key;
      }
   }
   
   public boolean isEmpty(){
      if(front == null){
         return true;
      }
      return false;
   }
   


}
