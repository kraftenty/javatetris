package org.nl.javatetris.settings.resetsettings;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class ResetSettingsControllerTest {

    /**
     * 키 입력을 제대로 처리하는지 확인한다.
     */
    @Test
    public void onSettingsTest() {
        // given
        AtomicBoolean onSettingsFlag = new AtomicBoolean(false);
        ResetSettingsController controller = new ResetSettingsController(
                2,
                () -> {onSettingsFlag.set(true);}
        );

        KeyEvent enterKeyEvent = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false);

        // when
        controller.handleKeyPress(enterKeyEvent);

        // then
        assert(onSettingsFlag.get());
    }
}
