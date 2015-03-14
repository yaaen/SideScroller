import MatterFolder.Matter;

public class ObjectDimensions {
  
  public ObjectDimensions(){
    //might need to have the level loaded into this constructor
    //or we could just add another method to have that information passed so it can be updated each level
  }
  
  //should this be called by the player class so it uses it to know if it can move or not then?
  public boolean collisionCheck(Matter object1, Matter object2){
    int object1XLocation = object1.getX();
    int object1YLocation = object1.getY();
    
    int object2XLocation = object2.getX();
    int object2YLocation = object2.getY();
    
    if(true){//TODO LOGIC 
      
      //true means collision detected (player get damage, wins level, or cant move)
      
      return true;
    }
    //no collision found, default to false
    
    return false;
  }
}
