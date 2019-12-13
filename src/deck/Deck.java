package deck;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    private Queue<Card> deck;

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
        Collections.shuffle(cards);
        deck = cards;
    }

    public int size(){
        return deck.size();
    }

    public Card getCard(){
        return deck.remove();
    }

}
