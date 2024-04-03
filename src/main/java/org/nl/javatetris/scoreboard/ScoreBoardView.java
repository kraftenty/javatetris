package org.nl.javatetris.scoreboard;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.config.FontManager;


public class ScoreBoardView {

    private ScoreBoardController scoreBoardController;
    private static Label[] menuItems = new Label[]{
            new Label("Main Menu"),
    };

    public ScoreBoardView(Runnable onBackToMenu) {
        this.scoreBoardController = new ScoreBoardController(onBackToMenu);
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 배경 설정
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Label title = new Label("ScoreBoard");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        layout.getChildren().add(title);

        // ScoreBoard 인스턴스에서 각 모드의 리스트를 가져옴
        List<Score> classicModeScores = ScoreBoard.getInstance().getClassicModeScores();
        List<Score> itemModeScores = ScoreBoard.getInstance().getItemModeScores();

        // 최근 점수를 본 적이 없다면 각 모드의 recentScoreIndex를 가져옴
        Integer classicModeRecentScoreIndex = null;
        if (!ScoreBoard.isClassicModeRecentScoreViewedFlag()) {
            ScoreBoard.setClassicModeRecentScoreViewedFlag(true);
            classicModeRecentScoreIndex = ScoreBoard.getClassicModeRecentScoreIndex();
        }
        Integer itemModeRecentScoreIndex = null;
        if (!ScoreBoard.isItemModeRecentScoreViewedFlag()) {
            ScoreBoard.setItemModeRecentScoreViewedFlag(true);
            itemModeRecentScoreIndex = ScoreBoard.getItemModeRecentScoreIndex();
        }


        // 스코어 10개 내림차순으로 display
        // Classic Mode
        for (int i = 0; i < classicModeScores.size(); i++) {
            Score score = classicModeScores.get(i);
            String formattedText = String.format("%-2d.   %-10s   %-8d   %-1d", i + 1, score.getName(), score.getPoint(), score.getDifficulty());
            Label scoreLabel = new Label(formattedText);
            if (classicModeRecentScoreIndex != null && classicModeRecentScoreIndex == i) {
                scoreLabel.setTextFill(Color.CYAN);
            } else {
                scoreLabel.setTextFill(Color.WHITE);
            }
            scoreLabel.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            layout.getChildren().add(scoreLabel);
        }

        // Item Mode
        for (int i = 0; i < itemModeScores.size(); i++) {
            Score score = itemModeScores.get(i);
            String formattedText = String.format("%-2d.   %-10s   %-8d", i + 1, score.getName(), score.getPoint());
            Label scoreLabel = new Label(formattedText);
            if (itemModeRecentScoreIndex != null && itemModeRecentScoreIndex == i) {
                scoreLabel.setTextFill(Color.CYAN);
            } else {
                scoreLabel.setTextFill(Color.WHITE);
            }
            scoreLabel.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
            layout.getChildren().add(scoreLabel);
        }

        configureMenuItems(layout);

        Scene scene = new Scene(
                layout,
                Settings.getInstance().getSizeSetting().getScreenWidth(),
                Settings.getInstance().getSizeSetting().getScreenHeight()
        );

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            scoreBoardController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(scoreBoardController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(scoreBoardController.getSelectedItemIndex());

        return scene;
    }

    private static void configureMenuItems(VBox layout) {
        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
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