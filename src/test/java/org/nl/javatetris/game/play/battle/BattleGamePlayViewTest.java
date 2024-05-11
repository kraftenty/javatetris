package org.nl.javatetris.game.play.battle;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.pause.PauseMenuParam;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class BattleGamePlayViewTest {

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
        // JavaFX 애플리케이션 스레드를 시작합니다.
    }

    @Test
    public void GamePlaySceneCreateTest() {
        // Given
        GameParam gameParam = new GameParam(ControllerConst.BATTLE_CLASSIC, 0);
        Consumer<PauseMenuParam> onPause = (param) -> {};
        Runnable onBackToMenu = () -> {};

        BattleGamePlayView battleGamePlayView = new BattleGamePlayView(gameParam, onPause, onBackToMenu);

        // When
        Scene scene = battleGamePlayView.createScene();

        // Then
        assertNotNull(scene);
    }
}
