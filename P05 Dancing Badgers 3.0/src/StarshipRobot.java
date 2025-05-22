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

public class StarshipRobot extends MovingThing {
	private Thing source;
	private Thing destination;
	
	public StarshipRobot(Thing source, Thing destination, int speed) {
		super(source.x, source.y, speed, "starshipRobot.png");
		this.source = source;
		this.destination = destination;
		
		if (source.x < destination.x) {
			isFacingRight = true;
		} else {
			isFacingRight = false;
		}
	}
	
	@Override
	public void draw() {
		super.draw();
	}
	
	public boolean isOver(Thing thing) {
		float x1 = x - this.image().width / 2;
	    float x2 = x + this.image().width / 2;
	    float y1 = y - this.image().height / 2;
	    float y2 = y + this.image().height / 2;

	    float x3 = thing.x - thing.image().width / 2;
	    float x4 = thing.x + thing.image().width / 2;
	    float y3 = thing.y - thing.image().height / 2;
	    float y4 = thing.y + thing.image().height / 2;

	    return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
	  }
	
	public void moveTowardsDestination() {
		float dx = destination.x - this.x; // x-move towards destination
	    float dy = destination.y- this.y; // y-move towards destination
	    int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
	    if (d != 0) { // move!
	      this.x += speed * dx / d;
	      this.y += speed * dy / d;
	    }
	}
	
	
	public void go() {
		moveTowardsDestination();
	    // switch source and destination if this StarshipRobot reached its destination
	    if (this.isOver(this.destination)) {
	      Thing d = destination;
	      destination = source;
	      source = d;
	    }
	}


}
