package MatterFolder;

import java.awt.*;

public abstract class Matter{
    
    private final int HEIGHT = 100;
    private final int WIDTH = 100;
    private int locX;
    private int locY;
    
    //this needs an import
    private Image img;

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
    
    public void setImage(Image img){
        this.img = img;
    }
    
    public int getX(){
        return locX;
    }
    
    public int getY(){
        return locY;   
    }
    
    public Image getImage(){
        return img; //this is for now
    }
}
