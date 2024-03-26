package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.SettingKeyController;

import static org.nl.javatetris.view.ViewConst.*;

public class SettingsKeyMenuView {
    private SettingKeyController settingKeyController;
    private static Label[] menuItems = new Label[]{
            new Label("Up"),
            new Label("Left"),
            new Label("Right"),
            new Label("Down"),
            new Label("Space Bar"),
            new Label("Back to settings")
    };

    private static Label[] explainItems = new Label[]{
            new Label("회전"),
            new Label("왼쪽으로 이동"),
            new Label("오른쪽으로 이동"),
            new Label("한칸 아래로 이동"),
            new Label("한번에 끝까지 내리기"),
            //new Label("설정으로 돌아가기")
    };

    public SettingsKeyMenuView(Runnable onSettings) {
        this.settingKeyController = new SettingKeyController(menuItems.length, onSettings);
    }
    //키 정렬, 간격 수정하기
    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("Settings");
        title.setFont(new Font(20));
        layout.getChildren().add(title);

        Text title2 = new Text("조작 키 설정");
        title2.setFont(new Font(18));
        layout.getChildren().add(title2);

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

        Scene scene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            settingKeyController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(settingKeyController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(settingKeyController.getSelectedItemIndex());

        return scene;
    }

    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }
}
