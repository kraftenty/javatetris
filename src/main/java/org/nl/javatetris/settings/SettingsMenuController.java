package org.nl.javatetris.settings;

import javafx.scene.input.KeyEvent;


public class SettingsMenuController {

    private int meunItemsCount;
    private Runnable onBackToMenu;
    private Runnable onCheckingInitSet;
    private Runnable onCheckingBoardInit;
    private Runnable onSettingKeyMenu;
    private Runnable onHandleScreenSizeSettings;
    private int selectedItemIndex = 0;


    public SettingsMenuController(
            int menuItemsCount,
            Runnable onBackToMenu,
            Runnable onCheckingInitSet,
            Runnable onCheckingBoardInit,
            Runnable onSettingKeyMenu,
            Runnable onHandleScreenSizeSettings
    ) {
        this.meunItemsCount = menuItemsCount;
        this.onBackToMenu = onBackToMenu;
        this.onCheckingInitSet=onCheckingInitSet;
        this.onCheckingBoardInit=onCheckingBoardInit;
        this.onSettingKeyMenu=onSettingKeyMenu;
        this.onHandleScreenSizeSettings = onHandleScreenSizeSettings;

    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                SettingsManager.saveSettings();
                onBackToMenu.run();
                break;
            case UP:
                selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                break;
            case DOWN:
                selectedItemIndex = Math.min(meunItemsCount - 1, selectedItemIndex + 1);
                break;
            case ENTER:
                switch(selectedItemIndex) {
                    case 0:
                        //화면 크기 조정
                        switch(Settings.getInstance().getSizeSetting().getOffset()) {
                            case 0:
                                //화면 크기 커짐
                                Settings.getInstance().getSizeSetting().setScreenSizeBigger();
                                onHandleScreenSizeSettings.run();
                                break;
                            case 1:
                                //화면 크기 더 커짐
                                Settings.getInstance().getSizeSetting().setScreenSizeBigger();
                                onHandleScreenSizeSettings.run();
                                break;
                            case 2:
                                //다시 기본화면으로 돌아옴
                                Settings.getInstance().getSizeSetting().setScreenSizeDefault();
                                onHandleScreenSizeSettings.run();
                                break;
                        }
                        break;
                    case 1:
                        //게임 조작 키 변경
                        onSettingKeyMenu.run();
                        break;
                    case 2:
                        // 색깔 모드 변경
                        Settings.getInstance().getColorSetting().roundColorSetting();
                        break;
                    case 3:
                        // 스코어 보드 초기화
                        onCheckingBoardInit.run();
                        break;
                    case 4:
                        // 모든 설정 초기화
                        onCheckingInitSet.run();
                        break;
                    case 5:
                        // 메뉴로
                        SettingsManager.saveSettings();
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
