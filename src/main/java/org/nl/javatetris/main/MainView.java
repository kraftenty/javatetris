package org.nl.javatetris.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.nl.javatetris.config.manager.BackgroundManager;
import org.nl.javatetris.config.manager.FontManager;
import org.nl.javatetris.settings.Settings;

public class MainView {

    private MainController mainController;
    private static final DropShadow DROP_SHADOW = createDropShadow();


    private static Label[] menuItems = new Label[]{
            new Label("Classic Mode"),
            new Label("Item Mode"),
            new Label("Battle Mode"),
            new Label("Settings"),
            new Label("Score Board"),
            new Label("Quit")
    };


    public MainView(Runnable onClassicModeLobby, Runnable onItemModeLobby, Runnable onBattleModeLobby, Runnable onSettings, Runnable onScoreBoard) {
        this.mainController = new MainController(menuItems.length, onClassicModeLobby, onItemModeLobby, onBattleModeLobby, onSettings, onScoreBoard);
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        configureBackground(layout);
        configureLogo(layout);
        configureMenuItems(layout);
        addKeyControlHints(layout);

        Scene scene = new Scene(
                layout,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            mainController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(mainController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(mainController.getSelectedItemIndex());

        return scene;

    }

    private static DropShadow createDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        return dropShadow;
    }

    private void configureBackground(VBox layout) {
        layout.setBackground(BackgroundManager.getMainBackground());
    }

    private void configureLogo(VBox layout) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/logo.png")));
        imageView.setFitWidth(250);
        imageView.setFitHeight(100);
        layout.getChildren().add(imageView);
    }

    private void configureMenuItems(VBox layout) {
        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            menuItem.setEffect(DROP_SHADOW);
            layout.getChildren().add(menuItem);
        }
    }

    // 선택된 항목에 따라 UI 업데이트
    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }

    // KeyControl hint를 표시
    private void addKeyControlHints(VBox layout) {
        Label keyControlHints = new Label(
                "Up Down to move, Enter to Select "
        );
        keyControlHints.setFont(FontManager.getSquareFont((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2))); // Smaller font size
        keyControlHints.setTextFill(Color.LIGHTGREY);
        keyControlHints.setEffect(DROP_SHADOW);
        VBox.setMargin(keyControlHints, new Insets((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2), 0, 0, 0)); // Add top margin to push the label down
        layout.getChildren().add(keyControlHints);
    }
}
