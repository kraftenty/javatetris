package org.nl.javatetris.settings;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class SettingsMenuControllerTest {

    /**
     * 키 입력을 제대로 처리하는지 확인한다.
     */

    @Test
    public void onHandleScreenSizeSettingsTest() {
        // given
        AtomicBoolean handleScreenSizeSettingsFlag = new AtomicBoolean(false);
        SettingsMenuController controller = new SettingsMenuController(
                6,
                () -> {},
                () -> {},
                () -> {},
                () -> {},
                () -> {handleScreenSizeSettingsFlag.set(true);}
        );

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(handleScreenSizeSettingsFlag.get());
    }

    @Test
    public void onSettingKeyMenuTest() {
        // given
        AtomicBoolean settingKeyMenuFlag = new AtomicBoolean(false);
        SettingsMenuController controller = new SettingsMenuController(
                6,
                () -> {},
                () -> {},
                () -> {},
                () -> {settingKeyMenuFlag.set(true);},
                () -> {}
        );

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(settingKeyMenuFlag.get());
    }

    @Test
    public void onCheckingBoardInitTest() {
        // given
        AtomicBoolean checkingBoardInitFlag = new AtomicBoolean(false);
        SettingsMenuController controller = new SettingsMenuController(
                6,
                () -> {},
                () -> {},
                () -> {checkingBoardInitFlag.set(true);},
                () -> {},
                () -> {}
        );

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(checkingBoardInitFlag.get());
    }

    @Test
    public void onCheckingInitSetTest() {
        // given
        AtomicBoolean checkingInitSetFlag = new AtomicBoolean(false);
        SettingsMenuController controller = new SettingsMenuController(
                6,
                () -> {},
                () -> {checkingInitSetFlag.set(true);},
                () -> {},
                () -> {},
                () -> {}
        );

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(checkingInitSetFlag.get());
    }

    @Test
    public void onBackToMenuTest_1() {
        // given
        AtomicBoolean backToMenuFlag = new AtomicBoolean(false);
        SettingsMenuController controller = new SettingsMenuController(
                6,
                () -> {backToMenuFlag.set(true);},
                () -> {},
                () -> {},
                () -> {},
                () -> {}
        );

        KeyEvent escKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ESCAPE, false, false, false, false);

        // when
        controller.handleKeyPress(escKeyEvent);

        // then
        assert(backToMenuFlag.get());
    }

    @Test
    public void onBackToMenuTest_2() {
        // given
        AtomicBoolean backToMenuFlag = new AtomicBoolean(false);
        SettingsMenuController controller = new SettingsMenuController(
                6,
                () -> {backToMenuFlag.set(true);},
                () -> {},
                () -> {},
                () -> {},
                () -> {}
        );

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(backToMenuFlag.get());
    }
}
