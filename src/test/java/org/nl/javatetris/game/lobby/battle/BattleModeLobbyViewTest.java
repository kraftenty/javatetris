package org.nl.javatetris.game.lobby.battle;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nl.javatetris.game.GameParam;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.function.Consumer;

@ExtendWith(ApplicationExtension.class)
public class BattleModeLobbyViewTest {

    private String output = "";

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
    }

    @Test
    public void battleModeLobbySceneCreateTest() {
        // Given
        BattleModeLobbyView battleModeLobbyView = new BattleModeLobbyView(
                () -> output = "BackToMenu",
                new Consumer<GameParam>() {
                    @Override
                    public void accept(GameParam gameParam) {
                        output = "Start:" + gameParam.getMode();
                    }
                }
        );

        // When
        Scene scene = battleModeLobbyView.createScene();

        // Then
        // 성공적으로 scene 이 생성되면 테스트 성공
    }

}
