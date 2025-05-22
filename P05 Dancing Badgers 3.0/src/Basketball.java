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
import processing.core.PApplet;

public class Basketball extends Thing implements Clickable {
	private int rotations;
	public float rotation;
	
	public Basketball(float x, float y) {
		super(x, y, "basketball.png");
		rotations = 0;
		rotation = PApplet.PI/2;
		
	}
	
	
	public void draw() {
		processing.pushMatrix();
		processing.translate(x, y);
		processing.rotate(this.rotations * rotation);
		processing.image(image(), 0.0f, 0.0f);
		processing.popMatrix();
	}
	
	
	public void mousePressed() {
		if (isMouseOver() == true) {
			rotations++;
		}
	}
	
	
	public void mouseReleased() {
		
	}
	
	
	public void rotate() {
		rotation += PApplet.PI/2;
        rotations++;
	}

}
