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
public interface ListADT <T> {

	public int size();
	public boolean isEmpty();

	public boolean contains(T findObject);
	public int indexOf(T findObject);
	public T get(int index);

	public void addLast(T newObject);
	public void addFirst(T newObject);
	public void add(int index, T newObject);

	public T delete(int index);
	
}
