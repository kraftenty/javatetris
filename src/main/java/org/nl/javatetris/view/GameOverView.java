package org.nl.javatetris.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.GameOverController;
import org.nl.javatetris.model.score.ScoreBoard;
import org.nl.javatetris.model.settings.Settings;

import java.util.Optional;

public class GameOverView implements View {

    private GameOverController gameOverController;
    private Runnable onBackToScoreBoard;
    private Integer point;

    public GameOverView(Integer point, Runnable onBackToScoreBoard) {
        this.point = point;
        this.gameOverController = new GameOverController(onBackToScoreBoard);
        this.onBackToScoreBoard = onBackToScoreBoard;
    }

    public Scene createScene() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Settings.getInstance().getScreenSizeSettings().getScreenWidth(), Settings.getInstance().getScreenSizeSettings().getScreenHeight());

        Text levelText = new Text("Your Score : " + point);
        levelText.setFont(Font.font("Arial", 26));
        levelText.setFill(Color.BLACK);
        levelText.setLayoutX(Settings.getInstance().getScreenSizeSettings().getScreenWidth()/2);
        levelText.setLayoutY(100);
        pane.getChildren().add(levelText);

        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Name Entry");
            dialog.setHeaderText("Enter Your Name");
            dialog.setContentText("Name:");

            // 'Cancel' 버튼 제거
            dialog.getDialogPane().getButtonTypes().removeAll(dialog.getDialogPane().getButtonTypes().get(1));

            // Dialog를 표시하고 사용자 입력값을 받음
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                // 사용자가 입력한 이름을 처리하는 로직
                System.out.println("User name: " + name); // 예시로 콘솔에 출력
                Text a = new Text("Your Score : ");
                a.setFont(Font.font("ScoreBoard", 26));
                a.setFill(Color.BLACK);
                a.setLayoutX(Settings.getInstance().getScreenSizeSettings().getScreenWidth()/2/2);
                a.setLayoutY(200);
                pane.getChildren().add(a);
                ScoreBoard.getInstance().addScore(name, point); // 스코어보드에 추가!!
                ScoreBoard.getInstance().saveScoreboard(); // 스코어보드 저장!!
            });

            onBackToScoreBoard.run();

        });

        return scene;

    }

}
