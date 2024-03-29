package org.nl.javatetris.model.score;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Score {

    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public static List<Score> loadScoreboard(String filename) {
        List<Score> scoreboard = new ArrayList<>();
        try {
            Gson gson = new Gson();
            scoreboard = gson.fromJson(new FileReader(filename), new TypeToken<List<Score>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreboard;
    }
    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}