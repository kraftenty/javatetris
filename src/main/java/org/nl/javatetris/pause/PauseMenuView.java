package org.nl.javatetris.pause;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.config.manager.FontManager;

public class PauseMenuView {

    private PauseMenuController pauseMenuController;
    private static Label[] menuItems = new Label[]{
            // 메뉴 항목. 추가할거면 여기에 추가해
            new Label("Resume"),
            new Label("Main Menu"),
            new Label("Quit")
    };
    private PauseMenuParam pauseMenuParam;

    public PauseMenuView(PauseMenuParam pauseMenuParam, Runnable onResumeSingleGame, Runnable onResumeBattleGame, Runnable onBackToMenu) {
        this.pauseMenuParam = pauseMenuParam;
        this.pauseMenuController = new PauseMenuController(menuItems.length, onResumeSingleGame, onResumeBattleGame, onBackToMenu, pauseMenuParam);
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 배경 설정
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Label title = new Label("Paused");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        layout.getChildren().add(title);

        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            layout.getChildren().add(menuItem);
        }
        addKeyControlHints(layout);

        Scene scene = null;
        if (pauseMenuParam.getMode() == ControllerConst.PAUSE_MENU_SINGLE_MODE) {
            scene = new Scene(
                    layout,
                    Settings.getInstance().getSizeSetting().getScreenWidth(),
                    Settings.getInstance().getSizeSetting().getScreenHeight()
            );
        } else if (pauseMenuParam.getMode() == ControllerConst.PAUSE_MENU_BATTLE_MODE) {
            scene = new Scene(
                    layout,
                    Settings.getInstance().getSizeSetting().getScreenWidth() * 2,
                    Settings.getInstance().getSizeSetting().getScreenHeight()
            );
        } else {
            throw new IllegalArgumentException("Invalid pause menu param");
        }

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            pauseMenuController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(pauseMenuController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(pauseMenuController.getSelectedItemIndex());

        return scene;
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
                "Up/Down to move, Enter to Select "
        );
        keyControlHints.setFont(FontManager.getSquareFont((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2))); // Smaller font size
        keyControlHints.setTextFill(Color.LIGHTGREY);
        VBox.setMargin(keyControlHints, new Insets((int)(Settings.getInstance().getSizeSetting().getDefaultFontSize()/2), 0, 0, 0)); // Add top margin to push the label down
        layout.getChildren().add(keyControlHints);
    }
}
