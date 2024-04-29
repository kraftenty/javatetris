package org.nl.javatetris.gameplay.tetromino.generator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.nl.javatetris.gameplay.GameParam;
import org.nl.javatetris.gameplay.tetromino.Tetromino;
import org.nl.javatetris.gameplay.tetromino.classic.TetrominoI;
import org.nl.javatetris.gameplay.tetromino.generator.ClassicModeTetrominoGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class ClassicModeTetrominoGeneratorTest {

    private static final int TOTAL_SELECTIONS = 50000; //테스트 횟수 증가
    private static final double ACCEPTABLE_ERROR_PERCENTAGE = 5.0; // 오차 범위 +-5%

    @RepeatedTest(value = 3, name = "Test {currentRepetition}")
    public void testTetrominoSelectionDistribution(RepetitionInfo repetitionInfo) {
        int difficulty = repetitionInfo.getCurrentRepetition() - 1; // 난이도: 0, 1, 2
        // 게임 파라미터 생성
        GameParam gameParam = new GameParam(0, difficulty);
        ClassicModeTetrominoGenerator tetrominoGenerator = new ClassicModeTetrominoGenerator(gameParam);
        int tetrominoICount = 0; // 테트로미노 I 선택 횟수

        // 50000번 반복하여 테트로미노 선택
        for (int i = 0; i < TOTAL_SELECTIONS; i++) {
            Tetromino nextTetromino = tetrominoGenerator.getNextTetromino(false);
            if (nextTetromino instanceof TetrominoI) {
                tetrominoICount++;
            }
        }

        // 기대값 계산
        double expectedFrequency = calculateExpectedFrequency(0, difficulty); // Tetromino I의 인덱스는 0

        // 실제값 계산
        double actualFrequency = (double) tetrominoICount / TOTAL_SELECTIONS * 100; // 백분율로 변환

        // 허용 가능한 오차 계산
        double acceptableError = ACCEPTABLE_ERROR_PERCENTAGE / 100 * expectedFrequency; // 허용 가능한 오차
        String errorMessage = "Tetromino I의 선택 빈도가 기대값과 차이가 너무 큽니다.";

        System.out.println("expectedFrequency: " + expectedFrequency);
        System.out.println("actualFrequency: " + actualFrequency);
        System.out.println("acceptableError: " + acceptableError);

        // 테스트 수행
        assertEquals(expectedFrequency, actualFrequency, acceptableError, errorMessage);
    }

    //I 블럭 빈도의 기댓값 계산
    private double calculateExpectedFrequency(int tetrominoTypeIndex, int difficulty) {
        int[] defaultWeights = {10, 10, 10, 10, 10, 10, 10}; // 기본 가중치
        if (difficulty == 0) {
            defaultWeights[0] = 12; // 난이도가 0인 경우 I 블록 가중치 증가
        } else if (difficulty == 2) {
            defaultWeights[0] = 8; // 난이도가 2인 경우 I 블록 가중치 감소
        }
        int totalWeight = 0;
        for (int weight : defaultWeights) {
            totalWeight += weight;
        }
        double tetrominoWeight = (double) defaultWeights[tetrominoTypeIndex];
        return (tetrominoWeight / totalWeight) * 100;
    }


    @Test
    public void testRefillQueue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GameParam gameParam = new GameParam(0, 0);
        ClassicModeTetrominoGenerator tetrominoGenerator = new ClassicModeTetrominoGenerator(gameParam);

        Method method = ClassicModeTetrominoGenerator.class.getDeclaredMethod("refillQueue");
        method.setAccessible(true);
        method.invoke(tetrominoGenerator);

        // refillQueue를 호출한 후에 큐의 크기가 2여야 한다.
        assertEquals(2, tetrominoGenerator.getTetrominoQueue().size());
    }

}
