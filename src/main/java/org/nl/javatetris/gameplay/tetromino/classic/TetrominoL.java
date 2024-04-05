package org.nl.javatetris.gameplay.tetromino.classic;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.L;

public class TetrominoL extends AbstractTetromino {

    public TetrominoL() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 0, L},
                        {L, L, L},
                        {0, 0, 0}
                },
                {
                        {0, L, 0},
                        {0, L, 0},
                        {0, L, L}
                },
                {
                        {0, 0, 0},
                        {L, L, L},
                        {L, 0, 0}
                },
                {
                        {L, L, 0},
                        {0, L, 0},
                        {0, L, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return L;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoL();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
