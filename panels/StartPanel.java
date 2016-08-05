package panels;

import settings.Settings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class StartPanel extends MasterPanel {

    JButton startButton;
    boolean wasClicked;

    public StartPanel() {
        setFocusable(true);
        requestFocus();
        setBorder(new EmptyBorder(250, 600, 250, 600));
        setLayout(new GridLayout(1, 1, 100, 100));

        event e = new event();

        startButton = new JButton("START GAME");
        startButton.setFont(new Font("Arial", Font.PLAIN, 40));
        startButton.setMargin(new Insets(0, 0, 0, 0));
        startButton.addActionListener(e);
        startButton.setBackground(Color.MAGENTA);
        this.add(startButton);

        wasClicked = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void resetButton() {
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
