package panels;

import settings.Settings;
import javax.swing.JFrame;

public class Frame extends JFrame{
  public Frame(){
    setBounds(50, 50, Settings.getGameWidth(), Settings.getGameHeight());
    setTitle("Side Scroller");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
