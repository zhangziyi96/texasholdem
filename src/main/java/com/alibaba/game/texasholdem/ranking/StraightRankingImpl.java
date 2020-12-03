package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.Card;
import com.alibaba.game.texasholdem.Player;
import com.alibaba.game.texasholdem.RankingEnum;

import java.util.List;
import java.util.Map;

/**
 * Class {@code StraightRankingImpl}
 * 解析玩家手中的牌是不是顺子
 */
public class StraightRankingImpl extends AbstractRanking {

    protected RankingResult doResolve(Player player) {

        RankingResult result = null;
        Map<Integer, Integer> rankCount = player.getCardsRankCountMap();
        List<Card> cards = player.getCards();
        boolean isStraight = false;
        Card previousCard = null;
        int count = 0;  //连续牌的数量
        for (Card card : cards) {
            if (previousCard != null) {
                //如果两张牌是连续的
                if (card.getRank().getNumber() - previousCard.getRank().getNumber() == -1) {
                    count++;
                    if (count == 4){
                        isStraight = true;
                        player.setStraightNum(previousCard.getRankNumber() + 3);//记录顺子的大小，方便同牌型比较用
                        break;
                    }
                    if (count == 3 && card.getRank().getNumber() == 2 && rankCount.containsKey(14)){
                        isStraight = true;
                        player.setStraightNum(previousCard.getRankNumber() + 2);//记录顺子的大小，方便同牌型比较用
                        break;
                    }
                }
                else if (card.getRank().getNumber() - previousCard.getRank().getNumber() == 0){
                    //两张牌一样不做处理
                }
                else {
                    count = 0;
                }
            }
            previousCard = card;
        }
        if (isStraight == true) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.STRAIGHT);
        }


        return result;
    }

}
