package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.Card;
import com.alibaba.game.texasholdem.CardSuitEnum;
import com.alibaba.game.texasholdem.Player;
import com.alibaba.game.texasholdem.RankingEnum;

import java.util.*;

/**
 * Class {@code StraightFlushRankingImpl}
 * 解析玩家手中的牌是不是同花顺(即是 最大牌是K 且同色)
 */
public class StraightFlushRankingImpl extends AbstractRanking {

    protected RankingResult doResolve(Player player) {

        RankingResult result = null;
        Boolean isStraight = false;
        List<Card> cards = player.getCards();
        int count = 0;
        Map<Integer, Integer> rankCount = player.getCardsRankCountMap();
        Map<CardSuitEnum,List<Integer>> suitMap = new HashMap<>();
        for (Card card : cards) {
            suitMap.putIfAbsent(card.getSuit(),new ArrayList<>());
            suitMap.get(card.getSuit()).add(card.getRankNumber());
        }

        Iterator<Map.Entry<CardSuitEnum, List<Integer>>> it = suitMap.entrySet().iterator();
        int previousCard = -1;
        while (it.hasNext()) {
            Map.Entry<CardSuitEnum, List<Integer>> next = it.next();
            List<Integer> list = next.getValue();
            if (list.size() >= 5){
                for (Integer cardNum : list) {
                    if (previousCard != -1) {
                        if (cardNum - previousCard == -1) {
                            count++;
                            if (count == 4){
                                isStraight = true;
                                player.setStraightNum(previousCard + 3);//记录顺子的大小，方便同牌型比较用
                                break;
                            }

                            //出现12345的情况
                            if (count == 3 && cardNum == 2 && rankCount.containsKey(14)){
                                isStraight = true;
                                player.setStraightNum(previousCard + 2);//记录顺子的大小，方便同牌型比较用
                                break;
                            }
                        }
                        else if (cardNum - previousCard == 0){

                        }
                        else {
                            count = 0;
                        }
                    }
                    previousCard = cardNum;
                }
            }
        }

            if (isStraight == true) {
                result = new RankingResult();
                result.setRankingEnum(RankingEnum.STRAIGHT_FLUSH);
            }



        return result;
    }

}
