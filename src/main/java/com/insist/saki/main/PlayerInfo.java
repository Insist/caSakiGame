package com.insist.saki.main;

import com.insist.saki.main.enums.GamePosition;

/**
 * Created by Administrator on 2015/12/21.
 */
public class PlayerInfo {

    private GamePosition playerPosition;
    private Integer score;

    public GamePosition getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(GamePosition playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
