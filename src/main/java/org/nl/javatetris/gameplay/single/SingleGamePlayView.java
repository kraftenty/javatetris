package org.nl.javatetris.gameplay.single;

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
import org.nl.javatetris.gameplay.GameParam;
import org.nl.javatetris.gameplay.gameover.GameOverParam;
import org.nl.javatetris.pause.PauseMenuParam;
import org.nl.javatetris.scoreboard.ScoreBoard;
import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static javafx.scene.paint.Color.*;
import static org.nl.javatetris.config.constant.ModelConst.*;

public class SingleGamePlayView {

    private final SingleGamePlayController singleGamePlayController;
    private Pane pane;

    private final Consumer<GameOverParam> showGameOver; // Runnable 대신 Consumer<> 사용
    private final Runnable onBackToMenu;

    private boolean isBlinking = false;

    public SingleGamePlayView(GameParam gameParam, Consumer<PauseMenuParam> onPause, Consumer<GameOverParam> showGameOver, Runnable onBackToMenu) {
        this.singleGamePlayController = new SingleGamePlayController(
                gameParam,
                onPause,
                this::updateGamePlayScene,
                this::updateGameOverScene
        );
        this.showGameOver = showGameOver;
        this.onBackToMenu = onBackToMenu;
    }

    public Scene createScene() {
        pane = new Pane();
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/play.jpg")));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        pane.setBackground(new Background(background));

        Scene scene = new Scene(
                pane,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        updateGamePlayScene();

        // 키 이벤트 핸들러 설정
        scene.setOnKeyPressed(e -> {
            boolean isGameKeepGoing = singleGamePlayController.handleKeyPress(e);
            // 키 입력 후 보드 상태 업데이트 로직 필요
            if (isGameKeepGoing && !isBlinking) {
                updateGamePlayScene();
            }
        });

        return scene;
    }

    // 게임 플레이 화면을 그리는 메서드
    private void updateGamePlayScene() {
        // Pane 에 그려진 모든 Rectangle 제거
        pane.getChildren().clear();
        // 부분별로 그리기
        drawBoard(true);
        drawScore();
        drawLevel();
        drawPreview();
    }

    // 게임오버 화면을 그리는 메서드
    private void updateGameOverScene() {
        // Pane 에 그려진 모든 Rectangle 제거
        pane.getChildren().clear();

        drawBoard(false);
        drawScore();
        drawLevel();


        // 'GAME OVER' 문구 생성
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        gameOverText.setFill(Color.RED);
        gameOverText.setLayoutY((double) (Y_MAX * Settings.getInstance().getSizeSetting().getBlockSize()) / 2);
        gameOverText.setLayoutX((double) (X_MAX * Settings.getInstance().getSizeSetting().getBlockSize()) / 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 20);

        // 문구를 pane 에 추가
        pane.getChildren().add(gameOverText);

        // 깜빡임 효과를 위한 Timeline 생성
        Timeline blinkTimeline = new Timeline(new KeyFrame(Duration.seconds(0.25), e -> {
            gameOverText.setVisible(!gameOverText.isVisible());
        }));

        blinkTimeline.setCycleCount(8);
        blinkTimeline.setOnFinished(e -> {
            // 10위 안에 들었을 경우 게임오버로, 그렇지 않을 경우 메인메뉴로 이동
            if (singleGamePlayController.getGameParam().getMode() == 0) { // classic mode
                if (ScoreBoard.getInstance().canUpdateClassicModeScores(singleGamePlayController.getPoint())) {
                    this.showGameOver.accept(new GameOverParam(singleGamePlayController.getPoint(), singleGamePlayController.getGameParam()));
                } else {
                    onBackToMenu.run();
                }
            } else if (singleGamePlayController.getGameParam().getMode() == 1) { // item mode
                if (ScoreBoard.getInstance().canUpdateItemModeScores(singleGamePlayController.getPoint())) {
                    this.showGameOver.accept(new GameOverParam(singleGamePlayController.getPoint(), singleGamePlayController.getGameParam()));
                } else {
                    onBackToMenu.run();
                }
            }

        });
        blinkTimeline.play();

    }

    private void drawBoard(boolean isDoingGamePlay) {
        // 보드의 상태를 가져와서 화면에 그리기
        List<Integer> completedLines = singleGamePlayController.getBoard().releaseCompletedLines(); // 완성된 가로줄의 y좌표 리스트. 삭제 효과를 위한 것임.

        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                // 셀 생성
                Rectangle cell = new Rectangle(
                        x * Settings.getInstance().getSizeSetting().getBlockSize(),
                        y * Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize(),
                        Settings.getInstance().getSizeSetting().getBlockSize()
                );
                // 셀의 테두리 설정
                cell.setStroke(Color.rgb(128, 128, 128, 0.5));

                // 셀의 색상 설정
                int cellValue = singleGamePlayController.getBoard().getValueAt(y, x);
                if (isDoingGamePlay) {
                    // 게임 플레이 중일 경우
                    cell.setFill(getColorOfCell(cellValue));
                    // 지울 줄이 포함된 경우, 흰색으로 깜빡이는 효과
                    if (completedLines.contains(y) && cellValue != BORDER) {
                        blinkLine(cell, cellValue);
                    }
                    // 문자가 들어가는 셀의 경우, 문자 그리기
                    if (cellValue == E || cellValue == N || cellValue == B || cellValue == V || (cellValue > 10 && cellValue < 20)) {
                        drawCharacterOnBlock(
                                (char) cellValue,
                                (y + 1) * Settings.getInstance().getSizeSetting().getBlockSize(),
                                (x + 0.1) * Settings.getInstance().getSizeSetting().getBlockSize(),
                                1.5
                        );
                    }
                } else {
                    // 게임 오버일 경우
                    if (cellValue == EMPTY) {
                        cell.setFill(rgb(255, 255, 255, 0.3)); // 비어있는 셀의 색상
                    } else {
                        cell.setFill(BLACK); // 활성화된 테트로미노의 색상
                    }
                }
                pane.getChildren().add(cell);
            }
        }
    }

    // 흰색으로 깜빡이는 효과를 주는 메서드
    private void blinkLine(Rectangle cell, int originalCellValue) {
        isBlinking = true;
        Timeline blinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), e -> cell.setFill(getColorOfCell(CLEAR))),
                new KeyFrame(Duration.seconds(0.2), e -> {
                    cell.setFill(getColorOfCell(originalCellValue));
                    isBlinking = false;
                })
        );
        blinkTimeline.setCycleCount(1);  // 한 번 실행
        blinkTimeline.play();
    }

    // 점수 표시 메서드
    private void drawScore() {
        Text scoreText = new Text("score\n" + singleGamePlayController.getPoint());
        scoreText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        scoreText.setFill(Color.WHITE);
        scoreText.setLayoutX(Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10);
        scoreText.setLayoutY(Settings.getInstance().getSizeSetting().getBlockSize());
        pane.getChildren().add(scoreText);
    }

    // 레벨 표시 메서드
    private void drawLevel() {
        Text levelText = new Text("level\n" + singleGamePlayController.getLevel());
        levelText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        levelText.setFill(Color.WHITE);
        levelText.setLayoutX(Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10);
        levelText.setLayoutY(Settings.getInstance().getSizeSetting().getBlockSize() * 4);
        pane.getChildren().add(levelText);
    }

    // 다음 테트로미노 표시 메서드
    private void drawPreview() {
        // NEXT
        Text nextText = new Text("next");
        nextText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        nextText.setFill(Color.CYAN);
        nextText.setLayoutX(Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10);
        nextText.setLayoutY(Settings.getInstance().getSizeSetting().getBlockSize() * 8);
        pane.getChildren().add(nextText);

        Tetromino nextTetromino = singleGamePlayController.getTetrominoGenerator().peekNextTetromino();
        int[][] shape = nextTetromino.getShape();
        for (int y = 0; y < nextTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < nextTetromino.getShapeWidth(); x++) {
                int cellValue = shape[y][x];
                if (cellValue != EMPTY) {
                    Rectangle previewCell = new Rectangle(
                            Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10 + x * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getBlockSize() * 9 + y * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                            Settings.getInstance().getSizeSetting().getPreviewBlockSize()
                    );


                    previewCell.setFill(getColorOfCell(cellValue)); // 다음 나올 테트로미노의 색상
                    previewCell.setStroke(Color.LIGHTGRAY); // 테두리 색상
                    pane.getChildren().add(previewCell);

                    if (cellValue == E || cellValue == N || cellValue == B || cellValue == V) {
                        drawCharacterOnBlock(
                                (char) cellValue,
                                Settings.getInstance().getSizeSetting().getBlockSize() * 9 + (y + 1) * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10 + (x + 0.1) * Settings.getInstance().getSizeSetting().getPreviewBlockSize(),
                                1.1
                        );
                    }
                }
            }
        }
    }


    private Color getColorOfCell(int cellValue) {
        return switch (cellValue) {
            // 보드 기본
            case BORDER -> BLACK;
            case CLEAR -> Color.WHITE;
            // 테트로미노
            case I -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(I);
            case J -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(J);
            case L -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(L);
            case O -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(O);
            case S -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(S);
            case T -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(T);
            case Z -> Settings.getInstance().getColorSetting().getColorOfTetrominoType(Z);
            // 아이템 테트로미노
            case N, B, V, E -> TRANSPARENT;
            case W -> DARKGRAY;
            // 기본 배경
            default -> Color.rgb(255, 255, 255, 0.3);
        };
    }

    private void drawCharacterOnBlock(char character, double y, double x, double fontScale) {
        System.out.println("drawCharacterOnBlock function call  " + character);
        Text text = new Text(String.valueOf(character));
        text.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getDefaultFontSize() * fontScale));
        text.setFill(Color.RED);
        text.setLayoutY(y);
        text.setLayoutX(x);
        pane.getChildren().add(text);
    }

}

