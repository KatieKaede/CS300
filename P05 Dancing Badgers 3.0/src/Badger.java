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
 * This class models a Badger object in the P05 Dancing Badgers 
 * III programming assignment
 * 
 * @author eugenepark + katiekrause
 */
public class Badger extends MovingThing implements Clickable {
	/**
	 * Private ArrayList storing this Badger's dance show steps
	 */
	private DanceStep[] danceSteps;
	/**
	 * indicates whether this badger is dancing or not
	 */
	private boolean isDancing;
	/**
	 * indicates whether this badger is being dragged or not
	 */
	private boolean isDragging;
	/**
	 * stores the next dance (x, y) position of this Badger.
	 */
	private float[] nextDancePosition;
	/**
	 * old x-position of the mouse
	 */
	private static int oldMouseX;
	/**
	 * old y-position of the mouse
	 */
	private static int oldMouseY;
	/**
	 * index position of the current dance step of this badger
	 */
	private int stepIndex;
	
	/**
	 * Creates a new badger object as a specific position in the display
	 * window. The new badger is not dragging or dancing. The constructor
	 * also sets the danceSteps of the badger to the one within the input
	 * 
	 * @param x - x-position of the Badger object on the window
	 * @param y - y-position of the Badger object on the window
	 * @param danceSteps - perfect-size array storing the badger dance steps
	 */
	public Badger(float x, float y, DanceStep[] danceSteps)	{
		super(x, y, 2, "badger.png");
		this.isDragging = false;
		this.isDancing = false;
		this.danceSteps = danceSteps;
		this.stepIndex = 1;
	}
	
	/**
	 * Draws the badger to be in the display window. When called, the method
	 * calls drag() if dragging and dance() if dancing
	 * 
	 */
	public void draw() {
		super.draw();
		
		if (isDragging) {
			drag();
		}
		if (isDancing) {
			dance();
		}
	}
	
	/**
	 * Checks whether the badger is being dragged or not
	 * 
	 * @return true if the badger is being dragged, false otherwise
	 */
	public boolean isDragging() {
		return isDragging;
	}
	
	/**
	 * Helper method to drag this Badger object to follow the mouse moves
	 */
	private void drag() {
	    int dx = processing.mouseX - oldMouseX;
	    int dy = processing.mouseY - oldMouseY;
	    x+=dx;
	    y+=dy;
	    
	    if(x > 0)
	      x = Math.min(x, processing.width);
	    else
	      x = 0;
	    if(y > 0)
	     y = Math.min(y, processing.height);
	    else
	      y = 0;
	    oldMouseX = processing.mouseX;
	    oldMouseY = processing.mouseY;
	}
	
	/**
	 * Starts dragging this badger
	 */
	public void startDragging() {
	    oldMouseX = processing.mouseX;
	    oldMouseY = processing.mouseY;
	    this.isDragging = true;
	    drag();
	}
	
	/**
	 * Stops dragging this Badger object
	 */
	public void stopDragging() {
	    this.isDragging = false;
	}
	
	/**
	 * Checks if the cursor is hovering over the badger and makes sure the badger is NOT
	 * dancing, then this method drags the badger
	 */
	public void mousePressed() {
		if (isMouseOver() && !isDancing) {
			startDragging();
		}
	}
	
	/**
	 * If the cursor releases the badger object, the badger stops being dragged
	 */
	public void mouseReleased() {
		if (isDragging()) {
			stopDragging();
		}
	}
	
	/**
	 * This helper method moves this badger one speed towards nextDancePosition.
	 * Checks whether this Badger is facing right and updates the isFacingRight 
	 * data field accordingly. After making one move dance, a badger 
	 * is facing right if the x-move towards its next dance position is positive, otherwise, it is facing left.
	 */
	private boolean makeMoveDance() {
	    float dx = nextDancePosition[0] - x;
	    float dy = nextDancePosition[1] - y;
	    float distance = (float) Math.sqrt(dx * dx + dy * dy);
	    
	    if (distance < 2 * speed) {
	        x = nextDancePosition[0]; // move directly to the next dance position
	        y = nextDancePosition[1];
	        
	        if (dx > 0) {
	            isFacingRight = true;
	        } else {
	            isFacingRight = false;
	        }
	        return true;
	    } else {
	        float ratio = speed / distance;
	        float moveX = dx * ratio;
	        float moveY = dy * ratio;
	        x += moveX;
	        y += moveY;
	        if (moveX > 0) {
	            isFacingRight = true;
	        } else {
	            isFacingRight = false;
	        }
	        return false;
	    }
	}

	/**
	 * This method prompts the Badger object to make one dance move. If makeMoveDance
	 * returns true, this method updates the next dance position, and increments stepIndex
	 */
	private void dance() {
		boolean nearlyReached = makeMoveDance();
		if (nearlyReached == true) {
			stepIndex = (stepIndex + 1) % danceSteps.length;
			nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);
		}
	}
	
	/**
	 * This method prompts the badger to being dancing, it updates the isDancing field
	 * stops dragging all badgers, sets stepIndex to zero, and resets nextDancePosition
	 */
	public void startDancing() {
		isDancing = true;
		isDragging = false;
		stepIndex = 0;
		nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);
	}
	
	/**
	 * Stops all the badgers dancing and sets isDancing to false
	 */
	public void stopDancing() {
		isDancing = false;
	}
}