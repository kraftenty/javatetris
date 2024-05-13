package org.nl.javatetris.game.tetromino.generator;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;
import org.nl.javatetris.game.tetromino.classic.*;
import org.nl.javatetris.game.tetromino.item.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ItemModeTetrominoGeneratorTest {

    private static final int TEST_ITERATIONS = 10000;

    @Test
    public void testRefillQueue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

        // 처음 큐의 크기가 1인지 확인
        assertEquals(1, tetrominoGenerator.getTetrominoQueue().size());

        // 큐를 비우고 refillQueue 메서드 호출하여 다시 채우기
        tetrominoGenerator.getTetrominoQueue().clear();
        Method method = ItemModeTetrominoGenerator.class.getDeclaredMethod("refillQueue", boolean.class);
        method.setAccessible(true);
        method.invoke(tetrominoGenerator, false);

        // 큐의 크기가 다시 1인지 확인
        assertEquals(1, tetrominoGenerator.getTetrominoQueue().size());
    }

    @Test
    public void testGetRandomEraseTetromino() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

        // 리플렉션을 사용하여 private 메서드에 접근
        Method method = ItemModeTetrominoGenerator.class.getDeclaredMethod("getRandomEraseTetromino");
        method.setAccessible(true); // private 메서드에 접근 가능하도록 설정

        // 테스트할 메서드 호출
        Tetromino tetromino = (Tetromino) method.invoke(tetrominoGenerator);

        // 테스트 결과 확인
        assertNotNull(tetromino); // 생성된 Tetromino 객체가 null이 아닌지 확인

        // Tetromino가 올바른 한 줄 제거 블록인지 확인
        assertTrue(tetromino instanceof TetrominoEraseI ||
                tetromino instanceof TetrominoEraseJ ||
                tetromino instanceof TetrominoEraseL ||
                tetromino instanceof TetrominoEraseO ||
                tetromino instanceof TetrominoEraseS ||
                tetromino instanceof TetrominoEraseT ||
                tetromino instanceof TetrominoEraseZ);
    }

    @Test
    public void testCreateClassicTetrominoByType() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

        Method method = ItemModeTetrominoGenerator.class.getDeclaredMethod("createClassicTetrominoByType", int.class);
        method.setAccessible(true);

        for (int type = 0; type < 7; type++) {
            Tetromino tetromino = (Tetromino) method.invoke(tetrominoGenerator, type);
            assertNotNull(tetromino);

            switch (type) {
                case 0 -> assertTrue(tetromino instanceof TetrominoI);
                case 1 -> assertTrue(tetromino instanceof TetrominoJ);
                case 2 -> assertTrue(tetromino instanceof TetrominoL);
                case 3 -> assertTrue(tetromino instanceof TetrominoO);
                case 4 -> assertTrue(tetromino instanceof TetrominoS);
                case 5 -> assertTrue(tetromino instanceof TetrominoT);
                case 6 -> assertTrue(tetromino instanceof TetrominoZ);
                default -> fail("Unknown tetromino type: " + type);
            }
        }
    }

    @Test
    public void testCreateItemTetrominoByRandom() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

        Method method = ItemModeTetrominoGenerator.class.getDeclaredMethod("createItemTetrominoByRandom", int.class);
        method.setAccessible(true);

        // 여러 번 실행하여 다양한 아이템 테트로미노 생성 여부 확인
        for (int i = 0; i < 100; i++) {
            Tetromino tetromino = (Tetromino) method.invoke(tetrominoGenerator, i);

            assertTrue(tetromino instanceof TetrominoNuclear ||
                    tetromino instanceof TetrominoBomb ||
                    tetromino instanceof TetrominoEraseI ||
                    tetromino instanceof TetrominoEraseJ ||
                    tetromino instanceof TetrominoEraseL ||
                    tetromino instanceof TetrominoEraseO ||
                    tetromino instanceof TetrominoEraseS ||
                    tetromino instanceof TetrominoEraseT ||
                    tetromino instanceof TetrominoEraseZ ||
                    tetromino instanceof TetrominoWeight ||
                    tetromino instanceof TetrominoVerticalBomb);
        }
    }

    @Test
    public void testGetNextTetromino() {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

        Tetromino tetromino = tetrominoGenerator.getNextTetromino(false);
        assertNotNull(tetromino);
        assertEquals(1, tetrominoGenerator.getTetrominoQueue().size());

        Tetromino itemTetromino = tetrominoGenerator.getNextTetromino(true);
        assertNotNull(itemTetromino);
        assertEquals(1, tetrominoGenerator.getTetrominoQueue().size());
    }

    @Test
    public void testPeekNextTetromino() {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

        Tetromino tetromino = tetrominoGenerator.peekNextTetromino();
        assertNotNull(tetromino);
        assertEquals(1, tetrominoGenerator.getTetrominoQueue().size());
    }

    //아이템 확률 테스트
    @Test
    public void testItemTetrominoDistribution() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

        Method method = ItemModeTetrominoGenerator.class.getDeclaredMethod("createItemTetrominoByRandom", int.class);
        method.setAccessible(true);

        Map<Class<? extends Tetromino>, Integer> counts = new HashMap<>();

        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Tetromino tetromino = (Tetromino) method.invoke(tetrominoGenerator, i % 100);

            counts.put(tetromino.getClass(), counts.getOrDefault(tetromino.getClass(), 0) + 1);
        }

        double nuclearProbability = counts.getOrDefault(TetrominoNuclear.class, 0) / (double) TEST_ITERATIONS;
        double bombProbability = counts.getOrDefault(TetrominoBomb.class, 0) / (double) TEST_ITERATIONS;
        double eraseProbability = (counts.getOrDefault(TetrominoEraseI.class, 0) +
                counts.getOrDefault(TetrominoEraseJ.class, 0) +
                counts.getOrDefault(TetrominoEraseL.class, 0) +
                counts.getOrDefault(TetrominoEraseO.class, 0) +
                counts.getOrDefault(TetrominoEraseS.class, 0) +
                counts.getOrDefault(TetrominoEraseT.class, 0) +
                counts.getOrDefault(TetrominoEraseZ.class, 0)) / (double) TEST_ITERATIONS;
        double weightProbability = counts.getOrDefault(TetrominoWeight.class, 0) / (double) TEST_ITERATIONS;
        double verticalBombProbability = counts.getOrDefault(TetrominoVerticalBomb.class, 0) / (double) TEST_ITERATIONS;

        assertTrue(nuclearProbability >= 0.009 && nuclearProbability <= 0.011, "Nuclear probability out of range: " + nuclearProbability);
        assertTrue(bombProbability >= 0.26 && bombProbability <= 0.28, "Bomb probability out of range: " + bombProbability);
        assertTrue(eraseProbability >= 0.23 && eraseProbability <= 0.25, "Erase probability out of range: " + eraseProbability);
        assertTrue(weightProbability >= 0.23 && weightProbability <= 0.25, "Weight probability out of range: " + weightProbability);
        assertTrue(verticalBombProbability >= 0.23 && verticalBombProbability <= 0.25, "Vertical Bomb probability out of range: " + verticalBombProbability);
    }
}
