package org.nl.javatetris.gameplay;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.nl.javatetris.gameplay.tetromino.generator.ItemModeTetrominoGenerator;
import org.nl.javatetris.gameplay.tetromino.generator.TetrominoGenerator;
import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.gameplay.tetromino.generator.ClassicModeTetrominoGenerator;
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


    private GameParam gameParam;                    // 게임 파라미터
    private Runnable onPause;                       // 일시정지 콜백
    private Runnable onDrawBoardUpdate;                 // 보드 업데이트 콜백
    private Runnable onDrawGameOver;                    // 게임오버 콜백

    private int level = 0;                          // 게임 레벨
    private int point = 0;                          // 게임 점수
    private boolean isGameOver = false;             // 게임오버 여부

    // 생성자
    public GamePlayController(GameParam gameParam, Runnable onPause, Runnable onDrawBoardUpdate, Runnable onDrawGameOver) {
        this.gameParam = gameParam;
        // tetrominoGenerator 주입
        if (gameParam.getMode() == 0) { // classic mode
            this.tetrominoGenerator = new ClassicModeTetrominoGenerator(gameParam);
        } else if (gameParam.getMode() == 1) { // item mode
            this.tetrominoGenerator = new ItemModeTetrominoGenerator();
        }
        this.onPause = onPause;
        this.onDrawBoardUpdate = onDrawBoardUpdate;
        this.onDrawGameOver = onDrawGameOver;
        this.board = new Board(this::addScoreOnLineClear, tetrominoGenerator); // 보드 생성
        board.spawnTetromino(false);
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

    public GameParam getGameParam() {
        return gameParam;
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
        double baseSpeed = 1.0 - level * 0.1;

        // 난이도에 따라 속도 조정하기
        //초반 속도는 그대로이고 줄 삭제에 대한 속도만 바꿔야 하는건지? 수정예정
        int difficulty = gameParam.getDifficulty();
        switch (difficulty) {
            case 0:
                // 난이도가 0일 때, 속도가 20% 감소
                baseSpeed *= 1.2;
                break;
            case 1:
                // 난이도가 1일 때, 원래 속도
                break;
            case 2:
                // 난이도가 2일 때, 속도가 20% 증가
                baseSpeed *= 0.8;
                break;
            default:
                break;
        }

        return Math.max(0.1, baseSpeed);
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
