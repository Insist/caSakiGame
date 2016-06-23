package com.insist.saki.main.enums;

/**
 * Created by Administrator on 2015/12/21.
 */
public enum CardType {
    BIN(0),SUO(1),WAN(2),ZI(3);
    int value;

    CardType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static CardType valueOf(int i){
        switch (i){
            case 0:return BIN;
            case 1:return SUO;
            case 2:return WAN;
            case 3:return ZI;
        }
        return null;
    }
}
