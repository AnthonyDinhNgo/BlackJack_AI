package players;

import deck.Card;
import deck.Deck;

import java.util.ArrayList;
import java.util.List;

public class CardCounter extends StrategicPlayer{

    private final List<Card> cardOptions = new ArrayList<>();
    private int bettingUnit;

    public CardCounter(String name, int bettingUnit) {
        super(name);
        this.bettingUnit = bettingUnit;
    }

    @Override
    public int getBet(Deck deck) {
        int minimumBet = 0;
        int[] cardCounts = deck.getCardCounts();
        int runningCount = 0 - cardCounts[0];
        for (int i = 1; i < 6; i++) {
            runningCount += (deck.getOgCardCount() - cardCounts[i]);
        }
        for (int i = 9; i < cardCounts.length; i++) {
            runningCount -= (deck.getOgCardCount() - cardCounts[i]);
        }

        int trueCount = runningCount * 52 /deck.size();
        if (trueCount < 0) {
            return minimumBet;
        }
        return trueCount * bettingUnit;
    }
}
