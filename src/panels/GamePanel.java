package panels;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

import matter.*;
import settings.Settings;
import tools.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GamePanel extends MasterPanel implements KeyListener {

    //you could make these private
    String[][] fileArray;
    Matter[][] matter;
    Player player;
    Block door;
    int playerX;
    int playerY;
    Color playerColor;
    int level;
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean spacePressed = false;
    boolean isGameFinished = false;
    public final int BLOCKSIZE = Settings.getBlockSize();
    public final int MOVESPEED = Settings.getMovementSpeed();
    public final int JUMPHEIGHT = (int) (2.5 * BLOCKSIZE);
    public final int GRAVITY = Settings.getGravity();
    JButton exitButton = new JButton();
    JPanel beatLevelPanel = new JPanel();
    JButton beatLevelButton = new JButton();
    JPanel lostLevelPanel = new JPanel();
    JButton lostLevelButton = new JButton();
    JLabel time = new JLabel();
    ObjectDimensions objDim = new ObjectDimensions(BLOCKSIZE);
    Camera c = new Camera();
    Timer timer = new Timer();

    public GamePanel() {
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        setLayout(null);

        exitButton.setSize(75, 75);
        exitButton.setLocation(35, 35);
        exitButton.setText("EXIT");
        exitButton.setBackground(Settings.getExitButtonColor());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.resetLevel();
                leftPressed = false;
                rightPressed = false;
                spacePressed = false;
                isGameFinished = true;
            }
        });
        add(exitButton);

        beatLevelPanel.setSize(300, 200);
        beatLevelPanel.setLocation(400, 400);
        beatLevelPanel.setLayout(new GridLayout(1, 1));
        beatLevelButton.setText("<html><center>" + "You beat the level!" + "<br>" + "Click here to continue." + "</center></html>");
        beatLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGameFinished = true;
            }
        });
        beatLevelButton.setBackground(Color.MAGENTA);
        beatLevelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        beatLevelPanel.add(beatLevelButton);
        
        lostLevelPanel.setSize(300, 200);
        lostLevelPanel.setLocation(400, 400);
        lostLevelPanel.setLayout(new GridLayout(1, 1));
        lostLevelButton.setText("<html><center>" + "You lost the level." + "<br>" + "Click here to continue." + "</center></html>");
        lostLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGameFinished = true;
            }
        });
        lostLevelButton.setBackground(Color.MAGENTA);
        lostLevelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        lostLevelPanel.add(lostLevelButton);
        
        //initialise the time label and everything
        //put the label in the top right corner
    }

    public void setLevel(int level) {
        this.remove(beatLevelPanel);
        this.level = level;
        isGameFinished = false;
        FileRead fr = new FileRead();
        fr.setLevelArray(level);
        fileArray = fr.getLevelArray();
        matter = new Matter[fileArray.length][fileArray[0].length];
        transFileToArray();

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

                //update time on screen
                //set it to current time - time at the beggining
                
                repaint();
            }
        }, 1, 50);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i < matter.length; i++){
            for(int j = 0; j < matter[0].length; j++){
                if(matter[i][j].getColor() == Color.CYAN){
                    //don't paint the sky, background will take care of it
                } else if(matter[i][j].isPlayer()){
                    //don't paint player, that's taken care of below
                } else{
                    if(matter[i][j].shouldPaint()){
                        g.setColor(matter[i][j].getColor());
                        g.fillRect(matter[i][j].getX(), matter[i][j].getY(), BLOCKSIZE, BLOCKSIZE);
                    }
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
                    matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.BLACK);
                } else if(fileArray[i][j].equals("p")){ //player
                    playerX = j * BLOCKSIZE;
                    playerY = i * BLOCKSIZE;
                    playerColor = Settings.getPlayerColor();
                    player = new Player(playerX, playerY, playerColor);
                    matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, playerColor);
                    matter[i][j].setPlayer(true);
                } else if(fileArray[i][j].equals("d")){//door
                    door = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.RED);
                    matter[i][j] = door;
                    matter[i][j].setDoor(true);
                } else{//shouldn't get here (hopefully)
                    matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, Color.BLACK);
                    matter[i][j].setShouldPaint(false);
                }
            }
        }
    }

    public void applyGravity() {
        if(playerCanMoveY(GRAVITY)){
            player.enteredAir(true);
            player.moveVertical(GRAVITY);
        } else{
            player.enteredAir(false);
        }
    }

    public boolean playerCanMoveX(int move) {
        for(int i = 0; i < matter.length; i++){
            for(int j = 0; j < matter[0].length; j++){
                if(matter[i][j].getColor() == Color.BLACK){
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
                if(matter[i][j].getColor() == Color.BLACK){
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
                    player.moveVertical(GRAVITY);
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
        }
    }

    public void checkForLevelFinished() {
        if(objDim.collisionCheck(player, door)){
            c.resetLevel();
            leftPressed = false;
            rightPressed = false;
            spacePressed = false;
            Settings.beatLevel(level);
            this.add(beatLevelPanel);
            beatLevelPanel.repaint();
            beatLevelPanel.revalidate();
            //for some reason this panel isn't properly showing anything
        }
        //here we need to check to see if they hit a bad black
        /**
         * if(objDim.collisionCheck(player, SOMEBADBLOCK){
             c.resetLevel();
            leftPressed = false;
            rightPressed = false;
            spacePressed = false;
            this.add(lostLevelPanel);
            lostLevelPanel.repaint();
            lostLevelPanel.revalidate();
         }
         */
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}
