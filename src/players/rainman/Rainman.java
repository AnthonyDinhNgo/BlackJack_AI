package players.rainman;

import deck.Card;
import deck.Deck;
import deck.Hand;
import players.GenericPlayer;

public class Rainman extends GenericPlayer {

    @Override
    public int getBet(Deck deck) {
        return 0;
    }

    @Override
    public String getFirstMove(Card dealerCard) {
        return null;
    }

    @Override
    public boolean getSplit(Hand h, Card dealerCard) {
        return false;
    }

    @Override
    public boolean getAction(Hand h, Card dealerCard) {
        return false;
    }
}
