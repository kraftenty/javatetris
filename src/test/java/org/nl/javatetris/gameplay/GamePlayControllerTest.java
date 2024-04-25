package org.nl.javatetris.gameplay;

import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.lang.model.util.ElementScanner6;

import static javafx.scene.input.KeyCode.*;

public class GamePlayControllerTest {
    private String output = "";

    private GamePlayController gamePlayController = new GamePlayController(
            () -> {output = "Pause";},
            () -> {output = "DrawBoardUpdate";},
            () -> {output = "DrawGameOver";}
    );


    @Test
    public void getBoardTest(){
        Assertions.assertEquals(1, gamePlayController.getBoard().getYX()[0]);
        Assertions.assertEquals(5, gamePlayController.getBoard().getYX()[1]);
    }

    @Test
    public void getTetrominoGeneratorTest(){
        int nextTetrominoNum = gamePlayController.getTetrominoGenerator().getNextTetromino(false).getShapeNumber();
        boolean result = false;
        if(nextTetrominoNum >= 1 && nextTetrominoNum <= 7){
            result = true;
        }
        Assertions.assertTrue(result);
    }

    @Test
    public void getGameParamTest(){
        Assertions.assertEquals(0, gamePlayController.getGameParam().getMode());
    }

    @Test
    public void getPointTest(){
        Assertions.assertEquals(0, gamePlayController.getPoint());
    }

    @Test
    public void getLevelTest() {
        Assertions.assertEquals(0, gamePlayController.getLevel());
    }

    @Test
    public void handleKeyPressTest() {
        gamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false));
        Assertions.assertEquals("Pause", output);

        gamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(2, gamePlayController.getBoard().getYX()[0]);

        gamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", LEFT, false, false, false, false));
        Assertions.assertEquals(4, gamePlayController.getBoard().getYX()[1]);

        gamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", RIGHT, false, false, false, false));
        Assertions.assertEquals(5, gamePlayController.getBoard().getYX()[1]);

        gamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false));
        Assertions.assertEquals(1, gamePlayController.getBoard().getCurrentTetromino().getShapeIndex());

        gamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", SPACE, false, false, false, false));
        Assertions.assertEquals(1, gamePlayController.getBoard().getYX()[0]);
    }
}
