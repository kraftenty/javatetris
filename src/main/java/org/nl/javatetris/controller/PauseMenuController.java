package org.nl.javatetris.controller;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

public class PauseMenuController {

    private Runnable onResume;
    private Runnable onBackToMenu;
    private int selectedItemIndex = 0;
    private int menuItemsCount;

    public PauseMenuController(int menuItemsCount, Runnable onResume, Runnable onBackToMenu) {
        this.menuItemsCount = menuItemsCount;
        this.onResume = onResume;
        this.onBackToMenu = onBackToMenu;
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
                        onResume.run();
                        break;
                    case 1:
                        // TODO : 메인 메뉴로 돌아갈 때 게임을 초기화하는 코드를 추가하세요.

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
