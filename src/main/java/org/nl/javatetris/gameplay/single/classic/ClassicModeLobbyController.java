package org.nl.javatetris.gameplay.single.classic;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.gameplay.GameParam;

import java.util.function.Consumer;

public class ClassicModeLobbyController {

    private Runnable onBackToMenu;
    private Consumer<GameParam> onStartGame;
    private int selectedItemIndex = 0;
    private int menuItemsCount;
    private int difficulty = 0;

    public ClassicModeLobbyController(int menuItemsCount, Runnable onBackToMenu, Consumer<GameParam> onStartGame) {
        this.menuItemsCount = menuItemsCount;
        this.onBackToMenu = onBackToMenu;
        this.onStartGame = onStartGame;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                onBackToMenu.run();
                break;
            case UP:
                selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                break;
            case DOWN:
                selectedItemIndex = Math.min(menuItemsCount - 1, selectedItemIndex + 1);
                break;
            case ENTER:
                switch (selectedItemIndex) {
                    case 0:
                        if (difficulty == 0) {
                            difficulty = 1;
                        } else if (difficulty == 1) {
                            difficulty = 2;
                        } else {
                            difficulty = 0;
                        }
                        break;
                    case 1:
                        onStartGame.accept(new GameParam(ControllerConst.SINGLE_CLASSIC, difficulty));
                        break;
                    case 2:
                        onBackToMenu.run();
                        break;
                }
                break;
        }
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
