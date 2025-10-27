package sprint2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class sosGameTest {
    private GUI gui;
    private Controller controller;
    
    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            gui = new GUI();
            controller = new Controller(gui);
        });
    }

    // AC1 Tests: Choose a board size
    @Test
    @DisplayName("AC1.1: Setting a valid board size")
    void testValidBoardSize() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Test with size 5
            gui.topPanel.boardSizeField.setText("5");
            gui.topPanel.newGameButton.doClick();
            
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
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                gui.topPanel.newGameButton.doClick();
            });
            
            assertEquals("Board must have a size of at least 3", exception.getMessage(),
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
            
            // Start new game
            gui.topPanel.newGameButton.doClick();
            
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
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                gui.topPanel.newGameButton.doClick();
            });
            
            assertEquals("Players cant both be S or O at same time", exception.getMessage(),
                "Should throw exception when both players choose same letter");
        });
    }
}
