package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.*;

import java.util.*;

/**
 * Class {@code RoyalFlushRankingImpl}
 * 解析玩家手中的牌是不是皇家同花顺(即是 10 J Q K A 且同色)
 */
public class RoyalFlushRankingImpl extends AbstractRanking {

    protected RankingResult doResolve(Player player) {

        RankingResult result = null;
        Boolean isRoyal = false;
        List<Card> cards = player.getCards();
        int count = 0;
        Map<CardSuitEnum,List<Integer>> suitMap = new HashMap<>();
        for (Card card : cards) {
            suitMap.putIfAbsent(card.getSuit(),new ArrayList<>());
            suitMap.get(card.getSuit()).add(card.getRankNumber());
        }

        Iterator<Map.Entry<CardSuitEnum, List<Integer>>> it = suitMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<CardSuitEnum, List<Integer>> next = it.next();
            List<Integer> list = next.getValue();
            if (list.size() >= 5){
                int previousCard = -1;
                for (Integer cardNum : list) {
                    if (previousCard != -1) {
                        if (cardNum - previousCard == -1) {
                            count++;
                            if (count == 4){
                                if (list.get(0) == 14&&
                                        list.get(1) == 13&&
                                        list.get(2) == 12&&
                                        list.get(3) == 11&&
                                        list.get(4) == 10) {
                                    isRoyal = true;
                                    break;
                                }
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
        if (isRoyal) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.ROYAL_FLUSH);
        }
        return result;
    }

}
