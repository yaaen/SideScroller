package MatterFolder;

import java.awt.Color;

public class Character extends Matter {
    
    boolean inAir;
    boolean inWater;

    public Character(int locX, int locY) {
        super(locX, locY);
    }

    public Character(int locX, int locY, Color c) {
        super(locX, locY, c);
    }

    public void enteredWater(boolean inWater){
        this.inWater = inWater;
    }

    public void enteredAir(boolean inAir){
        this.inAir = inAir;
    }

    public boolean isInAir(){
        return inAir;
    }

    public boolean isInWater(){
        return inWater;
    }
}
