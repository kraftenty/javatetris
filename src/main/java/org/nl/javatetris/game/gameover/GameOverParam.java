package org.nl.javatetris.game.gameover;

import org.nl.javatetris.game.GameParam;

public class GameOverParam {

    private Integer point;
    private GameParam gameParam;

    public GameOverParam(Integer point, GameParam gameParam) {
        this.point = point;
        this.gameParam = gameParam;
    }

    public Integer getPoint() {
        return point;
    }

    public GameParam getGameParam() {
        return gameParam;
    }

}
