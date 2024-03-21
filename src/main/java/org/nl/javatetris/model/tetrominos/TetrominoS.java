package org.nl.javatetris.model.tetrominos;

import org.nl.javatetris.model.ModelConst;

public class TetrominoS extends AbstractTetromino {

    public TetrominoS() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 1, 1},
                        {1, 1, 0},
                        {0, 0, 0}
                },
                {
                        {0, 1, 0},
                        {0, 1, 1},
                        {0, 0, 1}
                },
                {
                        {0, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.S;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoS();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
