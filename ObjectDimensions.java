public class ObjectDimensions {
  
  public ObjectDimensions(){
    //might need to have the level loaded into this constructor
  }
  
  //should this be called by the player class so it uses it to know if it can move or not then?
  public boolean collisionCheck(Matter object1, Matter object2){
    object1XLocation = object1.getX();
    object1YLocation = object1.getY();
    
    object2XLocation = object2.getX();
    object2YLocation = object2.getY();
    
    if(/*check for collsion*/){
      
      //true means collision detected (player get damage, wins level, or cant move)
      
      return true;
    }
    
    //no collision found, default to false
    
    return false;
  }
}
