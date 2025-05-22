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
import java.util.Calendar;
import java.util.Date;

/**
 * This data type models a tweet
 * 
 * @author katiekrause
 *
 */
public class Tweet extends Object {
	private static Calendar dateGenerator;
	private User user;
	private String text;
	private int numLikes;
	private int numRetweets;
	private Date timestamp;
	private static final int MAX_LENGTH = 280;
	
	/**
	 * Creates a fresh, new tweet by the given user. 
	 * This tweet has no likes or retweets yet, and its timestamp should be 
	 * set to the current time.
	 * 
	 * @param user - the User posting the tweet
	 * @param text - the text of the tweet
	 * @throws IllegalArgumentException - if tweet text exceeds MAX_LENGTH
	 * @throws NullPointerException - if given text or user are null
	 * @throws IllegalStateException - if dateGenerator has 
	 * not been initialized
	 */
	public Tweet(User user,String text) throws IllegalArgumentException,
			NullPointerException, IllegalStateException {
		if (text.length() > MAX_LENGTH) {
			throw new IllegalArgumentException("Tweet exceeds "
					+ "character max");
		}
		if (text == null || user == null) {
			throw new NullPointerException("User or tweet is null");
		}
		if (dateGenerator == null) {
			throw new IllegalStateException("dateGenerator "
					+ "has not been initialized");
		}
		
		this.user = user;
		this.text = text;
		this.numLikes = 0;
		this.numRetweets = 0;
		this.timestamp = dateGenerator.getTime();
	}
	
	/**
	 * Initializes the dateGenerator field to the Calendar object
	 * @param c - the Calendar to use for date generation
	 */
	public static void setCalendar(Calendar c) {
		dateGenerator = c;
	}
	
	/**
	 * Accesses the text of this tweet
	 * @return the text of the tweet
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Gets the total engagement numbers (likes + retweets) of this tweet
	 * @return the total engagement of this tweet
	 */
	public int getTotalEngagement() {
		return this.numLikes + this.numRetweets;
	}
	
	/**
	 * Checks whether the author of this tweet is a verified user
	 * @return true if user is verified, false otherwise
	 */
	public boolean isUserVerified() {
		return this.user.isVerified();
	}
	
	/**
	 * Returns the proportion of the total engagement made up of likes
	 * @return ratio of likes to total engagement, value between 0.0 and 1.0 
	 * or -1 if the total engagement is 0
	 */
	public double getLikesRatio() {
		int totalEngagement = getTotalEngagement();
		if (totalEngagement == 0) {
			return -1;
		} else {
			double wholeRatio = (double) numLikes / totalEngagement;
			return wholeRatio;
		}
	}
	
	/**
	 * Add one (1) to the number of likes for this tweet
	 */
	public void like() {
		this.numLikes++;
	}
	
	/**
	 * Add one (1) to the number of retweets for this tweet
	 */
	public void retweet() {
		this.numRetweets++;
	}
	
	@Override
	/**
	 * Compares tweet contents to the provided object. 
	 * If the object is the same
	 * tweet with the same text and time, we use toString() method
	 * but these tweets are considered equal 
	 * regardless of their likes/retweets
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Tweet)) {
			return false;
		}
		Tweet other = (Tweet) o;
        return this.text.equals(other.text) &&
                this.user.toString().equals(other.user.toString()) &&
                this.timestamp.equals(other.timestamp);
    }
    
    @Override
    /**
     * A string representation of this tweet.
     * @return a formatted string representation of this tweet
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tweet from ");
        sb.append(user.toString());
        sb.append(" at ");
        sb.append(timestamp);
        sb.append(":");
        sb.append("\n-- ");
        sb.append(text);
        sb.append("\n-- likes: ");
        sb.append(numLikes);
        sb.append("\n-- retweets: ");
        sb.append(numRetweets);
        return sb.toString();
    }
}