package org.nl.javatetris.main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainControllerTest {

    /**
     * 키 입력을 제대로 처리하는지 확인한다.
     */

    @Test
    public void onClassicModeLobbyTest() {
        // given
        AtomicBoolean onClassicModeLobbyFlag = new AtomicBoolean(false);
        MainController controller = new MainController(6, () -> {
            onClassicModeLobbyFlag.set(true);
        }, () -> {
        }, () -> {
        }, () -> {
        }, () -> {
        });

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(enterKeyEvent);

        // then
        assertTrue(onClassicModeLobbyFlag.get());

    }

    @Test
    public void onItemModeLobbyTest() {
        // given
        AtomicBoolean onItemModeLobbyFlag = new AtomicBoolean(false);
        MainController controller = new MainController(6, () -> {
        }, () -> {
            onItemModeLobbyFlag.set(true);
        }, () -> {
        }, () -> {
        }, () -> {
        });

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assertTrue(onItemModeLobbyFlag.get());
    }

    @Test
    public void onSettingsTest() {
        // given
        AtomicBoolean onSettingsFlag = new AtomicBoolean(false);
        MainController controller = new MainController(
                6,
                () -> {
                },
                () -> {
                },
                () -> {
                },
                () -> {
                    onSettingsFlag.set(true);
                },
                () -> {
                }
        );

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assertTrue(onSettingsFlag.get());
    }

    @Test
    public void onScoreBoardTest() {
        // given
        AtomicBoolean onScoreBoardFlag = new AtomicBoolean(false);
        MainController controller = new MainController(6, () -> {
        }, () -> {
        }, () -> {
        }, () -> {
        }, () -> {
            onScoreBoardFlag.set(true);
        });

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assertTrue(onScoreBoardFlag.get());
    }

}
