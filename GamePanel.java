import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Integer;
import java.util.Timer;
import java.util.TimerTask;

import MatterFolder.*;

public class GamePanel extends JPanel implements KeyListener {

	//you could make these private
	String[][] fileArray;
	Matter[][] matter;
	boolean isGameFinished = false;
	Player player;
	Timer timer = new Timer();
	private final int BLOCKSIZE = 50;
	ObjectDimensions objDim = new ObjectDimensions(BLOCKSIZE);

	public final int MOVESPEED = BLOCKSIZE / 10;
	public final int JUMPHEIGHT = 2 * BLOCKSIZE;
	public final int GRAVITY = 5;//gravity is for later may go somewhere else

	public GamePanel() {
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}

	public void setLevel(int level) {
		isGameFinished = false;
		FileRead fr = new FileRead();
		fr.setLevelArray(level);
		fileArray = fr.getLevelArray();
		matter = new Matter[fileArray.length][fileArray[0].length];
		transFileToArray();
		//fileArray will be strings
		//s = no background(sky)
		//g = ground block
		//p = player
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				applyGravity();
				repaint();
			}
		}, 1, 50);
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
				g.fillRect(matter[i][j].getX(), matter[i][j].getY(), BLOCKSIZE, BLOCKSIZE);
			}
		}

		g.setColor(Color.MAGENTA);
		g.fillRect(player.getX(), player.getY(), BLOCKSIZE, BLOCKSIZE);

		requestFocus();
	}

	public void transFileToArray() {
		int playerX = 0; //only need this for player's original position
		int playerY = 0;
		for (int i = 0; i < fileArray.length; i++) {
			for (int j = 0; j < fileArray[0].length; j++) {
				/*
				* this can be done a lot better; earlier on we can set each block's color and then
				* just loop through and get its color
				* I can work on that in the coming days
				*/
				if (fileArray[i][j].equals("s")) {//sky
					matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.CYAN); //multiply by 100 since each block is 100 x 100
				} else if (fileArray[i][j].equals("g")) {//ground
					matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.DARK_GRAY);
				} else if (fileArray[i][j].equals("p")) {//player
					playerX = j * BLOCKSIZE;
					playerY = i * BLOCKSIZE;
					matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.CYAN); //we'll have the player drawn separately
				} else {
					matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.BLACK);
				}
			}
		}
		player = new Player(playerX, playerY);
	}

	public void applyGravity() {
		if (playerCanMoveY(GRAVITY)) {
			player.moveVertical(GRAVITY);
		}
	}

	public boolean playerCanMoveX(int move) {
		for (int i = 0; i < matter.length; i++) {
			for (int j = 0; j < matter[0].length; j++) {
				if (matter[i][j].getColor() == Color.DARK_GRAY) {
					if (objDim.collisionCheck(new Player(player.getX() + move, player.getY()), matter[i][j])) {
						//System.out.println("px:" + player.getX() + "  py:" + player.getY() + "    mx:" + matter[i][j].getX() + "  my:" + matter[i][j].getY());
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean playerCanMoveY(int move) {
		for (int i = 0; i < matter.length; i++) {
			for (int j = 0; j < matter[0].length; j++) {
				if (matter[i][j].getColor() == Color.DARK_GRAY) {
					if (objDim.collisionCheck(new Player(player.getX(), player.getY() + move), matter[i][j])) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/*
	we do need to figure out how to handle holding down
	multiple keys though as well
	*/

	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		Integer c = ke.getKeyCode();
		if (c.equals(KeyEvent.VK_LEFT)) {
			//left arrow key
			if (playerCanMoveX(-MOVESPEED)) {
				System.out.println("yoo");
				player.moveLateral(-MOVESPEED);
			}
		} else if (c.equals(KeyEvent.VK_RIGHT)) {
			//right arrow key
			if (playerCanMoveX(MOVESPEED)) {
				player.moveLateral(MOVESPEED);
			}
		} else if (c.equals(KeyEvent.VK_UP)) {
			//up arrow key
			if (playerCanMoveX(-JUMPHEIGHT)) {
				player.moveVertical(-JUMPHEIGHT);
			}
		} else if (c.equals(KeyEvent.VK_SPACE)) {
			//space bar
			//also jump
			if (playerCanMoveY(-JUMPHEIGHT)) {
				player.moveVertical(-JUMPHEIGHT);
			}
		}
		repaint();
		//pr0ves
		//you just need these buttons listened, even if you add other buttons the final else can be avoided
		//we're going to need a repaint() somewhere around here. I think it would work here
	}

	@Override
	public void keyReleased(KeyEvent ke) {
	}
	
	public boolean isGameFinished(){
		return isGameFinished;
	}
}
