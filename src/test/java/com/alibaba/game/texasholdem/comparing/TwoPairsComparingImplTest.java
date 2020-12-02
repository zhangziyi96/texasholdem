package com.alibaba.game.texasholdem.comparing;

import com.alibaba.game.texasholdem.*;
import com.alibaba.game.texasholdem.ranking.IRanking;
import com.alibaba.game.texasholdem.ranking.RankingResult;
import com.alibaba.game.texasholdem.ranking.TwoPairsRankingImpl;
import junit.framework.TestCase;
import org.junit.Test;

public class TwoPairsComparingImplTest extends TestCase {

    @Test
    public void testComparingTwoSamePairs() {
        Card card1 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_QUEUE);
        Card card2 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_THREE);
        Card card3 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_THREE);
        Card card4 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_TWO);
        Card card5 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_TWO);

        Card card6 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_FOUR);
        Card card7 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_FOUR);

        Player p = new Player();
        p.addCard(card1);
        p.addCard(card2);
        p.addCard(card3);
        p.addCard(card4);
        p.addCard(card5);
        p.addCard(card6);
        p.addCard(card7);

        IRanking ranking = new TwoPairsRankingImpl();
        RankingResult result = ranking.resolve(p);
        System.out.println(p);

        assertTrue(result != null);
        assertEquals(result.getRankingEnum(), RankingEnum.TWO_PAIR);


        Card card21 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_EIGHT);
        Card card22 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_EIGHT);
        Card card23 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_FOUR);
        Card card24 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_FOUR);
        Card card25 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_QUEUE);
        Card card26 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_TWO);
        Card card27 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_THREE);

        Player p2 = new Player();
        p2.addCard(card1);
        p2.addCard(card2);
        p2.addCard(card3);
        p2.addCard(card4);
        p2.addCard(card5);
        p2.addCard(card26);
        p2.addCard(card27);

        IRanking ranking2 = new TwoPairsRankingImpl();
        RankingResult result2 = ranking2.resolve(p2);

        System.out.println(p2);
        assertTrue(result2 != null);
        assertEquals(result2.getRankingEnum(), RankingEnum.TWO_PAIR);
        System.out.println(p2.compareTo(p));
        assertEquals(-1, p.compareTo(p2));
    }

    @Test
    public void testComparingNotSamePairs() {
        Card card1 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_KING);
        Card card2 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_TEN);
        Card card3 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_TEN);
        Card card4 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_FOUR);
        Card card5 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_FOUR);

        Player p = new Player();
        p.addCard(card1);
        p.addCard(card2);
        p.addCard(card3);
        p.addCard(card4);
        p.addCard(card5);

        IRanking ranking = new TwoPairsRankingImpl();
        RankingResult result = ranking.resolve(p);

        assertTrue(result != null);
        assertEquals(result.getRankingEnum(), RankingEnum.TWO_PAIR);


        Card card21 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_EIGHT);
        Card card22 = new Card(CardSuitEnum.DIAMONDS, CardRankEnum.CARD_EIGHT);
        Card card23 = new Card(CardSuitEnum.CLUBS, CardRankEnum.CARD_FOUR);
        Card card24 = new Card(CardSuitEnum.HEARTS, CardRankEnum.CARD_FOUR);
        Card card25 = new Card(CardSuitEnum.SPADES, CardRankEnum.CARD_QUEUE);

        Player p2 = new Player();
        p2.addCard(card21);
        p2.addCard(card22);
        p2.addCard(card23);
        p2.addCard(card24);
        p2.addCard(card25);

        IRanking ranking2 = new TwoPairsRankingImpl();
        RankingResult result2 = ranking2.resolve(p2);

        assertTrue(result2 != null);
        assertEquals(result2.getRankingEnum(), RankingEnum.TWO_PAIR);

        assertEquals(-1, p.compareTo(p2));
    }
}
