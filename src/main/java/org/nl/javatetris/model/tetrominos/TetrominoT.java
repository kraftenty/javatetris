package org.nl.javatetris.model.tetrominos;

import org.nl.javatetris.model.ModelConst;

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
