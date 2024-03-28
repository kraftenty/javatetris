package org.nl.javatetris.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.ScoreBoardController;
import org.nl.javatetris.model.score.Score;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        //json파일에서 읽어오기
        List<Score> scoreboard = loadScoreboard("src/main/resources/scoreboard.json");

        //내림차순 정렬
        scoreboard.sort(Comparator.comparingInt(Score::getScore).reversed());

        //스코어 10개 내림차순으로 display
        int count = 0;
        for (Score score : scoreboard) {
            if (count >= 10) {
                break;
            }
            Label scoreLabel = new Label(score.getName() + ": " + score.getScore());
            scoreLabel.setFont(new Font(16));
            layout.getChildren().add(scoreLabel);
            count++;
        }

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
    //json파일로부터 scoreboard 읽어오기
    private List<Score> loadScoreboard(String filename) {
        List<Score> scoreboard = new ArrayList<>();
        try {
            Gson gson = new Gson();
            scoreboard = gson.fromJson(new FileReader(filename), new TypeToken<List<Score>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreboard;
    }
    // 선택된 항목에 따라 UI 업데이트
    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }
}