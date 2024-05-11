package org.nl.javatetris.settings.keysetting;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.settings.Settings;

import java.util.Arrays;

import static org.nl.javatetris.config.constant.ControllerConst.invalidKeys;

public class BattleKeySettingController {

    private int menuItemsCount;
    private Runnable onKeySettings;
    private int selectedItemIndex = 0;
    private boolean isWaitingForKey = false; //키 입력대기 여부를 나타내는 변수

    public BattleKeySettingController(int menuItemsCount, Runnable onKeySettings) {
        this.menuItemsCount = menuItemsCount;
        this.onKeySettings = onKeySettings;
    }

    public void handleKeyPress(KeyEvent e) {
        if (isWaitingForKey) {
            switch (selectedItemIndex) {
                case 0:
                    // P1 회전 조작 키 변경
                    if (isNotDuplicatedKey(0, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP1RotateKeyValue(e.getCode().getCode());
                    }
                    break;
                case 1:
                    // P1 왼쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(1, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP1LeftKeyValue(e.getCode().getCode());
                    }
                    break;
                case 2:
                    // P1 오른쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(2, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP1RightKeyValue(e.getCode().getCode());
                    }
                    break;
                case 3:
                    // P1 아래로 이동 키 변경
                    if (isNotDuplicatedKey(3, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP1DownKeyValue(e.getCode().getCode());
                    }
                    break;
                case 4:
                    // P1 끝까지 내리는 키 변경
                    if (isNotDuplicatedKey(4, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP1DropKeyValue(e.getCode().getCode());
                    }
                    break;
                case 5:
                    // P2 회전 조작 키 변경
                    if (isNotDuplicatedKey(0, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP2RotateKeyValue(e.getCode().getCode());
                    }
                    break;
                case 6:
                    // P2 왼쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(1, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP2LeftKeyValue(e.getCode().getCode());
                    }
                    break;
                case 7:
                    // P3 오른쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(2, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP2RightKeyValue(e.getCode().getCode());
                    }
                    break;
                case 8:
                    // P4 아래로 이동 키 변경
                    if (isNotDuplicatedKey(3, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP2DownKeyValue(e.getCode().getCode());
                    }
                    break;
                case 9:
                    // P5 끝까지 내리는 키 변경
                    if (isNotDuplicatedKey(4, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setP2DropKeyValue(e.getCode().getCode());
                    }
                    break;
            }
            isWaitingForKey = false; // 키 입력 대기 종료
        } else {
            // 키 입력 대기 중이 아닌 경우
            switch (e.getCode()) {
                case ESCAPE:
                    onKeySettings.run();
                    break;
                case UP:
                    selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                    break;
                case DOWN:
                    selectedItemIndex = Math.min(menuItemsCount - 1, selectedItemIndex + 1);
                    break;
                case ENTER:
                    if (selectedItemIndex < 10) {
                        // 키 변경 항목 선택 시 키 입력 대기로 전환
                        isWaitingForKey = true;
                    } else {
                        // 설정으로 돌아가기
                        onKeySettings.run();
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
        if (inputKey == Settings.getInstance().getKeySetting().getP1LeftKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP1RightKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP1DownKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP1DropKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP1RotateKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP2LeftKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP2RightKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP2DownKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP2DropKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getP2RotateKeyValue()) return false;
        return true;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
