package org.nl.javatetris.gameplay.battle;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.nl.javatetris.gameplay.GameParam;
import org.nl.javatetris.pause.PauseMenuParam;
import org.nl.javatetris.settings.Settings;

import java.util.function.Consumer;

public class BattleGamePlayView {

    private BattleGamePlayController battleGamePlayController;
    private Pane pane;
    private Runnable onBackToMenu;

    private boolean isBlinking = false;

    public BattleGamePlayView(GameParam gameParam, Consumer<PauseMenuParam> onPause, Runnable onBackToMenu) {
        this.onBackToMenu = onBackToMenu;
        this.battleGamePlayController = new BattleGamePlayController(
                gameParam,
                onPause,
                this::drawGamePlayScreen,
                this::drawGameOverScreen
        );
        this.onBackToMenu = onBackToMenu;
    }

    public Scene createScene() {
        pane = new Pane();
        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/play.jpg"));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        pane.setBackground(new Background(background));

        Scene scene = new Scene(
                pane,
                Settings.getInstance().getSizeSetting().getScreenWidth() * 2,
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        drawGamePlayScreen();

        // 키 이벤트 핸들러 설정
        scene.setOnKeyPressed(e -> {
            boolean isGameKeepGoing = battleGamePlayController.handleKeyPress(e);
            // 키 입력 후 보드 상태 업데이트 로직 필요
            if (isGameKeepGoing && !isBlinking) {
                drawGamePlayScreen();
            }
        });

        return scene;
    }

    private void drawGamePlayScreen() {

    }

    private void blinkLine() {

    }

    private void drawGameOverScreen() {

    }

    private void drawScore() {

    }

    private void drawLevel() {

    }

    private void drawNextTetromino() {

    }

}
