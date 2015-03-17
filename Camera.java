
//This class works the camera for the game
//as the player moves around the camera follows it
//The technique is just smoothly moving everything to the opposite direction of the character's motion
import MatterFolder.*;

public class Camera{
    /*enum for the different directions that the camera can move
    it is tricky because the camera movement is relative to block movement.
    For example, to move the camera upwards, all of the blocks need to be moved downward
    */
    //also if you want to get even more specific we could add directions like:
    //northnorthwest or something but that might be too much
    public enum Direction {
        NORTH, NORTHEAST, NORTHWEST,
        SOUTH, SOUTHEAST, SOUTHWEST,
        EAST, WEST;
    }
    
    //all of the matter pieces currently in the frame
    //we should discuss if new pieces should be introduced in this class or the gamePanel class
    //Ethan: right now the gamePanel class have a 2d array for the blocks so we might just
    //  want to get this class to work with that one
    Block[][] field;
    
    Player player;
    
    //these save how much the player has moved
    int dx, dy;
    
    //door in frame which would lock the camera in place
    boolean lock = false;
    
    public Camera(Block[][] field, Player player){
        this.field = field;
        this.player = player;
    }
    
    public void move(){
        if(!lock){
            for(int i = 0; i < field.length; i++){
            	for(int j = 0; j < field[0].length; j++){
                    field[i][j].setY(field[i][j].getY() - dy);
                    field[i][j].setX(field[i][j].getX() - dx);
            	}
            }
        }
    }
}
