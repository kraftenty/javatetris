package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.ResetSettingsController;
import org.nl.javatetris.model.settings.Settings;

public class ResetSettingsView {

    private ResetSettingsController resetSettingsController;

    private static Label[] menuItems = new Label[]{
            new Label("Yes"),
            new Label("No"),
    };

    public ResetSettingsView(Runnable onSettings) {
        this.resetSettingsController = new ResetSettingsController(menuItems.length,onSettings);
    }

    public Scene createScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 배경 설정
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Label title= new Label("Are you sure\nto reset\nall settings?");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        layout.getChildren().add(title);


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
            resetSettingsController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(resetSettingsController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(resetSettingsController.getSelectedItemIndex());

        return scene;

    }

    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }
}
