package org.nl.javatetris;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.model.Board;

public class DropTest {

    @Test
    public void DropTetrominoTest() {
        Board board = new Board();
        //for (int i = 0; i < 20; i++) {
            //give
            board.spawnTetromino();
            //int[] xy=board.getXY();
            //System.out.println(xy[0]);
            //System.out.println(xy[1]);
            //when
            board.dropTetromino();

            //then
            //Assertions.assertTrue(board.isCurrentTetrominoPosition(5,19));
            //Assertions.assertEquals(19,xy[1]);
            //board.clearTetrominoFromBoard();
       // }
    }
}
