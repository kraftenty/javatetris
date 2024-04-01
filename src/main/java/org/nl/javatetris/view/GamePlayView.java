package org.nl.javatetris.view;

import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.nl.javatetris.controller.GamePlayController;
import org.nl.javatetris.model.settings.Settings;
import org.nl.javatetris.model.tetrominos.Tetromino;

import java.util.Set;
import java.util.function.Consumer;

import static org.nl.javatetris.model.ModelConst.*;
import static org.nl.javatetris.view.ViewConst.*;

public class GamePlayView implements View {

    private GamePlayController gamePlayController;
    private Pane pane;
    private Consumer<Integer> showGameOver; // Runnable 대신 Consumer<> 사용

    public GamePlayView(Runnable onPause, Consumer<Integer> showGameOver) {
        this.gamePlayController = new GamePlayController(
                onPause,
                this::drawGamePlayScreen,
                this::drawGameOverScreen
        );
        this.showGameOver = showGameOver;
    }

    public Scene createScene() {
        pane = new Pane();
        Scene scene = new Scene(pane,Settings.getInstance().getScreenSizeSettings().getScreenWidth() , Settings.getInstance().getScreenSizeSettings().getScreenHeight());

        drawGamePlayScreen();

        // 키 이벤트 핸들러 설정
        scene.setOnKeyPressed(e -> {
            boolean isGameKeepGoing = gamePlayController.handleKeyPress(e);
            // 키 입력 후 보드 상태 업데이트 로직 필요
            if (isGameKeepGoing) {
                drawGamePlayScreen();
            }
        });

        return scene;
    }

    // 게임 플레이 화면을 그리는 메서드
    private void drawGamePlayScreen() {
        // Pane 에 그려진 모든 Rectangle 제거
        pane.getChildren().clear();

        // 보드의 상태를 가져와서 화면에 그리기
        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                Rectangle cell = new Rectangle(
                        x * Settings.getInstance().getScreenSizeSettings().getBlockSize(),
                        y * Settings.getInstance().getScreenSizeSettings().getBlockSize(),
                        Settings.getInstance().getScreenSizeSettings().getBlockSize(),
                        Settings.getInstance().getScreenSizeSettings().getBlockSize()
                );
                cell.setStroke(Color.LIGHTGRAY); // 셀의 테두리 색상

                int cellValue = gamePlayController.getBoard().getValueAt(y, x);
                switch(cellValue) {
                    case EMPTY:
                        cell.setFill(Color.WHITE); // 비어있는 셀의 색상
                        break;
                    case BORDER:
                        cell.setFill(Color.BLACK); // 벽의 색상
                        break;
                    case I:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(I-1)); // 활성화된 테트로미노의 색상
                        break;
                    case J:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(J-1)); // 활성화된 테트로미노의 색상
                        break;
                    case L:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(L-1)); // 활성화된 테트로미노의 색상
                        break;
                    case O:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(O-1)); // 활성화된 테트로미노의 색상
                        break;
                    case S:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(S-1)); // 활성화된 테트로미노의 색상
                        break;
                    case T:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(T-1)); // 활성화된 테트로미노의 색상
                        break;
                    case Z:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(Z-1)); // 활성화된 테트로미노의 색상
                        break;
                }
                pane.getChildren().add(cell);
            }
        }

        drawScore();
        drawLevel();
        drawNextTetromino();

    }

    // 게임오버 화면을 그리는 메서드
    private void drawGameOverScreen() {
        pane.getChildren().clear();

        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                Rectangle cell = new Rectangle(
                        x * Settings.getInstance().getScreenSizeSettings().getBlockSize(),
                        y * Settings.getInstance().getScreenSizeSettings().getBlockSize(),
                        Settings.getInstance().getScreenSizeSettings().getBlockSize(),
                        Settings.getInstance().getScreenSizeSettings().getBlockSize());
                cell.setStroke(Color.LIGHTGRAY); // 셀의 테두리 색상

                int cellValue = gamePlayController.getBoard().getValueAt(y, x);
                switch(cellValue) {
                    case EMPTY:
                        cell.setFill(Color.WHITE); // 비어있는 셀의 색상
                        break;
                    case BORDER:
                        cell.setFill(Color.BLACK); // 벽의 색상
                        break;
                    default:
                        cell.setFill(Color.GREY); // 활성화된 테트로미노의 색상
                        break;
                }
                pane.getChildren().add(cell);
            }
        }

        drawScore();
        drawLevel();


        // 'GAME OVER' 문구 생성
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Arial", 40));
        gameOverText.setFill(Color.RED);
        gameOverText.setLayoutY(Y_MAX*Settings.getInstance().getScreenSizeSettings().getBlockSize()/2);
        gameOverText.setLayoutX(X_MAX*Settings.getInstance().getScreenSizeSettings().getBlockSize()/2 - 120);

        // 문구를 pane에 추가
        pane.getChildren().add(gameOverText);

        // 깜빡임 효과를 위한 Timeline 생성
        Timeline blinkTimeline = new Timeline(new KeyFrame(Duration.seconds(0.25), e -> {
            if (gameOverText.isVisible()) {
                gameOverText.setVisible(false);
            } else {
                gameOverText.setVisible(true);
            }
        }));

        blinkTimeline.setCycleCount(8);
        blinkTimeline.setOnFinished(e -> {
            // TODO :
            this.showGameOver.accept(gamePlayController.getScore());
        });
        blinkTimeline.play();

    }

    // 점수 표시 메서드
    private void drawScore() {
        Text scoreText = new Text("Score: " + gamePlayController.getScore());
        scoreText.setFont(Font.font("Arial", 18));
        scoreText.setFill(Color.BLACK);
        scoreText.setLayoutX(Settings.getInstance().getScreenSizeSettings().getScreenWidth() - 130);
        scoreText.setLayoutY(25);
        pane.getChildren().add(scoreText);
    }

    // 레벨 표시 메서드
    private void drawLevel() {
        Text levelText = new Text("Level: " + gamePlayController.getLevel());
        levelText.setFont(Font.font("Arial", 18));
        levelText.setFill(Color.BLACK);
        levelText.setLayoutX(Settings.getInstance().getScreenSizeSettings().getScreenWidth() - 130);
        levelText.setLayoutY(50);
        pane.getChildren().add(levelText);
    }

    // 다음 테트로미노 표시 메서드
    private void drawNextTetromino() {
        Tetromino nextTetromino = gamePlayController.getTetrominoGenerator().peekNextTetromino();
        int[][] shape = nextTetromino.getShape();
        for (int y = 0; y < nextTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < nextTetromino.getShapeWidth(); x++) {
                if (shape[y][x] != EMPTY) {
                    Rectangle previewCell = new Rectangle(
                            Settings.getInstance().getScreenSizeSettings().getScreenWidth() - 100 + x * PREVIEW_CELL_SIZE,
                            100 + y * PREVIEW_CELL_SIZE,
                            PREVIEW_CELL_SIZE,
                            PREVIEW_CELL_SIZE
                    );
                    previewCell.setFill(Color.GRAY); // 다음 나올 테트로미노의 색상
                    previewCell.setStroke(Color.LIGHTGRAY); // 테두리 색상
                    pane.getChildren().add(previewCell);
                }
            }
        }
    }

}

