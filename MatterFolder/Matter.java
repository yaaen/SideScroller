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


    //pr0ves
    //made these methods to modify locX and locY since they are private

    public void modLocX(int dx, boolean direction){
        if (dx>0){
            if (direction){
                this.locX += dx;
            } else {
                this.locX -= dx;
            }
        }

    }

    public void modLocY(int dy, boolean direction){

        if (dy>0){
            if (direction){
                this.locY += dy;
            } else{
                this.locY -= dy;
            }
        }

    }

}
