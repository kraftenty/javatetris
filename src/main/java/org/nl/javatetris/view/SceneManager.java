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
    private Scene CheckingInitSetScene;
    private Scene CheckingBoardInitScene;
    private Scene SettingKeyScene;

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
        StartMenuView startMenuView = new StartMenuView(
                this::showGamePlay,
                this::showSettingsMenu,
                this::showScoreBoard
        );
        this.startMenuScene = startMenuView.createScene();

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
                this::endGame,
                this::showSettingsMenu);
        this.pauseMenuScene = pauseMenuView.createScene();

        setScene(pauseMenuScene);
        currentSceneNumber = PAUSE_MENU_SCENE;
    }

    public void showSettingsMenu() {
        SettingsMenuView settingsMenuView = new SettingsMenuView(
                this::showStartMenu,
                this::showCheckingInitSet,
                this::showCheckingBoardInit,
                this::showSettingKeyScene
        );
        this.settingsMenuScene = settingsMenuView.createScene();

        setScene(settingsMenuScene);
        currentSceneNumber = SETTINGS_MENU_SCENE;
    }

    public void showCheckingInitSet() {
        CheckingInitSetView checkingInitSetView = new CheckingInitSetView(this::showSettingsMenu);
        this.CheckingInitSetScene = checkingInitSetView.createScene();

        setScene(CheckingInitSetScene);
        currentSceneNumber = CHECKING_INIT_SET_SCENE;
    }

    public void showCheckingBoardInit() {
        CheckingBoardInitView checkingBoardInitView = new CheckingBoardInitView(this::showSettingsMenu);
        this.CheckingBoardInitScene = checkingBoardInitView.createScene();

        setScene(CheckingBoardInitScene);
        currentSceneNumber = CHECKING_BOARD_INIT;
    }
    public void showSettingKeyScene() {
        SettingsKeyMenuView settingsKeyMenuView = new SettingsKeyMenuView(this::showSettingsMenu);
        this.SettingKeyScene = settingsKeyMenuView.createScene();

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
