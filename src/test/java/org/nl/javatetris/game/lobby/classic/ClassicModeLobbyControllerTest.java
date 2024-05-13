package org.nl.javatetris.game.lobby.classic;

import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.lobby.classic.ClassicModeLobbyController;

import java.util.function.Consumer;

import static javafx.scene.input.KeyCode.*;

public class ClassicModeLobbyControllerTest {
    private String output = "";

    @Test
    public void test() {
        ClassicModeLobbyController classicModeLobbyController = new ClassicModeLobbyController(
                3,
                ()->{output = "BackToMenu";},
                new Consumer<GameParam>() {
                    @Override
                    public void accept(GameParam gameParam) {
                        output = "Start";
                    }
                }
        );
        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(1, classicModeLobbyController.getSelectedItemIndex());
        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("Start", output);

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", DOWN, false, false, false, false));
        Assertions.assertEquals(2, classicModeLobbyController.getSelectedItemIndex());

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals("BackToMenu", output);

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false));
        Assertions.assertEquals(1, classicModeLobbyController.getSelectedItemIndex());

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", UP, false, false, false, false));
        Assertions.assertEquals(0, classicModeLobbyController.getSelectedItemIndex());

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals(1, classicModeLobbyController.getDifficulty());

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals(2, classicModeLobbyController.getDifficulty());

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ENTER, false, false, false, false));
        Assertions.assertEquals(0, classicModeLobbyController.getDifficulty());

        classicModeLobbyController.handleKeyPress(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", ESCAPE, false, false, false, false));
        Assertions.assertEquals("BackToMenu", output);

    }

}
