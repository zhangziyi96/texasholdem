package com.alibaba.game;

import com.alibaba.game.texasholdem.Dealer;
import com.alibaba.game.texasholdem.Player;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 100000; i++) {

            Dealer d = new Dealer();

            Player me = new Player();
            Player you = new Player();

            d.join(me);
            d.join(you);

            d.start();
            d.showHand();
            if (me.compareTo(you) == -1){
                count++;
            }

//            System.out.println(me);
//            System.out.println(you);
//            System.out.println(d.getCommonCardList());
//            System.out.println(me.compareTo(you));
        }
        System.out.println(count);
        System.out.println(count / 100000.0);
    }

}
