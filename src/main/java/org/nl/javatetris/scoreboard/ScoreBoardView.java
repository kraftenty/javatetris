package org.nl.javatetris.scoreboard;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.config.manager.FontManager;


public class ScoreBoardView {

    private final ScoreBoardController scoreBoardController;
    private static final Label[] menuItems = new Label[]{
            new Label("Main Menu"),
    };

    public ScoreBoardView(Runnable onBackToMenu) {
        this.scoreBoardController = new ScoreBoardController(onBackToMenu);
    }

    public Scene createScene() {
        VBox verticalLayout = new VBox(20);
        verticalLayout.setAlignment(Pos.CENTER);

        Label title = new Label("ScoreBoard");
        title.setTextFill(Color.YELLOW);
        title.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getTitleFontSize()));
        verticalLayout.getChildren().add(title);


        HBox horizontalLayout = new HBox(20);
        horizontalLayout.setAlignment(Pos.CENTER);

        // 배경 설정
        verticalLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Classic Mode 스코어보드를 담을 VBox
        VBox classicLayout = new VBox(10);
        classicLayout.setAlignment(Pos.CENTER_LEFT);

        // Item Mode 스코어보드를 담을 VBox
        VBox itemLayout = new VBox(10);
        itemLayout.setAlignment(Pos.CENTER_RIGHT);

        // Classic Mode 타이틀 설정
        Label classicTitle = new Label("Classic Mode");
        classicTitle.setTextFill(Color.YELLOW);
        classicTitle.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        classicTitle.setAlignment(Pos.CENTER_LEFT);
        classicLayout.getChildren().add(classicTitle);

        // Item Mode 타이틀 설정
        Label itemTitle = new Label("Item Mode");
        itemTitle.setTextFill(Color.YELLOW);
        itemTitle.setFont(FontManager.getTopshowFont(Settings.getInstance().getSizeSetting().getDefaultFontSize()));
        itemTitle.setAlignment(Pos.CENTER_LEFT);
        itemLayout.getChildren().add(itemTitle);

        // 최근 점수를 본 적이 없다면 각 모드의 recentScoreIndex 를 가져옴
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

        // ScoreBoard 인스턴스에서 각 모드의 리스트를 가져옴
        List<Score> classicModeScores = ScoreBoard.getInstance().getClassicModeScores();
        List<Score> itemModeScores = ScoreBoard.getInstance().getItemModeScores();

        addScoresToLayout(classicModeScores, classicModeRecentScoreIndex, classicLayout, 0);
        addScoresToLayout(itemModeScores, itemModeRecentScoreIndex, itemLayout, 1);

        horizontalLayout.getChildren().addAll(classicLayout, itemLayout);
        verticalLayout.getChildren().add(horizontalLayout);
        configureMenuItems(verticalLayout);

        Scene scene = new Scene(
                verticalLayout,
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

    private void addScoresToLayout(List<Score> scores, Integer recentScoreIndex, VBox layout, Integer gameMode) {
        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);

            // 순위 번호 생성
            Text rankText = new Text(i + 1 + ". ");
            rankText.setFill(Color.CYAN);
            rankText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize() / 1.5));

            // scoreInfo 생성
            String scoreInfo = null;
            if (gameMode == 0) {
                int difficulty = score.getDifficulty();
                String difficultyText = difficulty == 0 ? "Easy" : difficulty == 1 ? "Normal" : "Hard";
                scoreInfo = String.format("%-8s   %-7d [%s]", score.getName(), score.getPoint(), difficultyText);
            } else if (gameMode == 1) {
                scoreInfo = String.format("%8s   %7d", score.getName(), score.getPoint());
            }

            Text scoreInfoText = new Text(scoreInfo);
            scoreInfoText.setFill(recentScoreIndex != null && recentScoreIndex == i ? Color.CYAN : Color.WHITE);
            scoreInfoText.setFont(FontManager.getSquareFont(Settings.getInstance().getSizeSetting().getDefaultFontSize() / 1.5));

            // TextFlow 에 순위와 나머지 정보 추가
            TextFlow textFlow = new TextFlow(rankText, scoreInfoText);
            if (gameMode == 0) {
                textFlow.setTextAlignment(TextAlignment.LEFT);
            } else if (gameMode == 1) {
                textFlow.setTextAlignment(TextAlignment.RIGHT);
            }

            // VBox 에 TextFlow 추가
            layout.getChildren().add(textFlow);
        }
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