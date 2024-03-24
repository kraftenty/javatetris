package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.StartMenuController;

import static org.nl.javatetris.view.ViewConst.*;

public class StartMenuScene {

    private StartMenuController startMenuController;
    private static Label[] menuItems = new Label[]{
            // 메뉴 항목. 추가할거면 여기에 추가해
            new Label("Start Game"),
            new Label("Settings"),
            new Label("Score Board"),
            new Label("Quit")
    };


    public StartMenuScene(Runnable onStartGame, Runnable onSettings) {
        this.startMenuController = new StartMenuController(menuItems.length, onStartGame, onSettings);
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 게임의 이름
        Text title = new Text("Tetris");
        title.setFont(new Font(20));
        layout.getChildren().add(title);

        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(new Font(16));
            layout.getChildren().add(menuItem);
        }

        Scene scene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            startMenuController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(startMenuController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(startMenuController.getSelectedItemIndex());

        return scene;

    }

    // 선택된 항목에 따라 UI 업데이트
    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }
}
