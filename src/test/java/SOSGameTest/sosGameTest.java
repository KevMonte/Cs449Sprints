package SOSGameTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import view.*;
import controller.*;

public class sosGameTest {
    private GUI gui;
    private sosGameController controller;
    private volatile Exception capturedException;
    
    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            gui = new GUI();
            controller = new sosGameController(gui);
        });
    }

    // AC1 Tests: Choose a board size
    @Test
    @DisplayName("AC1.1: Setting a valid board size")
    void testValidBoardSize() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Test with size 5
            gui.topPanel.boardSizeField.setText("5");
            capturedException = null;
            try {
                gui.topPanel.newGameButton.doClick();
            } catch (Exception e) {
                capturedException = e;
            }
            
            assertNull(capturedException, "Should not throw exception for valid board size");
            assertEquals(5, gui.boardPanel.boardButtons.length, 
                "Board should have 5 rows");
            assertEquals(5, gui.boardPanel.boardButtons[0].length, 
                "Board should have 5 columns");
            
            // Verify all cells are empty
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    assertEquals("", gui.boardPanel.boardButtons[i][j].getText(),
                        "All cells should be empty in new game");
                }
            }
        });
    }

    @Test
    @DisplayName("AC1.2: Attempting to set invalid small board size")
    void testInvalidSmallBoardSize() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            gui.topPanel.boardSizeField.setText("2");
            capturedException = null;
            
            try {
                gui.topPanel.newGameButton.doClick();
            } catch (Exception e) {
                capturedException = e;
            }
            
            assertNotNull(capturedException, "Should throw exception for board size less than 3");
            assertTrue(capturedException instanceof IllegalArgumentException,
                "Should throw IllegalArgumentException");
            assertEquals("Board must have a size of at least 3", capturedException.getMessage(),
                "Should throw exception for board size less than 3");
        });
    }


    // AC3 Tests: Start a new game of the chosen board size and game mode
    @Test
    @DisplayName("AC3.1: Start new game with valid setup")
    void testStartNewGameWithValidSetup() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Setup initial board state
            gui.leftPanel.sButton.setSelected(true);
            gui.rightPanel.oButton.setSelected(true);
            gui.topPanel.boardSizeField.setText("4");
            gui.topPanel.simpleButton.setSelected(true);
            
            capturedException = null;
            try {
                // Start new game
                gui.topPanel.newGameButton.doClick();
            } catch (Exception e) {
                capturedException = e;
            }
            
            assertNull(capturedException, "Should not throw exception for valid setup");
            // Verify board size
            assertEquals(4, gui.boardPanel.boardButtons.length,
                "Board should be 4x4");
            
            // Verify turn is reset
            assertEquals(0, gui.turn,
                "Game should start with red player's turn");
            
            // Verify game mode is set
            assertTrue(gui.topPanel.simpleButton.isSelected(),
                "Simple game mode should be selected");
        });
    }

    @Test
    @DisplayName("AC3.2: Start new game with invalid player setup")
    void testStartNewGameWithInvalidPlayerSetup() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Set both players to same letter
            gui.leftPanel.sButton.setSelected(true);
            gui.rightPanel.sButton.setSelected(true);
            gui.topPanel.boardSizeField.setText("4");
            
            capturedException = null;
            try {
                gui.topPanel.newGameButton.doClick();
            } catch (Exception e) {
                capturedException = e;
            }
            
            assertNotNull(capturedException, "Should throw exception when both players choose same letter");
            assertTrue(capturedException instanceof IllegalArgumentException,
                "Should throw IllegalArgumentException");
            assertEquals("Players cant both be S or O at same time", capturedException.getMessage(),
                "Should throw exception when both players choose same letter");
        });
    }

    @Test
    @DisplayName("AC8.3: computerOpponent Makes valid Moves")
    void testComputerOpponentMakesValidMove() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Set up game with blue player as computer
            gui.leftPanel.oButton.setSelected(true);
            gui.rightPanel.sButton.setSelected(true);

            gui.rightPanel.computerButton.setSelected(true);

            gui.topPanel.boardSizeField.setText("4");
            
            gui.topPanel.simpleButton.setSelected(true);
            assertDoesNotThrow(()-> {
                gui.topPanel.newGameButton.doClick();

                gui.boardPanel.boardButtons[1][1].doClick(); 
                gui.boardPanel.boardButtons[0][3].doClick();

            });
            
            
            

            
            //computer should have made 2 moves
            boolean computerMoved = false;
            int computerMoves=0;
            for (int r =0; r<4; r++){
                for (int c=0; c<4; c++){
                    if (gui.boardPanel.boardButtons[r][c].getText().equals("S")){
                        computerMoves++;
                    }
                }
            }
            if (computerMoves==2){
                computerMoved=true;
            }
           
            assertTrue(computerMoved);
        });
    }
    @Test
    @DisplayName("AC8.3: computerOpponent Makes winning/point Move")
    void testComputerOpponentMakesWinningMove() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            
            gui.leftPanel.oButton.setSelected(true);
            gui.rightPanel.sButton.setSelected(true);

            gui.rightPanel.computerButton.setSelected(true);

            gui.topPanel.boardSizeField.setText("4");

            gui.topPanel.simpleButton.setSelected(true);
            assertDoesNotThrow(()-> {
                gui.topPanel.newGameButton.doClick();

                gui.boardPanel.boardButtons[1][1].doClick(); 
                gui.boardPanel.boardButtons[0][3].doClick();

            });
            
            
            

            
            
            boolean madePointMove = false;
            
            if (gui.rightPanel.getScore() ==1){
                madePointMove=true;
            }
           
            assertTrue(madePointMove);
        });
    }

    @Test
    @DisplayName("AC8.3: computerOpponents succesfully fills board during general game")
    void testComputerOpponentsSuccesfullyCompleteGeneralGame() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Set up game with blue player as computer
            gui.leftPanel.sButton.setSelected(true);
            gui.rightPanel.oButton.setSelected(true);

            gui.rightPanel.computerButton.setSelected(true);
            gui.leftPanel.computerButton.setSelected(true);

            gui.topPanel.boardSizeField.setText("8");

            gui.topPanel.generalButton.setSelected(true);

            assertDoesNotThrow(()-> {gui.topPanel.newGameButton.doClick();});
            

            boolean boardFilled = true;
            for (int r =0; r<8; r++){
                for (int c=0; c<8; c++){
                    if (gui.boardPanel.boardButtons[r][c].getText().equals("")){
                        boardFilled = false;
                    }
                }
            }

            
            
           
            assertTrue(boardFilled);
        });
    }
}
