package MatterFolder;

import java.awt.*;

public class Block extends Matter {
	
	//It shouldn't need any of this color stuff because the Matter 
	//class has a variable for the Image that it will use
	Color bColor;
	
	public Block() {
		
	}
	
	public Block(int locX, int locY) {
		super(locX, locY);
	}
	
	public Block(int locX, int locY, Color c) {
		super(locX, locY);
		bColor = c;
	}
	
	public Color getColor() {
		return bColor;
	}
	
	public void setColor(Color c) {
		bColor = c;
	}
}
