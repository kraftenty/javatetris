package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static org.nl.javatetris.view.ViewConst.*;

public class SettingsMenuScene {

    private Runnable onBackToMenu;

    public SettingsMenuScene(Runnable onBackToMenu) {
        this.onBackToMenu = onBackToMenu;
    }

    public Scene createScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("Settings");
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> onBackToMenu.run());

        layout.getChildren().addAll(title, backButton);

        return new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);

    }
}
