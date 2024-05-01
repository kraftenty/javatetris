package org.nl.javatetris.game.play.single;

import javafx.scene.Scene;
import javafx.scene.layout.*;

import org.nl.javatetris.config.BackgroundManager;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.gameover.GameOverParam;
import org.nl.javatetris.game.play.GamePlayView;
import org.nl.javatetris.pause.PauseMenuParam;
import org.nl.javatetris.scoreboard.ScoreBoard;
import org.nl.javatetris.settings.Settings;

import java.util.function.Consumer;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class SingleGamePlayView extends GamePlayView {

    private final SingleGamePlayController singleGamePlayController;
    private Pane pane;

    private final Consumer<GameOverParam> showGameOver; // Runnable 대신 Consumer<> 사용
    private final Runnable onBackToMenu;

    private Boolean isBlinking = false;

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
        pane.setBackground(BackgroundManager.getPlayBackground());

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
        drawBoard(
                pane,
                0,
                0,
                true,
                singleGamePlayController.getBoard(),
                isBlinking
        );
        drawPoint(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize(),
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                singleGamePlayController.getPoint()
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                singleGamePlayController.getLevel()
        );
        drawPreview(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 8,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                singleGamePlayController.getTetrominoGenerator().peekNextTetromino()
        );
    }

    // 게임오버 화면을 그리는 메서드
    private void updateGameOverScene() {
        // Pane 에 그려진 모든 Rectangle 제거
        pane.getChildren().clear();

        drawBoard(
                pane,
                0,
                0,
                false,
                singleGamePlayController.getBoard(),
                isBlinking
        );
        drawPoint(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize(),
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                singleGamePlayController.getPoint()
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                singleGamePlayController.getLevel()
        );
        drawBlinkingGameOver(
                pane,
                (double) (Y_MAX * Settings.getInstance().getSizeSetting().getBlockSize()) / 2,
                (double) (X_MAX * Settings.getInstance().getSizeSetting().getBlockSize()) / 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 20,
                e -> {
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
                }
        );
    }

}

