// Jonty Shah
// CSE 373 AD 
// TA: Chloe Lathe
// Homework Extra Credit
// 06/01/17

import java.util.Iterator;
public abstract class AVLTester {

	public static boolean verifyAVL(StringTree toTest){
		// TODO Return true if toTest is an AVL implementation of a String tree and false otherwise. 
		// All StringTree interface methods must behave correctly
		// You may assume that size() and isEmpty() return the correct values
		// Other than this, do not assume anything about the tree toTest, including its start size.
		return checkEmpty(toTest) && checkInsert(toTest) && checkMultiple(toTest) && empty(toTest)
             && checkBFS(toTest) && checkBalance(toTest) && checkFind(toTest) && checkLowerRotation(toTest);
	}
	
   // checks for the empty array.   
   private static boolean checkEmpty(StringTree toTest){
      toTest.makeEmpty();
      return toTest.size() == 0;
   }
   
   //checks the insert method
   private static boolean checkInsert(StringTree toTest){
      toTest.makeEmpty();
      int initial = toTest.size();
      toTest.insert("testKey","testValue");
      String temp = toTest.find("testKey");
      if(!temp.equals("testValue")){
         return false;
     }
     return initial + 1 == toTest.size();
   }
   
   // checks multiple insert
   private static boolean checkMultiple(StringTree toTest){
      toTest.makeEmpty();
      int initial = toTest.size();
      for(int i = 0; i < 10; i++){
         toTest.insert("" + i,"test" + i);
      }
      if(initial + 10 != toTest.size()){
         return false;
      }
      for(int i = 0; i < 10; i++){
         String temp = toTest.find("" + i);
         if(!temp.equals("test" + i)){
            return false;
         }
      }
      
   return true;
 }
 
 // checks make empty
 private static boolean empty(StringTree toTest){
   toTest.makeEmpty();
   int empty = toTest.size();
   for(int i = 0; i < 100; i++){
      toTest.insert("" + i,"test" + i);
   }
   toTest.makeEmpty();
   return toTest.size() == 0 && empty == 0;
 }
 
 private static boolean checkBFS(StringTree toTest){
   toTest.makeEmpty();
   Iterator<String> temp2 = toTest.getBFSIterator();
   if(temp2.hasNext() && toTest.size() == 0){
      return false;
   }
   toTest.insert("" + 0,"test" + 0);
   toTest.insert("" + 1,"test" + 1);
   Iterator<String> temp = toTest.getBFSIterator();
   for(int i = 0; i < 2; i++){
      if(!temp.next().equals("" + i)){
         return false;
      }
   }
   if(temp.hasNext()){
      return false;
   } 
   return true;
 }
  
 private static boolean checkBalance(StringTree toTest){
      toTest.makeEmpty();
      toTest.insert("" + 5,"test" + 5);
      toTest.insert("" + 6,"test" + 6);
      toTest.insert("" + 7,"test" + 7);
      toTest.insert("" + 4,"test" + 4);
      toTest.insert("" + 3,"test" + 3);
      toTest.insert("" + 9,"test" + 9);
      toTest.insert("" + 8,"test" + 8);
      toTest.insert("" + 1,"test" + 1);
      toTest.insert("" + 2,"test" + 2);
   
   Iterator<String> test = toTest.getBFSIterator();
   return checkValue(test.next(),"" + 6) && checkValue(test.next(),"" + 4) && checkValue(test.next(),"" + 8) &&
          checkValue(test.next(),"" + 2) && checkValue(test.next(),"" + 5) && checkValue(test.next(),"" + 7) &&
          checkValue(test.next(),"" + 9) && checkValue(test.next(),"" + 1) && checkValue(test.next(),"" + 3);
 }
 
 private static boolean checkValue(String check, String temp){
   if(!check.equals(temp)){
      return false;
   }
   return true;
 }
 
 private static boolean checkFind(StringTree toTest){
   toTest.makeEmpty();
   for(int i = 0; i < 10; i++) {
      toTest.insert("" + i,"test" + i);
   }
   Iterator<String> temp = toTest.getBFSIterator();
   for(int i = 0; i < 10; i++){
      if(!toTest.find("" + i).equals("test" + i)){
         return false;
      }
   }
   return true;
 }
 
 private static boolean checkLowerRotation(StringTree toTest){
   toTest.makeEmpty();
   toTest.insert("k","test" + 10);
   toTest.insert("l","test" + 11);
   toTest.insert("i","test" + 8);
   toTest.insert("m","test" + 12);
   toTest.insert("h","test" + 7);
   toTest.insert("j","test" + 9);
   toTest.insert("g","test" + 6);
   toTest.insert("f","test" + 5);
   toTest.insert("d","test" + 4);
   
   Iterator<String> test = toTest.getBFSIterator();
   return checkValue(test.next(),"k") && checkValue(test.next(),"g") && checkValue(test.next(),"l") &&
          checkValue(test.next(),"f") && checkValue(test.next(),"i") && checkValue(test.next(),"m") &&
          checkValue(test.next(),"d") && checkValue(test.next(),"h") && checkValue(test.next(),"j");
 }
 
 
}
