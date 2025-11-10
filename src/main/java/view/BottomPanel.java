package view;
import javax.swing.*;
import java.awt.*;
public class BottomPanel extends JPanel {
    public JLabel turnLabel;

    public BottomPanel() {
        turnLabel = new JLabel("Red(S) Turn");
        turnLabel.setForeground(Color.RED);
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalGlue());
        add(turnLabel);
        add(Box.createHorizontalGlue());
        
        
        
    }
    public void setTurn(String Text,Color color)
    {
        turnLabel.setText(Text);
        turnLabel.setForeground(color);
    }

    

    
}
