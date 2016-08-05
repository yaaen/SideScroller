package matter;

public class Enemy extends Character{
  
  private boolean killsPlayer;
  
  public Enemy(int locX, int locY){
    super(locX, locY);
  }
  
  private void setKillsPlayer(boolean killsPlayer){
    this.killsPlayer = killsPlayer;
  }
  
  private boolean killsPlayer(){
    return killsPlayer;
  }
}
