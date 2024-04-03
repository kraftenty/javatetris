package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class TetrominoEraseT extends AbstractTetromino {

    public TetrominoEraseT() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, E, 0},
                        {T, T, T},
                        {0, 0, 0}
                },
                {
                        {0, T, 0},
                        {0, T, E},
                        {0, T, 0}
                },
                {
                        {0, 0, 0},
                        {T, T, T},
                        {0, E, 0}
                },
                {
                        {0, T, 0},
                        {E, T, 0},
                        {0, T, 0}
                },
                {
                        {0, T, 0},
                        {E, T, T},
                        {0, 0, 0}
                },
                {
                        {0, E, 0},
                        {0, T, T},
                        {0, T, 0}
                },
                {
                        {0, 0, 0},
                        {T, T, E},
                        {0, T, 0}
                },
                {
                        {0, T, 0},
                        {T, T, 0},
                        {0, E, 0}
                },
                {
                        {0, T, 0},
                        {T, E, T},
                        {0, 0, 0}
                },
                {
                        {0, T, 0},
                        {0, E, T},
                        {0, T, 0}
                },
                {
                        {0, 0, 0},
                        {T, E, T},
                        {0, T, 0}
                },
                {
                        {0, T, 0},
                        {T, E, 0},
                        {0, T, 0}
                },
                {
                        {0, T, 0},
                        {T, T, E},
                        {0, 0, 0}
                },
                {
                        {0, T, 0},
                        {0, T, T},
                        {0, E, 0}
                },
                {
                        {0, 0, 0},
                        {E, T, T},
                        {0, T, 0}
                },
                {
                        {0, E, 0},
                        {T, T, 0},
                        {0, T, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return ET;
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
        Tetromino tetromino = new TetrominoEraseT();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
