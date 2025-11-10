package view;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    public JLabel playerLabel;
    public JLabel scoreLabel;
    int score=0;


    public JRadioButton sButton;
    public JRadioButton oButton;
    public ButtonGroup soButtonGroup;



    public SidePanel(String playerColor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());

        playerLabel = new JLabel(playerColor);    
        add(playerLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        sButton = new JRadioButton("S");
        oButton = new JRadioButton("O");
        soButtonGroup = new ButtonGroup();

        soButtonGroup.add(sButton);
        soButtonGroup.add(oButton);

        add(sButton);
        add(oButton);

        scoreLabel = new JLabel("Score:" + score);
        add(scoreLabel);

        add(Box.createVerticalGlue());
       
    }
    public void setScore(int score) {
       this.score = score;
        scoreLabel.setText("Score:" + score);
    }
    public int getScore() {
        return score;
    }
}
