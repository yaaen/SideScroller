package tools;

import matter.*;
import settings.Settings;

public class Camera {

    boolean lock = false;
    int movementSpeed;
    int width;

    public Camera() {
        movementSpeed = Settings.getMovementSpeed();
        width = Settings.getGameWidth();
    }

    public Matter[][] moveCharRight(Matter[][] field) {
        if(!lock){
            for(int i = 0; i < field.length; i++){
                for(int j = 0; j < field[0].length; j++){
                    field[i][j].setX(field[i][j].getX() - movementSpeed);
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
                    field[i][j].setX(field[i][j].getX() + movementSpeed);
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
