package com.alibaba.game.texasholdem.ranking;

import com.alibaba.game.texasholdem.*;
import junit.framework.TestCase;
import org.junit.Test;

public class RoyalFlushRankingImplTest extends TestCase {


    @Test
    public void testIsRoyalFlushRanking() {

        Card card14 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_ACE);
        Card card13 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_KING);
        Card card12 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_QUEUE);
        Card card11 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_JACK);
        Card card10 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_TEN);
        Card card7 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_SEVEN);
        Card card8 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_EIGHT);
        Card card9 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_NINE);
        Card card6 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_SIX);
        Card card5 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_FIVE);
        Card otherSuitCard = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_TEN);

        Player p = new Player();
        p.addCard(card14);
        p.addCard(card12);
        p.addCard(card11);
        p.addCard(card13);
        p.addCard(card10);
        p.addCard(card7);
        p.addCard(card8);
        p.addCard(card9);

        IRanking ranking = new RoyalFlushRankingImpl();

        RankingResult result = ranking.resolve(p);
        System.out.println(p);
        assertTrue(result != null);

        assertEquals(result.getRankingEnum(), RankingEnum.ROYAL_FLUSH);




    }

}
