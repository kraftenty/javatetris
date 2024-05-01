package org.nl.javatetris.game.play.battle;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import org.nl.javatetris.config.manager.BackgroundManager;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.play.GamePlayView;
import org.nl.javatetris.pause.PauseMenuParam;
import org.nl.javatetris.settings.Settings;

import java.util.function.Consumer;

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
                battleGamePlayController.getPoint1()
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getLevel1()
        );
        drawPreview(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 8,
                Settings.getInstance().getSizeSetting().getScreenWidth() - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getTetrominoGenerator1().peekNextTetromino()
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
                battleGamePlayController.getPoint2()
        );
        drawLevel(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 4,
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getLevel2()
        );
        drawPreview(
                pane,
                Settings.getInstance().getSizeSetting().getBlockSize() * 8,
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2 - Settings.getInstance().getSizeSetting().getSidebarSize() + 10,
                battleGamePlayController.getTetrominoGenerator2().peekNextTetromino()
        );


    }


    private void updateGameOverScene() {

    }

}
