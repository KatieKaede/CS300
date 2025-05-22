//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Exceptional Care
// Course:   CS 300 Spring 2023
//
// Author:   Eugene Park
// Email:    empark@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Katie Krause
// Partner Email:   klkrause5@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x__ Write-up states that pair programming is allowed for this assignment.
//   _x__ We have both read and understand the course Pair Programming Policy.
//   _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Katie Krause, pair programmer. Helped implement every method with me
//
// Online Sources: 
// Used this method to implement the File Writer for the cleanPatientsList
//
// 
// 
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 *  This class is the tester method for the 
 *  ExceptionalCareAdmissions and PatientRecord Class to
 *  verify that the method runs as it is intended to.
 * @author Katie Krause and Eugene Park
 *
 */
public class ExceptionalCareTester {
  
  /**
   * This test method is provided for you in its entirety, to give you a model for testing
   * an instantiable class. This method verifies the correctness of your PatientRecord class.
   * 
   * In this test, we create two PatientRecords with different information and use the accessor
   * methods to verify that both contain the correct information and have the correct String
   * representation.
   * 
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testPatientRecord() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    
    // (1) create two PatientRecords with different, valid input
    // no exceptions should be thrown, so let's be safe:
    PatientRecord test1 = null, test2 = null;
    try {
      test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
      test2 = new PatientRecord('X', 21, PatientRecord.GREEN);
    } catch (Exception e) { 
    	return false; 
    }
    // (2) verify their data fields:
    {
      // CASE_NUMBER
      int expected1 = 21701;
      int expected2 = 32102;
      if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2) 
    	  return false;
    }
    {
        // triage
        int expected1 = PatientRecord.YELLOW;
        int expected2 = PatientRecord.GREEN;
        if (test1.getTriage() != expected1 || test2.getTriage() != expected2)
        	return false;
    }
    {
        // gender
        char expected1 = 'M';
        char expected2 = 'X';
        if (test1.getGender() != expected1 || test2.getGender() != expected2)
        	return false;
      }
      {
        // age
        int expected1 = 17;
        int expected2 = 21;
        if (test1.getAge() != expected1 || test2.getAge() != expected2)
        	return false;
      }
      {
          // orderOfArrival    	 
          int expected1 = 1;
          int expected2 = 2;
          if (test1.getArrivalOrder() != expected1 || 
              test2.getArrivalOrder() != expected2)
        	  return false;
      }
      {
          // hasBeenSeen - try the mutator too
          if (test1.hasBeenSeen() || test2.hasBeenSeen()) 
        	  return false;
          test1.seePatient();
          if (!test1.hasBeenSeen() || test2.hasBeenSeen())
        	  return false;
      }
      // (3) verify their string representations
      {
        String expected1 = "21701: 17M (YELLOW)";
        String expected2 = "32102: 21X (GREEN)";
        if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2))
        	return false;
      }
      // (4) finally, verify that the constructor throws an exception for an invalid triage value
      try {
        new PatientRecord('F', 4, -17);
        // if we get here, no exception was thrown and the test fails
        return false; 
      } catch (IllegalArgumentException e) {
        // correct exception type, but it should have a message:
        if (e.getMessage() == null || e.getMessage().isBlank()) return false;
      } catch (Exception e) {
        // incorrect exception type
        return false;
      }
    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }
  
  /**
   * This method tests the ExceptionalCareAdmissions constructor
   * Checks the following tests pass:
   *(1) Verifies that creating an ExceptionalCareAdmissions object with a 
   *valid capacity parameter does NOT throw an IllegalArgumentException.
   *(2) Verifies that the created ExceptionalCareAdmissions object has a size of 0, 
   *is not full, has no seen patients, and its toString() method returns an empty string.
   *If any of these checks fail, the method returns false. If all checks pass, the method returns true.
   * @return true if all checks pass, false otherwise
   */
  public static boolean testAdmissionsConstructorValid() {
	  PatientRecord.resetCounter();
	  // (1) verify that a normal, valid-input constructor call does NOT throw an exception
	  try {
		  new ExceptionalCareAdmissions(10);
		  } catch (IllegalArgumentException e) {
			  return false;
		  }
    // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
    // its toString() is an empty string
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(10);
	  if (admissions.size() != 0) {
		  System.out.println("Error: Admissions size should be 0");
		  return false;
		  }
	  if (admissions.isFull()) {
		  System.out.println("Error: Admissions should not be full");
		  return false;
		  }
	  if (admissions.getNumberSeenPatients() != 0) {
		  System.out.println("Error: Admissions should have 0 seen patients");
		  return false;
	  }
	  
	  String expectedString = "";
	  String actualString = admissions.toString();
	  if (!admissions.toString().isEmpty()) {
		  System.out.println("Error: Admissions toString should be empty");
		  System.out.println("Expected string: \"" + expectedString 
				  + "\", but got: \"" + actualString + "\"");
		  return false;
		  }
	  return true;
  }
  
  /**
   * This method tests the ExceptionalCareAdmissions constructor by verifying 
   * that it throws an IllegalArgumentException when called with a capacity 
   * less than or equal to 0. First, it resets the patient counter to 1. 
   * Then, it attempts to create an instance of ExceptionalCareAdmissions with 
   * a negative capacity value. If the constructor throws an IllegalArgumentException, 
   * the test method returns true, indicating that the test passed. 
   * If the constructor throws a different exception or no exception at all, the method returns false.
   * 
   * @return true if the ExceptionalCareAdmissions constructor throws an IllegalArgumentException
   */
  public static boolean testAdmissionsConstructorError() {
	  PatientRecord.resetCounter();
	  // (1) verify that calling the constructor with capacity <= 0 causes an IllegalArgumentException
	  try {
		  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(-1);
		  return false;
		  } catch (IllegalArgumentException e) {
			  return true;
			  } catch (Exception e) {
				  return false;
			  }
  }
  
  /**
   * This method tests the addPatient() method of the ExceptionalCareAdmissions class. 
   * It ensures that the method can add patients to an empty list, to the end of a list, 
   * and to the beginning of a list. The method also verifies the contents of the patientsList
   * using the toString() method, which returns a formatted string of all patients in the list.
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testAddPatientValid() {
	  PatientRecord.resetCounter();
	  // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
	  // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()
	  ExceptionalCareAdmissions admissions1 = new ExceptionalCareAdmissions(3);
	  PatientRecord patient1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
	  admissions1.addPatient(patient1, 0);
	  String expectedOutput = "21701: 17M (YELLOW)";
	  String actualOutput = admissions1.toString();
	  if (!actualOutput.equals(expectedOutput)) {
		  return false;
	  }
	  
	  // (2) add a new patient to the end of the list
	  ExceptionalCareAdmissions admissions2 = new ExceptionalCareAdmissions(3);
	  PatientRecord patient2 = new PatientRecord('F', 23, PatientRecord.GREEN);
	  PatientRecord patient3 = new PatientRecord('M', 45, PatientRecord.RED);
	  admissions2.addPatient(patient2, 0);
	  admissions2.addPatient(patient3, 1);
	  // adding new patient
	  PatientRecord patient4 = new PatientRecord('F', 32, PatientRecord.YELLOW);
	  admissions2.addPatient(patient4, admissions2.size());
	  // gets expected output string and actual output
	  expectedOutput = ("12302: 23F (GREEN)\n24503: 45M (RED)\n13204: 32F (YELLOW)");
	  actualOutput = admissions2.toString();
	  if (!actualOutput.equals(expectedOutput)) {
	    	  return false;
	  }
	  
	  // (3) add a new patient to the beginning of the list
	  ExceptionalCareAdmissions admissions3 = new ExceptionalCareAdmissions(3);
	  PatientRecord patient5 = new PatientRecord('M', 55, PatientRecord.GREEN);
	  PatientRecord patient6 = new PatientRecord('F', 42, PatientRecord.RED);
	  admissions3.addPatient(patient5, 0);
	  admissions3.addPatient(patient6, 0);
	  // gets expected output string and actual output
	  expectedOutput = "14206: 42F (RED)\n25505: 55M (GREEN)";
	  actualOutput = admissions3.toString();	  
	  if (!actualOutput.equals(expectedOutput)) {
		  return false;
	  }
	  return true;
  }
  
  /**
   * This test method is provided for you in its entirety, to give you a model for verifying a
   * method which throws exceptions. This method tests addPatient() with two different, full
   * patientsList arrays; one contains seen patients and one does not.
   * 
   * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor
   * and PatientRecord constructor are working properly.
   * 
   * This method must NOT allow ANY exceptions to be thrown from the tested method.
   * 
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAddPatientError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    
    ////// (1) a full Admissions object that contains no seen patients
    
    // create a small admissions object and fill it with patients. i'm filling the list
    // in decreasing order of triage, so the addPatient() method has to do the least
    // amount of work.
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    full.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    full.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
    
    // saving one patient in a local variable so we can mark them "seen" later
    PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
    full.addPatient(seenPatient, 2);
    
    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception, but for this method we expect a specific
      // error message so we have one more step to verify:
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected)) return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and we can just fail the test now
      return false;
    }
    
    ////// (2) a full Admissions object that contains at least one seen patient
    
    // since we have a reference to the patient at index 2, we'll just mark them seen here
    seenPatient.seePatient();
    
    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected)) return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }
    
    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }
  
  /**
   * This method tests the getAdmissionIndex() method of the ExceptionalCareAdmissions class.
   * It creates an Admissions object with capacity of 10 and adds three PatientRecord objects to it.
   * Then it tests three different cases where it expects to get the correct index 
   * of a new PatientRecord to be added to the Admissions list.
   * @return true if all test cases pass based on the inline comments, returns false if otherwise
   */
  public static boolean testGetIndexValid() {
	  PatientRecord.resetCounter();
	  // create an Admissions object and add a few Records to it, leaving some space
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(10);
	  PatientRecord p1 = new PatientRecord('M', 22, PatientRecord.YELLOW);
	  PatientRecord p2 = new PatientRecord('F', 17, PatientRecord.YELLOW);
	  PatientRecord p3 = new PatientRecord('X', 30, PatientRecord.GREEN);
	  admissions.addPatient(p1, 0);
	  admissions.addPatient(p2, 1);
	  admissions.addPatient(p3, 2);
	  
	  // (1) get the index of a PatientRecord that should go at the END
	  PatientRecord p4 = new PatientRecord('F', 28, PatientRecord.GREEN);
	  int expectedIndex1 = 3;
	  int actualIndex1 = admissions.getAdmissionIndex(p4);
	  if (actualIndex1 != expectedIndex1) {
		  return false;
	  }
	  
	  // (2) get the index of a PatientRecord that should go at the BEGINNING
	  PatientRecord p5 = new PatientRecord('F', 60, PatientRecord.RED);
	  int expectedIndex2 = 0;
	  int actualIndex2 = admissions.getAdmissionIndex(p5);
	  if (actualIndex2 != expectedIndex2) {
		  return false;
	  }
	  
	  // (3) get the index of a PatientRecord that should go in the MIDDLE
	  PatientRecord p6 = new PatientRecord('M', 40, PatientRecord.YELLOW);
	  int expectedIndex3 = 2;
	  int actualIndex3 = admissions.getAdmissionIndex(p6);
	  if (actualIndex3 != expectedIndex3) {
		  return false;
	  }
	  return true;
  }
  
  /**
   * This tester method tests the getAdmissionIndex method in the ExceptionalCareAdmissions 
   * class when an error occurs. It creates an ExceptionalCareAdmissions object and adds two 
   * PatientRecords to it until it is full, and then tests two cases where an exception should be thrown. 
   * The first case checks if an IllegalStateException is thrown when there are no patients 
   * who have been seen in the list. The second case checks if an IllegalStateException is thrown 
   * when there is at least one patient who has been seen. If the exception is thrown and the 
   * number of seen patients is not 0, the test fails.
   * @return true if the method passes the test, otherwise it returns false.
   */
  public static boolean testGetIndexError() {
	  PatientRecord.resetCounter();
	  // create an Admissions object and add Records to it until it is full, as in testAddPatientError
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(2);
	  PatientRecord p1 = new PatientRecord('M', 22, PatientRecord.RED);
	  PatientRecord p2 = new PatientRecord('F', 17, PatientRecord.YELLOW);
	  admissions.addPatient(p1, 0);
	  admissions.addPatient(p2, 1);
	  // (1) verify the exception when there are no patients who have been seen in the list
	  try {
		  admissions.getAdmissionIndex(new PatientRecord('F', 28, PatientRecord.GREEN));
		  } catch (IllegalStateException e) {
			  if (!e.getMessage().equals("Cannot admit new patients")) {
				  return false;
			  }
		  }
	  // (2) verify the exception when there is at least one patient who has been seen
	  boolean seenPatientsThrown = false;
	  try {
		  admissions.getAdmissionIndex(new PatientRecord('F', 60, PatientRecord.YELLOW));
		  } catch (IllegalStateException e) {
			  seenPatientsThrown = true;
			  }
	  if (!seenPatientsThrown && admissions.getNumberSeenPatients()!=0) {
		  return false;
		  }
	  return true;
  }
  
  /**
   * A test case that tests the basic accessors (isFull(), size(), and
   * getNumberofSeenPatients)
   * of the ExceptionalCareAdmissions class.
   * 
   * @return true if the method passes the test, otherwise it returns false.
   */
  public static boolean testAdmissionsBasicAccessors() {
	  PatientRecord.resetCounter();
	  // (1) verify isFull() on a non-full and a full Admissions object
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(2);
	  if (admissions.isFull()) {
		  System.out.println("Error: Admissions object shouldn't be full.");
		  return false;
	  }
	  PatientRecord p1 = new PatientRecord('F', 17, PatientRecord.YELLOW);
	  PatientRecord p2 = new PatientRecord('X', 30, PatientRecord.YELLOW);
	  admissions.addPatient(p1, 0);
	  admissions.addPatient(p2, 1);
	  if (!admissions.isFull()) {
		  System.out.println("Error: Admissions object should be full.");
		  return false;
	  }
	  // (2) verify size() before and after adding a PatientRecord 
	  if (admissions.size() != 2) {
		  System.out.println("Error: Admissions object should have a size of 2.");
		  return false;
	  }
	  // (3) verify getNumberSeenPatients() before and after seeing a patient
	  // (see testAddPatientError for a model of how to do this while bypassing seePatient())
	  int getNumberOfSeen = admissions.getNumberSeenPatients();
	  if (getNumberOfSeen != 0) {
		  return false;
	  }
	  admissions.seePatient(p1.CASE_NUMBER);
	  int getNumberOfSeen2 = admissions.getNumberSeenPatients(); 
	  if (getNumberOfSeen2 != 1 ) { 
		  return false;
	  }
	  return true; 
  }
  
  /**
   * This method tests the seePatient() method of the ExceptionalCareAdmissions class. 
   * checks if the method properly marks a patient as seen and updates the
   * number of seen patients. It creates an ExceptionalCareAdmissions object 
   * and saves a shallow copy of at least one of the PatientRecord references. 
   * It then calls seePatient() on the caseID of the saved reference
   * and verifies that its hasBeenSeen() accessor return the correct value changes.
   * Then tests the getNumberSeenPatients() method before and after seeing 
   * a different patient.
   * @return  true if the method passes the test, otherwise it returns false.
   */
  public static boolean testSeePatientValid() {
	  // create an Admissions object and add a few Records to it, saving a shallow copy of
	  // at least one of the PatientRecord references
	  PatientRecord.resetCounter();
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
	  PatientRecord p1 = new PatientRecord('M', 22, PatientRecord.RED);
	  PatientRecord p2 = new PatientRecord('F', 17, PatientRecord.YELLOW);
	  PatientRecord p3 = new PatientRecord('X', 30, PatientRecord.YELLOW);
	  admissions.addPatient(p1, 0);
	  admissions.addPatient(p2, 1);
	  admissions.addPatient(p3, 2);	  
	  // saves a shallow copy into the variable savedRecord
	  PatientRecord savedRecord = p2;
	  
	  // (1) call seePatient() on the caseID of your saved reference and verify that its
	  // hasBeenSeen() accessor return value changes
	  admissions.seePatient(savedRecord.CASE_NUMBER);
	  if (!savedRecord.hasBeenSeen()) {
		  return false;
		  }
	  
	  // (2) verify getNumberSeenPatients() before and after seeing a different patient
	  int numSeenPatientsBefore = admissions.getNumberSeenPatients();
	  admissions.seePatient(p1.CASE_NUMBER);
	  int numSeenPatientsAfter = admissions.getNumberSeenPatients();
	  if (numSeenPatientsAfter != numSeenPatientsBefore + 1) {
		  return false;
		  }
	  return true;
  }
  
  /**
   * Tests the ExceptionalCareAdmissions class's seePatient() method 
   * when it is called with an invalid caseID. The test verifies that 
   * when an invalid caseID is passed to the seePatient() method, an 
   * IllegalArgumentException is thrown. This method creates an 
   * ExceptionalCareAdmissions object and adds two PatientRecords to it. 
   * It then tests to see a patient with a caseID that is not present. 
   * If an IllegalArgumentException is thrown, the test passes.
   * @return true if an IllegalArgumentException is thrown
   */
  public static boolean testSeePatientError() {
	  // (1) verify that seeing a caseID for a patient not in the 
	  //list causes an IllegalArgumentException
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
	  PatientRecord p1 = new PatientRecord('M', 22, PatientRecord.GREEN);
	  PatientRecord p2 = new PatientRecord('F', 17, PatientRecord.YELLOW);
	  admissions.addPatient(p1, 0);
	  admissions.addPatient(p2, 1);
	  int wrongCaseID = 22134;
	  try {
		  admissions.seePatient(wrongCaseID);
		  } catch (IllegalArgumentException e) {
			  return true; // exception should be thrown
			  } catch (Exception e) {
				  return false;// wrong type of exception
			  }			  		  
	  return false;
  }
  
  /**
   * This method tests the getSummary() method in the ExceptionalCareAdmissions class.
   * Chose to test using a patientsList with multiple patients at a single triage level 
   * from P01 testers. The method then compares the summary string returned by the 
   * getSummary() method to an expected summary string. If the two strings match,
   * the method returns true. Otherwise, it returns false.
   * @return true if the summary string returned by the getSummary() 
   * method matches the expected summary string
   */
  public static boolean testGetSummary() {
	  PatientRecord.resetCounter();
	  // (1) choose one getSummary() test from P01; this method has not changed much
	  // used the "test getSummary using a patientsList with multiple 
	  // patients at a single triage level"
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
	  PatientRecord p1 = new PatientRecord('M', 22, PatientRecord.RED);
	  PatientRecord p2 = new PatientRecord('F', 17, PatientRecord.RED);
	  PatientRecord p3 = new PatientRecord('X', 30, PatientRecord.RED);
	  admissions.addPatient(p1, 0);
	  admissions.addPatient(p2, 1);
	  admissions.addPatient(p3, 2);  
	  PatientRecord p4 = new PatientRecord('M', 22, PatientRecord.RED);
	  PatientRecord p5 = new PatientRecord('F', 17, PatientRecord.RED);
	  admissions.addPatient(p4, 3);
	  admissions.addPatient(p5, 4);
	  String expectedSummary = "Total number of patients: 5\n" +
	      "Total number seen: 0\n" + "RED: 5\n" + "YELLOW: 0\n" +
			  "GREEN: 0\n";
	  if (!admissions.getSummary().equals(expectedSummary)) {
		  return false;
	  }
	  return true;
  }
  
  /**
   * This method tests the cleanPatientsList() method in ExceptionalCareAdmissions class.
   * By creating an Array of a variety of patients, this method checks that the file is 
   * being created properly and the correct values are within the file.
   * If there are no seen patients, we check that admissions in String form is unchanged.
   * If there are seen patients, an expected string is compared to the actual admissions
   * which was converted by toString()
   * 
   * @return true if admissions match the expected String after .cleanPatientsList is called.
   */
  public static boolean testCleanList() {
	  PatientRecord.resetCounter();
	  // create an Admissions object and add a few Records to it
	  ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
	  PatientRecord p1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
	  PatientRecord p2 = new PatientRecord('X', 21, PatientRecord.GREEN);
	  PatientRecord p3 = new PatientRecord('F', 17, PatientRecord.GREEN);
	  PatientRecord p4 = new PatientRecord('F', 4, PatientRecord.YELLOW);
	  admissions.addPatient(p1, 0);
	  admissions.addPatient(p2, 1);
	  admissions.addPatient(p3, 2);
	  admissions.addPatient(p4, 3);
    
    // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen 
    // patients does not change after calling cleanPatientsList()
	  String originalList = admissions.toString();
	  admissions.cleanPatientsList(new File("file.txt"));
	  String cleanedList = admissions.toString();
	  if (!originalList.equals(cleanedList)) {
		  return false;
	  }
	  
	  // (2) call seePatient() for at least two of the records in your
	  //     patientsList, and use toString()
	  // to verify that they have been removed after calling cleanPatientsList()
	  // NOTE: you do NOT need to verify file contents in this test method; please do so manually
	  String expectedNewArray = "11703: 17F (GREEN)" + "\n" + "10404: 4F (YELLOW)";
	  p1.seePatient();
	  p2.seePatient();
	  admissions.cleanPatientsList(new File("file.txt"));
	  String actualList = admissions.toString();
	  
	  if (!actualList.equals(expectedNewArray)) { 
		  System.out.println(actualList);
		  System.out.println(expectedNewArray);
		  return false;
	  }
	    return true;
}
  
  /**
   * Runs each of the tester methods and displays the result. Methods with two testers have
   * their output grouped for convenience; a failed test is displayed as "X" and a passed test
   * is displayed as "pass"
   * 
   * @param args unused
   * @author hobbes
   */
  public static void main(String[] args) {
    System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
    System.out.println("Admissions Constructor: " + 
        (testAdmissionsConstructorValid() ? "pass" : "X") + ", " +
        (testAdmissionsConstructorError() ? "pass" : "X"));
    System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", " +
        (testAddPatientError() ? "pass" : "X"));
    System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X") + ", " +
        (testGetIndexError() ? "pass" : "X"));
    System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
    System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", " +
        (testSeePatientError() ? "pass" : "X"));
    System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
    System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
  }
}