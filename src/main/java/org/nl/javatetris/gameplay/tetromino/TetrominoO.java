package org.nl.javatetris.gameplay.tetromino;

import org.nl.javatetris.config.constant.ModelConst;

public class TetrominoO extends AbstractTetromino {

    public static final int SHAPE_NUMBER = ModelConst.O;

    public TetrominoO() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
            {
                {1, 1},
                {1, 1}
            }
        };
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.O;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoO();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
