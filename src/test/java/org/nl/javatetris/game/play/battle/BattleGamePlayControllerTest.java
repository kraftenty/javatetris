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
import org.testfx.util.WaitForAsyncUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
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
    public void testStartTimer() {
        Platform.runLater(() -> {
            battleGamePlayController.startTimer();
            assertEquals(Timeline.Status.RUNNING, BattleGamePlayController.getTimer().getStatus());
        });
    }

    //두 플레이어의 타임라인에서 실행이 1초 이내에 완료되는지 테스트
    @Test
    public void timelineTickResponseTimeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method startTimeline1Method = BattleGamePlayController.class.getDeclaredMethod("startTimeline1");
        Method startTimeline2Method = BattleGamePlayController.class.getDeclaredMethod("startTimeline2");
        startTimeline1Method.setAccessible(true);
        startTimeline2Method.setAccessible(true);

        Platform.runLater(() -> {
            try {
                startTimeline1Method.invoke(battleGamePlayController);
                startTimeline2Method.invoke(battleGamePlayController);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        WaitForAsyncUtils.waitForFxEvents();

        assertTimeout(Duration.ofMillis(1000), () -> {
            Timeline timeline1 = BattleGamePlayController.getTimeline1();
            Timeline timeline2 = BattleGamePlayController.getTimeline2();
            timeline1.getKeyFrames().get(0).getOnFinished().handle(null);
            timeline2.getKeyFrames().get(0).getOnFinished().handle(null);
        });
    }

    //키 입력 이벤트의 처리 시간이 1초 이내에 완료되는지 테스트
    @Test
    public void handleKeyPressResponseTimeTest() {
        Platform.runLater(() -> {
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", S, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", A, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", D, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", W, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", Q, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", K, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", J, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", L, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", I, false, false, false, false)));
            assertTimeout(Duration.ofMillis(1000), () -> battleGamePlayController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", U, false, false, false, false)));
        });

        WaitForAsyncUtils.waitForFxEvents();
    }

}
