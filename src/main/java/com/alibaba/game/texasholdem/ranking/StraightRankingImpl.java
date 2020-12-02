package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.Card;
import com.alibaba.game.texasholdem.Player;
import com.alibaba.game.texasholdem.RankingEnum;

import java.util.List;

/**
 * Class {@code StraightRankingImpl}
 * 解析玩家手中的牌是不是顺子
 */
public class StraightRankingImpl extends AbstractRanking {

    protected RankingResult doResolve(Player player) {

        RankingResult result = null;

        List<Card> cards = player.getCards();

        boolean isStraight = false;
        Card previousCard = null;
        int count = 0;
        for (Card card : cards) {
            if (previousCard != null) {
                if (card.getRank().getNumber() - previousCard.getRank().getNumber() == -1) {
                    count++;
                    if (count == 4){
                        isStraight = true;
                        break;
                    }
                }
                else if (card.getRank().getNumber() - previousCard.getRank().getNumber() == 0){

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
            player.setStraightNum(previousCard.getRankNumber() + 3);
        }


        return result;
    }

}
