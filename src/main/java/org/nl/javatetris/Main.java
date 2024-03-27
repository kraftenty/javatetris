package org.nl.javatetris;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nl.javatetris.model.settings.Settings;
import org.nl.javatetris.view.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Settings.getInstance(); // 싱글톤 인스턴스 생성 및 JSON 파일 로드
        new SceneManager(primaryStage).showStartMenu();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
