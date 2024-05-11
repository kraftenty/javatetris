package org.nl.javatetris.settings.keysetting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
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
import org.nl.javatetris.config.manager.FontManager;
import org.nl.javatetris.settings.Settings;

public class BattleKeySettingView {

    private BattleKeySettingController battleKeySettingController;
    private Timeline blinkTimeline;

    public BattleKeySettingView(Runnable onSettings) {
        this.battleKeySettingController = new BattleKeySettingController(menuItems.length, onSettings);
        initializeBlinkTimeline();
    }

    private static Label[] menuItems = new Label[]{
            new Label(Settings.getInstance().getKeySetting().getP1RotateKeyString()),
            new Label(Settings.getInstance().getKeySetting().getP1LeftKeyString()),
            new Label(Settings.getInstance().getKeySetting().getRightKeyString()),
            new Label(Settings.getInstance().getKeySetting().getDownKeyString()),
            new Label(Settings.getInstance().getKeySetting().getDropKeyString()),
            new Label(Settings.getInstance().getKeySetting().getRotateKeyString()),
            new Label(Settings.getInstance().getKeySetting().getLeftKeyString()),
            new Label(Settings.getInstance().getKeySetting().getRightKeyString()),
            new Label(Settings.getInstance().getKeySetting().getDownKeyString()),
            new Label(Settings.getInstance().getKeySetting().getDropKeyString()),
            new Label("Back")
    };

    private static Label[] explainItems = new Label[]{
            new Label("P1 Rotate"),
            new Label("P1 Move Left"),
            new Label("P1 Move Right"),
            new Label("P1 Move Down"),
            new Label("P1 Drop"),
            new Label("P2 Rotate"),
            new Label("P2 Move Left"),
            new Label("P2 Move Right"),
            new Label("P2 Move Down"),
            new Label("P2 Drop"),
            //new Label("설정으로 돌아가기")
    };

    private void initializeBlinkTimeline() {
        // 깜빡임 효과를 위한 Timeline 생성
        blinkTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    // 선택된 항목의 색상을 변경
                    if (battleKeySettingController.getSelectedItemIndex() < menuItems.length - 1) {
                        Label selectedItem = menuItems[battleKeySettingController.getSelectedItemIndex()];
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

        Label title = new Label("Battle Key Setting");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        layout.getChildren().add(title);

        updateSetting();
        for (int i = 0; i < menuItems.length - 1; i++) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER);

            Label explainItem = explainItems[i];
            explainItem.setTextFill(Color.CYAN);
            explainItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()*0.8));
            HBox.setMargin(explainItem, new Insets(0, 20, 0, 0));

            Label menuItem = menuItems[i];
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()*0.8));
            HBox.setMargin(menuItem, new Insets(0, 0, 0, 20));

            row.getChildren().addAll(explainItem, menuItem);
            layout.getChildren().add(row);
        }

        Label backLabel = menuItems[menuItems.length - 1];
        backLabel.setTextFill(Color.WHITE);
        backLabel.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()*0.8));
        layout.getChildren().add(backLabel);
        addKeyControlHints(layout);

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
            battleKeySettingController.handleKeyPress(e);
            // 선택된 항목 변경 시 깜빡임 중지
            stopBlinking();
            // Enter 키가 눌렸을 때 깜빡임 시작
            if (e.getCode() == KeyCode.ENTER) {
                startBlinking(battleKeySettingController.getSelectedItemIndex());
            }
            // UI 업데이트
            updateSetting();
            updateMenuItems(battleKeySettingController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(battleKeySettingController.getSelectedItemIndex());

        return scene;
    }

    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }

    private void updateSetting(){
        menuItems[0].setText(Settings.getInstance().getKeySetting().getP1RotateKeyString());
        menuItems[1].setText(Settings.getInstance().getKeySetting().getP1LeftKeyString());
        menuItems[2].setText(Settings.getInstance().getKeySetting().getP1RightKeyString());
        menuItems[3].setText(Settings.getInstance().getKeySetting().getP1DownKeyString());
        menuItems[4].setText(Settings.getInstance().getKeySetting().getP1DropKeyString());
        menuItems[5].setText(Settings.getInstance().getKeySetting().getP2RotateKeyString());
        menuItems[6].setText(Settings.getInstance().getKeySetting().getP2LeftKeyString());
        menuItems[7].setText(Settings.getInstance().getKeySetting().getP2RightKeyString());
        menuItems[8].setText(Settings.getInstance().getKeySetting().getP2DownKeyString());
        menuItems[9].setText(Settings.getInstance().getKeySetting().getP2DropKeyString());
    }

    // KeyControl hint를 표시
    private void addKeyControlHints(VBox layout) {
        Label keyControlHints = new Label(
                "Up/Down to move, Enter to Select \n\n" +
                "Select and Press key want to use"
        );
        keyControlHints.setFont(FontManager.getSquareFont((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2))); // Smaller font size
        keyControlHints.setTextFill(Color.LIGHTGREY);
        VBox.setMargin(keyControlHints, new Insets((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2), 0, 0, 0)); // Add top margin to push the label down
        layout.getChildren().add(keyControlHints);
    }
}
