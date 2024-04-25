package org.nl.javatetris.gameplay.tetromino.generator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.GameParam;
import org.nl.javatetris.gameplay.tetromino.Tetromino;
import org.nl.javatetris.gameplay.tetromino.generator.ClassicModeTetrominoGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.nl.javatetris.config.constant.ModelConst.TETROMINO_TYPES;

class ClassicModeTetrominoGeneratorTest {
    @Test
    public void testBlockSelectionProbabilityWithCustomWeights() {
        GameParam gameParam = new GameParam(0, 0); // Difficulty와 Mode 모두 0으로 설정

        ClassicModeTetrominoGenerator tetrominoGenerator = new ClassicModeTetrominoGenerator(gameParam);

        int[] blockCounts = new int[TETROMINO_TYPES];
        int totalTrials = 100000; // 시뮬레이션 횟수

        // 시뮬레이션을 통해 각 블록이 선택된 횟수를 계산
        for (int i = 0; i < totalTrials; i++) {
            Tetromino nextTetromino = tetrominoGenerator.getNextTetromino(false);
            blockCounts[nextTetromino.getShapeIndex()]++; // 타입을 가져올 때 ordinal() 메서드를 사용하여 순서를 가져옴
        }

        // 기대되는 각 블록의 선택 횟수 계산
        int totalBlocks = TETROMINO_TYPES - 1; // i 블록은 따로 계산
        int totalNonI = totalTrials - (int) (totalTrials * 0.2); // i 블록을 제외한 총 시행 횟수
        int expectedI = (int) (totalTrials * 0.2); // i 블록 선택 확률
        int expectedNonI = totalNonI / totalBlocks; // 나머지 블록들의 선택 확률

        // i 블록의 선택 횟수가 기대값의 5% 범위 내에 있는지 확인
        assertEquals(expectedI, blockCounts[1], 0.05 * expectedI);

        // 나머지 블록의 선택 횟수가 기대값의 5% 범위 내에 있는지 확인
        /*for (int i = 0; i < TETROMINO_TYPES; i++) {
            assertEquals(expectedNonI, blockCounts[i], 0.05 * expectedNonI);
        }*/
    }
}