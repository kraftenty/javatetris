package org.nl.javatetris.gameplay.tetromino.item;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;
import org.nl.javatetris.gameplay.tetromino.classic.TetrominoI;

import static org.nl.javatetris.config.constant.ModelConst.W;

public class TetrominoWeight extends AbstractTetromino {


    public TetrominoWeight() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, W, W, 0},
                        {W, W, W, W}
                },
                {
                        {0, W, W, 0},
                        {W, W, W, W}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return W;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        return new TetrominoWeight();
    }

}
