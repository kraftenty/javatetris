package org.nl.javatetris.game.play.battle;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import org.nl.javatetris.config.manager.BackgroundManager;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.play.GamePlayView;
import org.nl.javatetris.pause.PauseMenuParam;
import org.nl.javatetris.settings.Settings;

import java.util.function.Consumer;

import static org.nl.javatetris.config.constant.ModelConst.X_MAX;
import static org.nl.javatetris.config.constant.ModelConst.Y_MAX;

public class BattleGamePlayView extends GamePlayView {

    private BattleGamePlayController battleGamePlayController;
    private Pane pane;
    private Runnable onBackToMenu;

    private Boolean isBlinking = false;

    public BattleGamePlayView(GameParam gameParam, Consumer<PauseMenuParam> onPause, Runnable onBackToMenu) {
        this.onBackToMenu = onBackToMenu;
        this.battleGamePlayController = new BattleGamePlayController(
                gameParam,
                onPause,
                this::updateGamePlayScene,
                this::updateGameOverScene
        );
        this.onBackToMenu = onBackToMenu;
    }

    public Scene createScene() {
        pane = new Pane();
        pane.setBackground(BackgroundManager.getPlayBackground());

        Scene scene = new Scene(
                pane,
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2,
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        updateGamePlayScene();

        // 키 이벤트 핸들러 설정
        scene.setOnKeyPressed(e -> {
            boolean isGameKeepGoing = battleGamePlayController.handleKeyPress(e);
            // 키 입력 후 보드 상태 업데이트 로직 필요
            if (isGameKeepGoing && !isBlinking) {
                updateGamePlayScene();
            }
        });

        return scene;
    }

    private void updateGamePlayScene() {
        pane.getChildren().clear();
        // Player 1
        drawBoard(
                pane,
                0,
                0,
                true,
                battleGamePlayController.getBoard1(),
                isBlinking
        );
        drawPoint(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize(),
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getPoint1(),
                "p1 point"
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getLevel1(),
                "p1 level"
        );
        drawPreview(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 8,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getTetrominoGenerator1().peekNextTetromino()
        );
        if (battleGamePlayController.getGameParam().getMode() == 12){
            drawTimeLimit(
                    pane,
                    Settings.getInstance().getSizeSetting().getBlockSize() * 12,
                    Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                    battleGamePlayController.getTimeLimit(),
                    "Time left"
            );
        }

        drawDeletedLinePreview(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 12,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getBoard2().getPreviousBoard(),
                battleGamePlayController.getBoard2().getCompletedLines()
        );

        // Player 2
        drawBoard(
                pane,
                0,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                true,
                battleGamePlayController.getBoard2(),
                isBlinking
        );
        drawPoint(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize(),
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getPoint2(),
                "p2 point"
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getLevel2(),
                "p2 level"
        );
        drawPreview(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 8,
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getTetrominoGenerator2().peekNextTetromino()
        );
        if (battleGamePlayController.getGameParam().getMode() == 12){
            drawTimeLimit(
                    pane,
                    Settings.getInstance().getSizeSetting().getBlockSize() * 12,
                    Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                    battleGamePlayController.getTimeLimit(),
                    "Time left"
            );
        }
        drawDeletedLinePreview(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 12,
                Settings.getInstance().getSizeSetting().getScreenWidth() *2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getBoard1().getPreviousBoard(),
                battleGamePlayController.getBoard1().getCompletedLines()
        );
    }


    private void updateGameOverScene() {
        pane.getChildren().clear();
        // Player 1
        drawBoard(
                pane,
                0,
                0,
                false,
                battleGamePlayController.getBoard1(),
                isBlinking
        );
        drawPoint(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize(),
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getPoint1(),
                "p1 point"
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getLevel1(),
                "p1 level"
        );


        // Player 2
        drawBoard(
                pane,
                0,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                false,
                battleGamePlayController.getBoard2(),
                isBlinking
        );
        drawPoint(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize(),
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getPoint2(),
                "p2 point"
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getLevel2(),
                "p2 level"
        );

        drawBlinkingGameOver(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 5,
                Settings.getInstance().getSizeSetting().getBlockSize(),
                e -> {
                    onBackToMenu.run();
                },
                "PLAYER " + battleGamePlayController.getWinner() + " WIN"
        );
        drawBlinkingGameOver(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 5,
                Settings.getInstance().getSizeSetting().getScreenWidth() + Settings.getInstance().getSizeSetting().getBlockSize(),
                e -> {
                    onBackToMenu.run();
                },
                "PLAYER " + battleGamePlayController.getWinner() + " WIN"
        );

    }

}
