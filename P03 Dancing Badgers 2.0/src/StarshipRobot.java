
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class StarshipRobot {
	private static PApplet processing;
	private int speed;
	private PImage image;
	private float x;
	private float y;
	private Thing source;
	private Thing destination;
	
	public StarshipRobot(Thing source, Thing destination, int speed) {
	    this.source = source;
	    this.destination = destination;
	    this.speed = speed;
	    this.image = processing.loadImage("images" + File.separator + "starshipRobot.png");
	    x = source.getX();
	    y = source.getY();
	}
    public PImage image() {
    	// returns a reference to the PImage of the current StarshipRobot object
    	return image;
    	}
    public float getX() {
    	// returns the x-position of the current StarshipRobot object
    		return x;
    	}
    public float getY() {
    	// returns the y-position of the current StarshipRobot object
    		return y;
    	}
    public void setX(float x) {
    	// sets the x-position of the current StarshipRobot object
    	this.x = x;
    	}
    public void setY(float y) {
    	// sets the y-position of the current StarshipRobot object
    	this.y = y;
    	}
    public static void setProcessing() {
    	// sets the processing PApplet static field to the processing
    	// of the Badger class.
    	processing = Badger.getProcessing();
    	}
    public void draw() {
    	// draws this StarshipRobot to the display window at its current
    	// (x,y) position by calling processing.image() method
    	processing.image(this.image(), x, y);
    	go();
    	}
    
   private void moveTowardsDestination() {
	    float dx = destination.getX() - x;
	    float dy = destination.getY() - y;
	    float distance = (int) Math.sqrt(dx * dx + dy * dy);
	    
	    float newX = x+speed*(dx)/distance;
	    float newY = y+speed*(dy)/distance;
	    
	    x = newX;
	    y = newY;

	}
   
	public boolean isOver(Thing thing) {
		float robotTopRightX = x + image.width/2;
		float robotTopRightY = y - image.height/2;
		float robotLowerLeftX = x - image.width/2;
		float robotLowerLeftY = y + image.height/2;
		
		float thingTopRightX = thing.getX() + thing.image().width/2;
	    float thingTopLeftY = thing.getY() - thing.image().width/2;
	    float thingLowerLeftX = thing.getX() - thing.image().width/2;
	    float thingLowerLeftY = thing.getY() + thing.image().width/2;
	    
	    if (robotTopRightX >= thingLowerLeftX && robotLowerLeftX <= thingTopRightX &&
	            robotTopRightY <= thingLowerLeftY && robotLowerLeftY >= thingTopLeftY) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	   public void go() {	
		   // move one step towards destination
		    moveTowardsDestination();

		    // check if robot is over destination
		    if (isOver(destination)) {
		        // switch source and destination
		        Thing temp = source;
		        source = destination;
		        destination = temp;

		    }
		}
}
