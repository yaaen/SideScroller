import java.awt.CardLayout;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

public class Application{
    
    Frame frame;
    StartPanel startPanel;
    static final String STARTPANELCON = "Start Panel";
    GamePanel gamePanel;
    static final String GAMEPANELCON = "Game Panel";
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
            if(startPanel.chosen != -1){
                break;
            }
        }
        play(startPanel.chosen);
    }
    
    public void play(int level){
        //TODO
        //load level onto game panel

        //switch to game panel
        cardLay.show(cards, GAMEPANELCON);

        //have them play the game

        //when finished, go back to waitForLevelChoice()
    }
}
