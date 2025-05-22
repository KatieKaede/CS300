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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through tweets in reverse chronological order
 * by high like ratio only
 * 
 * @author katiekrause
 *
 */
public class RatioTwiterator extends Object implements Iterator<Tweet> {
	private TweetNode next;
	private final double THRESHOLD;
	
	/**
	 * Constructor that makes a new twiterator at the given starting node
	 * @param startNode - the node to begin at
	 * @param threshold - minimum threshold value for the ratio of total
	 * engagement, between 0.0 and 1.0 thanks to range checking
	 */
	public RatioTwiterator(TweetNode startNode, double threshold) {
		this.THRESHOLD = threshold;
		while (startNode != null && startNode.getTweet().getLikesRatio() < threshold) {
			startNode = startNode.getNext();
		}
		this.next = startNode;
	}
	
	/**
	 * Checks whether there is a next tweet to return
	 * @return true if there is a next tweet, false otherwise
	 */
	public boolean hasNext() {
		TweetNode currentNode = next;
		while (currentNode != null && 
				currentNode.getTweet().
				getLikesRatio() < THRESHOLD) {
			currentNode = currentNode.getNext();
		}
		return currentNode != null;
	}
	/**
	 * Returns the next tweeet in the iteration if it exists, 
	 * and advances next to the 
	 * tweet with a ratio in the given threshold
	 * @throws NoSuchElementException if there is not a next tweet to return
	 */
	public Tweet next() throws NoSuchElementException {
		if (hasNext() == false) {
			throw new NoSuchElementException("No "
					+ "tweets left to iterate");
		}
		
		TweetNode currentNode = next;
		while (currentNode != null && 
				currentNode.getTweet().getLikesRatio() < THRESHOLD) {
			currentNode = currentNode.getNext();
		}
		
		next = currentNode.getNext();
		return currentNode.getTweet();
	}
}