package tools;

import matter.Matter;
import settings.Settings;

public class ObjectDimensions {

	private int blockSize;

	public ObjectDimensions() {
		blockSize = Settings.getBlockSize();
	}

	//should this be called by the player class so it uses it to know if it can move or not then?
	public boolean collisionCheck(Matter object1, Matter object2) {
		int object1XLocation = object1.getX();
		int object1YLocation = object1.getY();

		int object2XLocation = object2.getX();
		int object2YLocation = object2.getY();
		
		//no collision found, default to false
		//true means collision detected (player get damage, wins level, or cant move)
		return ((object1XLocation <= (object2XLocation - blockSize) || object1XLocation >= object2XLocation + blockSize)
				|| (object1YLocation <= (object2YLocation - blockSize) || object1YLocation >= object2YLocation + blockSize)) ? false : true;
	}
}
