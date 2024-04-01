package org.nl.javatetris.controller;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.model.score.Score;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoardController {

    private Runnable onBack;
    private int selectedItemIndex = 0;

    public ScoreBoardController(Runnable onResume) {
        this.onBack = onResume;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ENTER:
                onBack.run();
                break;
        }

    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public static void saveScoreboard(String filename, List<Score> scoreboard) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(scoreboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Score> loadScoreboard(String filename) {
        List<Score> scoreboard = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            scoreboard = (List<Score>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scoreboard;
    }
}