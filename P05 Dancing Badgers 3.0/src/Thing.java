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

import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Thing class displays a given image that can be drawn on the PApplet window.
 * This class stores the image, and its current x and y coordinates
 * 
 * @author Eugene Park + Katie Krause
 *
 */
public class Thing {
	/**
	 * image of this graphic thing of type PImage
	 */
	protected static processing.core.PApplet processing;
	/**
	 * PApplet object that represents the display window of 
	 * this graphic application
	 */
	private processing.core.PImage image;
	/**
	 * x-position of this thing in the display window
	 */
	protected float x;
	/**
	 * y-position of this thing in the display window
	 */
	protected float y;
	
	/**
	 * 
	 * Creates a Thing object with the given x and y coordinates 
     * and loads the image with the provided filename.
	 * @param x-position of this thing in the display window
	 * @param y-position of this thing in the display window
	 * @param imageFilename the name of the file containing the Thing image
	 * 
	 */
	public Thing(float x, float y, String imageFilename) {
		this.x = x;
		this.y = y;
		this.image = processing.loadImage("images" + File.separator + imageFilename);
	}
		
	/**
	 * Draws this thing to the display window at its current (x,y) position
	 */	
	public void draw() {
		processing.image(this.image, x, y);
	}
	
	/**
	 * Sets the PApplet object display window where this Thing object will be drawn
	 * 
	 * @param processing is a PApplet object that represent the display window
	 */
	public static void setProcessing(processing.core.PApplet processing) {
		Thing.processing = processing;
	}
	
	/**
	 * Returns a reference to the image of this thing
	 * 
	 * @return the image of type PImage of the thing object
	 */	
	public processing.core.PImage image() {
		return this.image;
	}
	
	/**
	 * Checks if the mouse is over the Thing object
	 * 
	 * @return true if the mouse is over Thing, otherwise false
	 */
	public boolean isMouseOver() {
	    // Get current mouse position
	    int mouseX = processing.mouseX;
	    int mouseY = processing.mouseY;

	    // Check if mouse is within Thing boundaries
	    int imageX = (int) (x - image.width / 2);
	    int imageY = (int) (y - image.height / 2);
	    if (mouseX >= imageX && mouseX <= imageX + image.width && 
	        mouseY >= imageY && mouseY <= imageY + image.height) {
	        return true;
	    } else {
	        return false;
	    }
	}
}