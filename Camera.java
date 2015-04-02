
//This class works the camera for the game
//as the player moves around the camera follows it
//The technique is just smoothly moving everything to the opposite direction of the character's motion
import MatterFolder.*;

public class Camera{
    
    //all of the matter pieces currently in the frame
    //we should discuss if new pieces should be introduced in this class or the gamePanel class
    //Ethan: right now the gamePanel class have a 2d array for the blocks so we might just
    //  want to get this class to work with that one
    Block[][] field;
    
    //these save how much the player has moved
    int dx, dy;
    
    //door in frame which would lock the camera in place
    boolean lock = false;
    
    //derrreks: what is this boolean for?
    boolean lookRight;
    
    public Camera(){
        
    }
    
    public Camera(Block[][] field){
        this.field = field;
    }
    
    public void setField(Block[][] field){
        this.field = field;
    }
    
    public void move(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
        if(!lock && lookRight){
            for(int i = 0; i < field.length; i++){
            	for(int j = 0; j < field[0].length; j++){
                    field[i][j].setY(field[i][j].getY() - dy);
                    field[i][j].setX(field[i][j].getX() - dx);
            	}
            }
        }
    }
    
    //check to see if the door is within the screen
    public void checkForDoor(){
        //loop through the field, if any of them are the door
        //set lock to true, if not do nothing
        //i'll fill in code later
    }
    
}
