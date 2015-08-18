package panels;

import matter.*;
import settings.Settings;
import tools.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GamePanel extends MasterPanel implements KeyListener {

    private String[][] fileArray;
    private Matter[][] matter;
    private Player player;
    private Block door;
    private int playerX;
    private int playerY;
    private Color playerColor;
    private int level;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    private boolean isGameFinished = false;
    private boolean isGameLost = false;
    private final int BLOCKSIZE = Settings.getBlockSize();
    private final int MOVESPEED = Settings.getMovementSpeed();
    private final int JUMPHEIGHT = (int) (2.5 * BLOCKSIZE);
    private final int GRAVITY = Settings.getGravity();
    private long startTime;
    private long endTime;
    private long totalTime;
    private long minTime;
    private long secTime;
    private boolean runTimer = true;
    private String toDisplay;
    private JLabel time = new JLabel();
    private JButton exitButton = new JButton();
    private JPanel beatLevelPanel = new JPanel();
    private JButton beatLevelButton = new JButton();
    private JPanel lostLevelPanel = new JPanel();
    private JButton lostLevelButton = new JButton();
    private ObjectDimensions objDim = new ObjectDimensions();
    private Camera c = new Camera();
    private Timer timer = new Timer();

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
        beatLevelPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                Integer key = ke.getKeyCode();
                if(key.equals(KeyEvent.VK_ENTER)){
                    isGameFinished = true;
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
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
        lostLevelPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                Integer key = ke.getKeyCode();
                if(key.equals(KeyEvent.VK_ENTER)){
                    isGameFinished = true;
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
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

        time.setSize(100, 100);
        time.setLocation(1400, 25);
        time.setFont(new Font("Arial", Font.PLAIN, 40));
        time.setForeground(Settings.getExitButtonColor());
        this.add(time);
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

        time.setText("");
        startTime = System.currentTimeMillis();
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

                //move character up
                if(spacePressed){
                    if(playerCanMoveY(-JUMPHEIGHT) && (!player.isInAir() || player.canDoubleJump())){
                        player.moveVertical(-JUMPHEIGHT);
                        if(player.isInAir() && player.canDoubleJump()){
                            player.setCanDoubleJump(false);
                            player.moveVertical(GRAVITY);
                        } else{
                            player.setCanDoubleJump(true);
                            player.moveVertical(GRAVITY);
                        }
                    } else if(playerCanMoveY(-BLOCKSIZE) && (!player.isInAir() || player.canDoubleJump())){
                        player.moveVertical(-BLOCKSIZE);
                        if(player.isInAir() && player.canDoubleJump()){
                            player.setCanDoubleJump(false);
                            player.moveVertical(GRAVITY);
                        } else{
                            player.setCanDoubleJump(true);
                            player.moveVertical(GRAVITY);
                        }
                    }
                    spacePressed = false;
                }

                if(runTimer){
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime;
                    minTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
                    secTime = TimeUnit.MILLISECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime));
                    toDisplay = "" + minTime + ":" + secTime;
                    time.setText(toDisplay);
                }

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
                Color blockColor;
                switch(fileArray[i][j]) {
                    case "s":
                        blockColor = Color.CYAN;
                        break;
                    case "g":
                        blockColor = Color.BLACK;
                        break;
                    case "p":
                        playerX = j * BLOCKSIZE;
                        playerY = i * BLOCKSIZE;
                        blockColor = Settings.getPlayerColor();
                        player = new Player(playerX, playerY, blockColor);
                        break;
                    case "d":
                        blockColor = Color.RED;
                        break;
                    default:
                        blockColor = Color.BLACK;
                        break;
                }
                matter[i][j] = new Block(j * BLOCKSIZE, i * BLOCKSIZE, blockColor);
                if(fileArray[i][j].equals("p")){
                    matter[i][j].setPlayer(true);
                } else if(fileArray[i][j].equals("d")){
                    matter[i][j].setDoor(true);
                }
            }
        }
    }

    public void applyGravity() {
        if(!playerCanMoveY(0)){
            player.enteredAir(false);
        } else{
            if(playerCanMoveY(GRAVITY)){
                player.enteredAir(true);
                player.moveVertical(GRAVITY);
            } else{
                player.enteredAir(false);
                //makes sure the player is on the ground
                while(true){
                    if(playerCanMoveY(1)){
                        player.moveVertical(1);
                    } else{
                        break;
                    }
                }
            }
        }
    }

    public boolean playerCanMoveX(int move) {
        for(int i = 0; i < matter.length; i++){
            for(int j = 0; j < matter[0].length; j++){
                if(matter[i][j].getColor() == Color.BLACK){
                    if(objDim.collisionCheck(new Player(player.getX() + move, player.getY()), matter[i][j])){
                        return false;
                    }
                } else if(matter[i][j].isEnemy()){
                    if(objDim.collisionCheck(new Player(player.getX() + 1, player.getY()), matter[i][j])){
                        isGameLost = true;
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
        Integer key = ke.getKeyCode();
        if((key.equals(KeyEvent.VK_LEFT) || key.equals(KeyEvent.VK_A)) && !leftPressed){
            //left arrow key/a
            leftPressed = true;
        }
        if((key.equals(KeyEvent.VK_RIGHT) || c.equals(KeyEvent.VK_D)) && !rightPressed){
            //right arrow key/d
            rightPressed = true;
        }
        if((key.equals(KeyEvent.VK_SPACE) || key.equals(KeyEvent.VK_UP) || key.equals(KeyEvent.VK_W)) && !spacePressed){
            //space bar/up/w
            spacePressed = true;
        }
        if(key.equals(KeyEvent.VK_ESCAPE)){
            c.resetLevel();
            leftPressed = false;
            rightPressed = false;
            spacePressed = false;
            isGameFinished = true;
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
            runTimer = false;
            leftPressed = false;
            rightPressed = false;
            spacePressed = false;
            Settings.beatLevel(level, time.getText());
            this.add(beatLevelPanel);
            beatLevelPanel.repaint();
            beatLevelPanel.revalidate();
        }
        if(isGameLost){
            c.resetLevel();
            runTimer = false;
            leftPressed = false;
            rightPressed = false;
            spacePressed = false;
            isGameLost = false;
            this.add(lostLevelPanel);
            lostLevelPanel.repaint();
            lostLevelPanel.revalidate();
        }
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}
