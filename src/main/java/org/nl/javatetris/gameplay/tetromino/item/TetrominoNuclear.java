package org.nl.javatetris.gameplay.tetromino.item;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;
import org.nl.javatetris.gameplay.tetromino.classic.TetrominoO;

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