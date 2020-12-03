package com.alibaba.game.texasholdem;

import com.alibaba.game.texasholdem.ranking.RankingFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class {@code Dealer} 荷官, 负责发牌, 启动游戏.
 */
public class Dealer {

    private Poker poker;
    private List<Player> playerList;
    private List<Card> commonCardList = new ArrayList<Card>(); //公共牌

    public Dealer() {
        this.poker = new Poker();
        this.playerList = new ArrayList<Player>();
    }

    public Dealer(Player top, Player... players) {
        this();
        this.playerList.add(top);
        this.playerList.addAll(Arrays.asList(players));
    }

    /**
     * 新增玩家
     *
     * @param player
     */
    public void join(Player player) {
        this.playerList.add(player);
    }

    /**
     * 获得玩家数量
     *
     * @return
     */
    public int getPlayerSize() {
        return this.playerList.size();
    }

    /**
     * 开始游戏, 负责被每个玩家发牌
     */
    public void start() {

        //测试用
//        Card card1 = new Card(CardSuitEnum.HEARTS,CardRankEnum.CARD_ACE);
//        Card card2 = new Card(CardSuitEnum.HEARTS,CardRankEnum.CARD_TEN);
//        this.poker.getCards().remove(card1);
//        this.poker.getCards().remove(card2);
//        this.playerList.get(0).addCard(card1);
//        this.playerList.get(0).addCard(card2);
//
//        dispatchCommonCard();
//        this.playerList.get(1).addCard(this.poker.dispatch());
//        this.playerList.get(1).addCard(this.poker.dispatch());

        //发公共牌
        dispatchCommonCard();
        for (int i = 0; i < this.playerList.size(); i++) {
            for (int j = 0; j < 2; j++) {
                this.playerList.get(i).addCard(this.poker.dispatch());
            }
        }
    }

    /**
     * 发公共牌
     */
    public void dispatchCommonCard(){
            for (int i = 0; i < Constants.COMMON_CARD_NUMERS; i++) {
                Card commonCard = this.poker.dispatch();
                commonCardList.add(commonCard);
                for (int j = 0; j < this.playerList.size(); j++) {
                    this.playerList.get(j).addCard(commonCard);
                }
            }

    }

    /**
     * 计算每个玩家的牌型
     */
    public void showHand() {
        for (int i = 0; i < this.playerList.size(); i++) {
            RankingFacade.getInstance().resolve(this.playerList.get(i));
        }
    }

    public List<Player> getRankingPlayers() {
        Collections.sort(this.playerList);
        return this.playerList;
    }

    public List<Card> getCommonCardList(){
        return this.commonCardList;
    }
}
