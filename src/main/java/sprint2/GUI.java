package sprint2;
import java.io.*;
import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class GUI extends JFrame {
	//JFrame frame = new JFrame("SOS game");
	public JButton button2;
	public JButton button1;
	public GUI() {
	
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JButton button2= new JButton("Hello");
	JButton button1 = new JButton("Hello 2");
	
	JPanel mainPanel = new JPanel();
	
	JPanel leftPanel = new JPanel();
	
	BoxLayout boxLayoutManager = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
	
	BorderLayout borderLayoutManager = new BorderLayout();
	setTitle("SOS Game");
	setSize(400, 300);
	mainPanel.setLayout(borderLayoutManager);
	leftPanel.setLayout(boxLayoutManager);
	
	leftPanel.add(Box.createVerticalGlue());
	
	leftPanel.add(button1);
	leftPanel.add(button2);
	leftPanel.add(Box.createVerticalGlue());
	
	mainPanel.add(leftPanel,borderLayoutManager.WEST);
	add(mainPanel);
	
	
	}
}