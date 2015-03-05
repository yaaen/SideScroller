

public class Application{
    
    Frame f;
    StartPanel sp;
    

    //this should be treated as the main method
    //it is easier because you do not need all of the methods to be static
    public Application(){
        f = new Frame();
        sp = new StartPanel();
        f.add(sp);
        f.setVisible(true);
      
        waitForLevelChoice();  
    }

    public static void main(String[] args){
        new Application();
    }
    
    /*
    *   chosen() in StartPanel returns -1 until a level is
    *   chosen so we use that to wait until a level
    *   is chosen and then when it is we call play()
    *   which will start the actual level...
    *   sleep() is used here because without it the
    *   loop goes too fast and could not work
    */
    public static void waitForLevelChoice(){
        while(true){
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            if(sp.chosen != -1){
                break;
            }
        }
        play(sp.chosen);
    }
    
    public static void play(int level){
        //TODO
        //actual game code
    }
}
