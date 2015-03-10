
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
