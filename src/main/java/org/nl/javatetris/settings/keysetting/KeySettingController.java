package org.nl.javatetris.settings.keysetting;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.settings.Settings;
import java.util.Arrays;

import static org.nl.javatetris.config.constant.ControllerConst.invalidKeys;

public class KeySettingController {

    private int menuItemsCount;
    private Runnable onSettings;
    private Runnable onBattleKeySetting;
    private int selectedItemIndex = 0;
    private boolean isWaitingForKey = false; //키 입력대기 여부를 나타내는 변수

    public KeySettingController(int menuItemsCount, Runnable onSettings, Runnable onBattleKeySetting) {
        this.menuItemsCount = menuItemsCount;
        this.onSettings = onSettings;
        this.onBattleKeySetting = onBattleKeySetting;
    }

    public void handleKeyPress(KeyEvent e) {
        if (isWaitingForKey) {
            switch (selectedItemIndex) {
                case 0:
                    // 회전 조작 키 변경
                    if (isNotDuplicatedKey(0, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setRotateKeyValue(e.getCode().getCode());
                    }
                    break;
                case 1:
                    // 왼쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(1, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setLeftKeyValue(e.getCode().getCode());
                    }
                    break;
                case 2:
                    // 오른쪽으로 이동 키 변경
                    if (isNotDuplicatedKey(2, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setRightKeyValue(e.getCode().getCode());
                    }
                    break;
                case 3:
                    // 아래로 이동 키 변경
                    if (isNotDuplicatedKey(3, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setDownKeyValue(e.getCode().getCode());
                    }
                    break;
                case 4:
                    // 끝까지 내리는 키 변경
                    if (isNotDuplicatedKey(4, e.getCode().getCode()) && !isInvalidKey(e.getCode().getCode())) {
                        Settings.getInstance().getKeySetting().setDropKeyValue(e.getCode().getCode());
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
                        isWaitingForKey = true;
                    }
                    else if (selectedItemIndex == 5) {
                        onBattleKeySetting.run();
                    }
                    else {
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
        if (inputKey == Settings.getInstance().getKeySetting().getLeftKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getRightKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getDownKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getDropKeyValue()) return false;
        if (inputKey == Settings.getInstance().getKeySetting().getRotateKeyValue()) return false;
        return true;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
