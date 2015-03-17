package MatterFolder;

import java.awt.*;

public class Block extends Matter {
	
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
