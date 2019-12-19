package players;

import deck.Card;
import deck.Hand;


public interface Actor {

    public void giveCard(Card c);

    public Hand getHand();

    public void clearHand();

    public boolean canHit();
}
