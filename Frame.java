import javax.swing.JFrame;

public class Frame extends JFrame{
  public Frame(){
    setBounds(100,25,1600,1000);
    setTitle("Side Scroller");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //do you think we should sat a layout here?
    //even though were only adding the panel should
    //we put something or will it be fine without one?
    //or even just good programming practice to have one?
  }
}
