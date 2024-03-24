package org.nl.javatetris.controller;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

public class StartMenuController {

    private int selectedItemIndex = 0;
    private int menuItemsCount;
    private Runnable onStartGame;
    private Runnable onSettings;

    public StartMenuController(int menuItemsCount, Runnable onStartGame, Runnable onSettings) {
        this.menuItemsCount = menuItemsCount;
        this.onStartGame = onStartGame;
        this.onSettings = onSettings;
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
                        onStartGame.run();
                        break;
                    case 1:
                        onSettings.run();
                        break;
                    case 2:
                        System.out.println("Score Board");
                        //TODO : 점수판 run
                        break;
                    case 3:
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
