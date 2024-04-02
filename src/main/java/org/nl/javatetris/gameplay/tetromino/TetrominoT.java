package org.nl.javatetris.gameplay.tetromino;

import org.nl.javatetris.config.constant.ModelConst;

public class TetrominoT extends AbstractTetromino {

    public TetrominoT() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 1, 0},
                        {1, 1, 1},
                        {0, 0, 0}
                },
                {
                        {0, 1, 0},
                        {0, 1, 1},
                        {0, 1, 0}
                },
                {
                        {0, 0, 0},
                        {1, 1, 1},
                        {0, 1, 0}
                },
                {
                        {0, 1, 0},
                        {1, 1, 0},
                        {0, 1, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.T;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoT();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
