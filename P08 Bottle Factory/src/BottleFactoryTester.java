//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 *
 */
public class BottleFactoryTester {

  /**
   * Ensures the correctness of the constructor and methods 
   * defined in the Bottle class

   * @return true if the tester verifies a correct functionality and 
   * false if at least one bug is
   *         detected
   */
  public static boolean bottleTester() {
	  Bottle.resetBottleCounter();
	  
	  // test equals method
	  {
	  Bottle yellowBottle = new Bottle("Yellow");
	  String expectedString = "SN1Yellow";
	  String actualString = yellowBottle.getSerialNumber();
	  
	  if (!expectedString.equals(actualString)) {
		  return false;
	  }
	  }
	  
	  // test toString method
	  // Tests cap and seal
	  {
		  Bottle blueBottle = new Bottle("Blue");
		  blueBottle.fillBottle();
		  blueBottle.sealBottle();
		  String expectedString = "SN2Blue:Filled:Capped";
		  String actualString = blueBottle.toString();
		  if (!expectedString.equals(actualString)) {
			  return false;
		  }
	  }
	  
	  // Tests that despite the same color, 
	  // two bottles are different
	  {
		  Bottle firstBottle = new Bottle("Green");
		  Bottle secondBottle = new Bottle("Green");
		  String firstString = firstBottle.toString();
		  String secondString = secondBottle.toString();
		  
		  if (firstString.equals(secondString)) {
			  return false;
		  }
	  }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined 
   * in the LinkedBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and
   *  false if at least one bug is detected
   */
  public static boolean linkedBottleQueueTester() {
	  Bottle.resetBottleCounter();
	  {
		  // test constructor - verify exception 
		  //behavior (when capacity is invalid)
		  try {
			  LinkedBottleQueue queue = new LinkedBottleQueue(-1);
		  } catch (IllegalArgumentException e) {
			  // exception caught
		  }
		  
		  // test constructor a valid input's fields 
		  // created are as expected
		  LinkedBottleQueue queue = new 
				  LinkedBottleQueue(5);
		  if (queue.isEmpty() != true) {
			  return false;
		  }
		  if (queue.size() != 0) {
			  return false;
		  }
		  if (queue.isFull() != false) {
			  return false;
		  }
	  }
	  
	  // test enqueue, dequeue and peek methods 
	  // using different scenarios
	   // 1) all methods on an empty queue 
	  {
		   LinkedBottleQueue emptyQueue = 
				   new LinkedBottleQueue(5);
		   if (!emptyQueue.isEmpty()) {
			   return false;
		   }
		   
		   // Testing dequeue
		   try {
			   emptyQueue.dequeue();
			   return false;
		   } catch (NoSuchElementException e) {
			   // exception caught!
		   }
		  
		   // Testing peek
		   try {
			   emptyQueue.dequeue();
			   return false;
		   } catch (NoSuchElementException | IllegalArgumentException e) {
			   // exception caught!
		   }
		   
		  // Testing enqueue
		   Bottle purpleBottle = new Bottle("Purple");
		   emptyQueue.enqueue(purpleBottle);
		   if (emptyQueue.size() != 1) {
			   return false;
		   }
	  }
	   
	   // 2) all methods on a full queue
	   {
		   LinkedBottleQueue fullQueue = 
				   new LinkedBottleQueue(3);
		   Bottle bottle1 = new Bottle("Red");
		   Bottle bottle2 = new Bottle("Orange");
		   Bottle bottle3 = new Bottle("Yellow");
		   fullQueue.enqueue(bottle1);
		   fullQueue.enqueue(bottle2);
		   fullQueue.enqueue(bottle3);
		   
		   if(!fullQueue.isFull()) {
			   return false;
		   }
		   
		   // Adding another bottle despite being full
		   try {
			   Bottle bottle4 = new Bottle("Green");
			   fullQueue.enqueue(bottle4);
			   return false;
		   } catch (IllegalStateException e) {
			   // exception caught!
		   }
		   
		   // Testing peek on the full queue
		   try {
			   Bottle peekedBottle = fullQueue.peek();
			   if (!peekedBottle.equals(bottle1)) {
				   return false;
			   }
		   } catch (NullPointerException | IllegalArgumentException e) {
			   return false;
		   }
		   
		   // Removing a bottle on full queue
		   try {
			   Bottle removedBottle = fullQueue.dequeue();
			   if (!removedBottle.equals(bottle1)) {
				   return false;
			   }
		   } catch (NullPointerException | IllegalArgumentException e) {
			   // expected exception
			   return false;
		   }
		   
		   // Testing peek on the now adjusted queue
		   try {
			   Bottle peekBottle = fullQueue.peek();
			   if (!peekBottle.equals(bottle2)) {
				   return false;
			   }
		   } catch (NullPointerException | IllegalArgumentException e) {
			   return false;
		   }
	   }
	   
	   // 3) all methods on a partially filled queue 
	   {
		   LinkedBottleQueue partialQueue = 
				   new LinkedBottleQueue(4);
		   Bottle blueBottle = new Bottle("Blue");
		   Bottle pinkBottle = new Bottle("Pink");
		   partialQueue.enqueue(blueBottle);
		   partialQueue.enqueue(pinkBottle);
		   
		   // Verify the size 
		   if (partialQueue.isFull()) {
			   return false;
		   }
		   if (partialQueue.size() != 2) {
			   return false;
		   }
		   
		   // Testing enqueue
		   Bottle addMeBottle = new Bottle("Purple");
		   partialQueue.enqueue(addMeBottle);
		   if (partialQueue.size() != 3) {
			   return false;
		   }
		   
		   // Testing peek
		   Bottle peekBottle = partialQueue.peek();
		   if (!peekBottle.equals(blueBottle)) {
			   return false;
		   }
		   
		   // Testing dequeue
		   Bottle removeMeBottle = partialQueue.dequeue();
		   if (!removeMeBottle.equals(blueBottle)) {
			   return false;
		   }
		   
		   // Verifying the size after adjustments
		   if (partialQueue.size() != 2) {
			   return false;
		   }
	   }
	   
	   // 4) Verify queue contents (using peek and size) 
	   // after a sequence of 
	   // enqueue-dequeue and dequeue-enqueue 
	   {
		   LinkedBottleQueue largeQueue = new LinkedBottleQueue(8);
		   
		   Bottle bottle1 = new Bottle("Red");
		   Bottle bottle2 = new Bottle("Orange");
		   Bottle bottle3 = new Bottle("Yellow");
		   Bottle bottle4 = new Bottle("Green");
		   Bottle bottle5 = new Bottle("Blue");
		   Bottle bottle6 = new Bottle("Purple");
		   largeQueue.enqueue(bottle1);
		   largeQueue.enqueue(bottle2);
		   largeQueue.enqueue(bottle3);
		   largeQueue.enqueue(bottle4);
		   largeQueue.enqueue(bottle5);
		   
		   largeQueue.dequeue();
		   largeQueue.dequeue();
		   largeQueue.enqueue(bottle6);
		   
		   // Verifying the size
		   if (largeQueue.size() != 4) {
			   return false;
		   }
		   
		   // Using peek
		   Bottle peekBottle = largeQueue.peek();
		   if (!peekBottle.equals(bottle3)) {
			   return false;
		   }
	   }
	   // 5) Enqueue until queue is full and 
	   // dequeue until queue is empty    
	   {
		   LinkedBottleQueue fullToEmpty = 
				   new LinkedBottleQueue(3);
		   Bottle bottle1 = new Bottle("Fuschia");
		   Bottle bottle2 = new Bottle("Mint");
		   Bottle bottle3 = new Bottle ("Amber");
		   
		   // Adding all bottles till full
		   fullToEmpty.enqueue(bottle1);
		   fullToEmpty.enqueue(bottle2);
		   fullToEmpty.enqueue(bottle3);
		   
		   // Verify it is full
		   if (!fullToEmpty.isFull()) {
			   return false;
		   }
		   // Verify the peek
		   Bottle peekBottle = fullToEmpty.peek();
		   if (!peekBottle.equals(bottle1)) {
			   return false;
		   }
		   
		   // Dequeue until empty
		   fullToEmpty.dequeue();
		   fullToEmpty.dequeue();
		   fullToEmpty.dequeue();
		   
		   // Verify it is empty
		   if (!fullToEmpty.isEmpty()) {
			   return false;
		   }
		   
		   // Peeking at empty queue
		   try {
			   fullToEmpty.peek();
			   return false;
		   } catch (NoSuchElementException e) {
			   // exception caught
		   }
	   }
	  
	  // test copy method
	   {
		   LinkedBottleQueue originalQueue = 
				   new LinkedBottleQueue(2);
		   Bottle bottle1 = new Bottle ("Rose");
		   originalQueue.enqueue(bottle1);
		   
		   // Make the copy
		   QueueADT<Bottle> copyQueue = originalQueue.copy();
		   if (copyQueue == originalQueue) {
			   return false;
		   }
		   if (copyQueue.size() != originalQueue.size()) {
			   return false;
		   }
		   if (!copyQueue.toString().equals(originalQueue.toString())) {
			   return false;
		   }
	   }
	   
	  // test toString method
	   {
		   Bottle.resetBottleCounter();
		   LinkedBottleQueue stringQueue = 
				   new LinkedBottleQueue(3);
		   Bottle bottle1 = new Bottle("Pink");
		   Bottle bottle2 =  new Bottle("Orange");
		   Bottle bottle3 = new Bottle("Emerald");
		   bottle1.fillBottle();
		   bottle2.fillBottle();
		   bottle1.sealBottle();
		   stringQueue.enqueue(bottle1);
		   stringQueue.enqueue(bottle2);
		   stringQueue.enqueue(bottle3);
		   
		   String expectedString = "SN1Pink:Filled:Capped\n"
		   		+ "SN2Orange:Filled:Open\nSN3Emerald"
		   		+ ":Empty:Open";
		   String actualString = stringQueue.toString();
		   if (!expectedString.equals(actualString)) {
			   return false;
		   }
	   }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean circularBottleQueueTester() {
	  Bottle.resetBottleCounter();
	  
	// test constructor - verify fields and exception behavior
	 {
		  CircularBottleQueue circleQueue = 
				  new CircularBottleQueue(8);
		  if (circleQueue.isFull()) {
			  return false;
		  }
		  
		  if (circleQueue.size() != 0) {
			  return false;
		  }
		  
		  if (!circleQueue.isEmpty()) {
			  return false;
		  }
		  
		  if (circleQueue.toString().length() > 0) {
			  return false;
		  }
		  
		  // Trying to construct a queue with a 
		  // 0 or lower capacity
		  try {
			  CircularBottleQueue badQueue = new CircularBottleQueue(0);
			  return false;
		  } catch (IllegalArgumentException e) {
			  // exception caught!
		  }
	 }

	// test enqueue, dequeue and peek methods using 
	// different scenarios
	// 1) all 3 methods on an empty queue
	 {
		 CircularBottleQueue emptyQueue =
				 new CircularBottleQueue(2);
		 if (!emptyQueue.isEmpty()) {
			 return false; 
			 }
		 
		 //Testing dequeue
		 try {
			 emptyQueue.dequeue();
			 return false;
			 } catch (NoSuchElementException e) {
				 // exception caught!
				 }
		 
		 // Testing enqueue
		 Bottle newBottle = new Bottle("Grey");
		 emptyQueue.enqueue(newBottle);
		 if (emptyQueue.size() != 1) {
			 return false;
			 }
		 
		 // Testing peek
		 if (emptyQueue.peek() != newBottle) {
			 return false;
			 }
		 }
	 
	 // 2) all 3 methods on a full queue
	 {
		 CircularBottleQueue fullQueue = 
				 new CircularBottleQueue(4);
		 Bottle bottle1 = new Bottle("Red");
		 Bottle bottle2 = new Bottle("Orange");
		 Bottle bottle3 = new Bottle("Yellow");
		 Bottle bottle4 = new Bottle("Green");
		 Bottle badBottle = new Bottle("Blue");
			  
		fullQueue.enqueue(bottle1);
		fullQueue.enqueue(bottle2);
		fullQueue.enqueue(bottle3);
		fullQueue.enqueue(bottle4);
			  
		if (!fullQueue.isFull()) {
			return false;
		}
			  
		// Checking that enqueue will cause an error
		try {
			fullQueue.enqueue(badBottle);
			return false;
			} catch (IllegalStateException e) {
				// exception caught
			}
		
		// Testing peek
		Bottle peekBottle = fullQueue.peek();
		if (!peekBottle.equals(bottle1)) {
			return false;
		}
		
		// Testing dequeue
		Bottle removedBottle = fullQueue.dequeue();
		if (!removedBottle.equals(bottle1)) {
			return false;
		}
	 }
		    
	  // 3) all 3 methods on a partially filled queue 
	 {
		 CircularBottleQueue partialQueue = 
				 new CircularBottleQueue(5);
		 Bottle bottle1 = new Bottle("Pink");
		 Bottle bottle2 = new Bottle("Lavender");
		 Bottle bottle3 = new Bottle ("Silver");
		 partialQueue.enqueue(bottle1);
		 partialQueue.enqueue(bottle2);
		 partialQueue.enqueue(bottle3);
		 
		 if (partialQueue.size() != 3) {
			 return false;
		 }
		 
		 if (partialQueue.peek() != bottle1) {
			 return false;
		 }
		 
		 // Testing dequeue
		 Bottle removeBottle = partialQueue.dequeue();
		 if (removeBottle != bottle1) {
			 return false;
		 }
		 
		 // Testing enqueue
		 Bottle newBottle = new Bottle("White");
		 partialQueue.enqueue(newBottle);
		 
		 // Testing that the peek was adjusted
		 if (partialQueue.peek() != bottle2) {
			 return false;
		 }
	 }
	 
	  // 4) Verify queue contents and size after 
	  // a sequence of enqueue-dequeue and 
	  //    dequeue-enqueue 
	 {
		 CircularBottleQueue adjustedQueue = 
				 new CircularBottleQueue(5);
		 Bottle bottle1 = new Bottle("red");
		 Bottle bottle2 = new Bottle("orange");
		 Bottle bottle3 = new Bottle("yellow");
		 Bottle bottle4 = new Bottle("rainbow");
		 adjustedQueue.enqueue(bottle1);
		 adjustedQueue.enqueue(bottle2);
		 adjustedQueue.enqueue(bottle3);
		 
		 // after enqueue -> dequeue
		 Bottle removedBottle = adjustedQueue.dequeue();
		 
		 if (!removedBottle.equals(bottle1)) {
			 return false;
		 }
		 
		 if (adjustedQueue.size() != 2) {
			 return false;
		 }
		 
		 if (adjustedQueue.peek() != bottle2) {
			 return false;
		 }
		 
		// after dequeue -> enqueue
		 adjustedQueue.dequeue();
		 adjustedQueue.dequeue();
		 adjustedQueue.enqueue(bottle4);
		 
		 if (adjustedQueue.size() != 1) {
			 return false;
		 }
		 
		 if (adjustedQueue.peek() != bottle4) {
			 return false;
		 } 
	 }
	 
	  // 5) Enqueue until queue is full and 
	 // dequeue until queue is empty
	 {
		 CircularBottleQueue adjustedQueue = new CircularBottleQueue(3);
		 Bottle bottle1 = new Bottle("red");
		 Bottle bottle2 = new Bottle("orange");
		 Bottle bottle3 = new Bottle("yellow");
		 Bottle bottle4 = new Bottle("rainbow");
		 adjustedQueue.enqueue(bottle1);
		 adjustedQueue.enqueue(bottle2);
		 adjustedQueue.enqueue(bottle3);
		 
		 if (adjustedQueue.size() != 3) {
			 return false;
		 }
		 
		 // Check if it is full 
		 if (!adjustedQueue.isFull()) {
			 return false;
		 }
		 
		 // Try adding another to the full one
		 try {
			 adjustedQueue.enqueue(bottle4);
			 return false;
		 } catch (IllegalStateException e) {
			 // exception caught!
		 }
		 
		 // check the peek
		 if (adjustedQueue.peek() != bottle1) {
			 return false;
		 }
		 
		 // Dequeuing eveyrthing
		 adjustedQueue.dequeue();
		 adjustedQueue.dequeue();
		 adjustedQueue.dequeue();
		 
		 // Check if it empty
		 if (!adjustedQueue.isEmpty()) {
			 return false;
		 }
		 
		 // Dequeue on empty queue
		 try {
			 adjustedQueue.dequeue();
			 return false;
		 } catch (NoSuchElementException e) {
			 // exception caught
		 } 
	 }
	 
	  // test copy method
	 {
		 CircularBottleQueue originalQueue = 
				 new CircularBottleQueue(2);
		 Bottle bottle1 = new Bottle("red");
		 originalQueue.enqueue(bottle1);
		 
		 // Make the copy
		 QueueADT<Bottle> copyQueue = originalQueue.copy();
		 
		 if (!originalQueue.toString().
				 equals(copyQueue.toString())) {
			 return false;
		 }
		 
		 if (originalQueue == copyQueue) {
			 return false;
		 }
		 
		 if (originalQueue.size() != copyQueue.size()) {
			 return false;
		 }
	 }
	 
	  // test toString method
	 {
		 Bottle.resetBottleCounter();
		 LinkedBottleQueue stringQueue = 
				 new LinkedBottleQueue(3);
		 Bottle bottle1 = new Bottle("Orange");
		 Bottle bottle2 = new Bottle("Yellow");
		 Bottle bottle3 = new Bottle("Rainbow");
		 bottle1.fillBottle();
		 bottle1.sealBottle();
		 stringQueue.enqueue(bottle1);
		 stringQueue.enqueue(bottle2);
		 stringQueue.enqueue(bottle3);
		 
		 String expectedString = "SN1Orange:Filled:Capped"
		 		+ "\nSN2Yellow:Empty:Open"
		 		+ "\nSN3Rainbow:Empty:Open";
		 String resultString = stringQueue.toString();
		 if (!expectedString.equals(resultString)) {
			 return false;
		 }
	 }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined 
   * in the BottleQueueIterator class
   * 
   * @return true if the tester verifies a correct functionality
   *  and false if at least one bug is
   *         detected
   */
  public static boolean bottleQueueIteratorTester() {
	  Bottle.resetBottleCounter();
	  /*
	   *  test 01: Create a LinkedBottleQueue with at least 
	   *  n bottles and 
	   *  use the bottleQueueIterator to traverse the queue. 
	   *  Verify if all 
	   *  the bottles are returned correctly  
	   */ 
	  {
		  LinkedBottleQueue iterateQueue = new 
				  LinkedBottleQueue(4);
		  Bottle bottle1 = new Bottle("Cerulean");
		  Bottle bottle2 = new Bottle("Dandelion");
		  iterateQueue.enqueue(bottle1);
		  iterateQueue.enqueue(bottle2);
		  
		  BottleQueueIterator iterator = new BottleQueueIterator(iterateQueue);
		  Bottle[] expectedBottle = new Bottle[] {bottle1, 
				  bottle2};
		  int idx = 0;
		  while (iterator.hasNext()) {
			  Bottle thisBottle = iterator.next();
			  if (thisBottle != expectedBottle[idx]) {
				  return false;
			  }
			  idx++;
		  }
		  // Verifying that all the bottles returned 
		  // are the size expected
		  if (idx != iterateQueue.size()) {
			  return false;
		  }
	  }
	  
	  /*
	   *  test 02: Create a CircularBottleQueue with at 
	   *  least n bottles and 
	   *  use the bottleQueueIterator to traverse the queue. 
	   *  Verify if all 
	   *  the bottles are returned correctly  
	   */ 
	  Bottle.resetBottleCounter();
	  {
		  CircularBottleQueue circleQueue = new 
				  CircularBottleQueue(5);
		  Bottle bottle1 = new Bottle("Brown");
		  Bottle bottle2 = new Bottle("White");
		    Bottle bottle3 = new Bottle("Green");
		    circleQueue.enqueue(bottle1);
		    circleQueue.enqueue(bottle2);
		    circleQueue.enqueue(bottle3);

		 // Initialize an iterator
		    BottleQueueIterator iterator = new 
		    		BottleQueueIterator(circleQueue);
		    Bottle[] expectedBottles = new Bottle[] {bottle1, 
		    		bottle2, bottle3};
		    int index = 0;
		    while (iterator.hasNext()) {
		        Bottle bottle = iterator.next();
		        if (bottle != expectedBottles[index]) {
		            return false;
		        }
		        index++;
		    }
		    if (index != circleQueue.size()) {
		        return false;
		    }
		}
		return true;
  }
 
  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? 
    		"Pass" : "Failed!"));
    System.out.println("bottleQueueIterator: " + 
    		(bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out
        .println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? 
        		"Pass" : "Failed!"));

    return bottleTester() && bottleQueueIteratorTester()
    		&& linkedBottleQueueTester()
        && circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
