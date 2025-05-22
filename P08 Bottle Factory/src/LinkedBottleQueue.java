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
 * This class implements a linked queue with objects of type Bottle. Bottle
 * are added and removed based on the FIFO policy
 * @author katiekrause
 *
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
    private LinkedNode<Bottle> front; // front of the queue
    private LinkedNode<Bottle> back; // back of the queue
    private int size; // current size of the queue
    private int capacity; // maximum size of the queue
   
    /**
     * This constructor initializes the fields of this queue as 
     * well as its capacity. The new queue must be 
     * empty and its front and back are null
     * @param capacity - max number of bottles a queue can have
     * @throws IllegalArgumentException - when capacity 
     * is less than or equal to 0
     */
    public LinkedBottleQueue(int capacity) 
    		throws IllegalArgumentException {
    	if (capacity <= 0) {
    		throw new IllegalArgumentException("Capacity "
    				+ "can't be 0 or less");
    	}
    	this.capacity = capacity;
    	this.size = 0;
    	this.front = null;
    	this.back = null;
    }
    
    @Override
    /**
     * Returns a string representation of the queue 
     * from the front to its back
     * with the string representation of each Bottle in a separate
     * line
     * @return String in expected format, empty 
     * string when queue is empty
     */
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	LinkedNode<Bottle> currentBottle = front;
    	while (currentBottle != null) {
    		sb.append(currentBottle.getData().toString());
    		if (currentBottle.getNext() != null) {
    			sb.append("\n");
    		}
    		currentBottle = currentBottle.getNext();
    	}
    	return sb.toString();
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
    	return front.getData();
    }
    
    /**
     * Checks and returns true if the queue is empty
     * @return boolean value
     */
    public boolean isEmpty() {
    	return (size == 0);
    }
    
    /**
     * Checks and returns true if the queue is full
     * @return boolean value
     */
    public boolean isFull() {
    	return (size == capacity);
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
    	Bottle frontBottle = front.getData();
    	front = front.getNext();
    	size --;
    	if (isEmpty()) {
    		back = null;
    	}
    	return frontBottle;
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
    	LinkedNode<Bottle> newNode = new LinkedNode<Bottle>(bottle);
    	if (isEmpty()) {
    		front = back = newNode;
    	} else {
    		back.setNext(newNode);
    		back = newNode;
    	}
    	size++;
    }
    
    @Override
    /**
     * Returns the number of bottles in the queue
     * @return size of the queue
     */
    public int size() {
    	return size;
    }
    
    @Override
    /**
     * Returns an  iterator for traversing the queue's items
     */
    public Iterator<Bottle> iterator() {
    	return new BottleQueueIterator(this);
    	}
    
    /**
     * Returns a deep copy of this queue
     * @return deep copy of this queue
     */
    public QueueADT<Bottle> copy() {
    	LinkedBottleQueue newQueue = new LinkedBottleQueue(capacity);
    	LinkedNode<Bottle> currentBottle = front;
    	while (currentBottle != null) {
    		Bottle copyBottle = currentBottle.getData();
    		newQueue.enqueue(copyBottle);
    		currentBottle = currentBottle.getNext();
    	}
    	return newQueue;
    }
}