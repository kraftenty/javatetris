package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.model.settings.Settings;
import org.nl.javatetris.model.settings.SettingsUtil;


public class SettingsMenuController {

    private int meunItemsCount;
    private Runnable onBack;
    private Runnable onCheckingInitSet;
    private Runnable onCheckingBoardInit;
    private Runnable onSettingKeyMenu;
    private int selectedItemIndex = 0;

    private int colorBlindModeIndex=0;

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
                    case 0:
                        //화면 크기 조정
                        switch(Settings.getInstance().getScreenSizeSettings().getOffset()) {
                            case 0:
                                System.out.println("화면 크기 커짐"); //화면 크기 커짐
                                Settings.getInstance().getScreenSizeSettings().setScreenSizeBigger();
                                System.out.println(Settings.getInstance().getScreenSizeSettings().getScreenWidth());
                                break;
                            case 1:
                                System.out.println("화면 크기 더 커짐"); //화면 크기 더 커짐
                                Settings.getInstance().getScreenSizeSettings().setScreenSizeBigger();
                                System.out.println(Settings.getInstance().getScreenSizeSettings().getScreenWidth());
                                break;
                            case 2:
                                System.out.println("다시 원래크기로 돌아옴"); //다시 기본화면으로 돌아옴
                                Settings.getInstance().getScreenSizeSettings().setScreenSizeDefault();
                                System.out.println(Settings.getInstance().getScreenSizeSettings().getScreenWidth());
                                break;
                        }
                        break;
                    case 1:
                        //게임 조작 키 변경
                        onSettingKeyMenu.run();
                        break;
                    case 2:
                        Settings.getInstance().getColorSetting().roundColorSetting();
                        break;

                    case 3: //스코어 보드 초기화
                        onCheckingBoardInit.run();
                        break;

                    case 4:  // 모든 설정 초기화
                        onCheckingInitSet.run();
                        break;
                    case 5:
//                        Settings.saveSettings(); //setting 메뉴 나갈 때 저장
                        SettingsUtil.saveSettings(); //TODO
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
