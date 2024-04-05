package org.nl.javatetris.gameplay.tetromino.classic;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.T;

public class TetrominoT extends AbstractTetromino {

    public TetrominoT() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, T, 0},
                        {T, T, T},
                        {0, 0, 0}
                },
                {
                        {0, T, 0},
                        {0, T, T},
                        {0, T, 0}
                },
                {
                        {0, 0, 0},
                        {T, T, T},
                        {0, T, 0}
                },
                {
                        {0, T, 0},
                        {T, T, 0},
                        {0, T, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return T;
    }



    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoT();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
