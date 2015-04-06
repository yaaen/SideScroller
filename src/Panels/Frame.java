package Panels;

import javax.swing.JFrame;

public class Frame extends JFrame{
  public Frame(){
    setBounds(100,25,1600,1000);
    setTitle("Side Scroller");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}