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
            StartMenuScene startMenuScene = new StartMenuScene(
                    this::showGamePlay,
                    this::showSettingsMenu
            );
            this.startMenuScene = startMenuScene.createScene();
        }

        setScene(startMenuScene);
        currentSceneNumber = START_MENU_SCENE;
    }

    public void showGamePlay() {
        if (gamePlayScene == null) {
            GamePlayScene gamePlayScene = new GamePlayScene(this::showPauseMenu);
            this.gamePlayScene = gamePlayScene.createScene();
        }

        setScene(gamePlayScene);
        currentSceneNumber = GAME_PLAY_SCENE;
    }

    private void resumeGame() {
        currentSceneNumber = GAME_PLAY_SCENE;
        setScene(gamePlayScene);
    }

    public void showPauseMenu() {
        if (pauseMenuScene == null) {
            PauseMenuScene pauseMenuScene = new PauseMenuScene(this::resumeGame);
            this.pauseMenuScene = pauseMenuScene.createScene();
        }

        setScene(pauseMenuScene);
        currentSceneNumber = PAUSE_MENU_SCENE;
    }

    public void showSettingsMenu() {
        if (settingsMenuScene == null) {
            SettingsMenuScene settingsMenuScene = new SettingsMenuScene(this::showStartMenu);
            this.settingsMenuScene = settingsMenuScene.createScene();
        }

        setScene(settingsMenuScene);
        currentSceneNumber = SETTINGS_MENU_SCENE;
    }

}
