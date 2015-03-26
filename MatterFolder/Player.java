package MatterFolder;

public class Player extends Character{
    public Player(int locX, int locY){
        super(locX, locY);
    }

    public void moveLateral(int dist){
		modLocX(dist);
	}
	
	public void moveVertical(int dist){
		modLocY(dist);
	}
}
