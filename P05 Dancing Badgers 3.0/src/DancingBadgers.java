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

import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class DancingBadgers extends PApplet{
	
	
	// array storing badgers dance show steps
	private static DanceStep[] badgersDanceSteps = new DanceStep[] {DanceStep.LEFT,
			DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN,
			DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};
	
	
	// array storing the positions of the dancing badgers at the start of the dance show
	private static float[][] startDancePositions =
			new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};
			
	
	// starts off as false to indicate dance is not happening
	private boolean danceShowOn = false;
	
	
	// sets the desired background picture
	private static PImage backgroundImage;
	
	
	// arraylist storing Thing objects
    private static ArrayList<Thing> things;
    
	
	// generates random numbers
	private static Random randGen;
	
	
	// maximum number of badgers
	private static int badgersCountMax;
	
	
	/**
	 * Sets the size of the display window of this graphic application
	 */
	@Override
	public void settings() {
		this.size(800, 600);
	}
	
	
	/**
	 * Defines initial environment properties of this graphic application.
	 * This method initializes all the data fields defined in this class.
	 */
	@Override
	public void setup() {
		Thing.setProcessing(this);
		Badger.setProcessing(this);
		
		things = new ArrayList<Thing>();
		
		things.add(new Thing(50, 50, "target.png"));
	    things.add(new Thing(750, 550, "target.png"));
	    things.add(new Thing(750, 50, "shoppingCounter.png"));
	    things.add(new Thing(50, 550, "shoppingCounter.png"));
	    
	    StarshipRobot robotOne = new StarshipRobot(things.get(0), things.get(2) , 3);
	    things.add(robotOne);
	    
	    StarshipRobot robotTwo = new StarshipRobot(things.get(1), things.get(3) , 5);
	    things.add(robotTwo);
	    
	    Basketball basketballOne = new Basketball(50, 300);
	    things.add(basketballOne);
	    
	    Basketball basketballTwo = new Basketball(750, 300);
	    things.add(basketballTwo);
	    
		this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
		this.textAlign(3, 3); // sets the alignment of the text
		this.imageMode(3); // interprets the x and y position of an image to its center
		this.focused = true; // confirms that this screen is "focused", meaning
		randGen = new Random();
	    backgroundImage = loadImage("images" + File.separator + "background.png");
	    badgersCountMax = 5;

		// it is active and will accept mouse and keyboard input.
		// TODO complete the implementation of this method
	}
	
	
	/**
	 * Callback method that draws and updates the application display window.
	 * This method runs in an infinite loop until the program exits.
	 */
	@Override
	public void draw() {
		// Set background color
	    background(255, 218, 185);
	    
	    // Draw background image in the center of the screen
	    image(backgroundImage, width / 2, height / 2);
	    
	    // Draw the ArrayList "things" images
	    for (int i = 0; i < things.size(); i++) {
	    	Thing currentThing = things.get(i);
	    	currentThing.draw();
	    		if (currentThing instanceof StarshipRobot) {
	    			((StarshipRobot) currentThing).go();
	    		}
	    }
	}
	
	
	 /**
	   * Callback method called each time the user presses the mouse
	   */
	@Override
	public void mousePressed() {
	   for (int i = 0; i < things.size(); i++) {
	       if (things.get(i).isMouseOver()) {
	           if (things.get(i) instanceof Clickable) {
	               ((Clickable) things.get(i)).mousePressed();
	               }
	           return;
	        }
	    }
	}
	
	
	public void mouseReleased() {
	    for (int i = 0; i < things.size(); i++) {
	        if (things.get(i) instanceof Clickable) {
	            ((Clickable) things.get(i)).mouseReleased();
	        }
	    }
	}


	public int badgersCount() {
	    int count = 0;
	    for (int i = 0; i < things.size(); i++) {
	        if (things.get(i) instanceof Badger) {
	            count++;
	        }
	    }
	    return count;
	}
	
	
	
	private void setStartDancePositions() {
		int currentPosition = 0;
	    for (int i = 0; i < things.size(); i++) {
	    	Thing thing = things.get(i);
	        if (thing instanceof Badger && currentPosition < startDancePositions.length) {
	        	Badger badger = (Badger) things.get(i);
	            float[] position = startDancePositions[currentPosition];
	            badger.x = position[0];
	            badger.y = position[1];
	            currentPosition++;
	        }
	    }
	}
	
	
	public void keyPressed() {
		if (key == 'b' && !danceShowOn) {
			if (badgersCount() < badgersCountMax) {
				Badger newBadger = new Badger(randGen.nextFloat(width), randGen.nextFloat(height), 
						badgersDanceSteps);
				things.add(newBadger);
		    }
		} else if (key == 'c') {
			danceShowOn = false;
			for (int i = things.size() -1; i >= 0; i--) {
				Thing thing = things.get(i);
				if (thing instanceof MovingThing) {
					things.remove(i);
				}
			}
		} else if (key == 'd' && !danceShowOn && badgersCount() > 0) {
			// Start the dance show if it was not already on and there is at least one Badger object
			danceShowOn = true;
			for (int i = 0; i < things.size(); i++) {
				Thing thing = things.get(i);
			if (thing instanceof Badger) {
					  // Call startDancing() method on every Badger object
					  setStartDancePositions();
					  ((Badger) thing).startDancing();
				  }
			  }
		} else if (key == 'r' && !danceShowOn) {
			for (int i = 0; i < things.size(); i++) {
				Thing thing = things.get(i);
				if (thing instanceof Badger && thing.isMouseOver()) {
					things.remove(i);
				}
			}
		} else if (key == 's' && danceShowOn) {
			danceShowOn = false;
			for (int i = 0; i < things.size(); i++) {
				Thing thing = things.get(i);
				if (thing instanceof Badger) {
					((Badger) thing).stopDancing();
				}
			}
		}
	}


	
	/**
	 * Driver method to run this graphic application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PApplet.main("DancingBadgers");
	}
}
