package org.nl.javatetris.game.play;

import java.util.List;

public class LineDTO {

    private List<Integer> line;

    public LineDTO(List<Integer> line) {
        this.line = line;
    }

    public List<Integer> getLine() {
        return line;
    }

    public void setLine(List<Integer> line) {
        this.line = line;
    }

    // TODO : 디버깅 용도
    public void print() {
        for (int i = 0; i < line.size(); i++) {
            System.out.print(line.get(i) + " ");
        }
        System.out.println();
    }
}
