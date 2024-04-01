package org.nl.javatetris.model.score;

import java.io.Serializable;


public class Score implements Serializable, Comparable<Score> {

    private static final long serialVersionUID = 1L;

    private String name;
    private int point;

    public Score(String name, int score) {
        this.name = name;
        this.point = score;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(o.getPoint(), this.getPoint());
    }
}