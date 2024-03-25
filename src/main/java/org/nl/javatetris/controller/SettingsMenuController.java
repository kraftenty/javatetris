package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;

public class SettingsMenuController {

    private int meunItemsCount;
    private Runnable onBack;
    private int selectedItemIndex = 0;

    public SettingsMenuController(int menuItemsCount, Runnable onResume) {
        this.meunItemsCount = menuItemsCount;
        this.onBack = onResume;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                break;
            case DOWN:
                selectedItemIndex = Math.min(meunItemsCount - 1, selectedItemIndex + 1);
                break;
            case ENTER:
                switch(selectedItemIndex){
                    // TODO : 설정 메뉴 로직들. 추가할거면 여기에 추가해
                    case 0:
                        System.out.println("hello1");
                        break;
                    case 1:
                        System.out.println("hello2");
                        break;
                    case 2:
                        onBack.run();
                        break;
                }
                break;
        }
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
}
