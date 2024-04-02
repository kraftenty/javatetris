package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.model.settings.Settings;
import java.util.Arrays;

import static org.nl.javatetris.controller.ControllerConst.invalidKeys;

public class KeySettingController {

    private int menuItemsCount;
    private Runnable onSettings;
    private int selectedItemIndex = 0;
    private boolean isWaitingForKey = false; //키 입력대기 여부를 나타내는 변수

    public KeySettingController(int menuItemsCount, Runnable onSettings) {
        this.menuItemsCount = menuItemsCount;
        this.onSettings = onSettings;
    }

    public void handleKeyPress(KeyEvent e) {
        if (isWaitingForKey) {
            switch (selectedItemIndex) {
                case 0:
                    // 회전 조작 키 변경
                    if (isNotDuplicatedKey(0, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setRotateKeyValue(e.getCode().getCode());
                        System.out.println("회전 조작 키가 변경되었습니다.");
                    } else {
                        System.out.println("회전 조작 키가 변경되지 않았습니다.");

                    }
                    break;
                case 1:
                    // 왼쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(1, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setLeftKeyValue(e.getCode().getCode());
                        System.out.println("왼쪽으로 이동 키가 변경되었습니다.");
                    }
                    break;
                case 2:
                    // 오른쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(2, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setRightKeyValue(e.getCode().getCode());
                        System.out.println("오른쪽으로 이동 키가 변경되었습니다.");
                    }
                    break;
                case 3:
                    // 아래로 이동 키 변경
                    if (isNotDuplicatedKey(3, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setDownKeyValue(e.getCode().getCode());
                        System.out.println("아래로 이동 키가 변경되었습니다.");
                    }
                    break;
                case 4:
                    // 끝까지 내리는 키 변경
                    if (isNotDuplicatedKey(4, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setDropKeyValue(e.getCode().getCode());
                        System.out.println("끝까지 내리는 키가 변경되었습니다.");
                    }
                    break;
            }
            isWaitingForKey = false; // 키 입력 대기 종료
        } else {
            // 키 입력 대기 중이 아닌 경우
            switch (e.getCode()) {
                case ESCAPE:
                    onSettings.run();
                    break;
                case UP:
                    selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                    break;
                case DOWN:
                    selectedItemIndex = Math.min(menuItemsCount - 1, selectedItemIndex + 1);
                    break;
                case ENTER:
                    if (selectedItemIndex < 5) {
                        // 키 변경 항목 선택 시 키 입력 대기로 전환
                        System.out.println("waiting for key input...");
                        isWaitingForKey = true;
                    } else {
                        // 설정으로 돌아가기
                        onSettings.run();
                    }
                    break;
            }
        }
    }

    public boolean isInvalidKey(int key) {
        if (Arrays.stream(invalidKeys).anyMatch(i -> i == key)) return true;
        return false;
    }

    public boolean isNotDuplicatedKey(int nowSelected, int inputKey) {
        if (nowSelected!=0 && inputKey == Settings.getInstance().getKeySetting().getLeftKeyValue()) return false;
        if (nowSelected!=1 && inputKey == Settings.getInstance().getKeySetting().getRightKeyValue()) return false;
        if (nowSelected!=2 && inputKey == Settings.getInstance().getKeySetting().getDownKeyValue()) return false;
        if (nowSelected!=3 && inputKey == Settings.getInstance().getKeySetting().getDropKeyValue()) return false;
        if (nowSelected!=4 && inputKey == Settings.getInstance().getKeySetting().getRotateKeyValue()) return false;
        return true;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
