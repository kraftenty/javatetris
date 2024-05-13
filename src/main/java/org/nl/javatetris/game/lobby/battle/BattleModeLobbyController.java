package org.nl.javatetris.game.lobby.battle;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.game.GameParam;

import java.util.function.Consumer;


public class BattleModeLobbyController {

    private Runnable onBackToMenu;
    private Consumer<GameParam> onStartGame;
    private int selectedItemIndex = 0;
    private int menuItemsCount;

    public BattleModeLobbyController(int menuItemsCount, Runnable onBackToMenu, Consumer<GameParam> onStartGame) {
        this.menuItemsCount = menuItemsCount;
        this.onBackToMenu = onBackToMenu;
        this.onStartGame = onStartGame;
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
                        onStartGame.accept(new GameParam(ControllerConst.BATTLE_CLASSIC, ControllerConst.DIFFICULTY_NORMAL));
                        break;
                    case 1:
                        onStartGame.accept(new GameParam(ControllerConst.BATTLE_ITEM, ControllerConst.DIFFICULTY_NORMAL));
                        break;
                    case 2:
                        onStartGame.accept(new GameParam(ControllerConst.BATTLE_TIME_ATTACK, ControllerConst.DIFFICULTY_NORMAL));
                        break;
                    case 3:
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
