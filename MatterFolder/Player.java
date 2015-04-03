package MatterFolder;

import java.awt.Color;

public class Player extends Character {

    public Player(int locX, int locY) {
        super(locX, locY);
    }
    
    public Player(int locX, int locY, Color c){
        super(locX, locY, c);
    }

    public void moveLateral(int dist) {
        modLocX(dist);
    }

    public void moveVertical(int dist) {
        modLocY(dist);
    }
}
