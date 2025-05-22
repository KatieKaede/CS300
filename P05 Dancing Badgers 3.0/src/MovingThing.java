//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Eugene Park
// Partner Email:   empark@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * 
 * This class models objects that can be referred to as "MovingThing".
 * A moving object is defined by its speed and the direction it is facing, left or right
 *
 */
public class MovingThing extends Thing implements Comparable<MovingThing> {
	/**
	 * movement speed of this MovingThing
	 */
	protected int speed;
	/**
	 * Indicates whether this MovingThing is facing right or not
	 */
	protected boolean isFacingRight;
	
	/**
	 * Creates a new MovingThing and sets its speed, image file, and initial 
	 * x and y position. A MovingThing object is initially facing right.
	 * 
	 * @param x is the starting x-coordinate of MovingThing
	 * @param y is the starting y-coordinate of MovingTHing
	 * @param speed is a number representing how quick the MovingThing is
	 * @param imageFileName
	 */
	public MovingThing(float x, float y, int speed, String imageFileName) {
		super(x, y, imageFileName);
		this.speed = speed;
		this.isFacingRight = true;
	}
	
	/**
	 * Draws this MovingThing at its current position. The implementation 
	 * details of this method is fully provided in the write-up of p05.
	 */
	public void draw() {
		// draw this MovingThing at its current position
		processing.pushMatrix();
		processing.rotate(0.0f);
		processing.translate(x, y);
		if (!isFacingRight) {
		processing.scale(-1.0f, 1.0f);
		}
		processing.image(image(), 0.0f, 0.0f);
		processing.popMatrix();
	}
	
	/**
	 * 
	 * Compares this object with the specified MovingThing for order, 
	 * in the increasing order of their speeds.
	 * 
	 * @param other - the MovingThing object to be compared.
	 * @return 0 if object and other have the same speed, a negative integer 
	 * if moving object speed is less than other, positive otherwise
	 */
	public int compareTo(MovingThing other) {
        if (this.speed < other.speed) {
            return -1;
        } else if (this.speed > other.speed) {
            return 1;
        } else {
            return 0;
        }
	}
}
