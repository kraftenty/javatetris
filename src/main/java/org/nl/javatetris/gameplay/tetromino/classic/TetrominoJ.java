package org.nl.javatetris.gameplay.tetromino.classic;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.J;

public class TetrominoJ extends AbstractTetromino {

    public TetrominoJ() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {J, 0, 0},
                        {J, J, J},
                        {0, 0, 0}
                },
                {
                        {0, J, J},
                        {0, J, 0},
                        {0, J, 0}
                },
                {
                        {0, 0, 0},
                        {J, J, J},
                        {0, 0, J}
                },
                {
                        {0, J, 0},
                        {0, J, 0},
                        {J, J, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return J;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoJ();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
