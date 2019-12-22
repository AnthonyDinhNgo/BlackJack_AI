package deck;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    private Queue<Card> deck;
    private int ogCardCount;
    private int[] cardCounts;

    public Deck(int deckCount){
        LinkedList<Card> cards = new LinkedList<>();
        for (int i = 0; i < deckCount; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card("Ace", 1, 11));
                cards.add(new Card("2", 2));
                cards.add(new Card("3", 3));
                cards.add(new Card("4", 4));
                cards.add(new Card("5", 5));
                cards.add(new Card("6", 6));
                cards.add(new Card("7", 7));
                cards.add(new Card("8", 8));
                cards.add(new Card("9", 9));
                cards.add(new Card("10", 10));
                cards.add(new Card("Jack", 10));
                cards.add(new Card("Queen", 10));
                cards.add(new Card("King", 10));
            }
        }
        cardCounts = new int[13];
        for (int i = 0; i < 13; i++) {
            cardCounts[i] = 4 * deckCount;
        }
        ogCardCount = 4 * deckCount;
        Collections.shuffle(cards);
        deck = cards;
    }

    public int size(){
        return deck.size();
    }

    public Card getCard(){
        Card retCard = deck.remove();
        int cardIndex = retCard.value().get(0);
        if (cardIndex != 10){
            cardCounts[cardIndex-1]--;
        } else {
            String cardName = retCard.toString();
            if (cardName.equalsIgnoreCase("10")) {
                cardCounts[9]--;
            } else if (cardName.equalsIgnoreCase("Jack")) {
                cardCounts[10]--;
            } else if (cardName.equalsIgnoreCase("Queen")) {
                cardCounts[11]--;
            } else if (cardName.equalsIgnoreCase("King")) {
                cardCounts[12]--;
            }
        }
        return retCard;
    }

    public int[] getCardCounts() {
        return cardCounts;
    }

    public int getOgCardCount() {
        return ogCardCount;
    }
}
