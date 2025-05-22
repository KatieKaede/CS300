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
 * by verified users only
 * @author katiekrause
 *
 */
public class VerifiedTwiterator extends Object implements Iterator<Tweet> {
	private TweetNode next;
	
	/**
	 * A constructor that make a new twiterator at the given start
	 * @param startNode - the node to start at
	 */
	public VerifiedTwiterator(TweetNode startNode) {
		this.next = startNode;
	}
	
	/**
	 * Checks whether there is a next tweet to return
	 * @return true if there is a next tweet, false otherwise
	 */
	public boolean hasNext() {
		TweetNode currentNode = next;
		while (currentNode != null && 
				!currentNode.getTweet().isUserVerified()) {
	        currentNode = currentNode.getNext();
		}
		next = currentNode;
		return currentNode != null;
	}
	
	/**
	 * This is an iterator that moves through tweets in 
	 * reverse chronological order 
	 * by verified users only
	 * @throws NoSuchElementException - if there is not a next tweet to return
	 */
	@Override
	public Tweet next() throws NoSuchElementException {
		if (hasNext() == false) {
			throw new NoSuchElementException("No more "
					+ "iterable tweets!");
		}
		
		TweetNode currentNode = next;
		while (currentNode != null 
				&& !currentNode.getTweet().isUserVerified()) {
			currentNode = currentNode.getNext();
		}
		
		next = currentNode.getNext();
		return currentNode.getTweet();
	}
}