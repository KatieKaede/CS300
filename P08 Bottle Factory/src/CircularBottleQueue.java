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
 * This class models a circular-indexing array queue 
 * which stores elements of type Bottle
 * @author katiekrause
 *
 */
public class CircularBottleQueue extends Object 
	implements QueueADT<Bottle>, Iterable<Bottle> {
	private Bottle[] bottles; // array of bottles
	private int front; // first added bottle
	private int back; // most recent added bottle
	private int size; //  number of bottles in the queue
	private int capacity; // bottle capacity
	
	/**
	 * Constructor that makes a CircularBottleObject queue
	 * The bottles oversize array goes to an 
	 * empty array of bottle object
	 * whose length is the input capacity
	 * its size to zero, and front and back to -1
	 * @param capacity - number of bottles the queue can hold
	 * @throws IllegalArgumentException - when capacity is not positive
	 */
	public CircularBottleQueue(int capacity)
            throws IllegalArgumentException{ 
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity cannot be 0 or less");
		}
			this.capacity = capacity;
		    this.bottles = new Bottle[capacity];
		    this.front = -1;
		    this.back = -1;
		    this.size = 0;
	}
	
	@Override
    /**
     * Returns an  iterator for traversing the queue's items
     */
    public Iterator<Bottle> iterator() {
    	return new BottleQueueIterator(this);
    	}
	
	@Override
    /**
     * Add a bottle to the end of the queue
     * @param bottle - bottle to add to the queue
     * @throw IllegalStateException - when queue is full
     * @throw NullPointerException - when bottle to add is null
     */
    public void enqueue(Bottle bottle) {
    	if (bottle == null) {
    		throw new NullPointerException("Added bottle is null");
    	}
    	if (isFull()) {
    		throw new IllegalStateException("The queue is full");
    	}
    	if (isEmpty()) {
    		front = 0;
    		back = 0;
    		
    	} else {
    		back = (back + 1) % capacity;
    	}
    	bottles[back] = bottle;
    	size++;
	}
	
	@Override
    /**
     * Remove and returns the first bottle in the queue
     * @return top/first bottle in the queue
     * @throw NoSuchElementException - when queue is empty
     */
    public Bottle dequeue() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("The queue is empty");
    	}
    	Bottle frontBottle = bottles[front];
    	bottles[front] = null;
    	if (size == 1) {
    		front = -1;
    		back = -1; 
    	} else {
    		front = (front + 1) % capacity;
    	}
    	size --;
    	if (isEmpty()) {
    		back = -1;
    	}
    	return frontBottle;
    }
	
	/**
     * Returns the first bottle in the queue without removing it
     * @return the first bottle
     * @throw when queue is empty
     */
    public Bottle peek() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("The Queue is Empty");
    	}
    	return bottles[front];
    }
	
    /**
     * Checks and returns true if the queue is empty
     * @return boolean value
     */
    public boolean isEmpty() {
    	return size == 0;
    }
    
    /**
     * Checks and returns true if the queue is full
     * @return boolean value
     */
    public boolean isFull() {
    	return size == capacity;
    }
    
    /**
     * Returns the number of bottles in the queue
     * @return size of the queue
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns a string representation of the 
     * queue from the front to its back
     * with the string representation of each Bottle in a separate
     * line
     * @return String in expected format, 
     * empty string when queue is empty
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Bottle> iterator = iterator(); // initialize an iterator
        while (iterator.hasNext()) { 
            Bottle bottle = iterator.next();
         // while there is a next, it is appended
            sb.append(bottle.toString()).append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
    
    /**
     * Returns a deep copy of this queue
     * @return deep copy of this queue
     */
    public QueueADT<Bottle> copy() {
    	LinkedBottleQueue newQueue = new LinkedBottleQueue(capacity);
    	for (int i = 0; i < size; i++) {
    		int index = (front + i) % capacity;
    		Bottle initialBottle = bottles[index];
    		Bottle copyBottle = initialBottle;
    		newQueue.enqueue(copyBottle);
    	}
    	return newQueue;
    }
}