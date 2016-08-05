package app;

import panels.CreditsPanel;
import panels.Frame;
import panels.StartPanel;
import panels.GamePanel;
import panels.LevelSelectPanel;
import panels.SettingsPanel;
import java.awt.CardLayout;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

public class Application {

    Frame frame;
    StartPanel startPanel;
    final String STARTPANELCON = "Start Panel";
    LevelSelectPanel levelSelectPanel;
    final String LEVELSELECTPANELCON = "Level Select Panel";
    GamePanel gamePanel;
    final String GAMEPANELCON = "Game Panel";
    SettingsPanel settingsPanel;
    final String SETTINGSPANELCON = "Settings Panel";
    CreditsPanel creditsPanel;
    final String CREDITSPANELCON = "Credits Panel";
    CardLayout cardLay;
    JPanel cards;

    public Application() {
        frame = new Frame();

        startPanel = new StartPanel();
        levelSelectPanel = new LevelSelectPanel();
        gamePanel = new GamePanel();
        settingsPanel = new SettingsPanel();
        creditsPanel = new CreditsPanel();

        cardLay = new CardLayout();
        cards = new JPanel(cardLay);

        cards.add(startPanel, STARTPANELCON);
        cards.add(levelSelectPanel, LEVELSELECTPANELCON);
        cards.add(gamePanel, GAMEPANELCON);
        cards.add(settingsPanel, SETTINGSPANELCON);
        cards.add(creditsPanel, CREDITSPANELCON);
        cardLay.show(cards, STARTPANELCON);
        
        frame.add(cards);
        frame.setVisible(true);

        waitForButtonClicked();
    }

    public void waitForButtonClicked() {
        while(true){
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            if(startPanel.wasButtonClicked()){
                startPanel.resetButton();
                break;
            }
        }
        levelSelectPanel.setLevels();
        cardLay.show(cards, LEVELSELECTPANELCON);
        waitForLevelChoice();
    }

    /**
     *   chosen() in LevelSelectssPanel returns -1 until a level is
     *   chosen so we use that to wait until a level
     *   is chosen and then when it is we call play()
     *   which will start the actual level...
     *   sleep() is used here because without it the
     *   loop goes too fast and could not work
     */
    public void waitForLevelChoice() {
        while(true){
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            if(levelSelectPanel.getChosen() != -1){
                break;
            }
        }
        if(levelSelectPanel.getChosen() > 99){
            if(levelSelectPanel.getChosen() == 100){
                levelSelectPanel.setChosen(-1);
                cardLay.show(cards, CREDITSPANELCON);
                waitForCreditsDone();
            } else if(levelSelectPanel.getChosen() == 101){
                levelSelectPanel.setChosen(-1);
                cardLay.show(cards, STARTPANELCON);
                waitForButtonClicked();
            } else if(levelSelectPanel.getChosen() == 102){
                levelSelectPanel.setChosen(-1);
                cardLay.show(cards, SETTINGSPANELCON);
                waitForSettingsDone();
            }
        } else{
            play(levelSelectPanel.getChosen());
        }
    }

    /**
     * similar to the above method, it will wait for the player
     * to finish the level they are playing so that it will know
     * when to move back to the level select screen or whatever
     * we want to appear after they beat a level
     */
    public boolean waitForLevelFinish() {
        try {
            TimeUnit.NANOSECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        return !gamePanel.isGameFinished();
    }

    public void waitForSettingsDone() {
        while(true){
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            if(settingsPanel.goBack()){
                settingsPanel.setBack();
                break;
            }
        }
        levelSelectPanel.setLevels();
        cardLay.show(cards, LEVELSELECTPANELCON);
        waitForLevelChoice();
    }
    
    public void waitForCreditsDone(){
        while(true){
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            if(creditsPanel.goBack()){
                creditsPanel.setBack();
                break;
            }
        }
        levelSelectPanel.setLevels();
        cardLay.show(cards, LEVELSELECTPANELCON);
        waitForLevelChoice();
    }

    public void play(int level) {
        //reset level select panel chosen
        levelSelectPanel.setChosen(-1);

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
        levelSelectPanel.setLevels();
        cardLay.show(cards, LEVELSELECTPANELCON);
        waitForLevelChoice();
    }
}
