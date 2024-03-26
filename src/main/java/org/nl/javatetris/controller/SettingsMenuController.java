package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;

public class SettingsMenuController {

    private int meunItemsCount;
    private Runnable onBack;
    private Runnable onCheckingInitSet;
    private Runnable onCheckingBoardInit;
    private Runnable onSettingKeyMenu;
    private int selectedItemIndex = 0;

    public SettingsMenuController(int menuItemsCount, Runnable onResume, Runnable onCheckingInitSet, Runnable onCheckingBoardInit, Runnable onSettingKeyMenu) {
        this.meunItemsCount = menuItemsCount;
        this.onBack = onResume;
        this.onCheckingInitSet=onCheckingInitSet;
        this.onCheckingBoardInit=onCheckingBoardInit;
        this.onSettingKeyMenu=onSettingKeyMenu;
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
                switch(selectedItemIndex) {
                    // TODO : 설정 메뉴 로직들. 추가할거면 여기에 추가해
                    //로직 추가예정
                    case 0:
                        //화면 크기 조정
                        System.out.println("화면 크기 조정");
                        break;
                    case 1:
                        //게임 조작 키 변경
                        onSettingKeyMenu.run();
                        break;
                    case 2:
                        System.out.println("색맹모드");
                        break;

                    case 3:
                        onCheckingBoardInit.run();
                        break;

                    case 4:
                        onCheckingInitSet.run();
                        break;
                    case 5:
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
