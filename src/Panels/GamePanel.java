package Panels;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import MatterFolder.*;
import Tools.*;

public class GamePanel extends JPanel implements KeyListener {

    //you could make these private
    String[][] fileArray;
    Matter[][] matter;
    boolean isGameFinished = false;
    Player player;
    Block door;
    Timer timer = new Timer();
    private final int BLOCKSIZE = 50;
    int playerX;
    int playerY;
    Color playerColor;
    ObjectDimensions objDim = new ObjectDimensions(BLOCKSIZE);
    Camera c = new Camera(1600);
    public final int MOVESPEED = BLOCKSIZE / 10;
    public final int JUMPHEIGHT = 2 * BLOCKSIZE;
    // public final int OGGRAVITY = 5;
    public final int GRAVITY = 5;

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

        //fps Thread
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                applyGravity();

                //move the character left
                if(leftPressed){
                    if(playerCanMoveX(-MOVESPEED)){
                        if(c.isCameraLocked()){
                            player.moveLateral(-MOVESPEED);
                        } else{
                            matter = c.moveCharLeft(matter);
                        }
                    }
                }

                //move the character right
                if(rightPressed){
                    if(playerCanMoveX(MOVESPEED)){
                        if(c.isCameraLocked()){
                            player.moveLateral(MOVESPEED);
                        } else{
                            matter = c.moveCharRight(matter);
                        }
                    }
                }

                repaint();
            }
        }, 1, 50);
    }

    public void paint(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 470, 320);
        g.setColor(Color.RED);
        g.fillRect(100, 700, 200, 190);
        g.setColor(Color.GREEN);
        g.fillRect(1200, 100, 300, 700);

        g.setColor(Color.BLUE);
        g.fillRect(1050, 600, 270, 320);
        g.setColor(Color.RED);
        g.fillRect(700, 50, 290, 100);
        g.setColor(Color.GREEN);
        g.fillRect(250, 600, 300, 200);

        for(int i = 0; i < matter.length; i++){
            for(int j = 0; j < matter[0].length; j++){
                if(matter[i][j].getColor() == Color.CYAN){
                } else if(matter[i][j].isPlayer()){
                } else{
                    g.setColor(matter[i][j].getColor());
                    g.fillRect(matter[i][j].getX(), matter[i][j].getY(), BLOCKSIZE, BLOCKSIZE);
                }
            }
        }

        g.setColor(player.getColor());
        g.fillRect(player.getX(), player.getY(), BLOCKSIZE, BLOCKSIZE);

        requestFocus();
        checkForLevelFinished();
    }

    public void transFileToArray() {
        for(int i = 0; i < fileArray.length; i++){
            for(int j = 0; j < fileArray[0].length; j++){
                if(fileArray[i][j].equals("s")){//sky
                    matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.CYAN);
                } else if(fileArray[i][j].equals("g")){//ground
                    matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.DARK_GRAY);
                } else if(fileArray[i][j].equals("p")){ //player
                    playerX = j * BLOCKSIZE;
                    playerY = i * BLOCKSIZE;
                    playerColor = Color.MAGENTA;
                    player = new Player(playerX, playerY, playerColor);
                    matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, playerColor);
                    matter[i][j].setPlayer(true);
                } else if(fileArray[i][j].equals("d")){//door
                    door = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.RED);
                    matter[i][j] = door;
                    matter[i][j].setDoor(true);
                } else{//shouldn't get here (hopefully)
                    matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.BLACK);
                }
            }
        }
    }

    public void applyGravity() {
        if(playerCanMoveY(GRAVITY)){
            player.enteredAir(true);
            player.moveVertical(GRAVITY);
            //GRAVITY++;
        } else{
            //GRAVITY = OGGRAVITY;
            player.enteredAir(false);
        }
    }

    public boolean playerCanMoveX(int move) {
        for(int i = 0; i < matter.length; i++){
            for(int j = 0; j < matter[0].length; j++){
                if(matter[i][j].getColor() == Color.DARK_GRAY){
                    if(objDim.collisionCheck(new Player(player.getX() + move, player.getY()), matter[i][j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean playerCanMoveY(int move) {
        for(int i = 0; i < matter.length; i++){
            for(int j = 0; j < matter[0].length; j++){
                if(matter[i][j].getColor() == Color.DARK_GRAY){
                    if(objDim.collisionCheck(new Player(player.getX(), player.getY() + move), matter[i][j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean upPressed = false;
    boolean downPressed = false;
    boolean spacePressed = false;

    @Override
    public void keyPressed(KeyEvent ke) {
        Integer c = ke.getKeyCode();
        if((c.equals(KeyEvent.VK_LEFT) || c.equals(KeyEvent.VK_A)) && !leftPressed){
            //left arrow key
            leftPressed = true;
        }
        if((c.equals(KeyEvent.VK_RIGHT) || c.equals(KeyEvent.VK_D)) && !rightPressed){
            //right arrow key
            rightPressed = true;
        }
        if((c.equals(KeyEvent.VK_SPACE) || c.equals(KeyEvent.VK_UP) || c.equals(KeyEvent.VK_W)) && !spacePressed){
            //space bar
            //also jump
            spacePressed = true;
            if(playerCanMoveY(-JUMPHEIGHT) && (!player.isInAir() || player.canDoubleJump())){
                player.moveVertical(-JUMPHEIGHT);
                if(player.isInAir()){
                    player.setCanDoubleJump(false);
                } else{
                    player.setCanDoubleJump(true);
                }
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
            case KeyEvent.VK_UP:
                spacePressed = false;
                break;
            case KeyEvent.VK_W:
                spacePressed = false;
                break;
        };
    }

    public void checkForLevelFinished() {
        //if player and door have same location:
        //isGameFinished = true
        if(objDim.collisionCheck(player, door)){
            c.resetLevel();
            isGameFinished = true;
        }
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}
