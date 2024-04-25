package org.nl.javatetris.pause;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class PauseMenuControllerTest {

    /**
     * 키 입력을 제대로 처리하는지 확인한다.
     */
    @Test
    public void onBackToMenuTest() {
        // given
        AtomicBoolean backToMenuFlag = new AtomicBoolean(false);
        PauseMenuController controller = new PauseMenuController(3, () -> {
        }, () -> {
            backToMenuFlag.set(true);
        });

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(backToMenuFlag.get());
    }

    @Test
    public void onResumeTest_select(){
        // given
        AtomicBoolean resumeFlag = new AtomicBoolean(false);
        PauseMenuController controller = new PauseMenuController(3, () -> {
            resumeFlag.set(true);
        }, () -> {
        });

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(resumeFlag.get());
    }

    @Test
    public void onResumeTest_esc() {
        // given
        AtomicBoolean resumeFlag = new AtomicBoolean(false);
        PauseMenuController controller = new PauseMenuController(3, () -> {
            resumeFlag.set(true);
        }, () -> {
        });

        KeyEvent escKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ESCAPE, false, false, false, false);

        // when
        controller.handleKeyPress(escKeyEvent);

        // then
        assert(resumeFlag.get());

    }


}
