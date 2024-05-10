package org.nl.javatetris.pause;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.game.play.battle.BattleGamePlayController;

public class PauseMenuController {

    private Runnable onResumeSingleGame;
    private Runnable onResumeBattleGame;
    private Runnable onBackToMenu;
    private int selectedItemIndex = 0;
    private int menuItemsCount;
    private PauseMenuParam pauseMenuParam;

    public PauseMenuController(int menuItemsCount, Runnable onResumeSingleGame, Runnable onResumeBattleGame, Runnable onBackToMenu, PauseMenuParam pauseMenuParam) {
        this.menuItemsCount = menuItemsCount;
        this.onResumeSingleGame = onResumeSingleGame;
        this.onResumeBattleGame = onResumeBattleGame;
        this.onBackToMenu = onBackToMenu;
        this.pauseMenuParam = pauseMenuParam;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                if (pauseMenuParam.getMode() == ControllerConst.PAUSE_MENU_SINGLE_MODE) {
                    onResumeSingleGame.run();
                } else if (pauseMenuParam.getMode() == ControllerConst.PAUSE_MENU_BATTLE_MODE) {
                    pauseMenuParam.getOnResumeGame().run();
                    onResumeBattleGame.run();
                } else {
                    throw new IllegalStateException("Invalid pause menu param");
                }
                break;
            case UP:
                selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                break;
            case DOWN:
                selectedItemIndex = Math.min(menuItemsCount - 1, selectedItemIndex + 1);
                break;
            case ENTER:
                switch(selectedItemIndex) {
                    case 0:
                        if (pauseMenuParam.getMode() == ControllerConst.PAUSE_MENU_SINGLE_MODE) {
                            onResumeSingleGame.run();
                        } else if (pauseMenuParam.getMode() == ControllerConst.PAUSE_MENU_BATTLE_MODE) {
                            pauseMenuParam.getOnResumeGame().run();
                            onResumeBattleGame.run();
                        } else {
                            throw new IllegalStateException("Invalid pause menu param");
                        }
                        break;
                    case 1:
                        pauseMenuParam.getOnShutdownGame().run();
                        onBackToMenu.run();
                        break;
                    case 2:
                        Platform.exit();
                        break;
                }
                break;
        }
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
