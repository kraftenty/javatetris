package org.nl.javatetris.game.lobby.battle;

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

public class BattleModeLobbyView {

    private BattleModeLobbyController battleModeLobbyController;
    private static final DropShadow DROP_SHADOW = createDropShadow();

    private static Label[] menuItems = new Label[]{
            new Label("Classic Battle"),
            new Label("Item Battle"),
            new Label("Time Attack Battle"),
            new Label("Back")
    };

    public BattleModeLobbyView(Runnable onBackToMenu, Consumer<GameParam> onStartGame) {
        this.battleModeLobbyController = new BattleModeLobbyController(
                menuItems.length,
                onBackToMenu,
                onStartGame
        );
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        configureBackground(layout);

        Label title = new Label("Battle Mode");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        title.setEffect(DROP_SHADOW);
        layout.getChildren().add(title);

        configureMenuItems(layout);
        addKeyControlHints(layout);

        Scene scene = new Scene(
                layout,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        scene.setOnKeyPressed(e -> {
            battleModeLobbyController.handleKeyPress(e);
            updateMenuItems(battleModeLobbyController.getSelectedItemIndex());
        });

        updateMenuItems(battleModeLobbyController.getSelectedItemIndex());

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
