package org.nl.javatetris.gameplay;

public class GameParam {

    private Integer mode;
    private Integer difficulty;

    public GameParam(Integer mode, Integer difficulty) {
        this.mode = mode;
        this.difficulty = difficulty;
    }

    public Integer getMode() {
        return mode;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

}
