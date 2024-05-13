package org.nl.javatetris.pause;

public class PauseMenuParam {

    private Integer mode;
    private Runnable onShutdownGame;
    private Runnable onResumeGame;

    public PauseMenuParam(Integer mode) {
        this.mode = mode;
    }

    public PauseMenuParam(Integer mode, Runnable onShutdownGame) {
        this.mode = mode;
        this.onShutdownGame = onShutdownGame;
    }

    public PauseMenuParam(Integer mode, Runnable onShutdownGame, Runnable onResumeGame) {  // 생성자 추가
        this.mode = mode;
        this.onShutdownGame = onShutdownGame;
        this.onResumeGame = onResumeGame;
    }

    public Integer getMode() {
        return mode;
    }

    public Runnable getOnShutdownGame() {
        return onShutdownGame;
    }

    public Runnable getOnResumeGame() {
        return onResumeGame;
    }

}