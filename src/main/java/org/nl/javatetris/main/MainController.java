package org.nl.javatetris.main;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

public class MainController {

    private int selectedItemIndex = 0;
    private int menuItemsCount;
    private Runnable onClassicModeLobby;
    private Runnable onItemModeLobby;
    private Runnable onBattleModeLobby;
    private Runnable onSettings;
    private Runnable onScoreBoard;

    public MainController(int menuItemsCount, Runnable onClassicModeLobby, Runnable onItemModeLobby, Runnable onBattleModeLobby, Runnable onSettings, Runnable onScoreBoard) {
        this.menuItemsCount = menuItemsCount;
        this.onClassicModeLobby = onClassicModeLobby;
        this.onItemModeLobby = onItemModeLobby;
        this.onBattleModeLobby = onBattleModeLobby;
        this.onSettings = onSettings;
        this.onScoreBoard = onScoreBoard;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                break;
            case DOWN:
                selectedItemIndex = Math.min(menuItemsCount - 1, selectedItemIndex + 1);
                break;
            case ENTER:
                switch(selectedItemIndex) {
                    case 0:
                        onClassicModeLobby.run();
                        break;
                    case 1:
                        onItemModeLobby.run();
                        break;
                    case 2:
                        onBattleModeLobby.run();
                        break;
                    case 3:
                        onSettings.run();
                        break;
                    case 4:
                        onScoreBoard.run();
                        break;
                    case 5:
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
