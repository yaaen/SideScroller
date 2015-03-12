import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import MatterFolder.*;

public class GamePanel extends JPanel{
    

    Matter[][] matter;
    FileRead fr = new FileRead();
    event e = new event();

    public GamePanel(){
        
    }

    public void setLevel(int level){
        String[][] fileArray = fr.readLevelFromFile(level);
        //fileArray will be strings
        //s = no background(sky)
        //g = ground block
    }

    public void paint(Graphics g){
        //Add collision detection, camera motion, and other movement calls here.
        //Or they can be put in another method that will separately update the figures, in another thread,
        //while this method is repeatedly called by itself with or without the changes from the other update method
        
        //also this method/class will have to interact with the camera so that it knows what part of the map to paint
        //this panel should probably use its own camera so there should be a global Camera c = new Camera() or something
        
        //draws everything onto the screen
        for(int i = 0; i < matter.length; i++){  //should this be fileArray.length?
            for(int j = 0; j < matter[0].length; j++){
               //we're gonna have to paint the square icon wherever and get its location,
               //which should all be in the class so I think it'd be something like:
              // g.drawImage(matter[i][j].getImage(), matter[i][j].getX(), matter[i][j].getY(), null);
            }
        }
    }
   
    //keep this lowercase bc its not a real class
    public class event implements KeyListener{

    @Override
    public void keyTyped(KeyEvent ke) {
        //could maybe use for something like opening the pause menu or something
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_LEFT){
            //left arrow key
            //move left
        } else if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            //right arrow key
            //move right
        } else if(ke.getKeyCode() == KeyEvent.VK_UP){
            //up arrow key
            //jump
        } else if(ke.getKeyCode() == KeyEvent.VK_SPACE){
            //space bar
            //also jump?
        } else {
            //not supported yet
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //stop movement
    }

}
    
}
