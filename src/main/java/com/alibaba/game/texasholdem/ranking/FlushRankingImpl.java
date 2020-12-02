package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class {@code FlushRankingImpl}
 * 解析玩家手中的牌是不是同花(花色连续一样)
 */
public class FlushRankingImpl extends AbstractRanking {

    protected RankingResult doResolve(Player player) {

        RankingResult result = null;

        List<Card> cards = player.getCards();

        Map<String,List<Integer>> suitMap = new HashMap();
        for (int i = 0; i < Constants.HAND_CARD_NUMERS; i++) {
            suitMap.putIfAbsent(cards.get(i).getSuit().toString(),new ArrayList<Integer>());
            List<Integer> list = suitMap.get(cards.get(i).getSuit().toString());
            list.add(cards.get(i).getRankNumber());
            if (list.size() == 5){
                result = new RankingResult();
                result.setRankingEnum(RankingEnum.FLUSH);
                player.setStraightNum(list.get(0));
                player.setFlushCards(list);
                return result;
            }
        }


//        if (suitMap.containsValue(5)||suitMap.containsValue(6)||suitMap.containsValue(7)) { // 如果是同色
//            result = new RankingResult();
//            result.setRankingEnum(RankingEnum.FLUSH);
//        }

        return result;
    }

}
