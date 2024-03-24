package org.nl.javatetris.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.GamePlayController;

import static org.nl.javatetris.model.ModelConst.*;
import static org.nl.javatetris.view.ViewConst.*;

public class GamePlayScene {

    private GamePlayController gamePlayController;
    private Pane pane;

    public GamePlayScene(Runnable onPause) {
        gamePlayController = new GamePlayController(onPause, this::drawBoard, this::drawGameOver);
    }

    public Scene createScene() {
        pane = new Pane();
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
        drawBoard();

        // 키 이벤트 핸들러 설정
        scene.setOnKeyPressed(e -> {
            boolean isGameKeepGoing = gamePlayController.handleKeyPress(e);
            // 키 입력 후 보드 상태 업데이트 로직 필요
            if (isGameKeepGoing) {
                drawBoard();
            }
        });

        return scene;
    }

    // 보드를 다시 그리는 메서드
    private void drawBoard() {
        pane.getChildren().clear(); // 기존에 그려진 모든 Rectangle 제거

        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                Rectangle rect = new Rectangle(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                rect.setStroke(Color.LIGHTGRAY); // 셀의 테두리 색상

                int cellValue = gamePlayController.getBoard().getValueAt(y, x);
                switch(cellValue) {
                    case EMPTY:
                        rect.setFill(Color.WHITE); // 비어있는 셀의 색상
                        break;
                    case BORDER:
                        rect.setFill(Color.BLACK); // 벽의 색상
                        break;
                    case I:
                        rect.setFill(Color.LIGHTBLUE); // 활성화된 테트로미노의 색상
                        break;
                    case J:
                        rect.setFill(Color.BLUE); // 활성화된 테트로미노의 색상
                        break;
                    case L:
                        rect.setFill(Color.ORANGE); // 활성화된 테트로미노의 색상
                        break;
                    case O:
                        rect.setFill(Color.YELLOW); // 활성화된 테트로미노의 색상
                        break;
                    case S:
                        rect.setFill(Color.GREEN); // 활성화된 테트로미노의 색상
                        break;
                    case T:
                        rect.setFill(Color.PURPLE); // 활성화된 테트로미노의 색상
                        break;
                    case Z:
                        rect.setFill(Color.RED); // 활성화된 테트로미노의 색상
                        break;
                }
                pane.getChildren().add(rect);
            }
        }
    }

    // 게임오버 화면을 그리는 메서드
    private void drawGameOver() {
        pane.getChildren().clear(); // 기존에 그려진 모든 Rectangle 제거

        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                Rectangle rect = new Rectangle(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                rect.setStroke(Color.LIGHTGRAY); // 셀의 테두리 색상

                int cellValue = gamePlayController.getBoard().getValueAt(y, x);
                switch(cellValue) {
                    case EMPTY:
                        rect.setFill(Color.WHITE); // 비어있는 셀의 색상
                        break;
                    case BORDER:
                        rect.setFill(Color.BLACK); // 벽의 색상
                        break;
                    default:
                        rect.setFill(Color.GREY); // 활성화된 테트로미노의 색상
                        break;
                }
                pane.getChildren().add(rect);
            }
        }

        // 'GAME OVER' 문구 생성
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Verdana", 40)); // 폰트와 크기 설정
        gameOverText.setFill(Color.RED); // 문구의 색상 설정
        gameOverText.setLayoutY(Y_MAX*CELL_SIZE/2);
        gameOverText.setLayoutX(X_MAX*CELL_SIZE/2 - 120);

        // 문구를 pane에 추가
        pane.getChildren().add(gameOverText);
    }

}

