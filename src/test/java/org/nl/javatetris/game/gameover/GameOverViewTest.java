package org.nl.javatetris.game.gameover;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nl.javatetris.game.GameParam;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


@ExtendWith(ApplicationExtension.class)
class GameOverViewTest {

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
    }

    @Test
    public void GameOverSceneCreateTest() {
        // Given
        GameOverView gameOverView = new GameOverView(new GameOverParam(0,new GameParam(0,0)), () -> {});
        // When
        // Then
        // 성공적으로 scene 이 생성되면 테스트 성공
    }

}