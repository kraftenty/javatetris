package org.nl.javatetris.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.GamePlayController;
import org.nl.javatetris.model.settings.Settings;
import org.nl.javatetris.model.tetrominos.Tetromino;

import static org.nl.javatetris.model.ModelConst.*;
import static org.nl.javatetris.view.ViewConst.*;

public class GamePlayView {

    private GamePlayController gamePlayController;
    private Pane pane;

    public GamePlayView(Runnable onPause) {
        gamePlayController = new GamePlayController(onPause, this::drawGamePlayScreen, this::drawGameOverScreen);
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
                        cell.setFill(Color.LIGHTBLUE); // 활성화된 테트로미노의 색상
                        break;
                    case J:
                        cell.setFill(Color.BLUE); // 활성화된 테트로미노의 색상
                        break;
                    case L:
                        cell.setFill(Color.ORANGE); // 활성화된 테트로미노의 색상
                        break;
                    case O:
                        cell.setFill(Color.YELLOW); // 활성화된 테트로미노의 색상
                        break;
                    case S:
                        cell.setFill(Color.GREEN); // 활성화된 테트로미노의 색상
                        break;
                    case T:
                        cell.setFill(Color.PURPLE); // 활성화된 테트로미노의 색상
                        break;
                    case Z:
                        cell.setFill(Color.RED); // 활성화된 테트로미노의 색상
                        break;
                }
                pane.getChildren().add(cell);
            }
        }

        // 점수 표시
        Text scoreText = new Text("Score: " + gamePlayController.getScore());
        scoreText.setFont(Font.font("Arial", 18));
        scoreText.setFill(Color.BLACK);
        scoreText.setLayoutX(DEFAULT_WINDOW_WIDTH - 130);
        scoreText.setLayoutY(25);
        pane.getChildren().add(scoreText);

        // 레벨 표시
        Text levelText = new Text("Level: " + gamePlayController.getLevel());
        levelText.setFont(Font.font("Arial", 18));
        levelText.setFill(Color.BLACK);
        levelText.setLayoutX(DEFAULT_WINDOW_WIDTH - 130);
        levelText.setLayoutY(50);
        pane.getChildren().add(levelText);

        // 다음 테트로미노 표시
        Tetromino nextTetromino = gamePlayController.getTetrominoGenerator().peekNextTetromino(); // 다음 나올 테트로미노 가져오기
        int[][] shape = nextTetromino.getShape(); // 다음 나올 테트로미노의 형태 가져오기\
        for (int y = 0; y < nextTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < nextTetromino.getShapeWidth(); x++) {
                if (shape[y][x] != EMPTY) {
                    Rectangle previewCell = new Rectangle(
                            DEFAULT_WINDOW_WIDTH - 100 + x * Settings.getInstance().getScreenSizeSettings().getPreviewBlockSize(),
                            100 + y * Settings.getInstance().getScreenSizeSettings().getPreviewBlockSize(),
                            Settings.getInstance().getScreenSizeSettings().getPreviewBlockSize(),
                            Settings.getInstance().getScreenSizeSettings().getPreviewBlockSize()
                    );
                    previewCell.setFill(Color.GRAY); // 다음 나올 테트로미노의 색상
                    previewCell.setStroke(Color.LIGHTGRAY); // 테두리 색상
                    pane.getChildren().add(previewCell);
                }
            }
        }

    }

    // 게임오버 화면을 그리는 메서드
    private void drawGameOverScreen() {
        System.out.println("drawGameOverScreen");
        pane.getChildren().clear(); // 기존에 그려진 모든 Rectangle 제거

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


        // 'GAME OVER' 문구 생성
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Verdana", 40));
        gameOverText.setFill(Color.RED);
        gameOverText.setLayoutY(Y_MAX*Settings.getInstance().getScreenSizeSettings().getBlockSize()/2);
        gameOverText.setLayoutX(X_MAX*Settings.getInstance().getScreenSizeSettings().getBlockSize()/2 - 120);

        // 문구를 pane에 추가
        pane.getChildren().add(gameOverText);
    }

}

