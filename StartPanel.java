import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
    
    JButton startButton;
    boolean wasClicked;
    
    public StartPanel(){
        setSize(1600, 1000);
        setLayout(null);
        setBackground(Color.CYAN);

        event e = new event();

        startButton = new JButton("START GAME");
        startButton.setSize(400, 250);
        startButton.setLocation(600, 450);
        startButton.addActionListener(e);
        this.add(startButton);

        wasClicked = false;
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
