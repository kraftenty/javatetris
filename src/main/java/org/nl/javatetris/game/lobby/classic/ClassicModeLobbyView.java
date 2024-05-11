package org.nl.javatetris.game.lobby.classic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.nl.javatetris.config.manager.BackgroundManager;
import org.nl.javatetris.config.manager.FontManager;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.settings.Settings;

import java.util.function.Consumer;

public class ClassicModeLobbyView {

    private ClassicModeLobbyController classicModeLobbyController;
    private static final DropShadow DROP_SHADOW = createDropShadow();
    private static Label[] menuItems = new Label[]{
            new Label("Difficulty - easy"),
            new Label("Start Game"),
            new Label("Back")
    };

    public ClassicModeLobbyView(Runnable onBackToMenu, Consumer<GameParam> onStartGame) {
        this.classicModeLobbyController = new ClassicModeLobbyController(
                menuItems.length,
                onBackToMenu,
                onStartGame
        );
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        configureBackground(layout);

        Label title = new Label("Classic Mode");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        title.setEffect(DROP_SHADOW);
        layout.getChildren().add(title);

        updateDifficultyText();
        configureMenuItems(layout);
        addKeyControlHints(layout);

        Scene scene = new Scene(
                layout,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        scene.setOnKeyPressed(e -> {
            classicModeLobbyController.handleKeyPress(e);
            updateDifficultyText();
            updateMenuItems(classicModeLobbyController.getSelectedItemIndex());
        });

        updateMenuItems(classicModeLobbyController.getSelectedItemIndex());

        return scene;
    }

    private void configureBackground(VBox layout) {
        layout.setBackground(BackgroundManager.getMainBackground());
    }

    private void configureMenuItems(VBox layout) {
        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            menuItem.setEffect(DROP_SHADOW);
            layout.getChildren().add(menuItem);
        }
    }

    private static DropShadow createDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        return dropShadow;
    }

    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }

    private void updateDifficultyText() {
        if (classicModeLobbyController.getDifficulty() == 0)
            menuItems[0].setText("Difficulty - easy");
        else if (classicModeLobbyController.getDifficulty() == 1)
            menuItems[0].setText("Difficulty - normal");
        else
            menuItems[0].setText("Difficulty - hard");
    }

    // KeyControl hint를 표시
    private void addKeyControlHints(VBox layout) {
        Label keyControlHints = new Label(
                "Up/Down to move, Enter to Select "
        );
        keyControlHints.setFont(FontManager.getSquareFont((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2))); // Smaller font size
        keyControlHints.setTextFill(Color.LIGHTGREY);
        keyControlHints.setEffect(DROP_SHADOW);
        VBox.setMargin(keyControlHints, new Insets((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2), 0, 0, 0)); // Add top margin to push the label down
        layout.getChildren().add(keyControlHints);
    }

}
