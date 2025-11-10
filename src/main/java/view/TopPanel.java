package view;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public JLabel sosLabel;
    public JLabel sizeLabel;
    public JRadioButton simpleButton;
    public JRadioButton generalButton;
    public ButtonGroup gamemodeButtonGroup;
    public JButton newGameButton;
    public JTextField boardSizeField;

    public TopPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        sosLabel = new JLabel("SOS Game Mode:");
        sizeLabel = new JLabel("Board Size:");
        simpleButton = new JRadioButton("Simple", true);
        generalButton = new JRadioButton("General");
        newGameButton = new JButton("New Game");
        boardSizeField = new JTextField("3", 3);

        gamemodeButtonGroup = new ButtonGroup();

        gamemodeButtonGroup.add(simpleButton);
        gamemodeButtonGroup.add(generalButton);

        add(sosLabel);
        add(simpleButton);
        add(generalButton);
        add(Box.createRigidArea(new Dimension(100, 10)));
        add(sizeLabel);
        add(boardSizeField);
        add(newGameButton);
    }
}
