//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrasue5@wisc.edu
// Lecturer: Mouna Kacem

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 *
 */
public class PriorityCareTester {

  /**
   * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
   * higher triage level is compared to a lower triage level, regardless of patient order of
   * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
   * returns a negative integer when a lower triage level is compared to a higher triage level,
   * regardless of patient order of arival.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToDifferentTriage() {
	  PatientRecord.resetCounter();
	  PatientRecord patient1 = new PatientRecord('F', 50, TriageLevel.GREEN); //
	  PatientRecord patient2 = new PatientRecord('M', 10, TriageLevel.YELLOW);
	  PatientRecord patient3 = new PatientRecord('X', 25, TriageLevel.RED);
    
	  int result1 = patient1.compareTo(patient2);
	  int result2 = patient3.compareTo(patient2);
    
	  if (result1 <= 0 || result2 >= 0) {
		  return false;
	  }
	  return true;
  	}

  /**
   * Tests whether patients in the same triage level are compared based on their order of arrival.
   * Patients of the same triage level with a lower arrival number compared to patients with a
   * higher arrival number should return a negative integer. The reverse situation should return a
   * positive integer.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
	  PatientRecord.resetCounter();
	  PatientRecord patient1 = new PatientRecord('X', 30, TriageLevel.YELLOW);
	  PatientRecord patient2 = new PatientRecord('F', 80, TriageLevel.YELLOW); 
	  PatientRecord patient3 = new PatientRecord('F', 30, TriageLevel.GREEN);
	  PatientRecord patient4 = new PatientRecord('M', 53, TriageLevel.GREEN);
    
	  int result1 = patient2.compareTo(patient1);
	  int result2 = patient3.compareTo(patient4);
    
	  if (result1 <= 0 || result2 >= 0) {
		  return false;
	  }
	  return true;
  	}

  /**
   * Tests whether patients in the same triage level and with the same order of arrival are equal
   * (compareTo should return 0). Even though this case will not be possible in your priority queue,
   * it is required for testing the full functionality of the compareTo() method. Hint: you will
   * need to use the resetCounter() to create equivalent PatientRecords.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageSameArrival() {
	  PatientRecord.resetCounter();
	  PatientRecord patient1 = new PatientRecord('M', 30, TriageLevel.GREEN);
	  PatientRecord.resetCounter();
	  PatientRecord patient2 = new PatientRecord('M', 20, TriageLevel.GREEN);
	  
	  int result = patient1.compareTo(patient2);
	  
	  if (result != 0) {
		  return false;
	  }
    return true;
  }

  /**
   * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
   * the following tests: 
   *
   * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
   * IllegalArgumentException 
   * - Calling the PriorityCareAdmissions with a valid capacity should not throw any errors, and
   * should result in a new PriorityCareAdmissions which is empty, has size 0, a capacity equal to
   * the capacity that was passed as a parameter.
   *
   * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
   * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
   */
  public static boolean testConstructor() {
	  PatientRecord.resetCounter();
	  // Testing an invalid capacity
	  try {
		  new PriorityCareAdmissions(-1);
		  return false;
	  } catch (IllegalArgumentException e) {
		  // exception caught!
	  }
	  
	  PatientRecord.resetCounter();
	  // Creating a new array with a valid capacity should not
	  // throw any errors
	  PriorityCareAdmissions validArray = new PriorityCareAdmissions(3);
	  if (validArray.size() != 0 || validArray.capacity() != 3
			  || !validArray.isEmpty()) {
		  return false;
	  }

    return true;
  }


  /**
   * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
   * throws a NoSuchElementException.
   * 
   * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
   */
  public static boolean testPeekEmpty() {
	  PatientRecord.resetCounter();
	  try {
		  PriorityCareAdmissions queue = new PriorityCareAdmissions(2);
		  queue.peek();
		  return false;
	  } catch (NoSuchElementException e) {
		  // exception caught!
	  }
	  return true;
  	}

  /**
   * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
   * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
   * the PatientRecord from the queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekNonEmpty() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(10);
	  PatientRecord record1 = new PatientRecord('F', 13, TriageLevel.YELLOW);
	  PatientRecord record2 = new PatientRecord('F', 13, TriageLevel.YELLOW);
	  PatientRecord record3 = new PatientRecord('M', 27, TriageLevel.YELLOW);
	  PatientRecord record4 = new PatientRecord('M', 32, TriageLevel.GREEN);
	  queue.addPatient(record1);
	  queue.addPatient(record2);
	  queue.addPatient(record3);
	  queue.addPatient(record4);
	  
	
	  if (queue.peek() != record1) {
		  return false;
	  }
	  
	  if (queue.size() != 4) {
		  return false;
	  }
    return true;
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
   * ensuring the method 1) adds the PatientRecord and 2) increments the size.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientEmpty() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(3);
	  
	  try {
		  PatientRecord record1 = new PatientRecord('X', 20, TriageLevel.RED);
		  queue.addPatient(record1);
		  
		  if (queue.peek() != record1) {
			  return false;
		  }
		  
		  if (queue.size() != 1) {
			  return false;
		  }
		  
		  if (queue.isEmpty()) {
			  return false;
		  }
	  } catch (Exception e) {
		  return false;
	  }
    return true;
  }


  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
   * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
   * size. Try add at least 5 PatientRecords.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
   */
  public static boolean testAddPatientNonEmpty() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(4);
	  PatientRecord record1 = new PatientRecord('X', 18, TriageLevel.RED);
	  PatientRecord record2 = new PatientRecord('F', 39, TriageLevel.YELLOW);
	  PatientRecord record3 = new PatientRecord('F', 3, TriageLevel.GREEN);
	  PatientRecord record4 = new PatientRecord('M', 71, TriageLevel.GREEN);
	  
	  try {
		  queue.addPatient(record1);
		  queue.addPatient(record2);
		  queue.addPatient(record3);
		  queue.addPatient(record4);
		  
		  if (queue.size() != 4) {
			  return false;
		  }
		  
		  if (!queue.peek().equals(record1)) {
			  return false;
		  }
		  
		  // Make sure an exception is thrown
		  try {
			  PatientRecord badRecord = new PatientRecord('X', 12, TriageLevel.YELLOW);
			  queue.addPatient(badRecord);
			  return false;
		  } catch (IllegalStateException e) {
			  // exception caught!
		  }
		  
		 // Try adding a null record
		  try {
			  queue.addPatient(null);
			  return false;
		  } catch (NullPointerException e) {
			  // exception caught!
		  }
		  
		  // Check that the queue is in the correct order
		  PatientRecord[] testQueue = queue.arrayHeapCopy();
		  if (!testQueue[0].equals(record1)) {
			  return false;
		  }
		  if (!testQueue[1].equals(record2)) {
			  return false;
		  }
		  if (!testQueue[2].equals(record3)) {
			  return false;
		  }
		  if (!testQueue[3].equals(record4)) {
			  return false;
		  }
		  
		  if (queue.isEmpty()) {
			  return false;
		  }
	  } catch (Exception e) {
		  return false;
	  }
	  return true;
  }


  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
   * ensuring the method throws an IllegalStateException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientFull() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(3);
	  PatientRecord record1 = new PatientRecord('F', 25, TriageLevel.YELLOW);
	  PatientRecord record2 = new PatientRecord('M', 32, TriageLevel.RED);
	  PatientRecord record3 = new PatientRecord('F', 19, TriageLevel.GREEN);
	  
	  try {
		  queue.addPatient(record1);
		  queue.addPatient(record2);
		  queue.addPatient(record3);
		  PatientRecord record4 = new PatientRecord('M', 45, TriageLevel.YELLOW);
		  try {
			  queue.addPatient(record4);
			  return false;
			  } catch (IllegalStateException e) {
		        // exception caught!
			  }
		  try {
			  queue.addPatient(null);
			  return false;
		  } catch (NullPointerException e) {
			  // exception caught
		  }
	  } catch (Exception e) {
		  return false;
	  }
	  return true;
	}

  /**
   * Tests the functionality of addPatient() method by calling addPatient() with a null
   * PatientRecord and ensuring the method throws a NullPointerException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientNull() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(8);
	  try {
		  queue.addPatient(null);
	      // If the method did not throw a NullPointerException, the test fails
	      return false;
	  } catch (NullPointerException e) {
		  // If the method threw a NullPointerException, the test passes
	      return true;
	  }
  }



  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
   * queue.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
   *         false otherwise
   */
  public static boolean testRemoveBestRecordEmpty() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(3);
	  try {
		  queue.removeBestRecord();
		  return false;
	  } catch (NoSuchElementException e) {
		  // exception caught!
	  }
    return true; // DONE
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
   * of size one.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
   *         size is 0
   */
  public static boolean testRemoveBestRecordSizeOne() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(1);
	  PatientRecord record = new PatientRecord('F', 19, TriageLevel.GREEN);
	  queue.addPatient(record);
	  PatientRecord removedRecord = queue.removeBestRecord();
	  if (!removedRecord.equals(record)) {
		  return false;
	  }
	  
	  if (queue.size() != 0) {
		  return false;
	  }
		
	  
    return true; // DONE
  }

  /**
   * Tests the functionality of removeBestRecord() methods. 
   * 
   * The removeBestRecord() method must remove, and return the patient record with the highest
   * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
   * method is successfully called. 
   * 
   * Remove the best record from a queue whose size is at least 6. Consider cases where
   * percolate-down recurses on left and right.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testRemoveBestRecordNonEmpty() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(10);
	  PatientRecord p1 = new PatientRecord('M', 84, TriageLevel.GREEN);
	  PatientRecord p2 = new PatientRecord('X', 18, TriageLevel.GREEN);
	  PatientRecord p3 = new PatientRecord('X', 20, TriageLevel.GREEN);
	  PatientRecord p4 = new PatientRecord('F', 12, TriageLevel.YELLOW);
	  PatientRecord p5 = new PatientRecord('X', 49, TriageLevel.YELLOW);
	  PatientRecord p6 = new PatientRecord('F', 3, TriageLevel.RED);
	  queue.addPatient(p1);
	  queue.addPatient(p2);
	  queue.addPatient(p3);
	  queue.addPatient(p4);
	  queue.addPatient(p5);
	  queue.addPatient(p6);
	  
	  PatientRecord removedRecord = queue.removeBestRecord();
	  if (!removedRecord.equals(p6)) {
	      return false;
	  }
	  
	  if (queue.size() != 5) {
		  return false;
	  }
	  
	  removedRecord = queue.removeBestRecord();
	  if (!removedRecord.equals(p4)) {
		  return false;
	  }
	  
	  if (queue.size() != 4) {
		  return false;
	  }
	  
	  removedRecord = queue.removeBestRecord();
	  if (!removedRecord.equals(p5)) {
		  return false;
	  }
	  
	  if (queue.size() != 3) {
		  return false;
	  }
	  
	  removedRecord = queue.removeBestRecord();
	  if (!removedRecord.equals(p1)) {
		  return false;
	  }
	  
	  if (queue.size() != 2) {
		  return false;
	  }
	  
	  removedRecord = queue.removeBestRecord();
	  if (!removedRecord.equals(p2)) {
		  return false;
	  }
	  
	  if (queue.size() != 1) {
		  return false;
	  }
	  
	  removedRecord = queue.removeBestRecord();
	  if (!removedRecord.equals(p3)) {
		  return false;
	  }

	  if (queue.size() != 0) {
		  return false;
	  }
    return true; // DONE
  }

  /**
   * Tests the functionality of the clear() method.
   * Should implement at least the following scenarios: 
   * - clear can be called on an empty queue with no errors 
   * - clear can be called on a non-empty queue with no errors 
   * - After calling clear(), the queue should contain zero PatientRecords. 
   * - After calling clear(), the size should be 0
   *
   * @return true if PriorityCareAdmissions.clear() functions properly
   */
  public static boolean testClear() {
	  PatientRecord.resetCounter();
	  PriorityCareAdmissions queue = new PriorityCareAdmissions(3);
	  queue.clear();
	  
	  if (!queue.isEmpty() || queue.size() != 0) {
		  return false;
	  }
	  
	  PriorityCareAdmissions filledQueue = new PriorityCareAdmissions(5);
	  PatientRecord p1 = new PatientRecord('X', 20, TriageLevel.GREEN);
	  PatientRecord p2 = new PatientRecord('F', 10, TriageLevel.RED);
	  PatientRecord p3 = new PatientRecord('M', 67, TriageLevel.GREEN);
	  
	  filledQueue.addPatient(p1);
	  filledQueue.addPatient(p2);
	  filledQueue.addPatient(p3);
	  
	  filledQueue.clear();
	  
	  if (!filledQueue.isEmpty() || filledQueue.size() != 0) {
		  return false;
	  }
    return true;
  }


  /**
   * Tests toString() method of PriorityCareAdmissions class.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testToString() {
	  PriorityCareAdmissions patientQueue = new PriorityCareAdmissions(4);
	  
	  PatientRecord p1 = new PatientRecord('X', 20, TriageLevel.YELLOW);
	  PatientRecord p2 = new PatientRecord('F', 90, TriageLevel.GREEN);
	  PatientRecord p3 = new PatientRecord('M', 42, TriageLevel.RED);
	  PatientRecord p4 = new PatientRecord('F', 10, TriageLevel.GREEN);
	  
	  patientQueue.addPatient(p1);
	  patientQueue.addPatient(p2);
	  patientQueue.addPatient(p3);
	  patientQueue.addPatient(p4);
	  
	  StringBuilder expectedString = new StringBuilder();
	  expectedString.append(p3.toString()).append(System.lineSeparator());
	  expectedString.append(p1.toString()).append(System.lineSeparator());
	  expectedString.append(p2.toString()).append(System.lineSeparator());
	  expectedString.append(p4.toString()).append(System.lineSeparator());
	  
	  String actualString = patientQueue.toString();
	  if (!expectedString.toString().equals(actualString)) {
		  return false;
	  }

    return true; // default return statement added to resolve compiler errors
  }


  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {

    return testPatientRecordCompareToDifferentTriage()
        && testPatientRecordCompareToSameTriageDifferentArrival()
        && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
        && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
        && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
        && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
        && testToString();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
	  
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToDifferentTriage: "
        + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
        + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
        + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
    System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
    System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
    System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
  }

}
