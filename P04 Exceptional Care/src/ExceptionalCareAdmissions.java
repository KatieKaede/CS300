import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

public class ExceptionalCareAdmissions {
	private PatientRecord[] patientsList;
	private int size;
	
	@Override
	/**
	* For testing purposes: this method creates and returns a string representation of the
	* patientsList, as the in-order string representation of each patient in the list on a
	* separate line. If patientsList is empty, returns an empty string.
	*
	* @return a string representation of the contents of patientsList
	*/
	public String toString() {
		String returnValue = "";
		for (PatientRecord r : patientsList) {
			returnValue += (r != null) ? r.toString()+"\n" : "";
		}
		return returnValue.trim();
	}
	
	
	/**
	 * The class constructor initializes the ExceptionalCareAdmissions object
	 * with the given capacity, and throws an IllegalArgumentException 
	 * if the capacity is less than or equal to zero.
	 * @param capacity the maximum number of patients
	 * @throws IllegalArgumentException  If the capacity is less than or equal to zero
	 */
	public ExceptionalCareAdmissions(int capacity) throws IllegalArgumentException {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity must be greater than zero");
		}
		this.patientsList = new PatientRecord[capacity];
		this.size = 0;
	}
	
	
	/**
	 * An accessor method to determine if the patientsList is currently full
	 * 
	 * @return true if the patientsList is full, false otherwise
	 */
	public boolean isFull() {
		return (size == patientsList.length);
	}
	
	
	/**
	 * Accesses the current number of patient records in the patientsList
	 * 
	 * @return size - the current number of PatientRecord objects 
	 * in this ExceptionalCareAdmissions object
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * Accesses the current number of patient records in this patientsList
	 *  representing patients who have already been seen 
	 *  (and could be removed from the list)
	 * 
	 * @return seenPatients - the current count of patientRecords for which the hasBeenSeen() 
	 * method returns true
	 */
	public int getNumberSeenPatients() {
		int seenPatients = 0;
		for (int i = 0; i < size; i++) {
			if (patientsList[i].hasBeenSeen()) {
				seenPatients++;
			}
		}
		return seenPatients;
	}
	
	
	/**
	 * This method, getAdmissionIndex, returns the index of a patient after they've been placed
	 * within patientsList. This placement is dependent on their triage level and the size.
	 * 
	 * @param rec - the PatientRecord to be added to the list
	 * @return index - the correct index of patientsList at which rec should be added
	 * @throws IllegalStateException if the patientsList is full
	 */	
	public int getAdmissionIndex(PatientRecord rec) throws IllegalStateException {
	    if (isFull()) {
	        if (getNumberSeenPatients() > 0) {
	            throw new IllegalStateException("cleanPatientsList()");
	        } else {
	            throw new IllegalStateException("Cannot admit new patients");
	        }
	    }

	    int index = 0;
	    for (int i = 0; i < size(); i++) {
	        if (patientsList[i].getTriage() <= rec.getTriage()) {
	            index = i + 1;
	        } else {
	            break;
	        }
	    }
	    return index;
	}
	
	
	/**
	 * Adds the provided PatientRecord at the provided position in the oversize patientsList array. 
	 * This method must maintain the ordering of the patientsList as before, and rather than returning the 
	 * new size, maintains the size field in this ExceptionalCareAdmissions object appropriately.
	 * 
	 * @param rec - the PatientRecord to be added to the list
	 * @param index - the index at which the PatientRecord should be added to patientsList, 
	 * which you may assume is the same as the output of getAdmissionIndex()
	 * @throws IllgegalStateException if the patientsList is full
	 * @throws IllegalArgumentException - with a descriptive error message if the patientsList is NOT full 
	 * and the index is not a valid index into the oversize array
	 */
	public void addPatient(PatientRecord rec, int index) throws IllegalStateException, IllegalArgumentException {
		if (isFull()) {
	        if (getNumberSeenPatients() > 0) {
	            throw new IllegalStateException("cleanPatientsList()");
	        } else {
	            throw new IllegalStateException("Cannot admit new patients");
	        }
	    }
		
		
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		
		
		// Move all elements from index to the end of the array one position to the right
	    for (int i = size; i > index; i--) {
	        patientsList[i] = patientsList[i - 1];
	    }

	    // Add the new patient record at the specified index
	    patientsList[index] = rec;
	    size++;
	}
	
	
	/**
	 * Marks the patient with the given caseID as having been seen. 
	 * This method will require you to find the patient with the given caseID within the 
	 * patientsList before you can modify their status.
	 * This method may only modify one PatientRecord, and may not modify the patientsList array or its size.
	 * 
	 * @param caseID - the CASE_NUMBER of the PatientRecord to be marked as having been seen
	 * @throws IllegalStateException - if the patientsList is currently empty
	 * @throws IllegalArgumentException - if no PatientRecord with the given caseID is found
	 */
	public void seePatient(int caseID) throws IllegalStateException, IllegalArgumentException	{
		//Checking if the patientsList is empty
		if (size == 0) {
			throw new IllegalArgumentException("No patient to see, list is empty");
		}
		
		//iterate through list to find specified case number
		boolean caseIDFound = false;
		for (int i = 0; i < size; i++) {
			if (patientsList[i].CASE_NUMBER == caseID) {
				patientsList[i].seePatient();
				caseIDFound = true;
				break;
	        }
	    }
	    
	    if (!caseIDFound) {
	    	throw new IllegalArgumentException("No PatientRecord with ID was found");
	    }
		
	}
	
	
	
	/**
	 * Returns a summary of the patients' records, including the total number of patients,
	 * the total number of seen patients, and the number of patients in each triage category 
	 * (red, yellow, and green).
	 * @return a string containing the summary of patients' records
	 */
	public String getSummary() {
		int numPatients = size();
	    int numSeenPatients = getNumberSeenPatients();
	    int numRed = 0;
	    int numYellow = 0;
	    int numGreen = 0;
	    for (int i = 0; i < size(); i++) {
	    	PatientRecord patient = patientsList[i];
	    	switch (patient.getTriage()) {
	    	case PatientRecord.RED:
	    		numRed++;
	    		break;
	    		case PatientRecord.YELLOW:
	    			numYellow++;
	    			break;
	    			case PatientRecord.GREEN:
	    				numGreen++;
	    				break;
	    	}
	    }
	    String summary = "Total number of patients: " + numPatients + "\n";
	    summary += "Total number seen: " + numSeenPatients + "\n";
	    summary += "RED: " + numRed + "\n";
	    summary += "YELLOW: " + numYellow + "\n";
	    summary += "GREEN: " + numGreen + "\n";
	    return summary;
	}
	
	/**
	 * 
	 * @param file
	 */
	public void cleanPatientsList(File file) {
	    try { 
	        FileWriter fos = new FileWriter(file);
	        fos.append(getSummary());
	        boolean hasBeenSeen = false; 
	        PatientRecord[] newPatientsList = new PatientRecord[size]; // new array to hold remaining patients
	        int newCount = 0; // counter for new array
	        for (int i = 0; i < size; i++) {
	            if (patientsList[i].hasBeenSeen()) {
	                fos.append(patientsList[i].toString() + "\n"); 
	                hasBeenSeen = true;
	            } else {
	                newPatientsList[newCount++] = patientsList[i]; // copy non-seen patient to new array
	            }
	        }
	        patientsList = newPatientsList; // replace old array with new array
	        size = newCount; // update size to reflect number of non-seen patients
	        // If no patients were seen, add a line indicating that
	        if (!hasBeenSeen) {
	            fos.append("Total number seen: 0\n");
	        }
	        if (size == 0) {
	            fos.append("Total number seen: 0\n");
	        }
	        fos.close();
	    } catch (IOException e) { 
	        return;
	    }
	}

}


