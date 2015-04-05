package MatterFolder;

import java.awt.*;

public abstract class Matter {

    private final int HEIGHT = 100;
    private final int WIDTH = 100;
    private int locX;
    private int locY;
    private Color matterColor;
    private boolean isDoor = false;
    
    //we will most likely change this later
    //to something like a BufferedImage or
    //ImageIcon but i don't know yet which
    //will work the best for this
    private Image img;

    public Matter() {
        this.locX = 0;
        this.locY = 0;
        this.matterColor = Color.WHITE;//default color?
    }

    public Matter(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
        this.matterColor = Color.WHITE;//default color?
    }

    public Matter(int locX, int locY, Image img) {
        this.locX = locX;
        this.locY = locY;
        this.img = img;
    }

    public Matter(int locX, int locY, Color c) {
        this.locX = locX;
        this.locY = locY;
        this.matterColor = c;
    }

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
    public void setX(int locX) {
        this.locX = locX;
    }

    /**
     * This method sets the location of the upper corner of this block
     * in the y direction
     * 
     * @param locY sets location in the ydirection of this block
     */
    public void setY(int locY) {
        this.locY = locY;
    }

    //don't know if we'll use this at least not yet
    public void setImage(Image img) {
        this.img = img;
    }

    /**
     * This method returns the x value of the upper left corner of the block
     * 
     * @return	the location of the the upper left corner of the block in the x direection
     */
    public int getX() {
        return locX;
    }

    /**
     * This method returns the y value of the upper left corner of the block
     * 
     * @return	the location of the the upper left corner of the block in the y direection
     */
    public int getY() {
        return locY;
    }

    public Image getImage() {
        return img; //this is for now
    }

    /**
     * Adds the specified value to the x location of 
     * this block; if a negative value is passed in,
     * the block will move left
     * 
     * @param dx adds this value to the current locX
     */
    public void modLocX(int dx) {
        locX += dx;
    }

    /**
     * Adds the specified value to the y location of 
     * this block; if a negative value is passed in,
     * the block will move up
     * 
     * @param dy adds this value to the current locY
     */
    public void modLocY(int dy) {
        locY += dy;
    }
    
    public void setDoor(boolean isDoor){
        this.isDoor = isDoor;
    }
    
    public boolean isDoor(){
        return isDoor;
    }
}
