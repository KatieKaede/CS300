import java.io.File;
import java.util.Random;
import processing.core.PImage;

/**
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // backgound image
  private static Badger[] badgers; // array storing badger objects
  private static Random randGen; // Generator of random numbers


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
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new Badger[5];
    randGen = new Random();
  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    for (int i = 0; i < badgers.length; i++)
      if (badgers[i] != null) {
        badgers[i].draw();
      }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < badgers.length; i++)
      if (badgers[i] != null && isMouseOver(badgers[i])) {
        badgers[i].startDragging();
        break;
      }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.length; i++)
      if (badgers[i] != null)
        badgers[i].stopDragging();
  }

  /**
   * Checks if the mouse is over a given badger whose reference is provided as input parameter
   * 
   * @param badger reference to a given badger object
   * @return true if the mouse is over the given badger object (i.e. over the image of the badger),
   *         false otherwise
   */
  public static boolean isMouseOver(Badger badger) {
    int badgerWidth = badger.image().width;
    int badgerHeight = badger.image().height;

    // checks if the mouse is over the badger
    return Utility.mouseX() >= badger.getX() - badgerWidth / 2
        && Utility.mouseX() <= badger.getX() + badgerWidth / 2
        && Utility.mouseY() >= badger.getY() - badgerHeight / 2
        && Utility.mouseY() <= badger.getY() + badgerHeight / 2;
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
        for (int i = 0; i < badgers.length; i++) {
          if (badgers[i] == null) {

            badgers[i] =
                new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
            break;
          }
        }
        break;
      case 'R': // delete the badger being pressed
        for (int i = 0; i < badgers.length; i++) {
          if (badgers[i] != null && isMouseOver(badgers[i])) {
            badgers[i] = null;
            break;
          }
        }
        break;
    }
  }
}
