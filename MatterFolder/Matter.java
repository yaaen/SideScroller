package MatterFolder;

import java.awt.*;

public abstract class Matter{
    
    private final int HEIGHT = 100;
    private final int WIDTH = 100;
    private int locX;
    private int locY;
    private Color matterColor;
    
    //this needs an import and we might have to
    //change it later to like a BufferedImage or
    //ImageIcon but i don't know yet
    private Image img;

    //i added some more constructers because we
    //don't know yet how we'll want to make some
    //of its child classes
    public Matter(){
        
    }

    public Matter(int locX, int locY){
        this.locX = locX;
        this.locY = locY;
    }
    
    public Matter(int locX, int locY, Image img){
        this.locX = locX;
        this.locY = locY;
        this.img = img;
    }
    
    	public Matter(int locX, int locY, Color c){
		this.locX = locX;
		this.locY = locY;
		this.matterColor = c;
	}

	public Color getColor() {
		return matterColor;
	}

	public void setColor(Color c) {
		matterColor = c;
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
