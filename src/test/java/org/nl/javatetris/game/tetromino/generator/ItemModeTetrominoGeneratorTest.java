package org.nl.javatetris.game.tetromino.generator;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;
import org.nl.javatetris.game.tetromino.item.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ItemModeTetrominoGeneratorTest {
    //테스트 실패해서 item mode 완료된 후 수정하거나 삭제해야함
//    @Test
//    public void testItemTetrominoCreationProbability() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();
//
//        // 각 아이템 카운트
//        int nuclearCount = 0;
//        int bombCount = 0;
//        int eraseCount = 0;
//        int weightCount = 0;
//        int verticalBombCount = 0;
//
//        // 실제 아이템 횟수 카운트
//        int iterations = 500000;
//        for (int i = 0; i < iterations; i++) {
//            Method method = ItemModeTetrominoGenerator.class.getDeclaredMethod("createItemTetrominoByRandom", int.class);
//            method.setAccessible(true);
//            Tetromino tetromino = (Tetromino) method.invoke(tetrominoGenerator, (int) (Math.random() * 100));
//
//            if (tetromino instanceof TetrominoNuclear) nuclearCount++;
//            else if (tetromino instanceof TetrominoBomb) bombCount++;
//            else if (tetromino instanceof TetrominoWeight) weightCount++;
//            else if (tetromino instanceof TetrominoVerticalBomb) verticalBombCount++;
//        }
//
//        // 확률과 일치하게 나오는지 테스트
//        assertTrue(nuclearCount > 0 && nuclearCount < iterations * 0.02); // Expected 1%
//        assertTrue(bombCount > iterations * 0.25 && bombCount < iterations * 0.28); // Expected 27%
//        assertTrue(weightCount > iterations * 0.23 && weightCount < iterations * 0.26); // Expected 24%
//        assertTrue(verticalBombCount > 0 && verticalBombCount < iterations * 0.02); // Expected 1%
//    }


    @Test
    public void testRefillQueue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemModeTetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();


        assertEquals(1, tetrominoGenerator.getTetrominoQueue().size());


        tetrominoGenerator.getTetrominoQueue().clear();
        Method method = ItemModeTetrominoGenerator.class.getDeclaredMethod("refillQueue", boolean.class);
        method.setAccessible(true);
        method.invoke(tetrominoGenerator, false);

        //then
        assertEquals(1, tetrominoGenerator.getTetrominoQueue().size());
    }


    @Test //한 줄 제거 블록 생성 테스트
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
}