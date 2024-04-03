package org.nl.javatetris.gameplay;

import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.nl.javatetris.config.FontManager;
import org.nl.javatetris.gameplay.gameover.GameOverParam;
import org.nl.javatetris.scoreboard.ScoreBoard;
import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import java.util.function.Consumer;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class GamePlayView {

    private GamePlayController gamePlayController;
    private Pane pane;
    private Consumer<GameOverParam> showGameOver; // Runnable 대신 Consumer<> 사용
    private Runnable onBackToMenu;

    public GamePlayView(GameParam gameParam, Runnable onPause, Consumer<GameOverParam> showGameOver, Runnable onBackToMenu) {
        this.gamePlayController = new GamePlayController(
                gameParam,
                onPause,
                this::drawGamePlayScreen,
                this::drawGameOverScreen
        );
        this.showGameOver = showGameOver;
        this.onBackToMenu = onBackToMenu;
    }

    public Scene createScene() {
        pane = new Pane();
        Image backgroundImage = new Image("file:src/main/resources/images/play.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        pane.setBackground(new Background(background));

        Scene scene = new Scene(
                pane,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

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
                        x * Settings.getInstance().getSizeSetting().getBlockSize(),
                        y * Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize()
                );
                cell.setStroke(Color.rgb(128,128,128,0.5)); // 셀의 테두리 색상

                int cellValue = gamePlayController.getBoard().getValueAt(y, x);
                switch(cellValue) {
                    case EMPTY:
                        cell.setFill(Color.rgb(255,255,255,0.3)); // 비어있는 셀의 색상
                        break;
                    case BORDER:
                        cell.setFill(Color.BLACK); // 벽의 색상
                        break;
                    case I:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(I)); // 활성화된 테트로미노의 색상
                        break;
                    case J:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(J)); // 활성화된 테트로미노의 색상
                        break;
                    case L:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(L)); // 활성화된 테트로미노의 색상
                        break;
                    case O:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(O)); // 활성화된 테트로미노의 색상
                        break;
                    case S:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(S)); // 활성화된 테트로미노의 색상
                        break;
                    case T:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(T)); // 활성화된 테트로미노의 색상
                        break;
                    case Z:
                        cell.setFill(Settings.getInstance().getColorSetting().getColorOfTetrominoType(Z)); // 활성화된 테트로미노의 색상
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
                        x * Settings.getInstance().getSizeSetting().getBlockSize(),
                        y * Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize());
                cell.setStroke(Color.rgb(128,128,128,0.5)); // 셀의 테두리 색상

                int cellValue = gamePlayController.getBoard().getValueAt(y, x);
                switch(cellValue) {
                    case EMPTY:
                        cell.setFill(Color.rgb(255,255,255,0.3)); // 비어있는 셀의 색상
                        break;
                    case BORDER:
                        cell.setFill(Color.BLACK); // 벽의 색상
                        break;
                    default:
                        cell.setFill(Color.BLACK); // 활성화된 테트로미노의 색상
                        break;
                }
                pane.getChildren().add(cell);
            }
        }

        drawScore();
        drawLevel();


        // 'GAME OVER' 문구 생성
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        gameOverText.setFill(Color.RED);
        gameOverText.setLayoutY(Y_MAX*Settings.getInstance().getSizeSetting().getBlockSize()/2);
        gameOverText.setLayoutX(X_MAX*Settings.getInstance().getSizeSetting().getBlockSize()/2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 20);

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
            // 10위 안에 들었을 경우 게임오버로, 그렇지 않을 경우 메인메뉴로 이동
            if (gamePlayController.getGameParam().getMode() == 0) { // classic mode
                if (ScoreBoard.getInstance().canUpdateClassicModeScores(gamePlayController.getPoint())) {
                    this.showGameOver.accept(new GameOverParam(gamePlayController.getPoint(), gamePlayController.getGameParam()));
                } else {
                    onBackToMenu.run();
                }
            } else if (gamePlayController.getGameParam().getMode() == 1) { // item mode
                if (ScoreBoard.getInstance().canUpdateItemModeScores(gamePlayController.getPoint())) {
                    this.showGameOver.accept(new GameOverParam(gamePlayController.getPoint(), gamePlayController.getGameParam()));
                } else {
                    onBackToMenu.run();
                }
            }

        });
        blinkTimeline.play();

    }

    // 점수 표시 메서드
    private void drawScore() {
        Text scoreText = new Text("score\n" + gamePlayController.getPoint());
        scoreText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        scoreText.setFill(Color.WHITE);
        scoreText.setLayoutX(Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10);
        scoreText.setLayoutY(Settings.getInstance().getSizeSetting().getBlockSize());
        pane.getChildren().add(scoreText);
    }

    // 레벨 표시 메서드
    private void drawLevel() {
        Text levelText = new Text("level\n" + gamePlayController.getLevel());
        levelText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        levelText.setFill(Color.WHITE);
        levelText.setLayoutX(Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10);
        levelText.setLayoutY(Settings.getInstance().getSizeSetting().getBlockSize() * 4);
        pane.getChildren().add(levelText);
    }

    // 다음 테트로미노 표시 메서드
    private void drawNextTetromino() {
        // NEXT
        Text nextText = new Text("next");
        nextText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        nextText.setFill(Color.CYAN);
        nextText.setLayoutX(Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10);
        nextText.setLayoutY(Settings.getInstance().getSizeSetting().getBlockSize() * 8);
        pane.getChildren().add(nextText);

        Tetromino nextTetromino = gamePlayController.getTetrominoGenerator().peekNextTetromino();
        int[][] shape = nextTetromino.getShape();
        for (int y = 0; y < nextTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < nextTetromino.getShapeWidth(); x++) {
                if (shape[y][x] != EMPTY) {
                    Rectangle previewCell = new Rectangle(
                            Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10 + x * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getBlockSize() * 9 + y * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getPreviewBlockSize()
                    );
                    previewCell.setFill(Color.CYAN); // 다음 나올 테트로미노의 색상
                    previewCell.setStroke(Color.LIGHTGRAY); // 테두리 색상
                    pane.getChildren().add(previewCell);
                }
            }
        }
    }

}

