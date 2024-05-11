package org.nl.javatetris.game.lobby.battle;

import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.game.GameParam;

import java.util.function.Consumer;

import static javafx.scene.input.KeyCode.*;

public class BattleModeLobbyControllerTest {
    private String output = "";

    @Test
    public void testHandleKeyPress() {
        BattleModeLobbyController battleModeLobbyController = new BattleModeLobbyController(
                4,
                () -> output = "BackToMenu",
                new Consumer<GameParam>() {
                    @Override
                    public void accept(GameParam gameParam) {
                        output = "Start:" + gameParam.getMode();
                    }
                }
        );


        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(1, battleModeLobbyController.getSelectedItemIndex());

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("Start:" + ControllerConst.BATTLE_ITEM, output);

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(2, battleModeLobbyController.getSelectedItemIndex());

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("Start:" + ControllerConst.BATTLE_TIME_ATTACK, output);

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false));
        Assertions.assertEquals(1, battleModeLobbyController.getSelectedItemIndex());

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false));
        Assertions.assertEquals(0, battleModeLobbyController.getSelectedItemIndex());

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("Start:" + ControllerConst.BATTLE_CLASSIC, output);

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false));
        Assertions.assertEquals("BackToMenu", output);

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(3, battleModeLobbyController.getSelectedItemIndex());

        battleModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("BackToMenu", output);
    }
}
