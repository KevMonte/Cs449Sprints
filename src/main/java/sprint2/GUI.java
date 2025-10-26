package sprint2;
import java.io.*;
import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class GUI extends JFrame {
	//JFrame frame = new JFrame("SOS game");
	public JButton button2;
	public JButton button1;
	SidePanel leftPanel;
	SidePanel rightPanel;
	BoardPanel boardPanel;
	JPanel boardSizeLimiter;
	TopPanel topPanel;
	public GUI() {
	
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	JPanel mainPanel = new JPanel();
	
	
	
	
	
	BorderLayout borderLayoutManager = new BorderLayout();

	setTitle("SOS Game");
	setSize(800, 600);
	mainPanel.setLayout(borderLayoutManager);
	leftPanel = new SidePanel("Red PLayer");
	rightPanel = new SidePanel("Blue PLayer");
	boardPanel= new BoardPanel(20);
	boardSizeLimiter = new JPanel(new FlowLayout(FlowLayout.CENTER));
	topPanel = new TopPanel();
	

	boardPanel.setPreferredSize(new Dimension(500,500));

	boardSizeLimiter.add(boardPanel);
	boardSizeLimiter.add(Box.createVerticalGlue());

	add(Box.createRigidArea(new Dimension(25, 0)),borderLayoutManager.WEST);
	add(Box.createRigidArea(new Dimension(25, 0)),borderLayoutManager.EAST);
	//TO DO: make it so when right panel has an option clicked the other panel toggles, for example of left panel has S clicked, right panel will have O clicked.
	mainPanel.add(leftPanel,borderLayoutManager.WEST);
	leftPanel.sButton.setSelected(true);
	mainPanel.add(rightPanel,borderLayoutManager.EAST);
	rightPanel.oButton.setSelected(true);
	//mainPanel.add(boardPanel);
	mainPanel.add(boardSizeLimiter);
	mainPanel.add(topPanel,borderLayoutManager.NORTH);
	
	add(mainPanel);
	
	
	
	
	
	
	}

}

 class SidePanel extends JPanel {
	JLabel playerLabel;
	JRadioButton sButton;
	JRadioButton oButton;
	ButtonGroup soButtonGroup;
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


		add(Box.createVerticalGlue());
	}
}
class BoardPanel extends JPanel {
	JButton[][] boardButtons;

	public BoardPanel(int boardSize){
		setLayout(new GridLayout(boardSize,boardSize));
		boardButtons = new JButton[boardSize][boardSize];

		//setSize(new Dimension(200,200));
		setPreferredSize(new Dimension(100,100));
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				JButton cell= new JButton("");
				boardButtons[row][col]= cell;
				add(cell);

			}
		}
	}
}

class TopPanel extends JPanel{
	JLabel sosLabel;
	JLabel sizeLabel;
	JRadioButton simpleButton;
	JRadioButton generalButton;
	ButtonGroup gamemodeButtonGroup;
	JTextField boardSizeField;
	public TopPanel(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		sosLabel= new JLabel("SOS Game Mode:");
		sizeLabel= new JLabel("Board Size");
		simpleButton= new JRadioButton("Simple",true);
		generalButton= new JRadioButton("General");
		boardSizeField = new JTextField("3",3);
		

		gamemodeButtonGroup = new ButtonGroup();

		gamemodeButtonGroup.add(simpleButton);
		gamemodeButtonGroup.add(generalButton);



		add(sosLabel);
		add(simpleButton);
		add(generalButton);
		add(Box.createRigidArea(new Dimension(100, 10)));
		add(sizeLabel);
		add(boardSizeField);


	}
}