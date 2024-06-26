package org.nl.javatetris.game.lobby.item;


import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nl.javatetris.game.lobby.item.ItemModeLobbyView;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class ItemModeLobbyViewTest {

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
    }

    @Test
    public void ItemModeLobbySceneCreateTest() {
        // Given
        ItemModeLobbyView itemModeLobbyView = new ItemModeLobbyView(() -> {}, (v) -> {});
        // When
        Scene scene = itemModeLobbyView.createScene();
        // Then
        // 성공적으로 scene 이 생성되면 테스트 성공
    }
}
