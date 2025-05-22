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
// https://www.baeldung.com/java-write-to-file 
// https://www.w3schools.com/java/java_files_create.asp
// https://learn.microsoft.com/en-us/dotnet/api/system.io.ioexception?view=net-7.0
// refer to line 
// 
// 
///////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.io.IOException; 

/**
 * The class ExceptionalCareAdmissions represents the list of patients waiting for admission.
 * It contains the methods to add a new patient to the list, calculate the 
 * admission index for a given patient based on their triage level, update the patient 
 * record for the patient with the specified case ID to indicate that the patient has been seen, 
 * and return a summary of the patients' records.
 * @author Katie Krause and Eugene Park
 *
 */
public class ExceptionalCareAdmissions {
	private PatientRecord[] patientsList;
	private int size;
	
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
	 * Checks if the patientsList is full and 
	 * returns.
	 * @return if the patientsList is full
	 */
	public boolean isFull() {
		return (size == patientsList.length);
	}
	
	/**
	 * Returns the number of patients currently in the patientsList.
	 * @return number of patients in the patientsList
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns the number of patients in the patientsList that have been seen.
	 * @return the number of patients that have been seen by a doctor
	 */
	public int getNumberSeenPatients() {
	    int count = 0;
	    for (int i = 0; i < size; i++) {
	        if (patientsList[i].hasBeenSeen()) {
	            count++;
	        }
	    }
	    return count;
	}
	
	/**
	 * Adds a new patient to the patientsList at the specified index.
	 * @param rec the PatientRecord object to add to the list
	 * @param index the index at which to add the new patient
	 * @throws IllegalStateException if the patientsList is full and there 
	 * are no more available spaces to admit new patients, 
	 * or if there are still seen patients in the list.
	 * 
	 * @throws IllegalArgumentException if the index is less than 0 
	 * or greater than the size of the patientsList
	 */
	public void addPatient(PatientRecord rec,int index) 
	    throws IllegalStateException,IllegalArgumentException {
		if (isFull()) {
	        if (getNumberSeenPatients() > 0) {
	            throw new IllegalStateException("cleanPatientsList()");
	        } else {
	            throw new IllegalStateException("Cannot admit new patients");
	        }
	    } else if (index < 0 || index > size) {
	        throw new IllegalArgumentException("Invalid index: " + index);
	    } else {
	        for (int i = size - 1; i >= index; i--) {
	            patientsList[i + 1] = patientsList[i];
	        }
	        patientsList[index] = rec;
	        size++;
	    }
	}
	
	/**
	 * Calculates the admission index for a given patient record based on their triage score.
	 * @param rec the PatientRecord object for which to calculate the admission index
	 * @return the admission index for the given patient record
	 * @throws IllegalStateException if the patientsList is full and there are no more 
	 * available spaces to admit new patients, or if there are still seen patients in the list.
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
	    for (int i = 0; i < size; i++) {
	    	if (patientsList[i].getTriage() <= rec.getTriage()) {
	    		index = i + 1;
	    		} else {
	    			break;
	    		}
	    }
	    return index;
	}
	
	/**
	 * Updates the patient record for the patient with the specified case ID to 
	 * indicate that the patient has been seen.
	 * @param caseID the case ID of the patient to be updated
	 * @throws IllegalStateException if the patient list is currently empty
	 * @throws IllegalArgumentException if there is no patient record with the 
	 * specified case ID.
	 */
	public void seePatient(int caseID)
			throws IllegalStateException,IllegalArgumentException {
		if (size == 0) {
			throw new IllegalStateException("patientsList is currently empty");
		}
		boolean caseIDFound = false;
		for (int i = 0; i < size; i++) {
			if (patientsList[i].CASE_NUMBER == caseID) {
				patientsList[i].seePatient();
				caseIDFound = true;
				break;
			}
		}
		if (!caseIDFound) {
			throw new IllegalArgumentException("No PatientRecord with caseID " 
		        + caseID + " found");
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
	 * This method takes in a File object and records the current summary of 
	 * the patient records and a cleaned list of patients that have not been seen. 
	 * The method then updates the patientsList array with a new cleaned list and updates
	 * the size of the list. If no patients were seen, the method adds a line.
	 * If the cleaned list is empty, the method also adds a line. The updated information 
	 * is then written to the file.
	 * @param file the File object that is updated with the cleaned patient list and summary
	 */
	public void cleanPatientsList(File file) {	
		try { 
	        FileWriter fos = new FileWriter(file);
	        fos.append(getSummary());
	        boolean hasBeenSeen = false; 
	        // new array to hold remaining patients
	        PatientRecord[] newPatientsList = new PatientRecord[size]; 
	        // counter for new array
	        int newCount = 0; 
	        for (int i = 0; i < size; i++) {
	            if (patientsList[i].hasBeenSeen()) {
	                fos.append(patientsList[i].toString() + "\n"); 
	                hasBeenSeen = true;
	            } else {
	            	// copy non-seen patient to new array
	                newPatientsList[newCount++] = patientsList[i]; 
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
	
	/**
	* For testing purposes: this method creates and returns a string representation of the
	* patientsList, as the in-order string representation of each patient in the list on a
	* separate line. If patientsList is empty, returns an empty string.
	*
	* @return a string representation of the contents of patientsList
	*/
	@Override
	public String toString() {
		String returnValue = "";
		for (PatientRecord r : patientsList) {
			returnValue += (r != null) ? r.toString()+"\n" : "";
		}
		return returnValue.trim();
	}
}
