package org.nl.javatetris.gameplay.classic;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.nl.javatetris.config.FontManager;
import org.nl.javatetris.gameplay.GameParam;
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
        Image backgroundImage = new Image("file:src/main/resources/images/main.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        layout.setBackground(new Background(background));
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

}