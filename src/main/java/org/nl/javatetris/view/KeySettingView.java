package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.KeySettingController;
import org.nl.javatetris.model.settings.Settings;

public class KeySettingView {
    private KeySettingController keySettingController;
    private static Label[] menuItems = new Label[]{
            new Label(Settings.getInstance().getKeySetting().getRotateKeyString()),
            new Label(Settings.getInstance().getKeySetting().getLeftKeyString()),
            new Label(Settings.getInstance().getKeySetting().getRightKeyString()),
            new Label(Settings.getInstance().getKeySetting().getDowntKeyString()),
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

    public KeySettingView(Runnable onSettings) {
        this.keySettingController = new KeySettingController(menuItems.length, onSettings);
    }
    //키 정렬, 간격 수정하기
    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("Key Binding Settings");
        title.setFont(new Font(20));
        layout.getChildren().add(title);

        for (int i = 0; i < menuItems.length - 1; i++) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER);

            Label explainItem = explainItems[i];
            explainItem.setTextFill(Color.BLACK);
            explainItem.setFont(new Font(16));
            HBox.setMargin(explainItem, new javafx.geometry.Insets(0, 20, 0, 0));

            Label menuItem = menuItems[i];
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(new Font(16));
            HBox.setMargin(menuItem, new javafx.geometry.Insets(0, 0, 0, 20));

            row.getChildren().addAll(explainItem, menuItem);
            layout.getChildren().add(row);
        }

        Label backLabel = menuItems[menuItems.length - 1];
        backLabel.setTextFill(Color.WHITE);
        backLabel.setFont(new Font(16));
        layout.getChildren().add(backLabel);

        Scene scene = new Scene(layout, Settings.getInstance().getScreenSizeSettings().getScreenWidth(), Settings.getInstance().getScreenSizeSettings().getScreenHeight());

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            keySettingController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
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
        menuItems[3].setText(Settings.getInstance().getKeySetting().getDowntKeyString());
        menuItems[4].setText(Settings.getInstance().getKeySetting().getDropKeyString());
    }
}
