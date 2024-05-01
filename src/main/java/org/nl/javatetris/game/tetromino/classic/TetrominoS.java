package org.nl.javatetris.game.tetromino.classic;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.S;

public class TetrominoS extends AbstractTetromino {

    public TetrominoS() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, S, S},
                        {S, S, 0},
                        {0, 0, 0}
                },
                {
                        {0, S, 0},
                        {0, S, S},
                        {0, 0, S}
                },
                {
                        {0, 0, 0},
                        {0, S, S},
                        {S, S, 0}
                },
                {
                        {S, 0, 0},
                        {S, S, 0},
                        {0, S, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return S;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoS();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
