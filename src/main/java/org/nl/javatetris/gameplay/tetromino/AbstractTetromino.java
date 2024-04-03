package org.nl.javatetris.gameplay.tetromino;

public abstract class AbstractTetromino implements Tetromino {

    protected int[][][] shapes; // 테트로미노의 모든 회전 상태를 저장
    protected int shapeIndex = 0; // 현재 모양의 인덱스

    @Override
    public void rotateRight() {
        shapeIndex = (shapeIndex + 1) % shapes.length;
    }

    @Override
    public int[][] getShape() {
        return shapes[shapeIndex];
    }

    @Override
    public int getTetrominoBlock(int y, int x) {
        return shapes[this.getShapeIndex()][y][x];
    }
    @Override
    public int getShapeIndex() {
        return shapeIndex;
    }

    protected abstract void setShapes(); // 자식 클래스에서 shapes 배열을 초기화하기 위한 추상 메서드

    @Override
    public int getShapeWidth() {
        return this.getShape().length;
    }

    @Override
    public int getShapeHeight() {
        return this.getShape()[0].length;
    }

    @Override
    public void setShapeIndex(int shapeIndex) {
        this.shapeIndex = shapeIndex;
    }
}
