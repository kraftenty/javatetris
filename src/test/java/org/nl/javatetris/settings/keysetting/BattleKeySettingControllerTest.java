package org.nl.javatetris.settings.keysetting;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class BattleKeySettingControllerTest {


    // invalidKeys 에 포함된 키인지 확인하는 메소드
    @Test
    public void invalidKeyTest() {
        // given
        BattleKeySettingController battleKeySettingController = new BattleKeySettingController(11, ()->{});

        // when
        boolean result = battleKeySettingController.isInvalidKey(27); // ESC 키

        // then
        assert (result);
    }

    // 키가 중복되는지 확인하는 메소드
    @Test
    public void duplicatedKeyTest() {
        // given
        BattleKeySettingController battleKeySettingController = new BattleKeySettingController(11, ()->{});

        // when
        boolean result = battleKeySettingController.isNotDuplicatedKey(0, 9999); // 왼쪽 화살표 키

        // then
        assert (result);
    }

    /**
     * 키 입력을 제대로 처리하는지 확인한다.
     */
    @Test
    public void onSettingsTest_1() {
        // given
        AtomicBoolean onKeySettingsFlag = new AtomicBoolean(false);
        BattleKeySettingController controller = new BattleKeySettingController(11, () -> {onKeySettingsFlag.set(true);});

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        KeyEvent downKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);

        // when
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(downKeyEvent);
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(onKeySettingsFlag.get());
    }

    @Test
    public void onSettingsTest_2() {
        // given
        AtomicBoolean onKeySettingsFlag = new AtomicBoolean(false);
        BattleKeySettingController controller = new BattleKeySettingController(11, () -> {onKeySettingsFlag.set(true);});

        KeyEvent escKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ESCAPE, false, false, false, false);

        // when
        controller.handleKeyPress(escKeyEvent);

        // then
        assert(onKeySettingsFlag.get());
    }


}
