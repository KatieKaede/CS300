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
 * @author katiekrause
 */
public class ChronoTwiterator extends Object implements Iterator<Tweet> {
	private TweetNode next;
	
	/**
	 * Constructs a new twiterator at the given start node
	 * @param startNode - the node to begin iteration at
	 */
	public ChronoTwiterator(TweetNode startNode) {
		this.next = startNode;
	}
	
	@Override
	/**
	 * Checks whether there is a next tweet to return
	 * @return true if there is a next tweet, false if next is null
	 */
	public boolean hasNext() {
		return next != null;
	}
	
	@Override
	/**
	 * Returns the next tweet in the iteration if it exists, then goes
	 * to the next tweet
	 * @throws NoSuchElementException - if no tweet to return
	 */
	public Tweet next() throws NoSuchElementException {
		if (hasNext() == false) {
			throw new NoSuchElementException("No tweets "
					+ "left to go through");
		}
		TweetNode current = next;
		next = next.getNext();
		return current.getTweet();
	}

}
