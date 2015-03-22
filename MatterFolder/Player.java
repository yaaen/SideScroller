package MatterFolder;

public class Player extends Character{
    public Player(int locX, int locY){
        super(locX, locY);
    }

    //pr0ves
    //locX and locY are private so I made the method modLocX() and modLocY() in Matter.java
    public void moveRight(){
        modLocX(1,true);
    }
    
    public void moveRight(int toMove){
        modLocX(toMove,true);;
    }
    
    public void moveLeft(){
        modLocX(1,false);;
    }
    
    public void moveLeft(int toMove){
        modLocX(toMove,false);;
    }
    
    public void moveUp(){
        modLocY(1,true);
    }
    
    public void moveUp(int toMove){
        modLocY(toMove,true);
    }
    
    public void moveDown(){
        modLocY(1,false);
    }
    
    public void moveDown(int toMove){
        modLocY(toMove,false);
    }
}
