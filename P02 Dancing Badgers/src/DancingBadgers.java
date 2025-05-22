//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P02 Dancing Badgers
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:	 klkrause5@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Eugene empark@wisc.edu explained to me the base setup of mousePressed and mouseReleased method.
// Online Sources:  Piazza question @288, explained the fix of not assuming y grid down is
// negative. Piazza questions @346 directed me to debug my keypress method to make sure
// that the corect specific characters were being searched for.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;

/**
 * This class implements and models the procedure of manipulating and altering badger
 * images on a visual screen
 */
public class DancingBadgers {
	
	private static PImage backgroundImage; // PImage object that represents the
	// background image
	private static Badger[] badgers; // perfect size array storing the badgers
	// present in this application
	private static Random randGen; // Generator of random numbers
	
	/**
	 * The setup method initializes the background, the random number generator, and the
	 * 5 badger array. By defining the data fields this creates the expected graphical
	 * settings
	 * 
	 */
	public static void setup() {
		
		// load the image of the background
		backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
		
		randGen = new Random();
        
        badgers = new Badger[5];
	
				
		}
	
	
	/**
	* The draw method reads and executes code continuously. What's drawn shows up
	* in the set-up display window and consistently updates the appearance
	* 
	*/
	public static void draw() {
		
		Utility.background(Utility.color(255, 218, 185));
		
		// Draw the background image to the center of the screen
		Utility.image(backgroundImage, Utility.width()/2, Utility.height()/2);
	
		for (int i = 0; i < badgers.length; i++) {
            if (badgers[i] != null) {
                badgers[i].draw();
            }
		}
	}
	/**
	* Checks if the mouse is over a specific Badger whose reference is provided
	* as input parameter. This method calculates the range of the height and width, then
	* finds if the user's cursor is within these bounds.
	*
	* @param Badg reference to a specific Badger object
	* @return true if the mouse is over the specific Badger object passed as input
	* (i.e. over the image of the Badger), false otherwise
	*/

	public static boolean isMouseOver(Badger Badg) {
	
		  
		  float badgerHeight = Badg.image().height;
		  float badgerWidth = Badg.image().width;
		  
		  float imageTop = Badg.getY() + (badgerHeight / 2);
		  float imageBottom = Badg.getY() - (badgerHeight / 2);
		  
		  float imageLeft = Badg.getX() - (badgerWidth / 2);
		  float imageRight = Badg.getX() + (badgerWidth / 2);
		  
		  if (Utility.mouseX() >= imageLeft && Utility.mouseX() <= imageRight &&
				  Utility.mouseY() >= imageBottom && Utility.mouseY() <= imageTop) {
				  return true;
			  }
		  
		  return false;
	}
		  
	/**
	* Callback method called each time the user presses the mouse 
	*/
	public static void mousePressed() {
		for (int i = 0; i < badgers.length - 1; i++) {
			if (badgers[i] != null) {
				if (isMouseOver(badgers[i])) {
				badgers[i].startDragging();
				break;
				}
			}
		}
	}
	
	
	/**
	* Callback method called each time the mouse is released
	*/
	public static void mouseReleased() {	  
		for (int i = 0; i < badgers.length - 1; i++) {
			if (badgers[i] != null) {
				badgers[i].stopDragging();
				
			}
		}
	}
	
	/**
	* Callback method called each time the user presses a key
	*/
	public static void keyPressed() {
		
		if (Utility.key() == 'b' || Utility.key() == 'B') {
			for (int i = 0; i < badgers.length; i++) {
				if (badgers[i] == null) {
					badgers[i] = new Badger(randGen.nextInt(Utility.width()) / 2, 
							randGen.nextInt(Utility.height()) / 2);
					badgers[i].draw();
					break;
					
				}
			}
			
		} else if (Utility.key() == 'r' || Utility.key() == 'R') {
		    for (int i = 0; i < badgers.length; i++) {
		      if (badgers[i] != null && isMouseOver(badgers[i])) {
		        badgers[i] = null;
		        break;
		        
		      }
		    }
		}
	}
	
	/**
	 * Main method that executes the entire beginning of the program
	 * 
	 */
	public static void main(String[] args) {
		Utility.runApplication();
		
		

	}

}





