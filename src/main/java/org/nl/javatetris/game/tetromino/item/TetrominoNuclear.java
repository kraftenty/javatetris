package org.nl.javatetris.game.tetromino.item;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.N;
public class TetrominoNuclear extends AbstractTetromino {
    public TetrominoNuclear() { setShapes();}

    @Override
    protected void setShapes() {
        shapes = new int [][][]{
                {
                        {N}
                }
        };
    }

    @Override
    public int getShapeNumber() { return N;}

    @Override
    public Tetromino getRotatedTetromino() {
        return new TetrominoNuclear();
    }
}