package org.nl.javatetris.game.play.single;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.play.single.SingleGamePlayController;
import org.testfx.util.WaitForAsyncUtils;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;

import static javafx.scene.input.KeyCode.*;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class SingleGamePlayControllerTest {
    private String output = "";

    private SingleGamePlayController singleGamePlayController = new SingleGamePlayController(
            (o) -> {output = "Pause";},
            () -> {output = "DrawBoardUpdate";},
            () -> {output = "DrawGameOver";}
    );


    @Test
    public void getBoardTest(){
        Assertions.assertEquals(1, singleGamePlayController.getBoard().getYX()[0]);
        Assertions.assertEquals(5, singleGamePlayController.getBoard().getYX()[1]);
    }

    @Test
    public void getTetrominoGeneratorTest(){
        int nextTetrominoNum = singleGamePlayController.getTetrominoGenerator().getNextTetromino(false).getShapeNumber();
        boolean result = false;
        if(nextTetrominoNum >= 1 && nextTetrominoNum <= 7){
            result = true;
        }
        Assertions.assertTrue(result);
    }

    @Test
    public void getGameParamTest(){
        Assertions.assertEquals(0, singleGamePlayController.getGameParam().getMode());
    }

    @Test
    public void getPointTest(){
        Assertions.assertEquals(0, singleGamePlayController.getPoint());
    }

    @Test
    public void getLevelTest() {
        Assertions.assertEquals(0, singleGamePlayController.getLevel());
    }

//    @Test   테스트 제외
    public void handleKeyPressTest() {
        singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false));
        Assertions.assertEquals("Pause", output);

        singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(2, singleGamePlayController.getBoard().getYX()[0]);

        singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", LEFT, false, false, false, false));
        Assertions.assertEquals(4, singleGamePlayController.getBoard().getYX()[1]);

        singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", RIGHT, false, false, false, false));
        Assertions.assertEquals(5, singleGamePlayController.getBoard().getYX()[1]);

        singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false));
        Assertions.assertEquals(1, singleGamePlayController.getBoard().getCurrentTetromino().getShapeIndex());

        singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", SPACE, false, false, false, false));
        Assertions.assertEquals(1, singleGamePlayController.getBoard().getYX()[0]);
    }
    // 키 이벤트 처리 시간이 1초 미만인지 테스트
    @Test
    public void handleKeyPressResponseTimeTest() {
        assertTimeout(Duration.ofMillis(1000), () -> singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false)));
        assertTimeout(Duration.ofMillis(1000), () -> singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false)));
        assertTimeout(Duration.ofMillis(1000), () -> singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", LEFT, false, false, false, false)));
        assertTimeout(Duration.ofMillis(1000), () -> singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", RIGHT, false, false, false, false)));
        assertTimeout(Duration.ofMillis(1000), () -> singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false)));
        assertTimeout(Duration.ofMillis(1000), () -> singleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", SPACE, false, false, false, false)));
    }
}
