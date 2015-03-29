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
    
    //I added some of this stuff to make sure errors don't get thrown
    public Matter(){
        this.locX = 0;
        this.locY = 0;
        this.matterColor = Color.WHITE;//default color?
    }

    public Matter(int locX, int locY){
        this.locX = locX;
        this.locY = locY;
        this.matterColor = Color.WHITE;//default color?
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
`   }

	/**
	 * Returns the color that is painted to represent this block
	 * 
	 * @return	the color that this block is represented with on the screen
	 */
	public Color getColor() {
		return matterColor;
	}

	/**
	 * Takes in a Color which is set to be the color that gets painted to
	 * represent this block
	 * 
	 * @param c	the color for this block to be represented with
	 */
	public void setColor(Color c) {
		matterColor = c;
	}
    
    /**
     * This method sets the location of the upper corner of this block
     * in the x direction
     * 
     * @param locX sets location in the xdirection of this block
     */
    public void setX(int locX){
        this.locX = locX;
    }
    
    /**
     * This method sets the location of the upper corner of this block
     * in the y direction
     * 
     * @param locY sets location in the ydirection of this block
     */
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

	/**
	 * Adds the specified value to the x location of 
	 * this block; if a negative value is passed in,
	 * the block will move left
	 * 
	 * @param dx adds this value to the current locX
	 */
    public void modLocX(int dx){
    	locX += dx;
    }

	/**
	 * Adds the specified value to the y location of 
	 * this block; if a negative value is passed in,
	 * the block will move up
	 * 
	 * @param dy adds this value to the current locY
	 */
    public void modLocY(int dy){
	    locY += dy;
    }

}
