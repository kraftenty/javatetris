package org.nl.javatetris.game.play;


import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nl.javatetris.game.tetromino.Tetromino;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class GamePlayViewTest {

    private GamePlayView gamePlayView;
    private Pane pane;

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
    }

    @BeforeEach
    public void setUp() {
        gamePlayView = new GamePlayView() {
            @Override
            protected void drawBoard(Pane pane, double layoutY, double layoutX, boolean isGamePlaying, Board board, Boolean isBlinking) {
                super.drawBoard(pane, layoutY, layoutX, isGamePlaying, board, isBlinking);
            }

            @Override
            protected void drawBlinkingGameOver(Pane pane, double layoutY, double layoutX, javafx.event.EventHandler<javafx.event.ActionEvent> eventHandler, String showText) {
                super.drawBlinkingGameOver(pane, layoutY, layoutX, eventHandler, showText);
            }

            @Override
            protected void drawPoint(Pane pane, double layoutY, double layoutX, int point, String showText) {
                super.drawPoint(pane, layoutY, layoutX, point, showText);
            }

            @Override
            protected void drawLevel(Pane pane, double layoutY, double layoutX, int level, String showText) {
                super.drawLevel(pane, layoutY, layoutX, level, showText);
            }

            @Override
            protected void drawPreview(Pane pane, double layoutY, double layoutX, Tetromino nextTetromino) {
                super.drawPreview(pane, layoutY, layoutX, nextTetromino);
            }

            @Override
            protected void drawTimeLimit(Pane pane, double layoutY, double layoutX, int timeLimit, String showText) {
                super.drawTimeLimit(pane, layoutY, layoutX, timeLimit, showText);
            }

            @Override
            protected void drawAttackLinesPreview(Pane pane, double layoutY, double layoutX, List<LineDTO> attackLines) {
                super.drawAttackLinesPreview(pane, layoutY, layoutX, attackLines);
            }

            @Override
            protected Color getColorOfCell(int cellValue) {
                return super.getColorOfCell(cellValue);
            }

            @Override
            protected void drawCharacterOnBlock(Pane pane, double layoutY, double layoutX, char character, double fontScale) {
                super.drawCharacterOnBlock(pane, layoutY, layoutX, character, fontScale);
            }
        };
        pane = new Pane();
    }

    @Test
    public void drawPointTest() {
        // Given
        int point = 100;
        String showText = "Score";

        // When
        Platform.runLater(() -> {
            gamePlayView.drawPoint(pane, 0, 0, point, showText);

            // Then
            assertEquals(1, pane.getChildren().size());
            assertEquals(showText + "\n" + point, ((Text) pane.getChildren().get(0)).getText());
        });
    }

    @Test
    public void drawLevelTest() {
        // Given
        int level = 5;
        String showText = "Level";

        // When
        Platform.runLater(() -> {
            gamePlayView.drawLevel(pane, 0, 0, level, showText);

            // Then
            assertEquals(1, pane.getChildren().size());
            assertEquals(showText + "\n" + level, ((Text) pane.getChildren().get(0)).getText());
        });
    }


    @Test
    public void drawTimeLimitTest() {
        // Given
        int timeLimit = 100;
        String showText = "Time left";

        // When
        Platform.runLater(() -> {
            gamePlayView.drawTimeLimit(pane, 0, 0, timeLimit, showText);

            // Then
            assertEquals(1, pane.getChildren().size());
            assertEquals(showText + "\n" + timeLimit, ((Text) pane.getChildren().get(0)).getText());
        });
    }
}
