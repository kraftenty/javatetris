package org.nl.javatetris.settings;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.nl.javatetris.config.FontManager;

public class SettingsMenuView {

    private SettingsMenuController settingsMenuController;

    public SettingsMenuView(
            Runnable onBackToMenu,
            Runnable onCheckingInitSet,
            Runnable onChekingBoardInit,
            Runnable onSettingKeyMenu,
            Runnable onHandleScreenSizeSettings
            ) {
        this.settingsMenuController = new SettingsMenuController(menuItems.length,
                onBackToMenu,
                onCheckingInitSet,
                onChekingBoardInit,
                onSettingKeyMenu,
                onHandleScreenSizeSettings
        );
    }
    private static Label[] menuItems = new Label[]{
            new Label(getLabelOfWindowSizeSetting()),
            new Label("Key Setting"),
            new Label(getLabelOfColorBlindModeSetting()),
            new Label("Reset ScoreBoard"),
            new Label("Reset All Settings"),
            new Label("Back"),
    };



    private static String getLabelOfWindowSizeSetting() {
        if (Settings.getInstance().getSizeSetting().getOffset() == 0) {
            return "Small";
        } else if (Settings.getInstance().getSizeSetting().getOffset() == 1) {
            return "Medium";
        } else {
            return "Big";
        }
    }

    private static String getLabelOfColorBlindModeSetting() {
        if (Settings.getInstance().getColorSetting().getColorOffset() == 0) {
            return "Normal";
        } else if (Settings.getInstance().getColorSetting().getColorOffset() == 1) {
            return "Red-Green Blindness";
        } else {
            return "Blue Blindness";
        }
    }



    public Scene createScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 배경 설정
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Label title = new Label("Settings");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        layout.getChildren().add(title);

        updateSetting();
        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            layout.getChildren().add(menuItem);
        }

        Scene scene = new Scene(
                layout,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            settingsMenuController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateSetting();
            updateMenuItems(settingsMenuController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(settingsMenuController.getSelectedItemIndex());

        return scene;

    }

    // 선택된 항목에 따라 UI 업데이트
    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }

    // 화면에 나타나는 설정을 업데이트
    private void updateSetting(){
        menuItems[0].setText("window size - " + getLabelOfWindowSizeSetting());
        menuItems[2].setText("color - " + getLabelOfColorBlindModeSetting());
    }

}
