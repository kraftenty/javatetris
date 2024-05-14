package org.nl.javatetris.game.play;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.generator.ItemModeTetrominoGenerator;
import org.nl.javatetris.game.tetromino.generator.TetrominoGenerator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
            Assertions.assertEquals(line1.get(x - 1) == 1 ? DAMAGED_BLOCK: EMPTY, board.getValueAt(Y_MAX - 3, x));
            Assertions.assertEquals(line2.get(x - 1) == 1 ? DAMAGED_BLOCK: EMPTY, board.getValueAt(Y_MAX - 2, x));
        }

        // 버퍼가 비어 있는지 확인
        Assertions.assertTrue(board.getDamagedLineBuffer().isEmpty());
    }

    //버퍼에 10줄이 채워져있을 때 줄 삭제 테스트
    @Test
    public void testIgnoreDamageWhenBufferFull() {
        Board board = new Board(() -> {}, tetrominoGenerator);

        // 10줄의 공격 라인이 버퍼에 채워짐
        List<LineDTO> initialLines = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> line = IntStream.range(0, X_MAX - 2).mapToObj(j -> 1).collect(Collectors.toList());
            LineDTO lineDTO = new LineDTO(line);
            initialLines.add(lineDTO);
            board.getDamagedLineBuffer().add(lineDTO);
        }

        // 새로운 공격 라인을 추가
        List<Integer> newLine = IntStream.range(0, X_MAX - 2).mapToObj(j -> j % 2).collect(Collectors.toList());
        List<LineDTO> newDamagedLines = Collections.singletonList(new LineDTO(newLine));

        board.receiveDamage(newDamagedLines);

        // 버퍼가 여전히 10줄인지 확인
        Assertions.assertEquals(10, board.getDamagedLineBuffer().size());

        // 버퍼가 원래 라인으로 유지되었는지 확인
        List<LineDTO> bufferLines = board.getDamagedLineBuffer();
        for (int i = 0; i < 10; i++) {
            Assertions.assertSame(initialLines.get(i), bufferLines.get(i));
        }
    }

    //현재 10줄이 다 차있지 않더라도 넘어온 줄의 수를 더하여 10줄이 넘게 됐을 때 테스트
    @Test
    public void testAddDamageAndTrimBuffer() {
        Board board = new Board(() -> {}, tetrominoGenerator);

        // 이미 8줄의 공격 라인이 버퍼에 채워진 상태
        for (int i = 0; i < 8; i++) {
            List<Integer> line = IntStream.range(0, X_MAX - 2).mapToObj(j -> 1).collect(Collectors.toList());
            board.getDamagedLineBuffer().add(new LineDTO(line));
        }

        // 새로운 3줄의 공격 라인을 추가
        List<Integer> newLine1 = IntStream.range(0, X_MAX - 2).mapToObj(j -> j % 2).collect(Collectors.toList());
        List<Integer> newLine2 = IntStream.range(0, X_MAX - 2).mapToObj(j -> (j + 1) % 2).collect(Collectors.toList());
        List<Integer> newLine3 = IntStream.range(0, X_MAX - 2).mapToObj(j -> j % 2).collect(Collectors.toList());
        List<LineDTO> newDamagedLines = Arrays.asList(new LineDTO(newLine1), new LineDTO(newLine2), new LineDTO(newLine3));

        board.receiveDamage(newDamagedLines);

        // 버퍼의 새로운 2줄이 추가되고 새로운 3줄 중 가장 아래 1줄이 잘렸는지 확인
        List<LineDTO> bufferLines = board.getDamagedLineBuffer();

        // 기존 8줄은 그대로인지 확인
        for (int i = 0; i < 8; i++) {
            Assertions.assertEquals(1, bufferLines.get(i).getLine().stream().distinct().count());
        }

        // 새로운 2줄이 추가되었는지 확인
        Assertions.assertEquals(newLine1, bufferLines.get(8).getLine());
        Assertions.assertEquals(newLine2, bufferLines.get(9).getLine());

        // 새로운 3줄 중 가장 아래 1줄이 잘렸는지 확인
        Assertions.assertFalse(bufferLines.contains(new LineDTO(newLine3)));
    }

    //Board 클래스의 주요 동작이 1초 미만의 시간 내에 완료되는지 테스트
    @Test
    public void testBoardActionsResponseTime() {
        Board board = new Board(() -> {}, tetrominoGenerator);

        // 블럭을 스폰한 후 테스트 시작
        board.spawnTetromino(false);

        // 블럭 이동 테스트
        assertTimeout(Duration.ofMillis(1000), () -> board.moveTetrominoDown());
        assertTimeout(Duration.ofMillis(1000), () -> board.moveTetrominoLeft());
        assertTimeout(Duration.ofMillis(1000), () -> board.moveTetrominoRight());
        assertTimeout(Duration.ofMillis(1000), () -> board.rotateTetromino());
        assertTimeout(Duration.ofMillis(1000), () -> board.dropTetromino());

        // 데미지 처리 테스트
        List<Integer> line1 = new ArrayList<>();
        List<Integer> line2 = new ArrayList<>();
        for (int i = 0; i < X_MAX - 2; i++) {
            line1.add(1);
            line2.add(i % 2);
        }

        List<LineDTO> damagedLines = new ArrayList<>();
        damagedLines.add(new LineDTO(line1));
        damagedLines.add(new LineDTO(line2));

        assertTimeout(Duration.ofMillis(1000), () -> board.receiveDamage(damagedLines));
        assertTimeout(Duration.ofMillis(1000), () -> board.addDamagedLinesToBoard());
    }
}
