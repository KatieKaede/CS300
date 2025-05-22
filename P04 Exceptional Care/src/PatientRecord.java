public class PatientRecord {
	
	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;
	
	private static int patientCounter = 1;
	
	final int CASE_NUMBER;
	private int age;
	private char gender;
	private boolean hasBeenSeen;
	private int orderOfArrival;
	private int triage;
	
	
	/**
	 * Creates a new patient record and assigns it a CASE_NUMBER using the provided information. 
	 * 
	 * @param gender - a single-character representation of this patient's reported gender
	 * @param age - the age of this patient in years
	 * @param triage - the triage level of this patient
	 * @throws IllegalArgumentException - with a descriptive error message if the 
	 * provided triage value is not one of the class constants
	 */
	public PatientRecord(char gender, int age, int triage) {
		if (triage != RED && triage != YELLOW && triage != GREEN) {
			throw new IllegalArgumentException("Not a valid triage level " + triage);
		}
		this.gender = gender;
	    this.age = age;
	    this.triage = triage;
	    this.hasBeenSeen = false;
	    this.orderOfArrival = patientCounter;
	    CASE_NUMBER = generateCaseNumber(gender, age);
	}
	
	
	/**
	 * Generates a five-digit case number for this patient using their reported gender and age. 
	 * 
	 * @param gender - a single-character representation of this patient's reported gender
	 * @param age - the age of this patient in years
	 * @return caseNumber -  a five-digit case number for the patient
	 */
	public static int generateCaseNumber(char gender, int age) {
		
		// compute first digit based on given gender
		int firstNumber;
		
		if (gender == 'F') {
			firstNumber = 1;
		} else if (gender == 'M') {
			firstNumber = 2;
		} else if (gender == 'X') {
			firstNumber = 3;
		} else {
			firstNumber = 4;
		}
		
		// compute second and third digits based of patient's age
		int secondThirdNumber = age % 100;
				
		// compute fourth and fifth digits based on admission number
		int fourthFifthNumber = (patientCounter % 100);

		//Add up all numbers to get the 5 digit case number
		int caseNumber = (firstNumber * 10000) + (secondThirdNumber * 100) + fourthFifthNumber;
		
		// Add to the patient counter
				patientCounter++;
		
		// reset patientCounter to 0 if it is currently 99
	    if (patientCounter == 100) {
	        patientCounter = 0;
	    }
	
		return caseNumber;
		
		
	}
	
	/**
	 * resets PatientRecord.patientCounter to 1. 
	 */
	public static void resetCounter() {
		patientCounter = 1;
		
	}
	
	
	/**
	 * Accessor method for triage
	 * 
	 * @return the PatientRecord's triage value
	 */
	public int getTriage() {
		return triage;
		
	}
	
	
	/**
	 * Accessor method for gender
	 * 
	 * @return the PatientRecord's gender
	 */
	public char getGender() {
		return gender;
	}
	
	
	/**
	 * Accessor method for age
	 * 
	 * @return the PatientRecord's age
	 */
	public int getAge() {
		return age;
	}
	
	
	/**
	 * Accessor method for the order of arrival
	 * 
	 * @return the PatientRecord's orderOfArrival value
	 */
	public int getArrivalOrder() {
		return orderOfArrival;
	}
	
	
	/**
	 * Accessor method for the hasBeenSeen
	 * 
	 * @return true if patient has been seen, false otherwise
	 */
	public boolean hasBeenSeen() {
		return hasBeenSeen;
	}
	
	
	/**
	 * Marks this patient as having been seen. There is no way to undo this action.
	 */
	public void seePatient() {
		hasBeenSeen = true;
	}
	
	
	/**
	 * Creates and returns a String representation of this PatientRecord using 
	 * its data field values:
	 * 
	 * @return a String representation of this PatientRecord
	 */
	@Override
	public String toString() {
		String triageReal;
		if (triage == RED) {
			triageReal = "RED";
		} else if (triage == YELLOW) {
			triageReal = "YELLOW";
		} else {
			triageReal = "GREEN";
		}
		return CASE_NUMBER + ": " + age + gender + " (" + triageReal + ")";
		
	}
}