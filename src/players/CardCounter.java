package players;

import deck.Deck;

public class CardCounter extends StrategicPlayer{

    private int bettingUnit; //Amount to bet for each positive true count

    // Takes a String name and integer bettingUnits as parameters
    // Sets the player  name to the parameter name and sets the betting unit to the parameter bettingUnits
    public CardCounter(String name, int bettingUnit) {
        super(name);
        this.bettingUnit = bettingUnit;
    }

    //Returns an integer bet of how much the player bets, depending on the card counts
    @Override
    public int getBet(Deck deck) {
        int minimumBet = 10;
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
