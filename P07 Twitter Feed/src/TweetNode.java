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
 * A container for a Tweet in a singly-linked list
 * @author katiekrause
 *
 */
public class TweetNode extends Object {
	private Tweet tweet;
	private TweetNode nextTweet;
	
	/**
	 * A constructor that created a singly-linked node with a tweet
	 * @param tweet - the tweet to put in this node
	 * @param next - the next tweet in the linked list
	 */
	public TweetNode(Tweet tweet, TweetNode next) {
		this.tweet = tweet;
        this.nextTweet = next;
	}

	/**
	 * Constructs a singly-linked node with a tweet, but no tweet after
	 * @param tweet
	 */
	public TweetNode(Tweet tweet) {
		this.tweet = tweet;
		this.nextTweet = null;
	}
	
	/**
	 * Accesses the tweet within this node
	 * @return the tweet in the node
	 */
	public Tweet getTweet() {
		return this.tweet;
	}
	
	/**
	 * Accesses the next TweetNode in the list
	 * @return the successor TweetNode
	 */
	public TweetNode getNext() {
		return this.nextTweet;
	}
	
	/**
	 * Links this node to another node
	 * @param next - the successor TweetNode (can be null)
	 */
	public void setNext(TweetNode next) {
		this.nextTweet = next;
	}
}
