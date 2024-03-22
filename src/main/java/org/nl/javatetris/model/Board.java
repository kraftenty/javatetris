package org.nl.javatetris.model;

import org.nl.javatetris.model.tetrominos.Tetromino;
import org.nl.javatetris.model.tetrominos.TetrominoGenerator;

import static org.nl.javatetris.model.ModelConst.*;


public class Board {

    // 보드 자체 관련 필드
    private int[][] board;

    // 테트로미노 관련 필드
    private Tetromino currentTetromino;
    private int tetrominoY, tetrominoX;

    // 생성자
    public Board() {
        board = new int[Y_MAX][X_MAX];
        // 전체 보드를 빈 칸으로 초기화
        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                if (isBorder(y, x)) {
                    board[y][x] = BORDER; // 테두리 설정
                } else {
                    board[y][x] = EMPTY; // 내부 칸은 빈 칸으로
                }
            }
        }
    }

    public void setXY(int x, int y) {
        tetrominoY = y;
        tetrominoX = x;
        return;
    }
    public int[] getXY() {
        int[] xy= new int[] {tetrominoX,tetrominoY};
        return xy;
    }

    public Tetromino getTet() {
        return currentTetromino;
    }
    public void setRotate(int idx) {
        currentTetromino.setShapeIndex(idx);
    }



    // 현재 테트로미노가 차지하고 있는 위치인지 확인하는 메서드
    private boolean isCurrentTetrominoPosition(int x, int y) {
        for (int row = 0; row < currentTetromino.getShape().length; row++) {
            for (int col = 0; col < currentTetromino.getShape()[row].length; col++) {
                if (currentTetromino.getShape()[row][col] != 0) {
                    int boardX = tetrominoX + col;
                    int boardY = tetrominoY + row;
                    if (boardX == x && boardY == y) {
                        return true; // 현재 테트로미노가 차지하고 있는 위치
                    }
                }
            }
        }
        return false;
    }

    /**
     * 줄 제거 및 이동 관련 메서드
     *
     */
    // 게임 보드에서 완성된 줄을 제거하고, 줄들을 아래로 이동시키는 메서드
    public void clearCompletedLines() {
        for (int y = 1; y < Y_MAX - 1; y++) {
            if (isLineComplete(y)) {
                // TODO : 점수 증가
                removeLine(y); // 한줄 지우고
                shiftLinesDown(y); // 하강
            }
        }
    }

    // 특정 줄이 완성되었는지 검사하는 메서드
    private boolean isLineComplete(int y) {
        for (int x = 1; x < X_MAX - 1; x++) {
            if (board[y][x] == EMPTY || board[y][x] == BORDER) {
                return false; // 해당 줄에 빈 칸이 있으면, 줄이 완성되지 않은 것으로 판단
            }
        }
        return true; // 줄이 완성됨
    }

    // 특정 줄을 제거하는 메서드
    private void removeLine(int lineIndex) {
        for (int x = 1; x < X_MAX - 1; x++) {
            board[lineIndex][x] = EMPTY; // 해당 줄의 모든 칸을 EMPTY로 설정하여 줄을 제거
        }
    }

    // 제거된 줄 위의 모든 줄을 아래로 한 칸씩 이동시키는 메서드
    private void shiftLinesDown(int fromLineIndex) {
        // 실제 게임 내용이 있는 줄만 이동
        for (int y = fromLineIndex; y > 1; y--) {
            for (int x = 1; x < X_MAX - 1; x++) {
                board[y][x] = board[y - 1][x];
            }
        }

        // 가장 위의 줄을 EMPTY로 설정
        for (int x = 1; x < X_MAX - 1; x++) {
            board[1][x] = EMPTY;
        }
    }


    /**
     * 유틸 메서드
     */

    // 가장자리 테두리인지 확인
    private boolean isBorder(int y, int x) {
        return y == 0 || y == (Y_MAX - 1) || x == 0 || x == (X_MAX - 1);
    }

    // 보드 상태를 콘솔에 출력하는 메서드 (디버깅 용도)
    public void drawBoard() {
        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                int currentPoint = board[y][x];
                if (currentPoint == BORDER) {
                    System.out.print("X");
                } else if (currentPoint == EMPTY) {
                    System.out.print(" ");
                } else {
                    System.out.print(currentPoint);
                }
            }
            System.out.println();
        }
    }


    // 테트로미노를 상단에 스폰시키는 메서드
    public void spawnTetromino() {
        // 테트로미노를 생성한다.
        currentTetromino = TetrominoGenerator.getRandomTetromino();
        // 생성한 테트로미노를 화면 상단에 위치시킨다.
        tetrominoX = X_MAX / 2 - 1;
        tetrominoY = 1;
        // 테트로미노를 스폰시킬 수 있는지 검사하고, 없으면 게임 오버
        if (!canSpawn()) {
            System.out.println("-------GAME OVER-------");
        } else {
            placeTetrominoOnBoard();
        }
    }


    // 이전 위치의 테트로미노를 보드에서 지우는 메서드
    public void clearTetrominoFromBoard() {
        for (int y = 0; y < currentTetromino.getShapeWidth(); y++) {
            for (int x = 0; x < currentTetromino.getShapeHeight(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    int boardX = tetrominoX + x;
                    int boardY = tetrominoY + y;
                    board[boardY][boardX] = EMPTY; //
                }
            }
        }
    }

    // 테트로미노를 보드에 배치하는 메서드
    private void placeTetrominoOnBoard() {
        for (int y = 0; y < currentTetromino.getShapeWidth(); y++) {
            for (int x = 0; x < currentTetromino.getShapeHeight(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    int boardX = tetrominoX + x;
                    int boardY = tetrominoY + y;
                    board[boardY][boardX] = currentTetromino.getShapeNumber();
                }

            }
        }
    }

    /**
     * Moving Methods
     */


    // 테트로미노를 왼쪽으로 이동시키는 메서드
    public void moveTetrominoLeft() {
        if (canMove(tetrominoX - 1, tetrominoY)) {
            clearTetrominoFromBoard();
            tetrominoX--;
            placeTetrominoOnBoard();
        }
    }

    // 테트로미노를 오른쪽으로 이동시키는 메서드
    public void moveTetrominoRight() {
        if (canMove(tetrominoX + 1, tetrominoY)) {
            clearTetrominoFromBoard();
            tetrominoX++;
            placeTetrominoOnBoard();
        }
    }

    // 테트로미노를 아래로 이동시키는 메서드
    public void moveTetrominoDown() {
        if (canMove(tetrominoX, tetrominoY + 1)) {
            clearTetrominoFromBoard();
            tetrominoY++;
            placeTetrominoOnBoard();


        } else {
            clearCompletedLines();
            spawnTetromino();
        }
    }

    // 테트로미노 회전이 가능한지 여부를 검사하고, 가능하면 회전을 수행하는 메서드
    public void rotateTetromino() {
        // 회전 후의 테트로미노 모양을 계산합니다.
        // 이 예시에서는 현재 테트로미노의 회전 메서드를 호출하여 "가상의" 회전을 수행하고,
        // 그 결과를 기반으로 회전 가능 여부를 판단합니다.
        if (!canRotate()) {
            return;
        }
        clearTetrominoFromBoard();
        int[] tetXY = stuckEscape();
        tetrominoX = tetXY[0];
        tetrominoY = tetXY[1];
        currentTetromino.rotateRight();
        placeTetrominoOnBoard();
    }


    /**
     * Checing Methods
     */

    public boolean canSpawn() {
        for (int y = 0; y < currentTetromino.getShape().length; y++) {
            for (int x = 0; x < currentTetromino.getShape()[y].length; x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    int boardX = tetrominoX + x;
                    int boardY = tetrominoY + y;

                    // 경계 조건 검사 추가
                    if (boardX < 0 || boardX >= X_MAX || boardY < 0 || boardY >= Y_MAX) {
                        return false; // 보드의 경계를 벗어나면 false 반환
                    }

                    // 해당 위치가 비어있지 않은 경우 false 반환
                    if (board[boardY][boardX] != EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean canMove(int newX, int newY) {
        for (int y = 0; y < currentTetromino.getShape().length; y++) {
            for (int x = 0; x < currentTetromino.getShape()[y].length; x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    int boardX = newX + x;
                    int boardY = newY + y;

                    // 경계 조건 검사
                    if (boardX < 0 || boardX >= X_MAX || boardY < 0 || boardY >= Y_MAX) {
                        return false; // 게임 보드의 경계를 벗어나는 경우
                    }

                    // 이미 채워진 칸(다른 테트로미노)과의 충돌 검사, 현재 테트로미노의 위치를 제외
                    if (!isCurrentTetrominoPosition(boardX, boardY) && board[boardY][boardX] != EMPTY) {
                        return false; // 이동하려는 위치가 빈 공간이 아니고, 현재 테트로미노의 위치도 아닌 경우
                    }
                }
            }
        }
        return true; // 위의 모든 검사를 통과한 경우, 이동 가능
    }

    //벽/블록에 붙은 상태에서의 회전인지 확인
    public int isStuckRotate(int tetNum, int rotIdx) { //1 2 3 4 : 상 우 하 좌 1칸, 5 6 7 8 2칸
        if (tetNum == 4) return 0;
        if (tetNum == 1) {
            int[] dx = new int[] {2,2,2,1,0,3,1,1,1,2,3,0};
            int[] dy = new int[] {2,3,0,2,2,2,1,0,3,1,1,1};
            if (board[tetrominoY + dy[rotIdx*3]][tetrominoX + dx[rotIdx*3]] != EMPTY)
                return rotIdx + 5;
            else if(board[tetrominoY + dy[rotIdx*3+1]][tetrominoX + dx[rotIdx*3+1]] != EMPTY)
                return rotIdx + 1;
            else if(board[tetrominoY + dy[rotIdx*3+2]][tetrominoX + dx[rotIdx*3+2]] != EMPTY)
                return (rotIdx + 2) % 4 + 1;
            else
                return 0;
        }
        int[] dx = new int[] {1,0,1,2};
        int[] dy = new int[] {2,1,0,1};

        if (board[tetrominoY+Math.abs(2-rotIdx)][tetrominoX+Math.abs(1-rotIdx)] != EMPTY)
            return rotIdx + 1;

        return 0;
    }
    //회전할 수 있도록 이동
    public int[] stuckEscape() {
        int tetNum = currentTetromino.getShapeNumber();
        int rotIdx = currentTetromino.getShapeIndex();

        int[] xy = {tetrominoX, tetrominoY};
        int move = isStuckRotate(tetNum, rotIdx);

        int[] dx = new int[] {0,0,1,0,-1,0,2,0,-2};
        int[] dy = new int[] {0,-1,0,1,0,-2,0,2,0};
        xy[0] += dx[move];
        xy[1] += dy[move];
        return xy;
    }

    // 회전 가능 여부를 검사하는 메서드
    public boolean canRotate() {
        Tetromino rotatedTetromino = currentTetromino.getRotatedTetromino();
        int[][] shape = rotatedTetromino.getShape();

        int[] tempXY = stuckEscape(); //회전할 수 있도록 이동시킨 후 위치

        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] != 0) {
                    int boardX = tempXY[0] + x;
                    int boardY = tempXY[1] + y;

                    // 경계 조건 검사 (후에 확인 후 삭제)
                    /*if (boardX < 0 || boardX >= X_MAX || boardY < 0 || boardY >= Y_MAX) {
                        return false; // 게임 보드의 경계를 벗어나는 경우
                    }*/

                    // 이미 채워진 칸(다른 테트로미노)과의 충돌 검사, 현재 테트로미노의 위치를 제외
                    if (!isCurrentTetrominoPosition(boardX, boardY) && board[boardY][boardX] != EMPTY) {
                        return false; // 이동하려는 위치가 빈 공간이 아니고, 현재 테트로미노의 위치도 아닌 경우
                    }
                }
            }
        }
        return true; // 위의 모든 검사를 통과한 경우, 회전 가능
    }


}
