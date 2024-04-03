package org.nl.javatetris.scoreboard;

import java.io.Serializable;


public class Score implements Serializable, Comparable<Score> {

    private static final long serialVersionUID = 1L;

    private String name;
    private int point;
    private int difficulty;

    public Score(String name, int score, int difficulty) {
        this.name = name;
        this.point = score;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(o.getPoint(), this.getPoint());
    }
}