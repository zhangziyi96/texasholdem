package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.Player;
import com.alibaba.game.texasholdem.RankingEnum;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class {@code FullHouseRankingImpl}
 * 解析玩家手中的牌是不是三条+一对
 */
public class FullHouseRankingImpl extends AbstractRanking {

    protected RankingResult doResolve(Player player) {

        RankingResult result = null;

        boolean isFullHouse = false;
        Map<Integer, Integer> rankCount = player.getCardsRankCountMap();
        //3+2+1+1 or 3+2+2
        if (rankCount.containsValue(3) && rankCount.containsValue(2)){
            isFullHouse = true;
        }

        //3+3+1
        if (rankCount.containsValue(3) && rankCount.size() == 3){
            isFullHouse = true;
        }

//        if (rankCount.size() == 2) {
//            Iterator<Map.Entry<Integer, Integer>> it = rankCount.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<Integer, Integer> next = it.next();
//                if (next.getValue() == 2 || next.getValue() == 3) {
//                    isFullHouse = true;
//                    break;
//                }
//            }
//        }

        if (isFullHouse) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.FULL_HOUSE);
        }

        return result;
    }

}
