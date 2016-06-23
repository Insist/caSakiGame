
package com.insist.saki.main;

import com.insist.saki.main.enums.CardType;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Card {

    private int id;
    private int number;
    private CardType type;

    Card(int id){
        this.id = id;
        this.type = CardType.valueOf((int)(id/36));
        this.number = ((int)(id/4)%9)+1;
    }

//    @Override
//    public String toString() {
//        return "Card{" +
//                "id=" + id +
//                ", number=" + number +
//                ", type=" + type +
//                '}';
//    }
    @Override
    public String toString() {
        return id+"";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }
}
