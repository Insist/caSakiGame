package com.insist.saki.main.enums;

/**
 * Created by Administrator on 2015/12/21.
 */
public enum GamePosition {
    DONG(1),NAN(2),XI(3),BEI(4);
    int value;
    GamePosition(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
