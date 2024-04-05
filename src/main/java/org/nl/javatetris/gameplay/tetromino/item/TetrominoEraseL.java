package org.nl.javatetris.gameplay.tetromino.item;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class TetrominoEraseL extends AbstractTetromino {

    public TetrominoEraseL() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 0, E},
                        {L, L, L},
                        {0, 0, 0}
                },
                {
                        {0, L, 0},
                        {0, L, 0},
                        {0, L, E}
                },
                {
                        {0, 0, 0},
                        {L, L, L},
                        {E, 0, 0}
                },
                {
                        {E, L, 0},
                        {0, L, 0},
                        {0, L, 0}
                },
                {
                        {0, 0, L},
                        {E, L, L},
                        {0, 0, 0}
                },
                {
                        {0, E, 0},
                        {0, L, 0},
                        {0, L, L}
                },
                {
                        {0, 0, 0},
                        {L, L, E},
                        {L, 0, 0}
                },
                {
                        {L, L, 0},
                        {0, L, 0},
                        {0, E, 0}
                },
                {
                        {0, 0, L},
                        {L, E, L},
                        {0, 0, 0}
                },
                {
                        {0, L, 0},
                        {0, E, 0},
                        {0, L, L}
                },
                {
                        {0, 0, 0},
                        {L, E, L},
                        {L, 0, 0}
                },
                {
                        {L, L, 0},
                        {0, E, 0},
                        {0, L, 0}
                },
                {
                        {0, 0, L},
                        {L, L, E},
                        {0, 0, 0}
                },
                {
                        {0, L, 0},
                        {0, L, 0},
                        {0, E, L}
                },
                {
                        {0, 0, 0},
                        {E, L, L},
                        {L, 0, 0}
                },
                {
                        {L, E, 0},
                        {0, L, 0},
                        {0, L, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return EL;
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
        Tetromino tetromino = new TetrominoEraseL();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
