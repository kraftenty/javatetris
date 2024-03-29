package org.nl.javatetris.model;

import org.nl.javatetris.model.tetrominos.Tetromino;
import org.nl.javatetris.model.tetrominos.TetrominoGenerator;

import static org.nl.javatetris.model.ModelConst.*;


public class Board {

    // 보드 자체 관련 필드
    private int[][] board;

    // 테트로미노 관련 필드
    private TetrominoGenerator tetrominoGenerator;
    private Tetromino currentTetromino;
    private int tetrominoY;
    private int tetrominoX;
    private Runnable onClearCompletedLines;

    // 생성자
    public Board(Runnable onClearCompletedLines, TetrominoGenerator tetrominoGenerator) {
        this.tetrominoGenerator = tetrominoGenerator;
        this.onClearCompletedLines = onClearCompletedLines;
        board = new int[Y_MAX][X_MAX];
        initialize();
    }

    /**
     * 유틸 메서드
     */

    // 보드 초기화 메서드
    private void initialize() {
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

    // 보드 위 특정 위치의 점의 값을 반환하는 메서드
    public int getValueAt(int y, int x) {
        return board[y][x];
    }

    // 게임 보드에서 완성된 줄을 제거하고, 줄들을 아래로 이동시키는 메서드
    private void clearCompletedLines() {
        for (int y = 1; y < Y_MAX - 1; y++) {
            if (isLineComplete(y)) {
                // TODO : 점수 증가
                removeLine(y); // 한줄 지우고
                shiftLinesDown(y); // 하강
                onClearCompletedLines.run();
            }
        }
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

    // 보드 상태를 콘솔에 출력하는 메서드 (디버깅 용도)
    public void drawBoard() {
        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                int currentPointValue = board[y][x];
                if (currentPointValue == BORDER) {
                    System.out.print("X");
                } else if (currentPointValue == EMPTY) {
                    System.out.print(" ");
                } else {
                    System.out.print(currentPointValue);
                }
            }
            System.out.println();
        }
    }

    // 테트로미노를 보드 상단에 스폰시키는 메서드
    public boolean spawnTetromino() {
        // 테트로미노를 생성한다.
        currentTetromino = tetrominoGenerator.getNextTetromino();
        // 생성한 테트로미노를 화면 상단에 위치시킨다.
        tetrominoX = X_MAX / 2 - 1;
        tetrominoY = 1;
        // 테트로미노를 스폰시킬 수 있는지 검사하고, 없으면 게임 오버
        if (!canSpawn()) {
            System.out.println("Game Over!");
            return false;
        } else {
            placeTetrominoOnBoard();
            return true;
        }
    }

    // 테트로미노를 보드에서 지우는 메서드
    private void clearTetrominoFromBoard() {
        for (int y = 0; y < currentTetromino.getShapeWidth(); y++) {
            for (int x = 0; x < currentTetromino.getShapeHeight(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    board[tetrominoY + y][tetrominoX + x] = EMPTY;
                }
            }
        }
    }

    // 테트로미노를 보드에 배치하는 메서드
    private void placeTetrominoOnBoard() {
        for (int y = 0; y < currentTetromino.getShapeWidth(); y++) {
            for (int x = 0; x < currentTetromino.getShapeHeight(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    board[tetrominoY + y][tetrominoX + x] = currentTetromino.getShapeNumber();
                }
            }
        }
    }


    /**
     * 무빙 메서드
     */

    // 테트로미노를 보드 내에서 왼쪽으로 이동시키는 메서드
    public void moveTetrominoLeft() {
        clearTetrominoFromBoard();
        if (canMove(tetrominoY, tetrominoX - 1)) {
            tetrominoX--;
        }
        placeTetrominoOnBoard();
    }

    // 테트로미노를 보드 내에서 오른쪽으로 이동시키는 메서드
    public void moveTetrominoRight() {
        clearTetrominoFromBoard();
        if (canMove(tetrominoY, tetrominoX + 1)) {
            tetrominoX++;
        }
        placeTetrominoOnBoard();
    }

    // 테트로미노를 보드 내에서 아래로 이동시키는 메서드
    public boolean moveTetrominoDown() {
        clearTetrominoFromBoard();
        if (canMove(tetrominoY + 1, tetrominoX)) {
            tetrominoY++;
            placeTetrominoOnBoard();
            return true;

        } else {
            placeTetrominoOnBoard();
            clearCompletedLines();
            return spawnTetromino();
        }

    }

    // 테트로미노를 보드 내에서 회전시키는 메서드. 벽 때문에 회전할 수 없는 경우, 왼쪽 또는 오른쪽으로 이동시킨 후 회전
    public void rotateTetromino() {
        // 회전 후의 테트로미노 모양을 계산합니다.
        // 이 예시에서는 현재 테트로미노의 회전 메서드를 호출하여 "가상의" 회전을 수행하고,
        // 그 결과를 기반으로 회전 가능 여부를 판단합니다.
        clearTetrominoFromBoard();
        if (!canRotate()) {
            placeTetrominoOnBoard();
            return;
        }
        int[] tetYX = moveForRotate();
        tetrominoY = tetYX[0];
        tetrominoX = tetYX[1];
        currentTetromino.rotateRight();
        placeTetrominoOnBoard();
    }

    // 스페이스바를 눌러 테트로미노를 가장 아래로 내리는 메서드
    public void dropTetromino() {
        clearTetrominoFromBoard();
        while (canMove(tetrominoY + 1, tetrominoX)) {
            tetrominoY ++;
        }
        placeTetrominoOnBoard();
        clearCompletedLines();
        spawnTetromino();
    }



    /**
     * 검사 메서드
     */

    // 특정 좌표가 보드의 경계인지 검사하는 메서드
    private boolean isBorder(int y, int x) {
        return y == 0 || y == (Y_MAX - 1) || x == 0 || x == (X_MAX - 1);
    }

    // 보드 내에서 특정 줄이 완성되었는지 검사하는 메서드
    private boolean isLineComplete(int y) {
        for (int x = 1; x < X_MAX - 1; x++) {
            if (board[y][x] == EMPTY) {
                return false; // 해당 줄에 빈 칸이 있으면, 줄이 완성되지 않은 것으로 판단
            }
        }
        return true; // 줄이 완성됨
    }

    // 보드 내에서 현재 테트로미노가 차지하고 있는 위치인지 확인하는 메서드


    // 테트로미노가 보드 내에서 생성될 수 있는지 검사하는 메서드
    private boolean canSpawn() {
        for (int y = 0; y < currentTetromino.getShape().length; y++) {
            for (int x = 0; x < currentTetromino.getShape()[y].length; x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    if (board[tetrominoY + y][tetrominoX + x] != EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 테트로미노가 보드 내에서 이동할 수 있는지 검사하는 메서드
    private boolean canMove(int newY, int newX) {
        for (int y = 0; y < currentTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < currentTetromino.getShapeWidth(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    int boardY = newY + y;
                    int boardX = newX + x;


                    // 경계 조건 검사
                    if (boardX < 0 || boardX >= X_MAX || boardY < 0 || boardY >= Y_MAX) {
                        return false;
                    }

                    // 이미 채워진 칸(다른 테트로미노)과의 충돌 검사
                    if (board[boardY][boardX] != EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true; // 위의 모든 검사를 통과한 경우, 이동 가능
    }

    // 회전할 때 걸리는 부분을 찾고, 이동해야할 방향과 거리 리턴
    public int getRotatedMove(int tetrominoNum, int rotationIdx) { //1 2 3 4 : 상 우 하 좌 1칸, 5 6 7 8 2칸
        if (tetrominoNum == 4) return 0;
        if (tetrominoNum == 1) {
            int[] dy = new int[]{2, 3, 0, 2, 2, 2, 1, 0, 3, 1, 1, 1};  // I가 회전할 때 걸리는 부분
            int[] dx = new int[]{2, 2, 2, 1, 0, 3, 1, 1, 1, 2, 3, 0};


            if (board[tetrominoY + dy[rotationIdx * 3]][tetrominoX + dx[rotationIdx * 3]] != EMPTY) //1자가 두칸 밀리는 경우
                return rotationIdx + 5;
            else if (board[tetrominoY + dy[rotationIdx * 3 + 1]][tetrominoX + dx[rotationIdx * 3 + 1]] != EMPTY) //1자가 한칸 밀리는 경우
                return rotationIdx + 1;
            else if (board[tetrominoY + dy[rotationIdx * 3 + 2]][tetrominoX + dx[rotationIdx * 3 + 2]] != EMPTY) //1자가 반대편(빈칸이 한칸)에서 밀리는 경우
                return (rotationIdx + 2) % 4 + 1;
            else
                return 0;
        }
        // 그 외 블록 회전
        Tetromino rotatedTetromino = currentTetromino.getRotatedTetromino();
        int[][] shape = rotatedTetromino.getShape();

        for (int y = 0; y < 3; y++) {  //걸리는 부분 탐색
            for (int x = 0; x < 3; x++) {
                if (shape[y][x] == 0) continue;
                int boardY = tetrominoY + y;
                int boardX = tetrominoX + x;

                if (board[boardY][boardX] == EMPTY) continue;
                if (rotationIdx % 2 == 0 && y != 1) {
                    return 3 - y;
                } else if (rotationIdx % 2 == 1 && x != 1) {
                    return x + 2;
                }
            }
        }
        return 0;
    }

    //회전할 수 있도록 이동
    public int[] moveForRotate() {
        int tetrominoNum = currentTetromino.getShapeNumber();
        int rotationIdx = currentTetromino.getShapeIndex();

        int[] yx = {tetrominoY, tetrominoX};
        int move = getRotatedMove(tetrominoNum, rotationIdx);

        int[] dy = new int[]{0, -1, 0, 1, 0, -2, 0, 2, 0};
        int[] dx = new int[]{0, 0, 1, 0, -1, 0, 2, 0, -2};

        yx[0] += dy[move];
        yx[1] += dx[move];
        return yx;
    }

    // 테트로미노가 보드 내에서 회전할 수 있는지 검사하는 메서드
    public boolean canRotate() {
        Tetromino rotatedTetromino = currentTetromino.getRotatedTetromino();
        int[][] shape = rotatedTetromino.getShape();

        int[] tempYX = moveForRotate(); //회전할 수 있도록 이동시킨 후 위치

        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] != 0) {
                    int boardY = tempYX[0] + y;
                    int boardX = tempYX[1] + x;

                    // 이미 채워진 칸(다른 테트로미노)과의 충돌 검사, 현재 테트로미노의 위치를 제외
                    if (board[boardY][boardX] != EMPTY) {
                        return false; // 이동하려는 위치가 빈 공간이 아니고, 현재 테트로미노의 위치도 아닌 경우
                    }
                }
            }
        }
        return true; // 위의 모든 검사를 통과한 경우, 회전 가능
    }


}
