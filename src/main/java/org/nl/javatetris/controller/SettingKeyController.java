package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;
public class SettingKeyController {
    private int meunItemsCount;
    private Runnable onSettings;
    private int selectedItemIndex = 0;

    public SettingKeyController(int menuItemsCount, Runnable onSettings) {
        this.meunItemsCount = menuItemsCount;
        this.onSettings = onSettings;
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
                switch (selectedItemIndex) {
                    //로직 추가예정
                    //엔터 누른 후 변경하고 싶은 키 입력받기?
                    case 0:
                        //회전 조작 키
                        System.out.println("회전 조작 키");
                        break;
                    case 1:
                        System.out.println("왼쪽으로 이동 키");
                        break;
                    case 2:
                        System.out.println("오른쪽으로 이동 키");
                        break;
                    case 3:
                        System.out.println("아래로 이동 키");
                        break;
                    case 4:
                        System.out.println("끝까지 내리는 키");
                        break;

                    case 5:
                        //설정으로 돌아가기
                        onSettings.run();
                        break;

                }
        }
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
