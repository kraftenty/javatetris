package org.nl.javatetris.game.play.single;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.nl.javatetris.game.play.Board;
import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.tetromino.generator.ItemModeTetrominoGenerator;
import org.nl.javatetris.game.tetromino.generator.TetrominoGenerator;
import org.nl.javatetris.pause.PauseMenuParam;
import org.nl.javatetris.settings.Settings;
import org.nl.javatetris.game.tetromino.generator.ClassicModeTetrominoGenerator;
import org.nl.javatetris.config.manager.SceneManager;
import org.nl.javatetris.config.constant.ViewConst;

import java.util.function.Consumer;

import static org.nl.javatetris.config.constant.ControllerConst.*;

/**
 * 게임 플레이의 운영을 담당하는 컨트롤러
 */
public class SingleGamePlayController {

    private Board board;                            // 보드
    private TetrominoGenerator tetrominoGenerator;  // 테트로미노 생성기

    public static Timeline getTimeline() {       //테스트용
        return timeline;
    }

    private static Timeline timeline;                      // 타임라인

    private GameParam gameParam;                    // 게임 파라미터
    private Consumer<PauseMenuParam> onPause;            // 일시정지 콜백
    private Runnable onDrawBoardUpdate;                 // 보드 업데이트 콜백
    private Runnable onDrawGameOver;                    // 게임오버 콜백

    private int level = 0;                          // 게임 레벨
    private int point = 0;                          // 게임 점수
    private boolean isGameOver = false;             // 게임오버 여부

    // TODO
    private int timelineCount = 0;

    // 생성자
    public SingleGamePlayController(GameParam gameParam, Consumer<PauseMenuParam> onPause, Runnable onDrawBoardUpdate, Runnable onDrawGameOver) {
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

    //테스트위한 생성자, 타이머 때문에 생성자 실행에 오류 생겨서 따로 만듦
    //위에꺼 쓰면 커버리지 더 넓어지긴 할텐데 건들였다가 일날까봐 만듦
    public SingleGamePlayController(Consumer<PauseMenuParam> onPause, Runnable onDrawBoardUpdate, Runnable onDrawGameOver) {
        this.gameParam = new GameParam(0, 0);
        this.tetrominoGenerator = new ClassicModeTetrominoGenerator(gameParam);
        this.onPause = onPause;
        this.onDrawBoardUpdate = onDrawBoardUpdate;
        this.onDrawGameOver = onDrawGameOver;
        this.board = new Board(this::addScoreOnLineClear, tetrominoGenerator); // 보드 생성
        board.spawnTetromino(false);
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
            System.out.println("timeline running..." + timelineCount++);
            if (SceneManager.getCurrentSceneNumber() == ViewConst.SINGLE_GAME_PLAY_SCENE) {
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
        this.point += DOWN_SCORE + (level / 2); //5레벨 마다 내려가는 점수 증가
        checkLevelUp(); // 레벨업 체크
    }

    private void addScoreOnDrop(int offset) {
        if (offset == -1) {
            return;
        }
        this.point += offset * DOWN_SCORE + (level/2);
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

        return Math.max(0.3, baseSpeed);
    }

    private int getLevelUpScore(int currentLevel) {
        return ((currentLevel+1)*(currentLevel+2)/2) * 500;
    }

    // 레벨업 메서드
    private void checkLevelUp() {
        if (point >= getLevelUpScore(level)) {
            level++;
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
        if (keyCode == KeyCode.ESCAPE.getCode()) {
            onPause.accept(new PauseMenuParam(PAUSE_MENU_SINGLE_MODE, this::shutdownGame));
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

    // 게임 자원 해제 및 종료 처리 메서드
    public void shutdownGame() {
        // 타임라인 중지 및 참조 제거
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        // 게임 관련 객체 참조 제거
        board = null;
        tetrominoGenerator = null;

        // 콜백 참조 제거
        onPause = null;
        onDrawBoardUpdate = null;
        onDrawGameOver = null;

        // 게임 파라미터 참조 제거
        gameParam = null;

        System.gc();
    }
}
