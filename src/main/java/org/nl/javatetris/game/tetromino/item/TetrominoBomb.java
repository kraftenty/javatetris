package org.nl.javatetris.game.tetromino.item;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.B;

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
