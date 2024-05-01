package org.nl.javatetris.game.tetromino.item;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class TetrominoEraseZ extends AbstractTetromino {

    public TetrominoEraseZ() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {E, Z, 0},
                        {0, Z, Z},
                        {0, 0, 0}
                },
                {
                        {0, 0, E},
                        {0, Z, Z},
                        {0, Z, 0}
                },
                {
                        {0, 0, 0},
                        {Z, Z, 0},
                        {0, Z, E}
                },
                {
                        {0, Z, 0},
                        {Z, Z, 0},
                        {E, 0, 0}
                },
                {
                        {Z, E, 0},
                        {0, Z, Z},
                        {0, 0, 0}
                },
                {
                        {0, 0, Z},
                        {0, Z, E},
                        {0, Z, 0}
                },
                {
                        {0, 0, 0},
                        {Z, Z, 0},
                        {0, E, Z}
                },
                {
                        {0, Z, 0},
                        {E, Z, 0},
                        {Z, 0, 0}
                },
                {
                        {Z, Z, 0},
                        {0, E, Z},
                        {0, 0, 0}
                },
                {
                        {0, 0, Z},
                        {0, E, Z},
                        {0, Z, 0}
                },
                {
                        {0, 0, 0},
                        {Z, E, 0},
                        {0, Z, Z}
                },
                {
                        {0, Z, 0},
                        {Z, E, 0},
                        {Z, 0, 0}
                },
                {
                        {Z, Z, 0},
                        {0, Z, E},
                        {0, 0, 0}
                },
                {
                        {0, 0, Z},
                        {0, Z, Z},
                        {0, E, 0}
                },
                {
                        {0, 0, 0},
                        {E, Z, 0},
                        {0, Z, Z}
                },
                {
                        {0, E, 0},
                        {Z, Z, 0},
                        {Z, 0, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return EZ;
    }

    @Override
    public void rotateRight() {
        int nextIndex = this.getShapeIndex() + 1;
        if (nextIndex == (this.getShapeIndex() / 4) * 4 + 4)
            nextIndex = (this.getShapeIndex() / 4) * 4;
        this.setShapeIndex(nextIndex);
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoEraseZ();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
