package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.SettingsMenuController;
import org.nl.javatetris.model.settings.Settings;

public class SettingsMenuView implements View {

    private SettingsMenuController settingsMenuController;
    private static Label[] menuItems = new Label[]{
            new Label(getLabelOfScreenSizeSetting()),
            new Label("Key Binding Settings"),
            new Label(getLabelOfColorBlindModeSetting()),
            new Label("Initialize ScoreBoard"),
            new Label("Initialize All Settings"),
            new Label("Back"),
    };

    private static String getLabelOfColorBlindModeSetting() {
        if (Settings.getInstance().getColorSetting().getColorOffset() == 0) {
            return "Color Mode : Normal";
        } else if (Settings.getInstance().getColorSetting().getColorOffset() == 1) {
            return "Color Mode : Red-Green Color Blindness";
        } else {
            return "Color Mode : Blue Blindness";
        }
    }
    private static String getLabelOfScreenSizeSetting() {
        if (Settings.getInstance().getScreenSizeSettings().getOffset() == 0) {
            return "Window Size : Small";
        } else if (Settings.getInstance().getScreenSizeSettings().getOffset() == 1) {
            return "Window Size : Medium";
        } else {
            return "Window Size : Big";
        }
    }
    public SettingsMenuView(Runnable onBackToMenu, Runnable onCheckingInitSet, Runnable onChekingBoardInit, Runnable onSettingKeyMenu) {
        this.settingsMenuController = new SettingsMenuController(menuItems.length,
                onBackToMenu,
                onCheckingInitSet,
                onChekingBoardInit,
                onSettingKeyMenu
                );
    }

    public Scene createScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("Settings");
        title.setFont(new Font(20));
        layout.getChildren().add(title);

        updateSetting();
        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(new Font(16));
            layout.getChildren().add(menuItem);
        }

        Scene scene = new Scene(
                layout,
                Settings.getInstance().getScreenSizeSettings().getScreenWidth(),
                Settings.getInstance().getScreenSizeSettings().getScreenHeight()
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
        menuItems[0].setText(getLabelOfScreenSizeSetting());
        menuItems[2].setText(getLabelOfColorBlindModeSetting());
    }

}
