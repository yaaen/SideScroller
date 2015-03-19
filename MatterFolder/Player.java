package MatterFolder;

public class Player extends Character{
    public Player(int locX, int locY){
        super(locX, locY);
    }
    
    public void moveRight(){
        locX++;    
    }
    
    public void moveRight(int toMove){
        locX += toMove;
    }
    
    public void moveLeft(){
        locX--;
    }
    
    public void moveLeft(int toMove){
        locX -= toMove;
    }
    
    public void moveUp(){
        locY++;
    }
    
    public void moveUp(int toMove){
        locY += toMove;
    }
    
    public void moveDown(){
        locY--;
    }
    
    public void moveDown(int toMove){
        locY -= toMove;
    }
}
