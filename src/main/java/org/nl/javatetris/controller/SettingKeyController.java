package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.model.settings.Settings;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import static org.nl.javatetris.controller.ControllerConst.invalidKeys;

public class SettingKeyController {
    private int menuItemsCount;
    private Runnable onSettings;
    private int selectedItemIndex = 0;
    private boolean isWaitingForKey = false; //키 입력대기 여부를 나타내는 변수

    public SettingKeyController(int menuItemsCount, Runnable onSettings) {
        this.menuItemsCount = menuItemsCount;
        this.onSettings = onSettings;
    }

    public void handleKeyPress(KeyEvent e) {
        if (isWaitingForKey) {
            // 키 입력 대기 중인 경우
            if (isInvalidKey(e.getCode().getCode())) {
                System.out.println("유효하지 않은 키입니다.");
                return;
            }
            if (isduplicated(e.getCode().getCode())) {
                System.out.println("중복된 키입니다.");
                return;
            }
            switch (selectedItemIndex) {
                case 0:
                    // 회전 조작 키 변경
                    Settings.getInstance().getKeySetting().setRotateKeyValue(e.getCode().getCode());
                    System.out.println("회전 조작 키가 변경되었습니다.");
                    break;
                case 1:
                    // 왼쪽으로 이동 키 변경
                    Settings.getInstance().getKeySetting().setLeftKeyValue(e.getCode().getCode());
                    System.out.println("왼쪽으로 이동 키가 변경되었습니다.");
                    break;
                case 2:
                    // 오른쪽으로 이동 키 변경
                    Settings.getInstance().getKeySetting().setRightKeyValue(e.getCode().getCode());
                    System.out.println("오른쪽으로 이동 키가 변경되었습니다.");
                    break;
                case 3:
                    // 아래로 이동 키 변경
                    Settings.getInstance().getKeySetting().setDownKeyValue(e.getCode().getCode());
                    System.out.println("아래로 이동 키가 변경되었습니다.");
                    break;
                case 4:
                    // 끝까지 내리는 키 변경
                    Settings.getInstance().getKeySetting().setDropKeyValue(e.getCode().getCode());
                    System.out.println("끝까지 내리는 키가 변경되었습니다.");
                    break;
            }
            isWaitingForKey = false; // 키 입력 대기 종료
        } else {
            // 키 입력 대기 중이 아닌 경우
            switch (e.getCode()) {
                case UP:
                    selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                    break;
                case DOWN:
                    selectedItemIndex = Math.min(menuItemsCount - 1, selectedItemIndex + 1);
                    break;
                case ENTER:
                    if (selectedItemIndex < 5) {
                        // 키 변경 항목 선택 시 키 입력 대기로 전환
                        System.out.println("키를 눌러 변경하세요.");
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
    public boolean isduplicated(int key) {
        int rotate_key = Settings.getInstance().getKeySetting().getRotateKeyValue();
        int down_key = Settings.getInstance().getKeySetting().getDownKeyValue();
        int left_key = Settings.getInstance().getKeySetting().getLeftKeyValue();
        int right_key = Settings.getInstance().getKeySetting().getRightKeyValue();
        int drop_key = Settings.getInstance().getKeySetting().getDropKeyValue();

        if (key == rotate_key) return true;
        if (key == down_key) return true;
        if (key == left_key) return true;
        if (key == right_key) return true;
        if (key == drop_key) return true;

        return false;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
}
