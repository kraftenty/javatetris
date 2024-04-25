package org.nl.javatetris.gameplay.tetromino.item;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;
import org.nl.javatetris.gameplay.tetromino.classic.TetrominoO;

import static org.nl.javatetris.config.constant.ModelConst.B;
import static org.nl.javatetris.config.constant.ModelConst.T;

public class TetrominoBomb extends AbstractTetromino {
    public TetrominoBomb() { setShapes(); }

    @Override
    protected void setShapes() {
        shapes = new int [][][]{
                {
                        {B}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return B;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        return new TetrominoBomb();
    }
}
