package org.nl.javatetris;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.model.Board;

public class RotateTest {

    @Test
    public void normalRotateTest() {  //정상 회전
        Board board = new Board();
        for (int i = 0; i < 20; i++) {
            board.spawnTetromino();
            board.clearTetrominoFromBoard();
            board.setXY(5,10);
            for(int j = 0; j < 4; j++) {
                board.rotateTetromino();
                int tetNum = board.getTet().getShapeNumber();
                int rotIdx = board.getTet().getShapeIndex();
                if (tetNum == 4) Assertions.assertEquals(0, rotIdx);
                else Assertions.assertEquals((j+1)%4, rotIdx);
            }
            board.clearTetrominoFromBoard();
        }
        board.spawnTetromino();
    }



    @Test
    public void stuckRotateTest() {
        Board board = new Board();
        for (int i = 0; i < 20; i++) { //아래에 닿았을 때 밀리는 회전 테스트
            // given
            board.spawnTetromino();
            board.clearTetrominoFromBoard();
            board.setXY(5,19);
            // when
            int tetNum = board.getTet().getShapeNumber();
            if (tetNum == 4) continue;
            board.rotateTetromino();
            // then
            int[] xy = board.getXY();
            if (tetNum == 1){
                Assertions.assertEquals(17, xy[1]); //2칸 밀림
                board.clearTetrominoFromBoard();
                board.setXY(5,18);
                board.setRotate(0);
                board.rotateTetromino();
                Assertions.assertEquals(17, xy[1]);  //1칸 밀림
            }
            else Assertions.assertEquals(18, xy[1]);
            board.clearTetrominoFromBoard();
        }

        for (int i = 0; i < 20; i++) { //왼쪽에 닿았을 때 밀리는 회전
            // given
            board.spawnTetromino();
            board.clearTetrominoFromBoard();
            // when
            int tetNum = board.getTet().getShapeNumber();
            if (tetNum == 4 ) continue;
            board.setXY(0,7);
            board.setRotate(1);
            board.rotateTetromino();
            // then
            int[] xy = board.getXY();
            if (tetNum == 1){
                Assertions.assertEquals(1, xy[0]); //1칸
                board.clearTetrominoFromBoard();
                board.setXY(-1,7);
                board.setRotate(1);
                board.rotateTetromino();
                Assertions.assertEquals(1, xy[0]); // 2칸
            }
            else Assertions.assertEquals(1, xy[0]);

            board.clearTetrominoFromBoard();
        }

        for (int i = 0; i < 20; i++) { //위에 닿았을 때 밀리는 회전
            // given
            board.spawnTetromino();
            board.clearTetrominoFromBoard();
            // when
            int tetNum = board.getTet().getShapeNumber();
            if (tetNum == 4) continue;
            board.setXY(5,0);
            board.setRotate(2);
            board.rotateTetromino();

            // then
            int[] xy = board.getXY();
            if (tetNum == 1) {
                Assertions.assertEquals(1, xy[1]); // 1칸 밀림
                board.clearTetrominoFromBoard();
                board.setXY(-1,7);
                board.setRotate(2);
                board.rotateTetromino();
                Assertions.assertEquals(1, xy[1]); // 2칸 밀림
            }
            else Assertions.assertEquals(1, xy[1]);

            board.clearTetrominoFromBoard();
        }

        for (int i = 0; i < 20; i++) { //오른쪽에 닿았을 때 밀리는 회전
            // given
            board.spawnTetromino();
            board.clearTetrominoFromBoard();
            // when
            int tetNum = board.getTet().getShapeNumber();
            if (tetNum == 4 ) continue;
            board.setXY(9,7);
            board.setRotate(3);
            board.rotateTetromino();
            // then
            int[] xy = board.getXY();
            if (tetNum == 1) {
                Assertions.assertEquals(7, xy[0]); // 2칸 밀림
                board.clearTetrominoFromBoard();
                board.setXY(8,7);
                board.setRotate(3);
                board.rotateTetromino();
                Assertions.assertEquals(7, xy[0]); // 1칸 밀림
            }
            else Assertions.assertEquals(8, xy[0]);

            board.clearTetrominoFromBoard();
        }

    }


}
