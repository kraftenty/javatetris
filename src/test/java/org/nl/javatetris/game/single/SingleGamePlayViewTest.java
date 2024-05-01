package org.nl.javatetris.game.single;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.play.single.SingleGamePlayView;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class SingleGamePlayViewTest {

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
    }

    @Test
    public void GamePlaySceneCreateTest() {
        // Given
        SingleGamePlayView singleGamePlayView = new SingleGamePlayView(new GameParam(0,0), (o) -> {}, (v) -> {}, () -> {});
        // When
        Scene scene = singleGamePlayView.createScene();
        // Then
        // 성공적으로 scene 이 생성되면 테스트 성공
    }
}
