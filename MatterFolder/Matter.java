package MatterFolder;

import java.awt.*;

public abstract class Matter{
    
    private final int HEIGHT = 100;
    private final int WIDTH = 100;
    private int locX;
    private int locY;

    public Matter(int locX, int locY){
        this.locX = locX;
        this.locY = locY;
    }
    
    public void setX(int locX){
        this.locX = locX;
    }
    
    public void setY(int locY){
        this.locY = locY;
    }
    
    public int getX(){
        return locX;
    }
    
    public int getY(){
        return locY;   
    }
    
    public Image getImage(){
        return null; //this is for now
    }
}
