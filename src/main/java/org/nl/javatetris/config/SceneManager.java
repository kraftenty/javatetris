package org.nl.javatetris.config;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nl.javatetris.gameplay.GameParam;
import org.nl.javatetris.gameplay.GamePlayView;
import org.nl.javatetris.gameplay.classic.ClassicModeLobbyView;
import org.nl.javatetris.gameplay.gameover.GameOverParam;
import org.nl.javatetris.gameplay.gameover.GameOverView;
import org.nl.javatetris.gameplay.item.ItemModeLobbyView;
import org.nl.javatetris.main.MainView;
import org.nl.javatetris.pause.PauseMenuView;
import org.nl.javatetris.scoreboard.ScoreBoardView;
import org.nl.javatetris.settings.SettingsMenuView;
import org.nl.javatetris.settings.keysetting.KeySettingView;
import org.nl.javatetris.settings.resetscoreboard.ResetScoreBoardView;
import org.nl.javatetris.settings.resetsettings.ResetSettingsView;

import static org.nl.javatetris.config.constant.ViewConst.*;


public class SceneManager {

    private Stage primaryStage;

    private Scene startMenuScene;
    private Scene classicModeLobbyScene;
    private Scene itemModeLobbyScene;
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
        MainView mainView = new MainView(
                this::showClassicModeLobby,
                this::showItemModeLobby,
                this::showSettingsMenu,
                this::showScoreBoard
        );
        this.startMenuScene = mainView.createScene();

        setScene(startMenuScene);
        currentSceneNumber = START_MENU_SCENE;
    }

    public void showClassicModeLobby() {
        ClassicModeLobbyView classicModeLobbyView = new ClassicModeLobbyView(
                this::showStartMenu,
                gameParam -> showGamePlay(gameParam)
        );
        this.classicModeLobbyScene = classicModeLobbyView.createScene();

        setScene(classicModeLobbyScene);
        currentSceneNumber = CLASSIC_MODE_LOBBY_SCENE;
    }

    public void showItemModeLobby() {
        ItemModeLobbyView itemModeLobbyView = new ItemModeLobbyView(
                this::showStartMenu,
                gameParam -> showGamePlay(gameParam)
        );
        this.itemModeLobbyScene = itemModeLobbyView.createScene();

        setScene(itemModeLobbyScene);
        currentSceneNumber = ITEM_MODE_LOBBY_SCENE;
    }

    public void showGamePlay(GameParam gameParam) {
        GamePlayView gamePlayView = new GamePlayView(
                gameParam,
                this::showPauseMenu,
                o -> showGameOver(o),
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

    public void showGameOver(GameOverParam gameOverParam) {
        GameOverView gameOverView = new GameOverView(
                gameOverParam,
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
