package com.alibaba.game.texasholdem.comparing;

import com.alibaba.game.texasholdem.*;
import com.alibaba.game.texasholdem.ranking.IRanking;
import com.alibaba.game.texasholdem.ranking.RankingResult;
import com.alibaba.game.texasholdem.ranking.StraightRankingImpl;
import junit.framework.TestCase;
import org.junit.Test;

public class StraightComparingImplTest extends TestCase {

    @Test
    public void testComparing() {
        Card card1 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_FOUR);
        Card card2 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_FIVE);
        Card card3 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_SIX);
        Card card4 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_SEVEN);
        Card card5 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_EIGHT);

        Card card6 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_NINE);
        Card card7 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_EIGHT);

        Player p = new Player();
        p.addCard(card1);
        p.addCard(card2);
        p.addCard(card3);
        p.addCard(card4);
        p.addCard(card5);
        p.addCard(card6);
        p.addCard(card7);

        IRanking ranking = new StraightRankingImpl();
        RankingResult result = ranking.resolve(p);
        System.out.println(p);
        assertTrue(result != null);
        assertEquals(result.getRankingEnum(), RankingEnum.STRAIGHT);


        Card card21 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_NINE);
        Card card22 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_TEN);


        Player p2 = new Player();
        p2.addCard(card1);
        p2.addCard(card2);
        p2.addCard(card3);
        p2.addCard(card4);
        p2.addCard(card5);
        p2.addCard(card21);
        p2.addCard(card22);

        IRanking ranking2 = new StraightRankingImpl();
        RankingResult result2 = ranking2.resolve(p2);
        System.out.println(p2);
        assertTrue(result2 != null);
        assertEquals(result2.getRankingEnum(), RankingEnum.STRAIGHT);
        System.out.println(p.compareTo(p2));
        assertEquals(0, p.compareTo(p2));
    }
}
