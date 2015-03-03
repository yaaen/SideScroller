public class Application{
    
    Frame f;
    StartPanel sp;
    
    public static void main(String[] args){
      f = new Frame();
      sp = new StartPanel();
      f.add(sp);
      f.setVisible(true);
      
      waitForLevelChoice()
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
            if(f.chosen != -1){
                break;
            }
        }
        play(f.chosen);
    }
    
    public static void play(int level){
        //TODO
        //actual game code
    }
}
