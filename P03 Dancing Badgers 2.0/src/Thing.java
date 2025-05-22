import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class Thing {
	private static PApplet processing; // PApplet object that represents the display window
	private PImage image; // thing's image
	private float x; // x-position
	private float y; // y-position
	
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
	 * Sets the PApplet object display window where this Thing will be drawn. 
	 * The processing PApplet static data field should be set to Badger.getProcessing() since Badgers and 
	 * Thing objects are going to be displayed to the same screen.
	 */
	
	public static void setProcessing() {
		processing = Badger.getProcessing();
		
	}
	
	/**
	* Returns a reference to the image of this thing
	* 
	* @return the image of type PImage of the thing object
	*/	
	
	public processing.core.PImage image() {
		return image;
	}
	
	/**
	 * Returns the x-position of this badger in the display window
	 * 
	 * @return the X coordinate of the badger position
	 */
	public float getX() {
		return x;
	}

	/**
	 * Returns the y-position of this badger in the display window
	 * 
	 * @return the y-position of the badger
	 */
	public float getY() {
		return y;
	}


	/**
	 * Sets the x-position of this badger in the display window
	 * 
	 * @param x the x-position to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Sets the y-position of this badger in the display window
	 * 
	 * @param y the y-position to set
	 */
	public void setY(float y) {
		this.y = y;
	}

}

