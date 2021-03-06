package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import junit.framework.TestCase;
import org.junit.Test;

public class StraightFlushRankingImplTest extends TestCase {


    @Test
    public void testIsStraightFlushRanking() {

        Card card13 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_KING);
        Card card12 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_QUEUE);
        Card card11 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_JACK);
        Card card10 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_TEN);
        Card card9 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_NINE);
        Card card7 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_SEVEN);
        Card card6 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_SIX);
        Card card5 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_FIVE);
        Card card4 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_FOUR);
        Card card3 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_THREE);
        Card card2 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_TWO);
        Card card1 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_ACE);
        Card otherSuitCard = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_TEN);

        Player p = new Player();
        p.addCard(card9);
        p.addCard(card10);
        p.addCard(card11);
        p.addCard(card4);
        p.addCard(card3);
        p.addCard(card5);
        p.addCard(card1);
        p.addCard(card2);

        IRanking ranking = new StraightFlushRankingImpl();

        RankingResult result = ranking.resolve(p);
        System.out.println(p);
        System.out.println(p.getStraightNum());
        assertTrue(result != null);

        assertEquals(result.getRankingEnum(), RankingEnum.STRAIGHT_FLUSH);

        Player p2 = new Player();
        p2.addCard(card9);
        p2.addCard(card10);
        p2.addCard(card11);
        p2.addCard(card12);
        p2.addCard(otherSuitCard);

        IRanking ranking2 = new StraightFlushRankingImpl();

        RankingResult result2 = ranking2.resolve(p2);
        assertTrue(result2 == null);

    }

}
