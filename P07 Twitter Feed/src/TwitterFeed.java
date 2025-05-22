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
 * This class models a reverse-chronological order of a Twitter feed with a
 * singly-linked list. New tweets are added to the head of the list while older
 * tweets get pushed back towards the tail. However, you can add tweets anywhere
 * in the list relative to each other, despite timestamps
 * @author katiekrause
 *
 */
public class TwitterFeed extends Object implements ListADT<Tweet>,  Iterable<Tweet> {
	private TweetNode head;
	private TweetNode tail;
	private int size;
	private TimelineMode mode;
	private static double ratio = 0.5;

	/**
	 * Created an empty twitter feed, sets all data 
	 * fields to default values and
	 * the timeline mode to chronological order
	 */
	public TwitterFeed() {
		head = null;
		tail = null;
		size = 0;
		mode = TimelineMode.CHRONOLOGICAL;
	}
	
	/**
	 * Accessor for the size of the feed
	 * @return the number of tweet nodes in the feed
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Checks if the feed is empty
	 * @return true of there are no tweet nodes in the feed, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0 && head == null && tail == null;
	}
	
	/**
	 * Determines whether a specific tweet is in the feed
	 * @param findObject - the tweet to look for
	 * @return true if the tweet is present, false otherwise
	 */
	public boolean contains(Tweet findObject) {
		TweetNode currentNode = head;
		while (currentNode != null) {
			if (currentNode.getTweet().equals(findObject)) {
				return true;
			}
			currentNode = currentNode.getNext();
		}
		return false;
	}
	
	/**
	 * Accessor method for the index of a given tweet in the feed
	 * @param findObject - the tweet to search for
	 * @return the index of the Tweet in the feed if present, -1 else
	 */
	public int indexOf(Tweet findObject) {
		TweetNode currentNode = head;
		int index = 0;
		
		while (currentNode != null) {
			if (currentNode.getTweet().equals(findObject)) {
				return index;
			}
			currentNode = currentNode.getNext();
			index ++;
		}
		return -1;
	}
	
	@Override
	/**
	 * Accessor method for the tweet at a given index
	 * @param index - the index of the tweet in question
	 * @return the tweet object at that index
	 * @throws IndexOutOfBoundsException if index is negative or greater
	 * than largest index in the feed
	 */
	public Tweet get(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index " 
		+ index + "is invalid");
		}
		
		TweetNode currentNode = head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode.getTweet();
	}
	
	/**
	 * Accessor method for the first tweet in the feed
	 * @return the tweet object at the head of the linked list
	 * @throws NoSuchElementException if feed is empty
	 */
	public Tweet getHead() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Your "
					+ "twitter feed is empty!");
		}
		return head.getTweet();
	}
	
	/**
	 * Accessor method for the last Tweet in the feed
	 * @return the tweet object at the tail of the linked list
	 * @throws NoSuchElementException if feed is empty
	 */
	public Tweet getTail() throws NoSuchElementException{
		if (isEmpty()) {
			throw new NoSuchElementException("Your "
					+ "twitter feed is empty");
			}
		return tail.getTweet();
	}
	
	/**
	 * Adds the given tweet to the tail of the linked list
	 * @param newObject - the tweet to add
	 */
	public void addLast(Tweet newObject) {
		TweetNode addNode = new TweetNode(newObject);
		if (head == null) {
			head = addNode;
			tail = addNode;
		} else {
			tail.setNext(addNode);
			tail = addNode;
		}
		size++;
	}
	
	/**
	 * Adds the given tweet to the head of the linked list
	 * @param newObject - the tweet to add
	 */
	public void addFirst(Tweet newObject) {
		TweetNode addNode = new TweetNode(newObject);
		if (head == null) {
			head = addNode;
			tail = addNode;
		} else {
			addNode.setNext(head);
			head = addNode;
		}
		size++;
	}
	
	/**
	 * Adds the Tweet to a specific position in the linked list
	 * @param index - the place where the tweet should be added
	 * @param newObject - the tweet to add
	 * @throws IndexOutOfBoundsException if the index is negative or
	 * greater than the size of the list
	 */
	public void add(int index, Tweet newObject) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index " 
		+ index + "is invalid");
		}
		
		TweetNode addNode = new TweetNode(newObject);
		if (head == null) {
			head = addNode;
			tail = addNode;
		} else if (index == 0) {
			addNode.setNext(head);
			head = addNode;
		} else if (index == size) {
			tail.setNext(addNode);
			tail = addNode;
		} else {
			TweetNode currentNode = head;
			for (int i = 0; i < index - 1; i++) {
				currentNode = currentNode.getNext();
			}
			addNode.setNext(currentNode.getNext());
			currentNode.setNext(addNode);
		}
		size++;
	}
	
	/**
	 * removes and returns the tweet at the given index
	 * @param index - the position of the tweet to remove
	 * @return the tweet object that was removed
	 * @throws IndexOutOfBoundsException if the index is 
	 * negative or greater than
	 * the largest index in the list
	 */
	public Tweet delete(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index " 
		+ index + "is invalid");
		}
		
		TweetNode removeNode = head;
		if (index == 0) {
			head = removeNode.getNext();
			if (head == null) {
				tail = null;
			}
		} else {
			TweetNode prevNode = head;
			for (int i = 0; i < index - 1; i++) {
				prevNode = prevNode.getNext();
			}
			
			removeNode = prevNode.getNext();
			prevNode.setNext(removeNode.getNext());
			if (prevNode.getNext() == null) {
				tail = prevNode;
			}
		}
		size --;
		
		if (size == 0) {
			head = null;
			tail = null;
		}
		return removeNode.getTweet();
	}
	
	/**
	 * Sets the iteration mode of this twitter feed
	 * @param m the iteration mode
	 */
	public void setMode(TimelineMode m) {
		mode = m;
	}
	
	@Override
	/**
	 * Creates and returns the correct type of iterator based on the 
	 * current mode of this TwitterFeed
	 * @return any of the Twiterators are returned and initialized to the head
	 * of this feed list
	 */
    public Iterator<Tweet> iterator() {
        if (mode == TimelineMode.CHRONOLOGICAL) {
            return new ChronoTwiterator(this.head);
        } else if (mode == TimelineMode.VERIFIED_ONLY) {
            return new VerifiedTwiterator(this.head);
        } else if (mode == TimelineMode.LIKE_RATIO) {
            return new RatioTwiterator(this.head, ratio);
        } else {
            return null;
        }
    }
}

