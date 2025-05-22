import java.io.File;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * This is the main class for the p03 Dancing Badgers II program
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // background image
  private static ArrayList<Badger> badgers; // ArrayList storing badger objects
  private static Random randGen; // Generator of random numbers
  private static int badgersCountMax = 5; // Specified number of allowed badgers
  private static ArrayList<Thing> things; //arraylist storing Thing objects
  private static PApplet processing;
  private static ArrayList<StarshipRobot> robots;


  /**
   * Driver method to run this graphic application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application
   */
  public static void setup() {
	Thing.setProcessing(this);
	StarshipRobot.setProcessing();
	Badger.setProcessing();
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    
    badgers = new ArrayList<Badger>();
    robots = new ArrayList<StarshipRobot>();
    things = new ArrayList<Thing>();
    randGen = new Random();

    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));
    
    
    // To retrieve source and destination, we directly call 
    // the specific index of ArrayList things
    StarshipRobot robotOne = new StarshipRobot(things.get(2), things.get(0) , 3);
    robots.add(robotOne);
    
    StarshipRobot robotTwo = new StarshipRobot(things.get(3), things.get(1), 3);
    robots.add(robotTwo);
  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    for (int i = 0; i < things.size(); i++) {
    	Thing currentThing = things.get(i);
    	currentThing.draw();
    }
    	
    for (int i = 0; i < robots.size(); i++) {
    	StarshipRobot currentRobot = robots.get(i);
    	currentRobot.draw();
    }
    	
    for (int i = 0; i < badgers.size(); i++) {
    	Badger currentBadger = badgers.get(i);
    	currentBadger.draw();
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < badgers.size(); i++) {
    	Badger currentBadger = badgers.get(i);
    	
    	if (currentBadger.isMouseOver()) {
    		currentBadger.startDragging();
    		break;
    	}
    }
}
  
  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.size(); i++) {
        badgers.get(i).stopDragging();
      }
    }

  
  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
        if (badgers.size() < badgersCountMax) {
        	badgers.add(new Badger(randGen.nextInt(Utility.width()), 
        			randGen.nextInt(Utility.height())));
 
        }
        break;
      case 'R': // delete the badger being pressed
        for (int i = 0; i < badgers.size(); i++) {
          if (badgers.get(i).isMouseOver()) {
            badgers.remove(badgers.get(i));
            break;
          }
        }
        break;
    }
  }
}

