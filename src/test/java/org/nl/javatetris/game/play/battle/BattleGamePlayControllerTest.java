package org.nl.javatetris.game.play.battle;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.pause.PauseMenuParam;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static javafx.scene.input.KeyCode.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class BattleGamePlayControllerTest {

    private String output = "";
    private BattleGamePlayController battleGamePlayController;

    @Start
    private void start(Stage stage) {
        // No need to initialize stage for this test
    }

    @BeforeEach
    public void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                GameParam gameParam = new GameParam(ControllerConst.BATTLE_TIME_ATTACK, 1);

                Consumer<PauseMenuParam> onPause = (param) -> {
                    output = "Pause";
                };
                Runnable onDrawBoardUpdate = () -> {
                    output = "DrawBoardUpdate";
                };
                Runnable onDrawGameOver = () -> {
                    output = "DrawGameOver";
                };

                battleGamePlayController = new BattleGamePlayController(
                        gameParam,
                        onPause,
                        onDrawBoardUpdate,
                        onDrawGameOver
                );
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    @Test
    public void getBoardTest() {
        assertNotNull(battleGamePlayController.getBoard1());
        assertNotNull(battleGamePlayController.getBoard2());
    }

    @Test
    public void getTetrominoGeneratorTest() {
        assertNotNull(battleGamePlayController.getTetrominoGenerator1());
        assertNotNull(battleGamePlayController.getTetrominoGenerator2());
    }

    @Test
    public void getGameParamTest() {
        assertNotNull(battleGamePlayController.getGameParam());
    }

    @Test
    public void getPointTest() {
        assertEquals(0, battleGamePlayController.getPoint1());
        assertEquals(0, battleGamePlayController.getPoint2());
    }

    @Test
    public void getLevelTest() {
        assertEquals(0, battleGamePlayController.getLevel1());
        assertEquals(0, battleGamePlayController.getLevel2());
    }

    @Test
    public void pauseAndResumeTimerTest() {
        Platform.runLater(() -> {
            battleGamePlayController.pauseTimer();
            assertEquals(Timeline.Status.PAUSED, BattleGamePlayController.getTimeline1().getStatus());
            assertEquals(Timeline.Status.PAUSED, BattleGamePlayController.getTimeline2().getStatus());
            assertEquals(Timeline.Status.PAUSED, BattleGamePlayController.getTimer().getStatus());

            battleGamePlayController.resumeTimer();
            assertEquals(Timeline.Status.RUNNING, BattleGamePlayController.getTimeline1().getStatus());
            assertEquals(Timeline.Status.RUNNING, BattleGamePlayController.getTimeline2().getStatus());
            assertEquals(Timeline.Status.RUNNING, BattleGamePlayController.getTimer().getStatus());
        });
    }

    @Test
    public void handleKeyPressTest() {
        Platform.runLater(() -> {
            // Test ESC key to pause
            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false));
            assertEquals("Pause", output);

            // Test Player 1 controls
            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", S, false, false, false, false));
            assertTrue(battleGamePlayController.getPoint1() > 0);

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", A, false, false, false, false));
            // Add assertions to verify left move

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", D, false, false, false, false));
            // Add assertions to verify right move

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", W, false, false, false, false));
            // Add assertions to verify rotation

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", Q, false, false, false, false));
            // Add assertions to verify drop

            // Test Player 2 controls
            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", K, false, false, false, false));
            assertTrue(battleGamePlayController.getPoint2() > 0);

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", J, false, false, false, false));
            // Add assertions to verify left move

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", L, false, false, false, false));
            // Add assertions to verify right move

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", I, false, false, false, false));
            // Add assertions to verify rotation

            battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", U, false, false, false, false));
            // Add assertions to verify drop
        });
    }

    @Test
    public void testStartTimer() {
        Platform.runLater(() -> {
            battleGamePlayController.startTimer();
            assertEquals(Timeline.Status.RUNNING, BattleGamePlayController.getTimer().getStatus());
        });
    }

}
