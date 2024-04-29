package org.nl.javatetris.gameplay.battle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.nl.javatetris.config.SceneManager;
import org.nl.javatetris.config.constant.ControllerConst;
import org.nl.javatetris.config.constant.ViewConst;
import org.nl.javatetris.gameplay.Board;
import org.nl.javatetris.gameplay.GameParam;
import org.nl.javatetris.gameplay.tetromino.generator.ClassicModeTetrominoGenerator;
import org.nl.javatetris.gameplay.tetromino.generator.ItemModeTetrominoGenerator;
import org.nl.javatetris.gameplay.tetromino.generator.TetrominoGenerator;
import org.nl.javatetris.pause.PauseMenuParam;

import java.util.function.Consumer;

import static org.nl.javatetris.config.constant.ControllerConst.*;
import static org.nl.javatetris.config.constant.ControllerConst.LEVEL_UP_SCORE;

public class BattleGamePlayController {

    private Board board1;
    private Board board2;
    private TetrominoGenerator tetrominoGenerator1;
    private TetrominoGenerator tetrominoGenerator2;
    private static Timeline timeline1;
    private static Timeline timeline2;

    private GameParam gameParam;
    private Consumer<PauseMenuParam> onPause;
    private Runnable onDrawBoardUpdate;
    private Runnable onDrawGameOver;

    private int level1 = 0;
    private int level2 = 0;
    private int point1 = 0;
    private int point2 = 0;
    private boolean isGameOver = false;

    // 생성자
    public BattleGamePlayController(GameParam gameParam, Consumer<PauseMenuParam> onPause, Runnable onDrawBoardUpdate, Runnable onDrawGameOver) {
        this.gameParam = gameParam;
        // tetrominoGenerator 주입
        if (gameParam.getMode() == 10) { // battle-classic mode
            this.tetrominoGenerator1 = new ClassicModeTetrominoGenerator(gameParam);
            this.tetrominoGenerator2 = new ClassicModeTetrominoGenerator(gameParam);
        } else if (gameParam.getMode() == 11) { // battle-item mode
            this.tetrominoGenerator1 = new ItemModeTetrominoGenerator();
            this.tetrominoGenerator2 = new ItemModeTetrominoGenerator();
        } else if (gameParam.getMode() == 12) { // battle-time-attack mode
            this.tetrominoGenerator1 = new ClassicModeTetrominoGenerator(gameParam);
            this.tetrominoGenerator2 = new ClassicModeTetrominoGenerator(gameParam);
        } else {
            throw new IllegalArgumentException("Invalid game mode");
        }

        this.onPause = onPause;
        this.onDrawBoardUpdate = onDrawBoardUpdate;
        this.onDrawGameOver = onDrawGameOver;
        this.board1 = new Board(this::addScoreOnLineClear1, tetrominoGenerator1);
        this.board2 = new Board(this::addScoreOnLineClear2, tetrominoGenerator2);
        board1.spawnTetromino(false);
        board2.spawnTetromino(false);
        startTimeline1();
        startTimeline2();
    }

    /**
     * 유틸 메서드
     */

    // 타임라인 시작 메서드
    private void startTimeline1() {
        if (timeline1 != null) {
            timeline1.stop(); // 기존 타임라인이 존재한다면 중지
        }
        timeline1 = new Timeline(new KeyFrame(Duration.seconds(getSpeedByLevel(1)), e -> {
            if (SceneManager.getCurrentSceneNumber() == ViewConst.SINGLE_GAME_PLAY_SCENE) {
                boolean isProperlyDowned = board1.moveTetrominoDown();
                if (!isProperlyDowned) {
                    timeline1.stop(); // 타임라인 중지 하고
                    isGameOver = true; // 게임오버 상태로 변경
                    onDrawGameOver.run();
                } else {
                    addScoreOnDown1();
                    onDrawBoardUpdate.run();
                }
            }

        }));
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();
    }

    private void startTimeline2() {
        if (timeline2 != null) {
            timeline2.stop(); // 기존 타임라인이 존재한다면 중지
        }
        timeline2 = new Timeline(new KeyFrame(Duration.seconds(getSpeedByLevel(2)), e -> {
            if (SceneManager.getCurrentSceneNumber() == ViewConst.SINGLE_GAME_PLAY_SCENE) {
                boolean isProperlyDowned = board2.moveTetrominoDown();
                if (!isProperlyDowned) {
                    timeline2.stop(); // 타임라인 중지 하고
                    isGameOver = true; // 게임오버 상태로 변경
                    onDrawGameOver.run();
                } else {
                    addScoreOnDown2();
                    onDrawBoardUpdate.run();
                }
            }

        }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
    }

    // 보드 반환 메서드
    public Board getBoard1() {
        return board1;
    }

    public Board getBoard2() {
        return board2;
    }

    public TetrominoGenerator getTetrominoGenerator1() {
        return tetrominoGenerator1;
    }

    public TetrominoGenerator getTetrominoGenerator2() {
        return tetrominoGenerator2;
    }

    public GameParam getGameParam() {
        return gameParam;
    }

    /**
     * 점수 관련 메서드
     */

    public void addScoreOnDown1() {
        this.point1 += DOWN_SCORE;
        checkLevelUp1(); // 레벨업 체크
    }

    public void addScoreOnDown2() {
        this.point2 += DOWN_SCORE;
        checkLevelUp2(); // 레벨업 체크
    }

    private void addScoreOnDrop1(int offset) {
        if (offset == -1) {
            return;
        }
        this.point1 += offset;
        checkLevelUp1();
    }

    private void addScoreOnDrop2(int offset) {
        if (offset == -1) {
            return;
        }
        this.point2 += offset;
        checkLevelUp2();
    }


    private void addScoreOnLineClear1() {
        this.point1 += LINE_CLEAR_SCORE;
    }

    private void addScoreOnLineClear2() {
        this.point2 += LINE_CLEAR_SCORE;
    }

    public int getPoint1() {
        return point1;
    }

    public int getPoint2() {
        return point2;
    }

    /**
     * 레벨 관련 메서드
     */
    public int getLevel1() {
        return level1;
    }

    public int getLevel2() {
        return level2;
    }

    // 레벨에 따른 속도 반환 메서드
    private double getSpeedByLevel(int playerNumber) {
        double baseSpeed = 1.0 - (playerNumber == 1 ? level1 : level2) * 0.1;

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

    // 레벨업 메서드
    private void checkLevelUp1() {
        if ((point1 / LEVEL_UP_SCORE) > level1) {
            level1 = point1 / LEVEL_UP_SCORE;
            startTimeline1(); // 새로운 속도로 타임라인 재시작
        }
    }

    private void checkLevelUp2() {
        if ((point2 / LEVEL_UP_SCORE) > level2) {
            level2 = point2 / LEVEL_UP_SCORE;
            startTimeline2(); // 새로운 속도로 타임라인 재시작
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
        if (keyCode == KeyCode.ESCAPE.getCode()) { // ESC
            onPause.accept(new PauseMenuParam(PAUSE_MENU_BATTLE_MODE));
        }
        // 플레이어 1
        else if (keyCode == KeyCode.S.getCode()) { // 플레이어1 - 아래(S)
            boolean isProperlyDowned = board1.moveTetrominoDown();
            if (!isProperlyDowned) {
                isGameOver = true;
            }
            addScoreOnDown1();
        } else if (keyCode == KeyCode.A.getCode()) { // 플레이어1 - 왼쪽(A)
            board1.moveTetrominoLeft();
        } else if (keyCode == KeyCode.D.getCode()) { // 플레이어1 - 오른쪽(D)
            board1.moveTetrominoRight();
        } else if (keyCode == KeyCode.W.getCode()) { // 플레이어1 - 회전(W)
            board1.rotateTetromino();
        } else if (keyCode == KeyCode.Q.getCode()) { // 플레이어1 - 드롭(Q)
            int offset = board1.dropTetromino();
            if (offset == 0) {
                isGameOver = true;
            }
            addScoreOnDrop1(offset);
        }
        // 플레이어 2
        else if (keyCode == KeyCode.K.getCode()) { // 플레이어2 - 아래(K)
            boolean isProperlyDowned = board2.moveTetrominoDown();
            if (!isProperlyDowned) {
                isGameOver = true;
            }
            addScoreOnDown2();
        } else if (keyCode == KeyCode.J.getCode()) { // 플레이어2 - 왼쪽(J)
            board2.moveTetrominoLeft();
        } else if (keyCode == KeyCode.L.getCode()) { // 플레이어2 - 오른쪽(L)
            board2.moveTetrominoRight();
        } else if (keyCode == KeyCode.I.getCode()) { // 플레이어2 - 회전(I)
            board2.rotateTetromino();
        } else if (keyCode == KeyCode.U.getCode()) { // 플레이어2 - 드롭(U)
            int offset = board2.dropTetromino();
            if (offset == 0) {
                isGameOver = true;
            }
            addScoreOnDrop2(offset);
        }
        return true;
    }





}
