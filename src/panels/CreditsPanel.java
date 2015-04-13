package panels;

import settings.Settings;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CreditsPanel extends MasterPanel {

    JLabel credits;
    JButton back;
    boolean goBack = false;

    public CreditsPanel() {
        setFocusable(true);
        requestFocus();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, 200)));
        
        credits = new JLabel("<html>Derrreks<br>EthanSchoen<br>Pierfier</html>", SwingConstants.CENTER);
        credits.setForeground(Settings.getTextColor());
        credits.setFont(new Font("Arial", Font.PLAIN, 100));
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(credits);

        Dimension min = new Dimension(0, 100);
        Dimension pref = new Dimension(0, 100);
        Dimension max = new Dimension(0, 150);
        this.add(new Box.Filler(min, pref, max));
        
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack = true;
            }
        });
        back.setFont(new Font("Arial", Font.PLAIN, 70));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setBackground(Settings.getSettingsButtonColor());
        back.setMinimumSize(new Dimension(200, 100));
        back.setPreferredSize(new Dimension(300, 150));
        back.setMaximumSize(new Dimension(300, 150));
        this.add(back);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void setBack() {
        goBack = false;
    }

    public boolean goBack() {
        return goBack;
    }
}
