package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.ScoreBoardController;

import static org.nl.javatetris.view.ViewConst.WINDOW_HEIGHT;
import static org.nl.javatetris.view.ViewConst.WINDOW_WIDTH;

public class ScoreBoardView implements View {

    private ScoreBoardController scoreBoardController;
    private static Label[] menuItems = new Label[]{
            // 메뉴 항목. 추가할거면 여기에 추가해
            new Label("Main Menu"),
    };

    public ScoreBoardView(Runnable onBackToMenu) {
        this.scoreBoardController = new ScoreBoardController(onBackToMenu);
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("ScoreBoard");
        title.setFont(new Font(20));
        layout.getChildren().add(title);

        // TODO : 스코어보드 뷰 코드를 여기에 짜면됨


        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(new Font(16));
            layout.getChildren().add(menuItem);
        }

        Scene scene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            scoreBoardController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(scoreBoardController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(scoreBoardController.getSelectedItemIndex());

        return scene;
    }

    // 선택된 항목에 따라 UI 업데이트
    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }
}
