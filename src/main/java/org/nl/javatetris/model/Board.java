package org.nl.javatetris.model;

import org.nl.javatetris.model.tetrominos.Tetromino;

import static org.nl.javatetris.model.BoardConst.*;


public class Board {

    // 보드 자체 관련 필드
    private int[][] board;

    // 테트로미노 관련 필드
    private Tetromino currentTetromino;
    private int tetrominoY, tetrominoX;

    // 생성자
    public Board() {
        board = new int[Y_MAX][X_MAX];
        initialize();
    }

    // 보드 초기화 메서드
    private void initialize() {
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

    // 가장자리 테두리인지 확인
    private boolean isBorder(int y, int x) {
        return y == 0 || y == (Y_MAX - 1) || x == 0 || x == (X_MAX - 1);
    }

    // 보드 상태를 콘솔에 출력하는 메서드 (디버깅 용도)
    public void printBoard() {
        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                if (board[y][x] == BORDER) {
                    System.out.print("9");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    // 여기에 블록을 추가하거나, 블록 이동, 회전 등의 메서드를 추가할 수 있습니다.
}
