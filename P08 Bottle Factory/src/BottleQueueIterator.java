//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 Bottle Factory
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an iterator to iterate over a queue of Bottle objects
 * When the queue is not empty, Bottle objects are iterated from the front to
 * the back of the queue. No more bottle objects are returned when all
 * objects are traversed. It iterates over and queue that implements 
 * QueueADT<Bottle> interface.
 * @author katiekrause
 *
 */
public class BottleQueueIterator extends Object implements Iterator<Bottle> {
	private QueueADT<Bottle> currentQueue;
	
	/**
	 * Initializes the instance fields. 
	 * The bottle queue of this iterator
	 * MUST be initialized to a deep copy of the 
	 * queue passed as input
	 * @param queue - the queue to iterate over, 
	 * implements QueueADT interface
	 * @throws IllegalArgumentException
	 */
	public BottleQueueIterator(QueueADT<Bottle> queue) 
			throws IllegalArgumentException {
		if (queue == null) {
			throw new IllegalArgumentException("Queue is null");
		}
		this.currentQueue = queue.copy();	
	}
	
	@Override
	/**
	 * returns true if the iteration is not yet exhausted, 
	 * meaning not every bottle has been iterated over
	 * @return boolean value
	 */
	public boolean hasNext() {
		return !currentQueue.isEmpty();
	}
	
	@Override
	/**
	 * returns the next bottle in the iteration
	 * @return Bottle - the next bottle in the iteration
	 * @throws NoSuchElementException - if there are 
	 * no more elements to iterate
	 */
	public Bottle next() {
		if (!hasNext()) {
			throw new NoSuchElementException
			("Next element is null");
		}
		return currentQueue.dequeue();
	}
}
