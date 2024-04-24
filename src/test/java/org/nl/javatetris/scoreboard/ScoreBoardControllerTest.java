package org.nl.javatetris.scoreboard;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreBoardControllerTest {

    /**
     * 키 입력을 제대로 처리하는지 확인한다.
     */
    @Test
    public void testHandleKeyPress() {
        // given
        AtomicBoolean runFlag = new AtomicBoolean(false);
        ScoreBoardController controller = new ScoreBoardController(() -> {
            runFlag.set(true);
        });
        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(enterKeyEvent);

        // then
        assertTrue(runFlag.get());
    }
}
