package org.nl.javatetris;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nl.javatetris.view.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new SceneManager(primaryStage).showStartMenu();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
