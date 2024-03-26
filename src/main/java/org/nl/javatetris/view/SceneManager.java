package org.nl.javatetris.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.nl.javatetris.view.ViewConst.*;


public class SceneManager {

    private Stage primaryStage;

    private Scene startMenuScene;
    private Scene gamePlayScene;
    private Scene pauseMenuScene;
    private Scene settingsMenuScene;
    private Scene scoreBoardScene;

    private static int currentSceneNumber; // 현재 Scene 번호


    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {
        primaryStage.setTitle("JavaTetris - TEAM 4");
        primaryStage.show();
        currentSceneNumber = NO_SCENE;
    }

    public static int getCurrentSceneNumber() {
        return currentSceneNumber;
    }

    private void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    public void showStartMenu() {
        if (startMenuScene == null) {
            StartMenuView startMenuView = new StartMenuView(
                    this::showGamePlay,
                    this::showSettingsMenu,
                    this::showScoreBoard
            );
            this.startMenuScene = startMenuView.createScene();
        }

        setScene(startMenuScene);
        currentSceneNumber = START_MENU_SCENE;
    }

    public void showGamePlay() {
        GamePlayView gamePlayView = new GamePlayView(
                this::showPauseMenu,
                score -> showGameOver(score)
        );
        this.gamePlayScene = gamePlayView.createScene();

        setScene(gamePlayScene);
        currentSceneNumber = GAME_PLAY_SCENE;
    }

    private void resumeGame() {
        currentSceneNumber = GAME_PLAY_SCENE;
        setScene(gamePlayScene);
    }

    private void endGame() {
        showStartMenu();
    }

    public void showGameOver(Integer score) {
        GameOverView gameOverView = new GameOverView(
                score,
                this::showStartMenu
        );
        Scene gameOverScene = gameOverView.createScene();

        setScene(gameOverScene);
        currentSceneNumber = GAME_OVER_SCENE;
    }

    public void showPauseMenu() {
        if (pauseMenuScene == null) {
            PauseMenuView pauseMenuView = new PauseMenuView(this::resumeGame, this::endGame);
            this.pauseMenuScene = pauseMenuView.createScene();
        }

        setScene(pauseMenuScene);
        currentSceneNumber = PAUSE_MENU_SCENE;
    }

    public void showSettingsMenu() {
        if (settingsMenuScene == null) {
            SettingsMenuView settingsMenuView = new SettingsMenuView(this::showStartMenu);
            this.settingsMenuScene = settingsMenuView.createScene();
        }

        setScene(settingsMenuScene);
        currentSceneNumber = SETTINGS_MENU_SCENE;
    }

    public void showScoreBoard() {
        if (scoreBoardScene == null) {
            ScoreBoardView scoreBoardView = new ScoreBoardView(this::showStartMenu);
            this.scoreBoardScene = scoreBoardView.createScene();
        }

        setScene(scoreBoardScene);
        currentSceneNumber = SCORE_BOARD_SCENE;
    }

}
