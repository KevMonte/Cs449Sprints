package view;
import javax.swing.*;
import java.awt.*;
public class BottomPanel extends JPanel {
    public JLabel turnLabel;
    public JCheckBox recordCheckbox;
    public JButton replayButton;

    public BottomPanel() {
        turnLabel = new JLabel("Red(S) Turn");
        turnLabel.setForeground(Color.RED);
        
        recordCheckbox = new JCheckBox("Record Game", true);
        
        replayButton = new JButton("Replay Game");

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));


        add(Box.createHorizontalGlue());
        

        add(recordCheckbox);
        add(Box.createRigidArea(new Dimension(120, 0)));
        add(turnLabel);
        add(Box.createRigidArea(new Dimension(120, 0)));
        add(replayButton);


        add(Box.createHorizontalGlue());
        
        
        
    }
    public void setTurn(String Text,Color color)
    {
        turnLabel.setText(Text);
        turnLabel.setForeground(color);
    }

    

    
}
