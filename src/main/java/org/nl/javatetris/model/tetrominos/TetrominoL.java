package org.nl.javatetris.model.tetrominos;

import org.nl.javatetris.model.ModelConst;

public class TetrominoL extends AbstractTetromino {

    public TetrominoL() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 0, 1},
                        {1, 1, 1},
                        {0, 0, 0}
                },
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {0, 1, 1}
                },
                {
                        {0, 0, 0},
                        {1, 1, 1},
                        {1, 0, 0}
                },
                {
                        {1, 1, 0},
                        {0, 1, 0},
                        {0, 1, 0}
                },

        };
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.L;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoL();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
