package org.nl.javatetris.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nl.javatetris.model.settings.Settings;

import static org.nl.javatetris.view.ViewConst.*;


public class SceneManager {

    private Stage primaryStage;

    private Scene startMenuScene;
    private Scene gamePlayScene;
    private Scene pauseMenuScene;
    private Scene settingsMenuScene;
    private Scene scoreBoardScene;
    private Scene CheckingInitSetScene;
    private Scene CheckingBoardInitScene;
    private Scene SettingKeyScene;

    private static int currentSceneNumber; // 현재 Scene 번호


    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {

        primaryStage.setTitle("Tetris - TEAM 4");
        primaryStage.setResizable(false);
        primaryStage.show();
        currentSceneNumber = NO_SCENE;
    }

    public static int getCurrentSceneNumber() {
        return currentSceneNumber;
    }

    private void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    private void handleScreenSizeChange() {
        showSettingsMenu();
    }

    public void showStartMenu() {
        MainMenuView mainMenuView = new MainMenuView(
                this::showGamePlay,
                this::showSettingsMenu,
                this::showScoreBoard
        );
        this.startMenuScene = mainMenuView.createScene();

        setScene(startMenuScene);
        currentSceneNumber = START_MENU_SCENE;
    }

    public void showGamePlay() {
        GamePlayView gamePlayView = new GamePlayView(
                this::showPauseMenu,
                score -> showGameOver(score),
                this::showStartMenu
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
                this::showScoreBoard
        );
        Scene gameOverScene = gameOverView.createScene();

        setScene(gameOverScene);
        currentSceneNumber = GAME_OVER_SCENE;
    }

    public void showPauseMenu() {
        PauseMenuView pauseMenuView = new PauseMenuView(
                this::resumeGame,
                this::endGame
        );
        this.pauseMenuScene = pauseMenuView.createScene();

        setScene(pauseMenuScene);
        currentSceneNumber = PAUSE_MENU_SCENE;
    }

    public void showSettingsMenu() {
        SettingsMenuView settingsMenuView = new SettingsMenuView(
                this::showStartMenu,
                this::showCheckingInitSet,
                this::showCheckingBoardInit,
                this::showSettingKeyScene,
                this::handleScreenSizeChange
        );
        this.settingsMenuScene = settingsMenuView.createScene();

        setScene(settingsMenuScene);
        currentSceneNumber = SETTINGS_MENU_SCENE;
    }

    public void showCheckingInitSet() {
        ResetSettingsView resetSettingsView = new ResetSettingsView(this::showSettingsMenu);
        this.CheckingInitSetScene = resetSettingsView.createScene();

        setScene(CheckingInitSetScene);
        currentSceneNumber = CHECKING_INIT_SET_SCENE;
    }

    public void showCheckingBoardInit() {
        ResetScoreBoardView resetScoreBoardView = new ResetScoreBoardView(this::showSettingsMenu);
        this.CheckingBoardInitScene = resetScoreBoardView.createScene();

        setScene(CheckingBoardInitScene);
        currentSceneNumber = CHECKING_BOARD_INIT;
    }
    public void showSettingKeyScene() {
        KeySettingView keySettingView = new KeySettingView(this::showSettingsMenu);
        this.SettingKeyScene = keySettingView.createScene();

        setScene(SettingKeyScene);
        currentSceneNumber = SETTING_KEY_MENU_SCENE;
    }

    public void showScoreBoard() {
        ScoreBoardView scoreBoardView = new ScoreBoardView(this::showStartMenu);
        this.scoreBoardScene = scoreBoardView.createScene();

        setScene(scoreBoardScene);
        currentSceneNumber = SCORE_BOARD_SCENE;
    }

}
