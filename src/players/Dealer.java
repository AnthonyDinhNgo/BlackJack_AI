package players;

import deck.Card;
import deck.Hand;

import java.util.Collections;


public class Dealer implements Actor {
    private Hand hand;

    public Dealer(){
        hand = new Hand();
    }

    public boolean getAction() {
        return Collections.max(hand.value()) < 17;
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

    @Override
    public boolean canHit() {
        return hand.value().size() > 0;
    }
}
