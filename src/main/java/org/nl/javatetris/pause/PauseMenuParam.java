package org.nl.javatetris.pause;

public class PauseMenuParam {

    private Integer mode;
    private Runnable onShutdownGame;

    public PauseMenuParam(Integer mode) {
        this.mode = mode;
    }

    public PauseMenuParam(Integer mode, Runnable onShutdownGame) {
        this.mode = mode;
        this.onShutdownGame = onShutdownGame;
    }

    public Integer getMode() {
        return mode;
    }

    public Runnable getOnShutdownGame() {
        return onShutdownGame;
    }

}
