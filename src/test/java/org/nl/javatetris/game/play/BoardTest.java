package org.nl.javatetris.game.play;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.generator.ItemModeTetrominoGenerator;
import org.nl.javatetris.game.tetromino.generator.TetrominoGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class BoardTest {
    private TetrominoGenerator tetrominoGenerator = new ItemModeTetrominoGenerator();

    @Test
    public void getValueAtTest() {
        Board board = new Board(() -> {}, tetrominoGenerator);
        Assertions.assertEquals(9, board.getValueAt(0, 0));
        for (int i = 0; i < 20; i++) {
            board = new Board(() -> {}, tetrominoGenerator);
            board.spawnTetromino(false);
            Assertions.assertEquals(board.getCurrentTetromino().getShapeNumber(), board.getValueAt(2, 6));
        }
    }

    @Test
    public void RotateTest() {  //정상 회전
        for (int i = 0; i < 20; i++) {
            Board board = new Board(() -> {}, tetrominoGenerator);
            board.spawnTetromino(false);
            board.moveTetrominoDown();
            board.moveTetrominoDown();
            for (int j = 0; j < 4; j++) {
                board.rotateTetromino();
                int tetrominoNum = board.getCurrentTetromino().getShapeNumber();
                int rotationIdx = board.getCurrentTetromino().getShapeIndex();
                if (tetrominoNum == 4) Assertions.assertEquals(0, rotationIdx);
                else Assertions.assertEquals((j + 1) % 4, rotationIdx);
            }
        }
    }

    @Test
    public void tetrominoMove() {  //이동 테스트
        int[] yx = new int[2];
        for (int i = 0; i < 20; i++) {
            yx[0] = 1;
            yx[1] = 5;
            Board board = new Board(() -> {}, tetrominoGenerator);
            board.spawnTetromino(false);
            board.moveTetrominoDown();
            yx[0]++;
            Assertions.assertArrayEquals(yx, board.getYX());
            board.moveTetrominoLeft();
            yx[1]--;
            Assertions.assertArrayEquals(yx, board.getYX());
            board.moveTetrominoRight();
            yx[1]++;
            Assertions.assertArrayEquals(yx, board.getYX());
        }
    }

    @Test
    public void dropTest() {  //이동 테스트
        int[] yx = new int[2];
        yx[0] = 1;
        yx[1] = 5;
        for (int i = 0; i < 20; i++) {
            Board board = new Board(() -> {}, tetrominoGenerator);
            board.spawnTetromino(false);
            board.moveTetrominoDown();
            board.dropTetromino();
            Assertions.assertArrayEquals(yx, board.getYX());
        }
    }

    @Test
    public void eraseLineTest() {  //라인 삭제 테스트
        for (int i = 0; i < 20; i++) {
            Board board = new Board(() -> {}, tetrominoGenerator);

            int tetrominoNum;
            do {
                board.spawnTetromino(true);
                tetrominoNum = board.getCurrentTetromino().getShapeNumber();
            } while (tetrominoNum < 11 || tetrominoNum > 17);

            board.dropTetromino();
            boolean isDeleted = true;
            for (int j = 0; j < 3; j++) {
                if (board.getValueAt(19, 5 + j) != 0) {
                    isDeleted = false;
                    break;
                }
            }
            Assertions.assertTrue(isDeleted);
        }
    }

    @Test
    public void receiveDamageTest() {
        Board board = new Board(() -> {}, tetrominoGenerator);

        // 공격받은 라인 생성
        List<Integer> line1 = new ArrayList<>();
        List<Integer> line2 = new ArrayList<>();
        for (int i = 0; i < X_MAX - 2; i++) {
            line1.add(1);
            line2.add(i % 2);
        }

        List<LineDTO> damagedLines = new ArrayList<>();
        damagedLines.add(new LineDTO(line1));
        damagedLines.add(new LineDTO(line2));

        // 데미지 받기
        board.receiveDamage(damagedLines);

        // 데미지 버퍼 확인
        List<LineDTO> damagedLineBuffer = board.getDamagedLineBuffer();
        Assertions.assertEquals(2, damagedLineBuffer.size());
        Assertions.assertEquals(line1, damagedLineBuffer.get(0).getLine());
        Assertions.assertEquals(line2, damagedLineBuffer.get(1).getLine());
    }

    @Test
    public void addDamagedLinesToBoardTest() {
        Board board = new Board(() -> {}, tetrominoGenerator);

        // 공격받은 라인 생성
        List<Integer> line1 = new ArrayList<>();
        List<Integer> line2 = new ArrayList<>();
        for (int i = 0; i < X_MAX - 2; i++) {
            line1.add(1);
            line2.add(i % 2);
        }

        List<LineDTO> damagedLines = new ArrayList<>();
        damagedLines.add(new LineDTO(line1));
        damagedLines.add(new LineDTO(line2));

        // 데미지 받기
        board.receiveDamage(damagedLines);

        // 데미지 라인을 보드에 추가
        board.addDamagedLinesToBoard();

        // 보드 상태 확인
        for (int x = 1; x < X_MAX - 1; x++) {
            Assertions.assertEquals(line1.get(x - 1) == 1 ? W : EMPTY, board.getValueAt(Y_MAX - 3, x));
            Assertions.assertEquals(line2.get(x - 1) == 1 ? W : EMPTY, board.getValueAt(Y_MAX - 2, x));
        }

        // 버퍼가 비어 있는지 확인
        Assertions.assertTrue(board.getDamagedLineBuffer().isEmpty());
    }
}
