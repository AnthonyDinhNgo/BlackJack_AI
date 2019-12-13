package players;

import deck.Card;
import deck.Hand;

import java.util.Collections;
import java.util.Set;


public class Dealer implements Actor {
    private Hand hand;

    public Dealer(){
        hand = new Hand();
    }

    public boolean getAction() {
        Set<Integer> values = hand.value();
        if (values.size() > 0) {
            return Collections.max(hand.value()) < 17;
        }
        return false;
    }

    public void giveCard(Card c) {
        hand.addCard(c);
    }

    public Hand getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }
}
