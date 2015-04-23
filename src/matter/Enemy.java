package matter;

public class Enemy extends Character{
  
  private boolean kills;
  
  public Enemy(int locX, int locY){
    super(locX, locY);
  }
  
  private void setKills(boolean kills){
    this.kills = kills;
  }
  
  private boolean killsPlayer(){
    return kills;
  }
}
