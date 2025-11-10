package model;
import javax.swing.*;
import view.*;
public class sosGeneralGame extends sosGame {
    public sosGeneralGame(int boardSize, JButton[][] boardButtons, String redPlayer, String bluePlayer, GUI gui) {
        super(boardSize, boardButtons, redPlayer, bluePlayer, gui);
    }

    public String getGameState() {
        if (turnNumber < (boardSize * boardSize)) {return "";}
        else{
            if (redScore > blueScore) {
                return "r";
            } else if (blueScore > redScore) {
                return "b";
            } else {
                return "d";
            }
        }
    }



    
}
