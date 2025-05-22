//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:  	P01 Urgent Care
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

// Javadoc style class header comes here
public class UrgentCareAdmissionsTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for the rest of this
   * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
   * patient records which we create and maintain here.
   * 
   * This method tests three scenarios:
   * 
   *   1. Adding a patient with a HIGHER triage priority than any currently present in the array. 
   *   To do this, we create an array with no RED priority patients and get the index to add a RED
   *   priority patient. We expect this to be 0, so if we get any other value, the test fails.
   *   
   *   2. Adding a patient with a LOWER triage priority than any currently present in the array. 
   *   To do this, we create an array with no GREEN priority patients and get the index to add a 
   *   GREEN priority patient. We expect this to be the current size of the oversize array, so if 
   *   we get any other value, the test fails.
   *   
   *   3. Adding a patient with the SAME triage priority as existing patients. New patients at the
   *   same priority should be added AFTER any existing patients. We test this for all three triage
   *   levels on an array containing patients at all three levels.
   * 
   * @author hobbes
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndex() {

    // The non-empty, non-full oversize arrays to use in this test.
    // Note that we're using the UrgentCareAdmissions named constants to create these test records,
    // rather than their corresponding literal int values. 
    // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.GREEN},
      null, null, null
    };
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][] {
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      null, null, null, null, null
    };
    int onlyYellowSize = 3;

    // scenario 1: add a patient with a higher priority than any existing patient
    {
    	
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, 
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual) return false;
    }

    // scenario 2: add a patient with a lower priority than any existing patient
    {
      int expected = onlyYellowSize;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, 
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual) return false;
    }

    // scenario 3: verify that a patient with the same priority as existing patients gets
    // added after all of those patients
    {
      int expected = 1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
      
      expected = 4;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
      
      expected = allLevelsSize;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
    }

    // and finally, verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int[][] {
        {32702, 3, UrgentCareAdmissions.RED},
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN},
        null, null, null
      };
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) return false;

      int[][] onlyYellowCopy = new int[][] {
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW},
        null, null, null, null, null
      };
      if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy)) return false;
    }

    return true;
  }
  
  /**
  * This method tests the AddPatient() method on a non-empty, non-full
  * array 
  *
  * @param patientRecord a three-element perfect size array of ints, contains
  * 5-digit number,
  * @param index the place where patientRecord should be added to the patientsList array
  * @param patientsList the current, active list of patient records
  * @param size the number of patients currently in the list
  * @return the number of patients in patientsList after this method has
  *    finished running
  */
  
  // Tests the behavior of the addPatient method using a non-empty, non-full array. Each test 
  // should verify that the returned size is as expected and that the array has been updated 
  // (or not) appropriately
  public static boolean testAddPatient() {
	  
	  int[][] nonEmptyNonFull = new int[][] { // array that is meant to be altered
		  {10204, 1, UrgentCareAdmissions.RED},
		  {19475, 2, UrgentCareAdmissions.YELLOW},
		  null, null, null
	  };
	  int nonEmpNonFullSize = 2;
	  
	  int[][] arrayForModification = new int[][]{
		  {19304, 1, UrgentCareAdmissions.RED},
		  {62930, 2, UrgentCareAdmissions.YELLOW},
		  {91738, 3, UrgentCareAdmissions.GREEN},
		  null
	  };
	  int modifiedArraySize = 3;
	  
	// (1) add a patient to the END of the patientsList
	  {
		  int expected = 3;
		  int[] newPatient = {19304, 3, UrgentCareAdmissions.GREEN};
		  int actual = UrgentCareAdmissions.addPatient(newPatient, 2, nonEmptyNonFull, nonEmpNonFullSize);
		  
		  if (expected != actual) return false;
	  }
    
    // (2) add a patient to the MIDDLE of the patientsList
	  {
		  int expected = 3;
		  int[] newPatient = {48592, 3, UrgentCareAdmissions.RED};
		  int actual = UrgentCareAdmissions.addPatient(newPatient, 1, nonEmptyNonFull, nonEmpNonFullSize);
		  
		  if (expected != actual) return false;
	  }
	  
    // (3) add a patient using an invalid (out-of-bounds) index
	  {
		  int expected = 3;
		  int[] newPatient = {48592, 3, UrgentCareAdmissions.RED};
		  int actual = UrgentCareAdmissions.addPatient(newPatient, 10, 
				  arrayForModification, modifiedArraySize);
		  
		  if (expected != actual) return false;
	  }
	  
	// Checking if the array was not modified
	  {
	      int[][] arrayForModificationCopy = new int[][]{
			  {19304, 1, UrgentCareAdmissions.RED},
			  {62930, 2, UrgentCareAdmissions.YELLOW},
			  {91738, 3, UrgentCareAdmissions.GREEN},
			  null
		  };
	      
	      if (!Arrays.deepEquals(arrayForModification, arrayForModificationCopy)) return false;
	  }
	  
  	return true;
  }
	  
  
  /**
   * Removes the patient record at index 0 of the patientsList, 
   * if there is one, and updates the rest of the list to maintain
   * the oversize array in its current ordering.
   * 
   * 1. Calling removeNextPatient on an oversized array with three filled elements,
   * we expect the patient at index 0 to be removed and the returned size will
   * be updated. If the size is not one less than the original, this fails.
   * 
   * 2. Calling removeNextPatient on singularPatientList with only one record
   * within the list, the method should return 0 as there will be no one else in the
   * waiting room. If number is not 0, this should fail.
   *
   * @param patientsList - the current, active list of patient records
   * @param size - the number of patients currently in the list
   * @return the number of patients in patientsList after this method has finished running
   */
  
  
  // Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each 
  // test should verify that the returned size is as expected and that the array has been updated
  // (or not) appropriately
  public static boolean testRemovePatient() {
	  
	  int[][] manyRecordPatientList = new int[][] {
		  {21801, 2, UrgentCareAdmissions.YELLOW},
		  {22002, 3, UrgentCareAdmissions.YELLOW},
		  {11901, 4, UrgentCareAdmissions.YELLOW},
		  null, null, null, null, null
	  };
	  int manyRecordSize = 3;
	  
	  int[][] singularPatientList = new int[][] {
		  {32702, 3, UrgentCareAdmissions.RED},
		  null, null
	  };
	  
	  int singularRecordSize = 1;
			  
    // (1) remove a patient from a patientsList containing more than one record
	  {
	  int expected = 2;
	  int actual = UrgentCareAdmissions.removeNextPatient(manyRecordPatientList, manyRecordSize);
	  if (expected != actual) return false;
	  }
	
    // (2) remove a patient from a patientsList containing only one record
	  {
		  
	  int expected = 0;
	  int actual = UrgentCareAdmissions.removeNextPatient(singularPatientList, singularRecordSize);
	  if (expected != actual) return false;
	  
	  }
	  
    return true;
    
  }
  
  /**
   * Finds the index of a patient given their caseID number. 
   * This method must not modify patientsList in any way.
   * 
   * 1. and 2. both are testers given a 5-digit patient code to find, both in
   * different elements. Fails if 5-digit code's patient index is not the same as the expected.
   * 
   * 3. Test method to see if code returns -1 corresponding to 5-digit not found.
   * Fails if returns anything other than -1.
   *
   * @param caseID - the five-digit case number assigned to the patient record to find
   * @param patientsList - the current, active list of patient records
   * @param size - the number of patients currently in the list
   * @return the index of the patient record matching the given caseID number, 
   * or -1 if the list is empty or the caseID is not found
   */

  // Tests the behavior of the getPatientIndex method using a non-empty, non-full array.
  public static boolean testGetPatientIndex() {
	  int[][] patientIndexList = new int[][] {
		  {32702, 3, UrgentCareAdmissions.RED},
		  {11901, 5, UrgentCareAdmissions.YELLOW},
		  {22002, 4, UrgentCareAdmissions.YELLOW},
		  {31501, 1, UrgentCareAdmissions.GREEN},
		  null, null,
	  };
	  int patientIndexListSize = 4;

    // (1) look for a patient at the end of the list
	  {
		  int expected = 3;
		  int actual = UrgentCareAdmissions.getPatientIndex(31501, patientIndexList, 
				  patientIndexListSize);
		  
		  if (expected != actual) return false;
	  }
    
    // (2) look for a patient in the middle of the list
	  {
		  int expected = 1;
		  int actual = UrgentCareAdmissions.getPatientIndex(11901, patientIndexList, 
				  patientIndexListSize);
		  
		  if (expected != actual) return false;
	  }
    
    // (3) look for a patient not present in the list
	  {
		  int expected = -1;
		  int actual = UrgentCareAdmissions.getPatientIndex(77777, patientIndexList, 
				  patientIndexListSize);
		  
		  if (expected != actual) return false;
	  }
	  
	// Check if the array was modified
	  {
	      int[][] patientIndexCopy = new int[][] {
			  {32702, 3, UrgentCareAdmissions.RED},
			  {11901, 5, UrgentCareAdmissions.YELLOW},
			  {22002, 4, UrgentCareAdmissions.YELLOW},
			  {31501, 1, UrgentCareAdmissions.GREEN},
			  null, null,
		  };
	      if (!Arrays.deepEquals(patientIndexCopy, patientIndexList)) return false;
	    }
	  
    return true;
    
  }
  
  /**
   * Finds the patient who arrived earliest still currently present in the patientsList, 
   * and returns the index of their patient record within the patientsList. 
   * The arrival value is strictly increasing for each new patient, 
   * so you will not need to handle the case where two values are equal.
   * 
   * 1. and 2. Checks to see if the getLongestWaitingPatientIndex works on arrays containing different
   * sizes. Fails if the returned value does not equal the expected. Arrays should not be modified.
   *
   * @param patientsList - the current, active list of patient records
   * @param size - the number of patients currently in the list
   * @return the index of the patient record with 
   * the smallest value in their arrival integer, or -1 if the list is empty
   */
  
  // Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When
  // designing these tests, recall that arrivalOrder values will all be unique!
  public static boolean testLongestWaitingPatient() {
	  
	  int[][] onePatientLongest = new int[][] {
		  {90124, 1, UrgentCareAdmissions.GREEN},
		  null, null
	  };
	  int onePatientSize = 1;
	  
	  int[][] threePatientLongest = new int[][] {
		  {18204, 3, UrgentCareAdmissions.RED},
		  {82910, 1, UrgentCareAdmissions.RED},
		  {48920, 2, UrgentCareAdmissions.YELLOW},
		  null, null, null, null, null
	  };
	  int threePatientSize = 3;
	  
	  
    // (1) call the method on a patientsList with only one patient
	  {
		  int expected = 0;
		  int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(onePatientLongest, onePatientSize);
		  if (expected != actual) return false;
	  }
    
    // (2) call the method on a patientsList with at least three patients
	  {
		  int expected = 1;
		  int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(threePatientLongest, threePatientSize);
		  if (expected != actual) return false;
	  }
	  
	// Check to see if array has been changed hooray
	  {
		  int[][] onePatientCopy = new int[][] {
			  {90124, 1, UrgentCareAdmissions.GREEN},
			  null, null
		  };
		  if (!Arrays.deepEquals(onePatientCopy, onePatientLongest)) return false;
		  
		  int[][] threePatientCopy = new int[][] {
			  {18204, 3, UrgentCareAdmissions.RED},
			  {82910, 1, UrgentCareAdmissions.RED},
			  {48920, 2, UrgentCareAdmissions.YELLOW},
			  null, null, null, null, null
		  };
		  
		  if (!Arrays.deepEquals(threePatientLongest, threePatientCopy)) return false;
		  
	  }
    return true;
  }
  
  /**
   * This tester method returns true if all test cases are passed. Each test case checks if each
   * implemented method outputs the correct value even when being called on an empty or full array.
   * 
   *
   * @param patientsList - the current, active list of patient records
   * @param size - the number of patients currently in the list
   * @return the index of the patient record with 
   * the smallest value in their arrival integer, or -1 if the list is empty
   */
  
  // Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays
  public static boolean testEmptyAndFullArrays() {  
	  
	  int[][] emptyArray = new int[][] {};
	  int emptyArraySize = 0;
	  
	  int[][] fullArray = new int[][] {
		  {32702, 3, UrgentCareAdmissions.RED},
		  {21801, 2, UrgentCareAdmissions.YELLOW},
		  {22002, 4, UrgentCareAdmissions.YELLOW},
		  {11901, 5, UrgentCareAdmissions.YELLOW},
		  {31501, 1, UrgentCareAdmissions.GREEN}
	  };
	  int fullArraySize = 5;
	  
	// (1) test getAdmissionIndex using an empty patientsList array and any triage level
	  {
		int expected = -1; 
	    int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, 
	    		emptyArray, emptyArraySize);
	    if (expected != actual) return false;

	  }
	  		 
    // (2) test getAdmissionIndex using a full patientsList array and any triage level
	  {
		int expected = -1;
		int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
				fullArray, fullArraySize);
		if (expected != actual) return false;
		
	  }
	  
    // (3) test addPatient using a full patientsList array
	  
	  {
		  int expected = 5;
		  int[] newPatient = {19304, 3, UrgentCareAdmissions.GREEN};
		  int actual = UrgentCareAdmissions.addPatient(newPatient, expected, fullArray, fullArraySize);
		  
		  if (expected != actual) return false;
		
	  }
	  
    
    // (4) test removeNextPatient using an empty patientsList array
	  {
		  int expected = 0;
		  int actual = UrgentCareAdmissions.removeNextPatient(emptyArray, emptyArraySize);
		  
		  if (expected != actual) return false;
	  }
    
    // (5) test getPatientIndex using an empty patientsList array
	  {
		  int expected = -1;
		  int actual = UrgentCareAdmissions.getPatientIndex(19032, emptyArray, emptyArraySize);
		  
		  if (expected != actual) return false;
	  }
    
    // (6) test getLongestWaitingPatientIndex using an empty patientsList array
	  {
		  int expected = -1;
		  int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(emptyArray, emptyArraySize);
		  
		  if (expected != actual) return false;
	  }
	  
    return true;
  }
  
  /**
   * Creates a formatted String summary of the current state of the patientsList array
   * This includes the size of the array and the count of each triage color
   *
   * @param patientsList - the current, active list of patient records
   * @param size - the number of patients currently in the list
   * @return a String summarizing the patientsList as shown in this comment
   */
  
  // Tests the getSummary method using arrays in various states
  public static boolean testGetSummary() {
	  
	  int[][] emptyArray = new int[][] {};
	  int emptyArraySize = 0;
	  
	  int[][] sameTriage = new int[][]{
		  {21801, 2, UrgentCareAdmissions.YELLOW},
		  {22002, 3, UrgentCareAdmissions.YELLOW},
		  {11901, 4, UrgentCareAdmissions.YELLOW},
		  null, null, null, null, null
	  };
	  int sameTriageSize = 3;
	  
	  int[][] differentTriages = new int[][] {
		  {10204, 1, UrgentCareAdmissions.RED},
		  {19475, 2, UrgentCareAdmissions.YELLOW},
		  {92323, 3, UrgentCareAdmissions.GREEN},
		  null, null
	  };
	  int differentTriagesSize = 3;
	  
	  
    // (1) test getSummary using an empty patientsList
	{
	  String expected = ("Total number of patients: " + emptyArraySize + "\n" + 
	  "RED: " + 0 + "\n" + "YELLOW: " + 0
	  + "\n" + "GREEN: " + 0);
	  
	  String actual = UrgentCareAdmissions.getSummary(emptyArray, emptyArraySize);
	  if (expected.compareTo(actual) != 0) return false;
	}
    
    // (2) test getSummary using a patientsList with multiple patients at a single triage level
	{
	  String expected = ("Total number of patients: " + sameTriageSize + "\n" + 
			  "RED: " + 0 + "\n" + "YELLOW: " + 3
			  + "\n" + "GREEN: " + 0);
	  
	  String actual = UrgentCareAdmissions.getSummary(sameTriage, sameTriageSize);
	  if (expected.compareTo(actual) != 0) return false;
	}
    
    // (3) test getSummary using a patientsList with patients at all triage levels
	{
		String expected = ("Total number of patients: " + differentTriagesSize + "\n" + 
				  "RED: " + 1 + "\n" + "YELLOW: " + 1
				  + "\n" + "GREEN: " + 1);
		
		String actual = UrgentCareAdmissions.getSummary(differentTriages, differentTriagesSize);
		if (expected.compareTo(actual) != 0) return false;
	}
    return true;
  }

  /**
   * Runs each of the tester methods and displays their result hooray!!
   * @param args
   */
  public static void main(String[] args) {
	  
    System.out.println("get index: "+testGetIndex());
    System.out.println("add patient: "+testAddPatient());
    System.out.println("remove patient: "+testRemovePatient());
    System.out.println("get patient: "+testGetPatientIndex());
    System.out.println("longest wait: "+testLongestWaitingPatient());
    System.out.println("empty/full: "+testEmptyAndFullArrays());
    System.out.println("get summary: "+testGetSummary());
 
  }

}
