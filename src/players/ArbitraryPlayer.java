package players;

import deck.Card;
import deck.Deck;
import deck.Hand;

public class ArbitraryPlayer extends GenericPlayer{

    public ArbitraryPlayer(String name) {
        this.name = name;
    }

    @Override
    public boolean getAction(Hand hand, Card dealerCard) {
        return Math.random() > 0.5;
    }

    @Override
    public int getBet(Deck deck) {
        return (int) ((Math.random() * (super.getBalance()))) + 1;
    }

    @Override
    public String getFirstMove(Card dealerCard) {
        int determiningFactor = (int) (Math.random() * 3);
        if (determiningFactor == 0) {
            return "SURRENDER";
        } else if (determiningFactor == 1) {
            return "DOUBLE";
        }
        return "NEITHER";
    }

    @Override
    public boolean getSplit(Hand hand, Card dealerCard) {
        return Math.random() > 0.5;
    }
}
