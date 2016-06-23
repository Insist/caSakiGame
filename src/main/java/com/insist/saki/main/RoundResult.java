package com.insist.saki.main;

/**
 * Created by Administrator on 2015/12/21.
 */
public class RoundResult {

    private Boolean gameOverFlag = false;

    private Player nextPlayer;

    public Boolean getGameOverFlag() {
        return gameOverFlag;
    }

    public void setGameOverFlag(Boolean gameOverFlag) {
        this.gameOverFlag = gameOverFlag;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
}
