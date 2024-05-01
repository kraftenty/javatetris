package org.nl.javatetris.config;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.play.battle.BattleGamePlayView;
import org.nl.javatetris.game.play.single.SingleGamePlayView;
import org.nl.javatetris.game.lobby.battle.BattleModeLobbyView;
import org.nl.javatetris.game.lobby.classic.ClassicModeLobbyView;
import org.nl.javatetris.game.gameover.GameOverParam;
import org.nl.javatetris.game.gameover.GameOverView;
import org.nl.javatetris.game.lobby.item.ItemModeLobbyView;
import org.nl.javatetris.main.MainView;
import org.nl.javatetris.pause.PauseMenuParam;
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
    private Scene battleModeLobbyScene;
    private Scene singleGamePlayScene;
    private Scene battleGamePlayScene;
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
                this::showBattleModeLobby,
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
                gameParam -> showSingleGamePlay(gameParam)
        );
        this.classicModeLobbyScene = classicModeLobbyView.createScene();

        setScene(classicModeLobbyScene);
        currentSceneNumber = CLASSIC_MODE_LOBBY_SCENE;
    }

    public void showItemModeLobby() {
        ItemModeLobbyView itemModeLobbyView = new ItemModeLobbyView(
                this::showStartMenu,
                gameParam -> showSingleGamePlay(gameParam)
        );
        this.itemModeLobbyScene = itemModeLobbyView.createScene();

        setScene(itemModeLobbyScene);
        currentSceneNumber = ITEM_MODE_LOBBY_SCENE;
    }

    public void showBattleModeLobby() {
        BattleModeLobbyView battleModeLobbyView = new BattleModeLobbyView(
                this::showStartMenu,
                gameParam -> showBattleGamePlay(gameParam)
        );
        this.battleModeLobbyScene = battleModeLobbyView.createScene();

        setScene(battleModeLobbyScene);
        currentSceneNumber = BATTLE_MODE_LOBBY_SCENE;
    }

    public void showSingleGamePlay(GameParam gameParam) {
        SingleGamePlayView singleGamePlayView = new SingleGamePlayView(
                gameParam,
                o -> showPauseMenu(o),
                o -> showGameOver(o),
                this::showStartMenu
        );
        this.singleGamePlayScene = singleGamePlayView.createScene();

        setScene(singleGamePlayScene);
        currentSceneNumber = SINGLE_GAME_PLAY_SCENE;
    }

    public void showBattleGamePlay(GameParam gameParam) {
        BattleGamePlayView battleGamePlayView = new BattleGamePlayView(
                gameParam,
                o -> showPauseMenu(o),
                this::showStartMenu
        );
        this.battleGamePlayScene = battleGamePlayView.createScene();
        setScene(battleGamePlayScene);
        currentSceneNumber = BATTLE_GAME_PLAY_SCENE;
    }

    private void resumeSingleGame() {
        setScene(singleGamePlayScene);
        currentSceneNumber = SINGLE_GAME_PLAY_SCENE;
    }

    private void resumeBattleGame() {
        setScene(battleGamePlayScene);
        currentSceneNumber = BATTLE_GAME_PLAY_SCENE;
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


    public void showPauseMenu(PauseMenuParam pauseMenuParam) {
        PauseMenuView pauseMenuView = new PauseMenuView(
                pauseMenuParam,
                this::resumeSingleGame,
                this::resumeBattleGame,
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
