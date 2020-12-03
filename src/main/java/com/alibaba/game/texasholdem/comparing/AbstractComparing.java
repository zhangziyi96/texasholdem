package com.alibaba.game.texasholdem.comparing;

import com.alibaba.game.texasholdem.Card;
import com.alibaba.game.texasholdem.Constants;
import com.alibaba.game.texasholdem.Player;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractComparing implements IComparing {

    /**
     * 按照三条或者或者四条的比较
     *
     * @param map1
     * @param map2
     * @param pair
     * @return
     */
    protected int multiComparing(Map<Integer, Integer> map1, Map<Integer, Integer> map2, int pair, boolean isFullHouse) {

        int p1Number = -1;
        int p2Number = -1;

        Iterator<Map.Entry<Integer, Integer>> it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next = it.next();
            if (next.getValue() == pair) {
                p1Number = next.getKey();
            }
        }

        Iterator<Map.Entry<Integer, Integer>> it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<Integer, Integer> next = it2.next();
            if (next.getValue() == pair) {
                p2Number = next.getKey();
            }
        }

        if (p1Number > p2Number) {
            return -1;
        }
        if (p1Number < p2Number) {
            return 1;
        }
        if (p1Number == p2Number){
            map1.remove(p1Number);
            map2.remove(p2Number);
            if (isFullHouse == false)
                return this.pairComparing(map1, map2, 1, 1);
            else
                return this.pairComparing(map1, map2, 2, 2);
        }

        return 0;
    }

    protected int flushComparing(Player p1, Player p2) {
        List<Integer> flushCards1 = p1.getFlushCards();
        List<Integer> flushCards2 = p2.getFlushCards();
        for (int i = 0; i < flushCards1.size(); i++) {
            if (flushCards1.get(i) > flushCards2.get(i)){
                return -1;
            }
            if (flushCards1.get(i) < flushCards2.get(i)){
                return 1;
            }
        }
        return 0;
    }

    protected int highCardComparing(Player p1,Player p2){
        List<Card> cards1 = p1.getCards();
        List<Card> cards2 = p2.getCards();
        int size = cards1.size();
        for (int i = 0; i < size; i++) {
            if (cards1.get(i).getRank().getNumber() > cards2.get(i).getRank().getNumber()){
                return -1;
            }
            if (cards1.get(i).getRank().getNumber() < cards2.get(i).getRank().getNumber()){
                return 1;
            }
            if (cards1.get(i).getRank().getNumber() == cards2.get(i).getRank().getNumber()){
                continue;
            }
        }
        return 0;
    }

    /**
     * 按照顺序的比较
     *
     * @param p1
     * @param p2
     * @return
     */
    protected int seqComparing(Player p1, Player p2) {

        int p1Num = p1.getStraightNum();
        int p2Num = p2.getStraightNum();
        if (p1Num > p2Num){
            return -1;
        }
        if (p1Num < p2Num){
            return 1;
        }
        else {
            return 0;
        }

//        List<Card> p1Cards = p1.getCards();
//        List<Card> p2Cards = p2.getCards();
//
//        int size = p1.getCardSize();
//
//        for (int i = 0; i < size; i++) {
//            if (p1Cards.get(i).getRank().getNumber() < p2Cards.get(i).getRank().getNumber()) {
//                return 1;
//            }
//            if (p1Cards.get(i).getRank().getNumber() > p2Cards.get(i).getRank().getNumber()) {
//                return -1;
//            }
//            if (p1Cards.get(i).getRank().getNumber() == p2Cards.get(i).getRank().getNumber()) {
//                continue;
//            }
//        }
//        return 0;
    }

    /**
     * @param map1
     * @param map2
     * @param pair              对子的数量
     * @param maxPairLoopAddOne 对子最大的循环数量+1
     * @return
     */
    protected int pairComparing(Map<Integer, Integer> map1, Map<Integer, Integer> map2, int pair, int maxPairLoopAddOne) {
        if (maxPairLoopAddOne - 1 == 0) {
            pair = 1;
        }
        int p1MaxNum = this.findMaxNumber(map1, pair);
        int p2MaxNum = this.findMaxNumber(map2, pair);

        if (p1MaxNum < p2MaxNum) {
            return 1;
        }
        if (p1MaxNum > p2MaxNum) {
            return -1;
        }
        if (p1MaxNum == p2MaxNum) {
            map1.remove(p1MaxNum);
            map2.remove(p2MaxNum);
            if (restCompareNum(map1) <= Constants.HAND_CARD_NUMERS - Constants.COMMON_CARD_NUMERS){
                return 0;
            }
            if (map1.size() == map2.size() && 0 == maxPairLoopAddOne - 1) {
                return this.pairComparing(map1, map2, pair - 1, 1);
            }
            return this.pairComparing(map1, map2, pair, maxPairLoopAddOne - 1);
        }
        return 0;
    }

    //剩余未比较牌数
    protected int restCompareNum(Map<Integer, Integer> map) {
        int count = 0;
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,Integer> next = it.next();
            count = count + next.getValue();
        }
        return count;
    }


        private int findMaxNumber(Map<Integer, Integer> map, int pair) {
        int p1Number = -1;

        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();

        //比高牌时可能会存在2+2+2+1 vs 2+2+1+1+1
        if (pair == 1){
            while (it.hasNext()){
                Map.Entry<Integer,Integer> next = it.next();
                int number = next.getKey();
                if (number > p1Number) {
                    p1Number = number;
                }
            }
            return p1Number;
        }

        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next = it.next();
            if (next.getValue() == pair) {
                int number = next.getKey();
                if (number > p1Number) {
                    p1Number = number;
                }
            }
        }
        return p1Number;

    }

}
