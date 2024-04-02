package org.nl.javatetris.gameplay;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.gameplay.tetromino.generator.TetrominoGenerator;
import org.nl.javatetris.config.SceneManager;
import org.nl.javatetris.config.constant.ViewConst;

import static org.nl.javatetris.config.constant.ControllerConst.*;

/**
 * 게임 플레이의 운영을 담당하는 컨트롤러
 */
public class GamePlayController {

    private Board board;                            // 보드
    private TetrominoGenerator tetrominoGenerator;  // 테트로미노 생성기
    private static Timeline timeline;                      // 타임라인


    private Integer modeParam;                      // 게임 모드
    private Runnable onPause;                       // 일시정지 콜백
    private Runnable onDrawBoardUpdate;                 // 보드 업데이트 콜백
    private Runnable onDrawGameOver;                    // 게임오버 콜백

    private int level = 0;                          // 게임 레벨
    private int point = 0;                          // 게임 점수
    private boolean isGameOver = false;             // 게임오버 여부

    // 생성자
    public GamePlayController(Integer modeParam, Runnable onPause, Runnable onDrawBoardUpdate, Runnable onDrawGameOver) {
        this.modeParam = modeParam;
        this.tetrominoGenerator = new TetrominoGenerator();
        this.onPause = onPause;
        this.onDrawBoardUpdate = onDrawBoardUpdate;
        this.onDrawGameOver = onDrawGameOver;
        board = new Board(this::addScoreOnLineClear, tetrominoGenerator); // 보드 생성
        board.spawnTetromino();
        startTimeline();
    }

    /**
     * 유틸 메서드
     */

    // 타임라인 시작 메서드
    private void startTimeline() {
        if (timeline != null) {
            timeline.stop(); // 기존 타임라인이 존재한다면 중지
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(getSpeedByLevel()), e -> {
            if (SceneManager.getCurrentSceneNumber() == ViewConst.GAME_PLAY_SCENE) {
                System.out.println("modeParam = " + modeParam);
                boolean isProperlyDowned = board.moveTetrominoDown();
                if (!isProperlyDowned) {
                    timeline.stop(); // 타임라인 중지 하고
                    isGameOver = true; // 게임오버 상태로 변경
                    onDrawGameOver.run();
                } else {
                    addScoreOnDown();
                    onDrawBoardUpdate.run();
                }
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // 보드 반환 메서드
    public Board getBoard() {
        return board;
    }

    public TetrominoGenerator getTetrominoGenerator() {
        return tetrominoGenerator;
    }

    /**
     * 점수 관련 메서드
     */

    // 한칸 내려갈 때 점수 가산 메서드
    private void addScoreOnDown() {
        this.point += DOWN_SCORE;
        checkLevelUp(); // 레벨업 체크
    }

    private void addScoreOnDrop(int offset) {
        this.point += offset;
        checkLevelUp();
    }


    // 라인 클리어 시 점수 가산 메서드
    private void addScoreOnLineClear() {
        this.point += LINE_CLEAR_SCORE;
    }

    // 점수 반환 메서드
    public int getPoint() {
        return point;
    }

    /**
     * 레벨 관련 메서드
     */

    // 레벨 반환 메서드
    public int getLevel() {
        return level;
    }

    // 레벨에 따른 속도 반환 메서드
    private double getSpeedByLevel() {
        return Math.max(0.1, 1 - level * 0.1);
    }

    // 레벨업 메서드
    private void checkLevelUp() {
        if ((point / LEVEL_UP_SCORE) > level) {
            level = point / LEVEL_UP_SCORE;
            startTimeline(); // 새로운 속도로 타임라인 재시작
        }
    }

    /**
     * 키 이벤트 관련 메서드
     */

    // 키 이벤트 핸들러 메서드
    public boolean handleKeyPress(KeyEvent e) {
        if (isGameOver) {
            return false;
        }

        int keyCode = e.getCode().getCode();
        if (keyCode == 27) {
            onPause.run();
        } else if (keyCode == Settings.getInstance().getKeySetting().getDownKeyValue()) {
            boolean isProperlyDowned = board.moveTetrominoDown();
            if (!isProperlyDowned) {
                isGameOver = true;
            }
            addScoreOnDown();
        } else if (keyCode == Settings.getInstance().getKeySetting().getLeftKeyValue()) {
            board.moveTetrominoLeft();
        } else if (keyCode == Settings.getInstance().getKeySetting().getRightKeyValue()) {
            board.moveTetrominoRight();
        } else if (keyCode == Settings.getInstance().getKeySetting().getRotateKeyValue()) {
            board.rotateTetromino();
        } else if (keyCode == Settings.getInstance().getKeySetting().getDropKeyValue()) {
            int offset = board.dropTetromino();
            if (offset == 0) {
                isGameOver = true;
            }
            addScoreOnDrop(offset);
        }
        return true;
    }
}
