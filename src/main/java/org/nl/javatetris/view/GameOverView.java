package org.nl.javatetris.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.GameOverController;

import java.util.Optional;

import static org.nl.javatetris.view.ViewConst.*;
public class GameOverView implements View {

    private GameOverController gameOverController;
    private Runnable onBackToMenu;
    private Integer score;

    public GameOverView(Integer score, Runnable onBackToMenu) {
        this.score = score;
        this.gameOverController = new GameOverController(onBackToMenu);
        this.onBackToMenu = onBackToMenu;
    }

    public Scene createScene() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        Text levelText = new Text("Your Score : " + score);
        levelText.setFont(Font.font("Arial", 26));
        levelText.setFill(Color.BLACK);
        levelText.setLayoutX(WINDOW_WIDTH/2);
        levelText.setLayoutY(100);
        pane.getChildren().add(levelText);

        if (true) { // scoreboard에서 10위권 안에 드는 경우
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
                    a.setLayoutX(WINDOW_WIDTH/2);
                    a.setLayoutY(200);
                    pane.getChildren().add(a);
                });
            });
        }

        return scene;

    }

}
