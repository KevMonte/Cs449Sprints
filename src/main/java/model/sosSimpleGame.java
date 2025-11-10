package model;
import javax.swing.*;
import view.*;
public class sosSimpleGame extends sosGame {
    public sosSimpleGame(int boardSize, JButton[][] boardButtons, String redPlayer, String bluePlayer, GUI gui) {
        super(boardSize, boardButtons, redPlayer, bluePlayer, gui);
    }

   
    public String getGameState() {
        
        if (redScore>0 ) {
            return "r";

        }
        if (blueScore>0) {
            return "b";
        }
        if (turnNumber >= (boardSize * boardSize)) {
            return "d";
        }
        return "";
    }

}

    



