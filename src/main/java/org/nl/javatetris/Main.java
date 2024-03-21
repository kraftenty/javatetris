package org.nl.javatetris;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.nl.javatetris.controller.Controller;
import org.nl.javatetris.model.Board;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {





        Board board = new Board();
        board.spawnTetromino();
        Controller controller = new Controller(board);

        // ui 만드는 부분
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 800);
        scene.setOnKeyPressed(controller::handleKeyPress);
        primaryStage.setTitle("JavaTetris");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
