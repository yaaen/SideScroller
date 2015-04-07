package Settings;

import Tools.FileRead;
import java.util.ArrayList;

/**
 *
 * @author Derek
 */
public class Settings {

    private static int gameHeight;
    private static int gameWidth;
    private static int levelsCompleted;
    private static int background;
    private static int movementSpeed;
    private static int gravity;
    private static int blocksize;

    public Settings() {
        FileRead fr = new FileRead();
        String settings = fr.getSettings();
        ArrayList<Integer> numbers = new ArrayList<>();

        String settingsRefined = settings.replaceAll("[^\\d=]", "");
        for(int i = 0; i < 7; i++){
            numbers.add(Integer.parseInt(settingsRefined.substring(1, settingsRefined.indexOf("=", 1))));
            settingsRefined = settingsRefined.substring(settingsRefined.indexOf("=", 1));
        }

        gameHeight = numbers.get(0);
        gameWidth = numbers.get(1);
        levelsCompleted = numbers.get(2);
        background = numbers.get(3);
        movementSpeed = numbers.get(4);
        gravity = numbers.get(5);
        blocksize = numbers.get(6);
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static int getGameWidth() {
        return gameWidth;
    }

    public static void setLevelsCompleted(int levelsCompleted) {
        if(levelsCompleted > -1 && levelsCompleted < 25){
            Settings.levelsCompleted = levelsCompleted;
        }
    }

    public static int getLevelsCompleted() {
        return levelsCompleted;
    }

    public static void setBackground(int background) {
        if(background > -1 && background < 3){
            Settings.background = background;
        }
    }

    public static int getBackground() {
        return background;
    }

    public static int getMovementSpeed() {
        return movementSpeed;
    }

    public static int getGravity() {
        return gravity;
    }

    public static int getBlockSize() {
        return blocksize;
    }
}
