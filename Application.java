import java.awt.CardLayout;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

public class Application{
    
    Frame frame;
    StartPanel startPanel;
    final String STARTPANELCON = "Start Panel";
    GamePanel gamePanel;
    final String GAMEPANELCON = "Game Panel";
    CardLayout cardLay;
    JPanel cards;
    
    //this should be treated as the main method
    //it is easier because you do not need all of the methods to be static
    public Application(){
        frame = new Frame();
        startPanel = new StartPanel();
        gamePanel = new GamePanel();
        cardLay = new CardLayout();
        cards = new JPanel(cardLay);
        
        cards.add(startPanel, STARTPANELCON);
        cards.add(gamePanel, GAMEPANELCON);
        cardLay.show(cards, STARTPANELCON);
        
        frame.add(cards);
        
        frame.setVisible(true);
      
        waitForLevelChoice();  
    }
    
    /*
    *   chosen() in StartPanel returns -1 until a level is
    *   chosen so we use that to wait until a level
    *   is chosen and then when it is we call play()
    *   which will start the actual level...
    *   sleep() is used here because without it the
    *   loop goes too fast and could not work
    */
    public void waitForLevelChoice(){
        while(true){
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            if(startPanel.getChosen() != -1){
                break;
            }
        }
        play(startPanel.getChosen());
    }
    
    /*
    * similar to the above method, it will wait for the player
    * to finish the level they are playing so that it will know
    * when to move back to the level select screen or whatever
    * we want to appear after they beat a level
    */
    public boolean waitForLevelFinish(){
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            
            return !gamePanel.isGameFinished();
    }
    
    public void play(int level){
        //reset start panel chosen
        startPanel.setChosen(-1);
        
        //switch to game panel
        gamePanel.setLevel(level);
        cardLay.show(cards, GAMEPANELCON);

        //have them play the game
        while(waitForLevelFinish()){
        //if waitForLevelFinish returns true
        //this means level is still being played
        }
        
        //when finished, go back to waitForLevelChoice() and
        //go back to the start menu
        cardLay.show(cards, STARTPANELCON);
        waitForLevelChoice();
    }
}
