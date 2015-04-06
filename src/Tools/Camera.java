package Tools;

import MatterFolder.*;
import Settings.Settings;

public class Camera {

    boolean lock = false;
    int width;

    public Camera() {
        width = Settings.getGameWidth();
    }

    public Matter[][] moveCharRight(Matter[][] field) {
        if(!lock){
            for(int i = 0; i < field.length; i++){
                for(int j = 0; j < field[0].length; j++){
                    field[i][j].setX(field[i][j].getX() - 5);
                }
            }
            checkForDoor(field);
        }
        return field;
    }

    public Matter[][] moveCharLeft(Matter[][] field) {
        if(!lock){
            for(int i = 0; i < field.length; i++){
                for(int j = 0; j < field[0].length; j++){
                    field[i][j].setX(field[i][j].getX() + 5);
                }
            }
        }
        return field;
    }

    //check to see if the door is within the screen
    private void checkForDoor(Matter[][] field) {
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[0].length; j++){
                if(field[i][j].isDoor() && field[i][j].getX() < (width - 300)){
                    lock = true;
                }
            }
        }
    }

    public boolean isCameraLocked() {
        return lock;
    }

    public void resetLevel() {
        lock = false;
    }
}