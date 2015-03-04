public class GamePanel{
    

    Matter[][] matter;
    
    //This could be included in the above array as an alternative
    Player player;

    public GamePanel(int level){
        //file that has all of the level information
        File level = new File("level\#" + level + ".txt");
        
    }

    public void paint(Graphics g){
        //Add collision detection, camera motion, and other movement calls here.
        //Or they can be put in another method that will separately update the figures, in another thread,
        //while this method is repeatedly called by itself with or without the changes from the other update method

        player.paint(g);
        
        //draws everything onto the screen
        for(int i = 0; i < matter.length; i++){
            for(int j = 0; j < matter[0].length; j++){
                matter[i][j].paint(g);
            }
        }
    }
}
