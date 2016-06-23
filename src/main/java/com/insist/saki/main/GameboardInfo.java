package com.insist.saki.main;

import com.insist.saki.main.enums.GamePosition;

/**
 * Created by Administrator on 2015/12/21.
 */
public class GameboardInfo {

    private GamePosition roundPosition;
    private Integer roundNum;
    private Integer subNum;

    public static GameboardInfo init() {
        GameboardInfo info = new GameboardInfo();
        info.setRoundPosition(GamePosition.DONG);
        info.setRoundNum(1);
        info.setSubNum(0);
        return info;
    }

    public GamePosition getRoundPosition() {
        return roundPosition;
    }

    public void setRoundPosition(GamePosition roundPosition) {
        this.roundPosition = roundPosition;
    }

    public Integer getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(Integer roundNum) {
        this.roundNum = roundNum;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

}
