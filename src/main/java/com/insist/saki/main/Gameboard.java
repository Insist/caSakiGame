package com.insist.saki.main;

import com.insist.saki.main.enums.GamePosition;

import java.util.*;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Gameboard {

    private GameboardInfo gameboardInfo;

    private List<Player> players;

    private Player actionPlayer ;

    private int nextPoint = 0;
    private int beforePoint = 0;

    private List<Card> yamaCards;
    private List<Card> usedCards;
    private Integer[] touzi =new Integer[2];

    private Gameboard(){}

    public static Gameboard createGameBoard(List<User> users){
        Gameboard gameboard = new Gameboard();
        gameboard.gameboardInfo = GameboardInfo.init();
        gameboard.initPlayerInfo(users);
        return gameboard;
    }

    private void initPlayerInfo(List<User> users) {
        players = new ArrayList<Player>(users.size());
        Collections.shuffle(users);
        for(int i=0;i<users.size();i++){
            Player p = Player.initPlayer(GameConfig.START_SCORE, GamePosition.values()[i]);
            players.add(p);
            if(i==0){
                actionPlayer = p;
            }
        }
    }

    public void startGame(){
        boolean end =false;
        while(!end){
            end = startRound();
        };
    }

    private boolean startRound() {
        initRound();
        RoundResult rr = new RoundResult();
        do {
            rr = this.actionPlayer.startTurn(this,rr);
            this.actionPlayer = rr.getNextPlayer();
        }while (!rr.getGameOverFlag());
        return countRoundEnd();
    }

    private void initRound() {
        //wash
        usedCards = new ArrayList<Card>(122);
        yamaCards = new ArrayList<Card>(136);
        for(int i=0;i<136;i++){
            yamaCards.add(new Card(i));
        }
        Collections.shuffle(yamaCards);
        //
        touzi[0]=(int)(Math.random()*6)+1;
        touzi[1]=(int)(Math.random()*6)+1;
        int total = touzi[0]+touzi[1];
        nextPoint = 34*((13-total)%4)+total*2;
        beforePoint = nextPoint-1>=0?nextPoint-1:135;
        //
        for(int i =0;i<3;i++){
            for(Player player:players){
                for(int j =0;j<4;i++) {
                    player.addHandCard(yamaCards.get(popNextPoint()));
                }
            }
        }
    }
    public int popNextPoint(){
        int old = nextPoint;
        nextPoint = old+1<136?old+1:0;
        return old;
    }

    public int popBeforePoint(){
        int old = beforePoint;
        beforePoint = old-1>=0?old-1:135;
        return old;
    }

    private boolean countRoundEnd() {
        return false;
    }

    public Card getNewCard() {
        return yamaCards.get(popNextPoint());
    }

    public Card getGangCard(){
        return yamaCards.get(popBeforePoint());
    }
}
