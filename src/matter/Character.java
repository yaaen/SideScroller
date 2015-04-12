package matter;

import java.awt.Color;

public class Character extends Matter {

    private boolean inAir;
    private boolean inWater;
    private boolean canDoubleJump;

    public Character(int locX, int locY) {
        super(locX, locY);
    }

    public Character(int locX, int locY, Color c) {
        super(locX, locY, c);
    }

    public void enteredWater(boolean inWater) {
        this.inWater = inWater;
    }

    public void enteredAir(boolean inAir) {
        this.inAir = inAir;
    }
    
    public void setCanDoubleJump(boolean canDoubleJump){
        this.canDoubleJump = canDoubleJump;
    }

    public boolean isInAir() {
        return inAir;
    }

    public boolean isInWater() {
        return inWater;
    }
    
    public boolean canDoubleJump(){
        return canDoubleJump;
    }
}
