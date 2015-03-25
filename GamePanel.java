import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Integer;
import java.util.concurrent.TimeUnit;

import MatterFolder.*;

public class GamePanel extends JPanel implements KeyListener {

	private String[][] fileArray;
	private Matter[][] matter;
	private Player player;

    //Aren't these constants too big for movement?
    //if the player jumps it will instantaneously teleported 15 blocks above his original position
	public final int MOVESPEED = 6;
	public final int JUMPHEIGHT = 15;
	public final int GRAVITY = 3;//gravity is for later may go somewhere else

    //while this is true you could half the lateral speed
    //needs to be set false when the player touches the ground
    private boolean jumping = false;

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
		for (int i = 0; i < fileArray.length; i++) {
			for (int j = 0; j < fileArray[0].length; j++) {
				if (fileArray[i][j].equals("s")) {//sky
					matter[i][j] = new Block(j * 100, i * 100, Color.CYAN); //multiply by 100 since each block is 100 x 100
				} else if (fileArray[i][j].equals("g")) {//ground
					matter[i][j] = new Block(j * 100, i * 100, Color.DARK_GRAY);
				} else if (fileArray[i][j].equals("p")) {//player
					int playerX = j * 100;
					int playerY = i * 100;
					player = new Player(playerX, playerY);
					matter[i][j] = new Block(j * 100, i * 100, Color.CYAN); //we'll have the player drawn separately
				} else {
					matter[i][j] = new Block(j * 100, i * 100, Color.BLACK);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		Integer c = ke.getKeyCode();
        int modifier = 0;
        if (jumping)
        {
            modifier = MOVESPEED/2;
        }
		if (c.equals(KeyEvent.VK_LEFT)) {
			//left arrow key
			player.moveLateral(-(MOVESPEED-modifier));
		} else if (c.equals(KeyEvent.VK_RIGHT)) {
			//right arrow key
			player.moveLateral(MOVESPEED-modifier);
		} else if (c.equals(KeyEvent.VK_UP)) {
			//up arrow key
            if (!jumping) {
                player.moveVertical(JUMPHEIGHT);
                jumping=true;
            }
        } else if (c.equals(KeyEvent.VK_SPACE)) {
			//space bar
			//also jump
            if (!jumping) {
                player.moveVertical(JUMPHEIGHT);
                jumping=false;
            }
        }
        //we're going to need a repaint() somewhere around here. I think it would work here
        repaint();
        //the KeyEvent triggers too fast after this method finishes executing the code, so it needs to wait some time
        //i put 50 milliseconds but it needs to be tested
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		//we need something here to stop movement stuff
        //you dont need to stop the player when it's moving laterally
        //anyway a method to make the player fall when he's jumping is needed
        //can't put that here because it needs to fall even though the jump button is pressed
	}
}
