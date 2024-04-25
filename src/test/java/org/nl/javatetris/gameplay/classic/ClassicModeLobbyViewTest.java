package org.nl.javatetris.gameplay.classic;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class ClassicModeLobbyViewTest {

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
    }

    @Test
    public void ClassicModeLobbySceneCreateTest() {
        // Given
        ClassicModeLobbyView classicModeLobbyView = new ClassicModeLobbyView(() -> {}, (v) -> {});
        // When
        Scene scene = classicModeLobbyView.createScene();
        // Then
        // 성공적으로 scene 이 생성되면 테스트 성공
    }
}
