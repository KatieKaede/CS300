import java.util.Random;

public class SpaceGameMain {

    //for random numbers
    public static Random rng = new Random();

    public static void main(String[] args) {
        //create game
        SpaceGame theGame=new SpaceGame(800,800);

        //add UFOs
        theGame.addUFO(UFO.SIMPLE_SAUCER);
        theGame.addUFO(UFO.SIMPLE_SAUCER);
        theGame.addUFO(UFO.SIMPLE_SAUCER);

        //run game
        theGame.start();
    }
}