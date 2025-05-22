/**
 * This class contains all the methods needed to model how an Urgent Care system functions
 */


public class UrgentCareAdmissions {
	
	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;

	
	/**
	 * This method, getAdmissionIndex, returns the index of a patient after they've been placed
	 * within patientsList. This placement is dependent on their triage level and the size.
	 * 
	 * @param triage is the urgency level of the next patient's issue, RED YELLOW or GREEN
	 * the number of patients currently in the list
	 * @param patientsList is the current, active list of patient record
	 * @param size is the number of patients currently in the list
	 * @return the index of where the inserted patient has been places
	 */
	
	public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
		if (size == patientsList.length)	{
			return -1; // the expected result if patientsList is full
		}
		
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (patientsList[i][2] <= triage)	{
				index = i + 1;
			} 
			else	{
				break; // breaks loop if higher triage or null
			}
		}
		return index;
	}
	
	
/**
* addPatient puts a patient and their info into a specific spot within
* a list. The array must remain ordered, higher patients are shifted
* 
* * @param patientRecord a three-element perfect size array of ints, contains
  * 5-digit number,
  * @param index the place where patientRecord should be added to the patientsList array
  * @param patientsList the current, active list of patient records
  * @param size the number of patients currently in the list
  * @return the number of patients in patientsList after this method has
  *    finished running
*/
	
	public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
		  if (index < 0 || index > patientsList.length || size == patientsList.length) {
		    return size; // invalid cases to addPatient
		  }

		  for (int i = size - 1; i >= index; i--) {
		    patientsList[i + 1] = patientsList[i]; // shifts the patiensList to maintain order
		  }

		  patientsList[index] = patientRecord;
		  return size + 1;
		}
	
	
/**
* removeNextPatient updates the list by removing the patient at index 0.
* Each patient behind is shifted forward.
* 
* @param patientsList - the current, active list of patient records
* @param size - the number of patients currently in the list
* @return the number of patients in patientsList after this method has finished running
*/

	public static int removeNextPatient(int[][] patientsList, int size)	{
		if (size == 0)	{
			return 0;
			
		}
		
		for (int i = 0; i <= size - 1; i++) {
			patientsList[i] = patientsList[i + 1]; //shifts patient:ist up after idx 0 is removed
			
			}
		
	return size - 1;
	
	}
	

/**
* Examining the elements of an array, getPatientIndex method takes a caseID
* parameter and finds the matching index. The array should not be modified.
* @param caseID - the five-digit case number assigned to the patient record to find
* @param patientsList - the current, active list of patient records
* @param size - the number of patients currently in the list
* @return the index of the patient record matching the given caseID number, 
* or -1 if the list is empty or the caseID is not found
*/

	public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
	    if (patientsList == null || size == 0) {
	    	
	    }
	    
	    for (int i = 0; i < size; i++) {
	        if (patientsList[i][0] == caseID) { //iterates until designated ID is found
	            return i;
	        }
	    }

	    return -1;
	}
	
	
/**
* Looking at the arrival order of a patient within each element, this method
* returns the index at which the smallest arrivalOrder number is found.
* This array should not be modified.
* 
* @param patientsList - the current, active list of patient records
* @param size - the number of patients currently in the list
* @return the index of the patient record with 
* the smallest value in their arrival integer, or -1 if the list is empty
*/

	public static int getLongestWaitingPatientIndex(int[][] patientsList,
			int size) {
		if (patientsList == null || size == 0) {
			return -1;
			
		}
		
		int earliestArriver = Integer.MAX_VALUE; //Takes in max to compare anything smaller
		int earliestArriverIndex = 0;
		
		for (int i = 0; i < size; i++) {
			if (patientsList[i][1] < earliestArriver) {
				earliestArriver = patientsList[i][1]; //replaces variable with earliest value
				earliestArriverIndex = i;
			}
		}
		
		return earliestArriverIndex;
	}
	
	
/**
* getSummary() should look at a patientsList and return a nice summary
* of the number of patients and the total of each triage color.
* @param patientsList - the current, active list of patient records
* @param size - the number of patients currently in the list
* @return a String summarizing the patientsList as shown in this comment
*/
		
	public static String getSummary(int[][] patientsList,
				 int size) {
		
		int redCount = 0;
		int yellowCount = 0;
		int greenCount = 0;
		
		for (int i = 0; i < size; i++ ) {
			int currentTriage = patientsList[i][2];
			
			if (currentTriage == 0) {
				redCount++;
				
			} else if (currentTriage == 1) {
				yellowCount++;
				
			} else {
				greenCount++;
			}
		}
		return ("Total number of patients: " + size + "\n" + "RED: " + redCount 
				+ "\n" + "YELLOW: " + yellowCount + "\n" + "GREEN: " + greenCount);
	}
}
			
		
	
	
	

	
