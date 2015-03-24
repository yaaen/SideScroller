package MatterFolder;

public class Player extends Character{
    public Player(int locX, int locY){
        super(locX, locY);
    }

    //pr0ves
    //locX and locY are private so I made the method modLocX() and modLocY() in Matter.java
    public void moveRight(){
        modLocX(1);
    }
    
    public void moveRight(int toMove){
        modLocX(toMove);
    }
    
    public void moveLeft(){
        modLocX(1);
    }
    
    public void moveLeft(int toMove){
        modLocX(toMove);
    }
    
    public void moveUp(){
        modLocY(1);
    }
    
    public void moveUp(int toMove){
        modLocY(toMove);
    }
    
    public void moveDown(){
        modLocY(1);
    }
    
    public void moveDown(int toMove){
        modLocY(toMove);
    }
}
