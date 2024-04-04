package org.nl.javatetris;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nl.javatetris.scoreboard.ScoreBoard;

import org.nl.javatetris.settings.SettingsManager;
import org.nl.javatetris.config.SceneManager;

public class
Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ScoreBoard.ready();
        SettingsManager.loadSettings();
        new SceneManager(primaryStage).showStartMenu();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
