/**
 * A class representing a single UFO object that can move, fire, and be hit.
 * 
 */
public class UFO {

	public static final int SIMPLE_SAUCER = 0;

	private int xPosition; //current x position of UFO's center
	private int yPosition; //current y position of UFO's center
	private int UFOType;   //type of UFO
	private boolean movingRight;
	private static final int SHOOT_DELAY = 70;
	private int ticksSinceLastShot = 0;
	private int hits;

	/**
	 * Constructs an UFO object given its type and initial position
	 * 
	 * @param startX initial x coordinate
	 * @param startY initial y coordinate
	 * @param UFOType int representing the type of UFO this is.
	 */
	public UFO(int startX, int startY, int UFOType) {
		this.xPosition = startX;
		this.yPosition = startY;
		this.UFOType = UFOType;
	}

	/**
	 * Get the current x coordinate of the center of this UFO.
	 * 
	 * @return The current x coordinate of the center of this UFO.
	 */
	public int getXPosition() {
		return this.xPosition;
	}

	/**
	 * Get the current y coordinate of the center of this UFO.
	 * 
	 * @return The current y coordinate of the center of this UFO.
	 */
	public int getYPosition() {
		return this.yPosition;
	}

	/**
	 * Get an int represented the type for this UFO
	 * 
	 * @return The int representing the type of this UFO
	 */
	public int getUFOType() {
		return this.UFOType;
	}

	/**
	 * Updates the position of the UFO for the next time it is redrawn.
	 * 
	 * @param defender The Defender object. Ignore for now, but it may
	 * be used later in the lab to determine movement some UFO types.
	 */
	public void takeOneStep(Defender defender) {
		if (movingRight) {
			xPosition += 3; // move right
			} else {
			xPosition -= 3; // move left
			}
		
		// check for reaching the edge of the screen
		if (xPosition <= SpaceGame.UFO_SIZE / 2) {
		    movingRight = true;  // start moving right
		} else if (xPosition >= SpaceGame.getMaximumX() - SpaceGame.UFO_SIZE / 2) {
		    movingRight = false;  // start moving left
		}

	}

	/**
	 * Return true if this UFO fires this during this tick.
	 * 
	 * @param defender The Defender object. Ignore for now, but it may
	 * be used later in the lab to determine firing for some UFO types.
	 * @return Whether or not this UFO shoots on this tick.
	 */
	public boolean shootsThisTurn(Defender defender) {
		ticksSinceLastShot++;
		
	    if (ticksSinceLastShot >= SHOOT_DELAY) {
	        ticksSinceLastShot = 0;
	        return true;
	        
	    } else {
	        return false;
	    }
	}

	/**
	 * Return the bullet this UFO is about to fire.
	 * 
	 * @param defender The Defender object. Ignore for now, but it may
	 * be used later in the lab to determine firing for some UFO types.
	 * @return The bullet this UFO is about to fire.
	 */
	public Laser fireWeapon(Defender defender) {
		int laserXPosition = getXPosition();
	    int laserYPosition = getYPosition();
	    int laserXVelocity = 0;
	    int laserYVelocity = 4;
	    return new Laser(laserXPosition, laserYPosition, laserXVelocity, laserYVelocity);
	}

	/**
	 * Returns whether this UFO intersects this Laser
	 * 
	 * @param theLaser a laser that may be near the UFO.
	 * @return true if this UFO intersects this Laser
	 */
	public boolean isHitByLaser(Laser theLaser) {
		int laserX = theLaser.getXPosition();
		int laserY = theLaser.getYPosition();
		int distance = (int) Math.sqrt(Math.pow(laserX - xPosition, 2) + Math.pow(laserY - yPosition, 2));
		return distance < SpaceGame.UFO_SIZE / 2;
		
	}

	/**
	 * Updates the Object to record that it has been hit by a Laser. This
	 * method is called every time the UFO is struck by a Laser.
	 */
	public void recordHit() {
		hits++;
	}
	
	/**
	 * Returns true if this UFO has been destroyed.
	 * 
	 * @return true if the UFO has been destroyed.
	 */
	public boolean removeMeFromGame() {
		return hits >= 2;
	}

}
