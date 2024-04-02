package org.nl.javatetris.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.nl.javatetris.controller.MainMenuController;
import org.nl.javatetris.model.settings.Settings;

public class MainMenuView implements View {

    private MainMenuController mainMenuController;
    private static final DropShadow DROP_SHADOW = createDropShadow();


    private static Label[] menuItems = new Label[]{
            // 메뉴 항목. 추가할거면 여기에 추가해
            new Label("Start Game"),
            new Label("Settings"),
            new Label("Score Board"),
            new Label("Quit")
    };


    public MainMenuView(Runnable onStartGame, Runnable onSettings, Runnable onScoreBoard) {
        this.mainMenuController = new MainMenuController(menuItems.length, onStartGame, onSettings, onScoreBoard);
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        configureBackground(layout);
        configureLogo(layout);
        configureMenuItems(layout);

        Scene scene = new Scene(layout, Settings.getInstance().getScreenSizeSettings().getScreenWidth(), Settings.getInstance().getScreenSizeSettings().getScreenHeight());

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            mainMenuController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(mainMenuController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(mainMenuController.getSelectedItemIndex());

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
        Image backgroundImage = new Image("file:src/main/resources/images/main.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        layout.setBackground(new Background(background));
    }

    private void configureLogo(VBox layout) {
        ImageView imageView = new ImageView(new Image("file:src/main/resources/images/logo.png"));
        imageView.setFitWidth(250);
        imageView.setFitHeight(100);
        layout.getChildren().add(imageView);
    }

    private void configureMenuItems(VBox layout) {
        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getScreenSizeSettings().getDefaultFontSize()));
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
}
