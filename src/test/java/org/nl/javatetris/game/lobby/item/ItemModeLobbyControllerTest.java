package org.nl.javatetris.game.lobby.item;

import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.lobby.item.ItemModeLobbyController;

import java.util.function.Consumer;

import static javafx.scene.input.KeyCode.*;

public class ItemModeLobbyControllerTest {
    private String output = "";

    @Test
    public void test() {
        ItemModeLobbyController itemModeLobbyController = new ItemModeLobbyController(
                2,
                ()->{output = "BackToMenu";},
                new Consumer<GameParam>() {
                    @Override
                    public void accept(GameParam gameParam) {
                        output = "Start";
                    }
                }
        );
        itemModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("Start", output);
        itemModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(1, itemModeLobbyController.getSelectedItemIndex());
        itemModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("BackToMenu", output);
        itemModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false));
        Assertions.assertEquals(0, itemModeLobbyController.getSelectedItemIndex());
        itemModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false));
        Assertions.assertEquals("BackToMenu", output);
    }
}
