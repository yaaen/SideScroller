import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Integer;

import MatterFolder.*;

public class GamePanel extends JPanel implements KeyListener {

	private String[][] fileArray;
	private Matter[][] matter;
	private Player player;

	public final int MOVESPEED = 6
	public final int JUMPHEIGHT = 15
	public final int GRAVITY = 3;//gravity is for later may go somewhere else

	public GamePanel() {
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}

	public void setLevel(int level) {
		FileRead fr = new FileRead(level);
		fileArray = fr.getLevelArray();
		matter = new Matter[fileArray.length][fileArray[0].length];
		transFileToArray();
		//fileArray will be strings
		//s = no background(sky)
		//g = ground block
		//p = player
	}


	public void paint(Graphics g) {
		//Add collision detection, camera motion, and other movement calls here.
		//Or they can be put in another method that will separately update the figures, in another thread,
		//while this method is repeatedly called by itself with or without the changes from the other update method

		//also this method/class will have to interact with the camera so that it knows what part of the map to paint
		//this panel should probably use its own camera so there should be a global Camera c = new Camera() or something

		for (int i = 0; i < matter.length; i++) {
			for (int j = 0; j < matter[0].length; j++) {
				//we're gonna have to paint the square icon wherever and get its location,
				//which should all be in the class so I think it'd be something like:
				g.setColor(matter[i][j].getColor());
				g.fillRect(matter[i][j].getX(), matter[i][j].getY(), 100, 100);
			}
		}

		g.setColor(Color.MAGENTA);
		g.fillRect(player.getX(), player.getY(), 100, 100);

		requestFocus();
	}

	public void transFileToArray() {
		int playerX = 0; //only need this for player's original position
		int playerY = 0;
		for (int i = 0; i < fileArray.length; i++) {
			for (int j = 0; j < fileArray[0].length; j++) {
				if (fileArray[i][j].equals("s")) {//sky
					matter[i][j] = new Block(j * 100, i * 100, Color.CYAN); //multiply by 100 since each block is 100 x 100
				} else if (fileArray[i][j].equals("g")) {//ground
					matter[i][j] = new Block(j * 100, i * 100, Color.DARK_GRAY);
				} else if (fileArray[i][j].equals("p")) {//player
					playerX = j * 100;
					playerY = i * 100;
					matter[i][j] = new Block(j * 100, i * 100, Color.CYAN); //we'll have the player drawn separately
				} else {
					matter[i][j] = new Block(j * 100, i * 100, Color.BLACK);
				}
			}
		}
		player = new Player(playerX, playerY);
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		Integer c = ke.getKeyCode();
		if (c.equals(KeyEvent.VK_LEFT)) {
			//left arrow key
			player.moveLateral(-MOVESPEED);
		} else if (c.equals(KeyEvent.VK_RIGHT)) {
			//right arrow key
			player.moveLateral(MOVESPEED);
		} else if (c.equals(KeyEvent.VK_UP)) {
			//up arrow key
			player.moveVertical(JUMPHEIGHT);
		} else if (c.equals(KeyEvent.VK_SPACE)) {
			//space bar
			//also jump
			player.moveVertical(JUMPHEIGHT);
		}
		repaint();
		//pr0ves
		//you just need these buttons listened, even if you add other buttons the final else can be avoided
		//we're going to need a repaint() somewhere around here. I think it would work here
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		//we need something here to stop movement stuff
	}
}
