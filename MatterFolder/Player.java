package MatterFolder;

public class Player extends Character{
    public Player(int locX, int locY){
        super(locX, locY);
    }

    //pr0ves
    //locX and locY are private so I made the method modLocX() and modLocY() in Matter.java
    public void moveLateral(int dist){
		modLocX(dist);
	}
	
	public void moveVertical(int dist){
		modLocY(dist);
	}
}
