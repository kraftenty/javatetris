package org.nl.javatetris.gameplay.item;

import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

public class ItemModeLobbyController {

    private Runnable onBackToMenu;
    private Consumer<Integer> onStartGame;
    private int selectedItemIndex = 0;
    private int menuItemsCount;

    public ItemModeLobbyController(int menuItemsCount, Runnable onBackToMenu, Consumer<Integer> onStartGame) {
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
                        onStartGame.accept(1);
                        break;
                    case 1:
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
