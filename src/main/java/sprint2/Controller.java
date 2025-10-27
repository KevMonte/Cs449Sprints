package sprint2;
import javax.swing.*;
import java.awt.*;
public class Controller {
    private GUI gui;
    private String redPlayer;
    private String bluePlayer;
    
    public Controller(GUI gui){
        this.gui=gui;

        this.gui.topPanel.newGameButton.addActionListener(e-> newGame());
        initBoardListeners(3);
        redPlayer="S";
        bluePlayer="O";



    }

    private void newGame(){
        if (this.gui.leftPanel.sButton.isSelected()==this.gui.rightPanel.sButton.isSelected()) {

            throw new IllegalArgumentException("Players cant both be S or O at same time");
            
        }
        int boardSize= Integer.parseInt(this.gui.topPanel.boardSizeField.getText());
        if (boardSize<3){
            throw new IllegalArgumentException("Board must have a size of at least 3");
        }
        this.gui.turn=0;

        this.gui.boardPanel.removeAll(); 
        this.gui.boardSizeLimiter.removeAll(); 

        this.gui.boardPanel = new BoardPanel(boardSize);
        this.gui.boardPanel.setPreferredSize(new Dimension(500,500));

        initBoardListeners(boardSize);

        this.gui.boardSizeLimiter.add(this.gui.boardPanel);
        this.gui.revalidate();
        this.gui.repaint();
        
    

    }
    
    private void  makeMove(JButton cell){
        if(cell.getText()!=""){
            return;
        }
        if(this.gui.turn==0)
        {
            cell.setText(redPlayer);
        }
        else{cell.setText(bluePlayer);}
        this.gui.changeTurn();


    }
    private void initBoardListeners(int size){
        for (int row = 0; row <size; row++) {
            for (int col = 0; col <size; col++) {
                final int r = row;
                final int c = col;
                this.gui.boardPanel.boardButtons[row][col].addActionListener(e->makeMove(this.gui.boardPanel.boardButtons[r][c]));

            }
            
        }

    }


    
}
