import javax.swing.JFrame;

public class Frame extends JFrame{
  public Frame(){
    //what is this setBounds and what are the
    //100 and 25 there for? shouldn't it be
    //set size(1600, 1000)?
    //also what layout should we go for here?
    setBounds(100,25,1600,1000);
    setTitle("Side Scroller");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
