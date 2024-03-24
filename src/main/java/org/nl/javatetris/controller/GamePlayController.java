package org.nl.javatetris.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.nl.javatetris.model.Board;
import org.nl.javatetris.view.SceneManager;
import org.nl.javatetris.view.ViewConst;

/**
 * 게임 플레이의 운영을 담당하는 컨트롤러
 */
public class GamePlayController {

    private Board board;                // 보드
    private Runnable onPause;           // 일시정지 콜백
    private Runnable onBoardUpdate;     // 보드 업데이트 콜백
    private Runnable onGameOver;        // 게임오버 콜백
    private Timeline timeline;          // 타임라인
    private int level = 0;              // 게임 레벨
    private boolean isGameOver = false; // 게임오버 여부


    public GamePlayController(Runnable onPause, Runnable onBoardUpdate, Runnable onGameOver ) {
        this.onPause = onPause;
        this.onBoardUpdate = onBoardUpdate;
        this.onGameOver = onGameOver;
        initialize();
    }

    private void initialize() {
        board = new Board(); // 보드 생성
        board.spawnTetromino(); // 테트로미노 스폰
        startTimeline(); // 타임라인 시작
    }

    private void startTimeline() {
        if (timeline != null) {
            timeline.stop(); // 기존 타임라인이 존재한다면 중지
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(getSpeedByLevel()), e -> {
            if(SceneManager.getCurrentSceneNumber() == ViewConst.GAME_PLAY_SCENE) {
                boolean isProperlyDowned = board.moveTetrominoDown();
                if (!isProperlyDowned) {
                    System.out.println("!isProperlyDowned");
                    timeline.stop(); // 타임라인 중지 하고
                    isGameOver = true; // 게임오버 상태로 변경
                    onGameOver.run(); // 게임오버 콜백 호출
                } else {
                    // 디버그용
                    // board.drawBoard();
                    onBoardUpdate.run();
                }
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private double getSpeedByLevel() {
        return Math.max(0.1, 1 - level * 0.1);
    }

    public void levelUp() {
        level++;
        startTimeline(); // 새로운 속도로 타임라인 재시작
    }

    public Board getBoard() {
        return board;
    }

    public boolean handleKeyPress(KeyEvent e) {
        if (isGameOver) {
            return false;
        }
        switch (e.getCode()) {
            case ESCAPE:
                onPause.run();
                break;
            case DOWN:
                board.moveTetrominoDown();
                break;
            case LEFT:
                board.moveTetrominoLeft();
                break;
            case RIGHT:
                board.moveTetrominoRight();
                break;
            case UP:
                board.rotateTetromino();
                break;
            default:
                break;
        }
        return true;
    }
}
