package Tools;

import MatterFolder.Matter;

public class ObjectDimensions {

	private int blockSize;

	public ObjectDimensions(int size) {
		//might need to have the level loaded into this constructor
		//or we could just add another method to have that information passed so it can be updated each level
		//  we might not need it if we just use the get location stuff in collisionCheck()
		blockSize = size;
	}

	//should this be called by the player class so it uses it to know if it can move or not then?
	public boolean collisionCheck(Matter object1, Matter object2) {
		int object1XLocation = object1.getX();
		int object1YLocation = object1.getY();

		int object2XLocation = object2.getX();
		int object2YLocation = object2.getY();

		//System.out.println("ob1x:" + object1XLocation + "  ob1y:" + object1YLocation + "   ob2x:" + object2XLocation + "  ob2y:" + object2YLocation);

		//no collision found, default to false
		//true means collision detected (player get damage, wins level, or cant move)
		return ((object1XLocation <= (object2XLocation - blockSize) || object1XLocation >= object2XLocation + blockSize)
				|| (object1YLocation <= (object2YLocation - blockSize) || object1YLocation >= object2YLocation + blockSize)) ? false : true;
	}
}
