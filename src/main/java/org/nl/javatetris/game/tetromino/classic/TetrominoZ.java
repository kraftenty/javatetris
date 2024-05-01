package org.nl.javatetris.game.tetromino.classic;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.Z;

public class TetrominoZ extends AbstractTetromino {

    public TetrominoZ() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {Z, Z, 0},
                        {0, Z, Z},
                        {0, 0, 0}
                },
                {
                        {0, 0, Z},
                        {0, Z, Z},
                        {0, Z, 0}
                },
                {
                        {0, 0, 0},
                        {Z, Z, 0},
                        {0, Z, Z}
                },
                {
                        {0, Z, 0},
                        {Z, Z, 0},
                        {Z, 0, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return Z;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoZ();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
