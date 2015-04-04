import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartPanel extends JPanel {
    
    JButton startButton;
    boolean wasClicked;
    
    public StartPanel(){
        setSize(1600, 1000);
        setBorder(new EmptyBorder(250, 600, 250, 600));
        setLayout(new GridLayout(1, 1, 100, 100));

        event e = new event();
        
        startButton = new JButton("START GAME");
        startButton.setFont(new Font("Arial", Font.Plain, 40));
        startButton.setMargin(new Insets(0, 0, 0, 0));
        startButton.addActionListener(e);
        startButton.setBackground(Color.MAGENTA);
        this.add(startButton);

        wasClicked = false;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 470, 320);
        g.setColor(Color.RED);
        g.fillRect(100, 700, 200, 190);
        g.setColor(Color.GREEN);
        g.fillRect(1200, 100, 300, 700);
        
        g.setColor(Color.BLUE);
        g.fillRect(1050, 600, 270, 320);
        g.setColor(Color.RED);
        g.fillRect(700, 50, 290, 100);
        g.setColor(Color.GREEN);
        g.fillRect(250, 600, 300, 200);
    }
    
    public boolean wasButtonClicked() {
        return wasClicked;
    }

    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wasClicked = true;
        }
    }
}
