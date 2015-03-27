public interface VelocityAndForces{
    // this can probably be deleted right? Now that we have gravity in GamePanel()
    public static final int GRAVITY = 3;
    
    public void setSpeed(int v);
    public int getSpeed();
    
    //probably have 1 = right, 2 = up right, etc. going around with a total of 8 or more. Using angles is a possibility as well
    public void setDirection(int d);
    public int getDirection();
    
    public void setPosition(int x, int y);
    //don't knwo how we will want to got about getting position yet
    
    public void setDrag(int d);
    public int getDrag(); //might not need this
}
