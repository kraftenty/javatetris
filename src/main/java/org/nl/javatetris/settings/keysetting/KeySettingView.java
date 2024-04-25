package org.nl.javatetris.settings.keysetting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.nl.javatetris.config.FontManager;
import org.nl.javatetris.settings.Settings;

public class KeySettingView {

    private KeySettingController keySettingController;
    private Timeline blinkTimeline;

    public KeySettingView(Runnable onSettings) {
        this.keySettingController = new KeySettingController(menuItems.length, onSettings);
        initializeBlinkTimeline();
    }

    private static Label[] menuItems = new Label[]{
            new Label(Settings.getInstance().getKeySetting().getRotateKeyString()),
            new Label(Settings.getInstance().getKeySetting().getLeftKeyString()),
            new Label(Settings.getInstance().getKeySetting().getRightKeyString()),
            new Label(Settings.getInstance().getKeySetting().getDownKeyString()),
            new Label(Settings.getInstance().getKeySetting().getDropKeyString()),
            new Label("Back")
    };

    private static Label[] explainItems = new Label[]{
            new Label("Rotate"),
            new Label("Move Left"),
            new Label("Move Right"),
            new Label("Move Down"),
            new Label("Drop"),
            //new Label("설정으로 돌아가기")
    };

    private void initializeBlinkTimeline() {
        // 깜빡임 효과를 위한 Timeline 생성
        blinkTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    // 선택된 항목의 색상을 변경
                    if (keySettingController.getSelectedItemIndex() < menuItems.length - 1) {
                        Label selectedItem = menuItems[keySettingController.getSelectedItemIndex()];
                        selectedItem.setTextFill(selectedItem.getTextFill().equals(Color.RED) ? Color.WHITE : Color.RED);
                    }
                }),
                new KeyFrame(Duration.millis(250))
        );
        blinkTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    // 키 설정값을 바꾸려고 엔터를 눌렀을 때 호출되는 메소드
    private void startBlinking(int selectedIndex) {
        if (selectedIndex < menuItems.length - 1) {
//            blinkTimeline.stop(); // 현재 진행 중인 애니메이션을 중지
            blinkTimeline.play(); // 깜빡임 애니메이션 시작
        }
    }

    // 다른 항목을 선택하거나, 설정이 완료되었을 때 깜빡임을 중지하는 메소드
    private void stopBlinking() {
        blinkTimeline.stop();
        // 모든 항목의 색상을 원래대로 되돌림
        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
        }
    }

    //키 정렬, 간격 수정하기
    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 배경 설정
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Label title = new Label("Key Setting");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        layout.getChildren().add(title);

        updateSetting();
        for (int i = 0; i < menuItems.length - 1; i++) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER);

            Label explainItem = explainItems[i];
            explainItem.setTextFill(Color.CYAN);
            explainItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            HBox.setMargin(explainItem, new javafx.geometry.Insets(0, 20, 0, 0));

            Label menuItem = menuItems[i];
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            HBox.setMargin(menuItem, new javafx.geometry.Insets(0, 0, 0, 20));

            row.getChildren().addAll(explainItem, menuItem);
            layout.getChildren().add(row);
        }

        Label backLabel = menuItems[menuItems.length - 1];
        backLabel.setTextFill(Color.WHITE);
        backLabel.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        layout.getChildren().add(backLabel);

        Scene scene = new Scene(
                layout,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

//        // 키 입력에 따른 액션을 처리합니다.
//        scene.setOnKeyPressed(e -> {
//            keySettingController.handleKeyPress(e);
//            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
//            updateSetting();
//            updateMenuItems(keySettingController.getSelectedItemIndex());
//        });

        scene.setOnKeyPressed(e -> {
            keySettingController.handleKeyPress(e);
            // 선택된 항목 변경 시 깜빡임 중지
            stopBlinking();
            // Enter 키가 눌렸을 때 깜빡임 시작
            if (e.getCode() == KeyCode.ENTER) {
                startBlinking(keySettingController.getSelectedItemIndex());
            }
            // UI 업데이트
            updateSetting();
            updateMenuItems(keySettingController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(keySettingController.getSelectedItemIndex());

        return scene;
    }

    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }

    private void updateSetting(){
        menuItems[0].setText(Settings.getInstance().getKeySetting().getRotateKeyString());
        menuItems[1].setText(Settings.getInstance().getKeySetting().getLeftKeyString());
        menuItems[2].setText(Settings.getInstance().getKeySetting().getRightKeyString());
        menuItems[3].setText(Settings.getInstance().getKeySetting().getDownKeyString());
        menuItems[4].setText(Settings.getInstance().getKeySetting().getDropKeyString());
    }
}
