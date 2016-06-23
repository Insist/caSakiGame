package com.insist.saki.main;

import com.insist.saki.main.enums.GamePosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Player {
    private PlayerInfo playerInfo;

    private List<Card> handCards;



    public static Player initPlayer(Integer startScore,GamePosition pp) {
        Player player = new Player();
        PlayerInfo pi = new PlayerInfo();
        pi.setPlayerPosition(pp);
        pi.setScore(startScore);
        player.setPlayerInfo(pi);
        player.handCards = new ArrayList<Card>(13);
        return player;
    }


    public RoundResult startTurn(Gameboard gameboard, RoundResult rr) {
        checkAndSendAction(gameboard,rr,gameboard.getNewCard());
        return null;
    }

    private void checkAndSendAction(Gameboard gameboard, RoundResult rr, Card newCard) {
        //check can win
        //check can richi
        //check can gang
        //check can skill
        //sendMessage(card,action(1,2,3,4,5))
    }

    public boolean checkOtherPlayerAction(){
        //can eat
        //can peng
        //can gang
        //skill
        return false;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public void addHandCard(Card card) {
        this.handCards.add(card);
    }
}
