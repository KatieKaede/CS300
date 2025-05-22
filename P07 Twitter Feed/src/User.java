//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Twitter Feed
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This data type models a Twitter user
 * 
 * @author katiekrause
 */
public class User extends Object {
	private String username;
	private boolean isVerified;
	
	/**
	 * Constructs a new User object with a given username. 
	 * Everyone is unverified by default
	 * 
	 * @param username
	 * @throws IllegalArgumentException if user name has an 
	 * asterisk, null, or blank
	 */
	public User(String username) throws IllegalArgumentException {
		if (username == null || username.isBlank() 
				|| username.contains("*")) {
			throw new IllegalArgumentException("Username cannot "
					+ "have asterisk, be null, or blank");
		}
		this.username = username;
		this.isVerified = false;
	}
	
	/**
	 * Accesses the username of this User
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Determines whether the User is a verified user or not
	 * @return true if this user is verified
	 */
	public boolean isVerified() {
		return isVerified;
	}
	
	public void verify() {
		isVerified = true;
	}
	
	public void revokeVerification() {
		this.isVerified = false;
	}
	
	public String toString() {
		if (isVerified() == true) {
			return "@" + username + "*";
		} else {
			return "@" + username;
		}
	}
}
