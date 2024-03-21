package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.model.Board;

public class Controller {

    private Board board;

    public Controller(Board board) {
        this.board = board;
    }

    public void handleKeyPress(KeyEvent e) {
        switch(e.getCode()) {
            case DOWN:
                board.moveTetrominoDown();
                break;
            case LEFT:
                board.moveTetrominoLeft();
                break;
            case RIGHT:
                board.moveTetrominoRight();
                break;
            case UP:
                board.rotateTetromino();
                break;
            default:
                break;
        }
        // 디버그용
        board.drawBoard();
    }
}
