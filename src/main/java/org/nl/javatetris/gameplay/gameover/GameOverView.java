package org.nl.javatetris.gameplay.gameover;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.nl.javatetris.config.FontManager;
import org.nl.javatetris.gameplay.GameParam;
import org.nl.javatetris.scoreboard.ScoreBoard;
import org.nl.javatetris.settings.Settings;

import java.util.Optional;

public class GameOverView {

    private GameOverController gameOverController;
    private Runnable onBackToScoreBoard;
    private GameOverParam gameOverParam;

    public GameOverView(GameOverParam gameOverParam, Runnable onBackToScoreBoard) {
        this.gameOverParam = gameOverParam;
        this.gameOverController = new GameOverController(onBackToScoreBoard);
        this.onBackToScoreBoard = onBackToScoreBoard;
    }

    public Scene createScene() {
        Pane pane = new Pane();
        Image backgroundImage = new Image("file:src/main/resources/images/play.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        pane.setBackground(new Background(background));

        Scene scene = new Scene(
                pane,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        Text levelText = new Text("Your Score : " + gameOverParam.getPoint());
        levelText.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        levelText.setFill(Color.WHITE);
        levelText.setLayoutX(Settings.getInstance().getSizeSetting().getScreenWidth() / 2 - Settings.getInstance().getSizeSetting().getSidebarSize() - 60);
        levelText.setLayoutY(100);
        pane.getChildren().add(levelText);

        Platform.runLater(() -> {
            boolean isValidInput = false;
            String name = "";
            while (!isValidInput) {
                TextInputDialog dialog = new TextInputDialog(name);
                dialog.setTitle("Enroll into Scoreboard");
                dialog.setHeaderText("Enter Your Name (10 characters max)");
                dialog.setContentText("Name:");

                // 'Cancel' 버튼 제거
                dialog.getDialogPane().getButtonTypes().removeAll(dialog.getDialogPane().getButtonTypes().get(1));

                // Dialog를 표시하고 사용자 입력값을 받음
                Optional<String> result = dialog.showAndWait();

                if (result.isPresent() && result.get().length() <= 10) {
                    name = result.get();
                    isValidInput = true;
                } else {
                    // 사용자에게 경고 메시지를 표시합니다.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Enroll into Scoreboard");
                    alert.setHeaderText(null);
                    alert.setContentText("The name must be 10 characters or less.");
                    alert.showAndWait();
                }
            }
            if (name.isEmpty()) {
                name = "Anonymous";
            }
            ScoreBoard.getInstance().addScoreByMode(name, gameOverParam.getPoint(), gameOverParam.getGameParam());
            ScoreBoard.getInstance().saveScoreboard(); // 스코어보드 저장!!
            onBackToScoreBoard.run();
        });

        return scene;

    }

}
