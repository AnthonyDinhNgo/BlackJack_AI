package deck;

import java.util.LinkedList;
import java.util.List;

public class Card {
    private String card;
    private List<Integer> valList;

    public Card(String card, int val1, int val2){
        this.card = card;
        valList = new LinkedList<>();
        valList.add(val1);
        if (val1 != val2) {
            valList.add(val2);
        }
    }

    public Card(String card, int val){
        this.card = card;
        valList = new LinkedList<>();
        valList.add(val);
    }

    //Returns the String name of the card
    @Override
    public String toString() {
        return card;
    }

    //Returns the hashCode of the card
    public int hashCode() {
        String hashString = card + valList.toString();
        return hashString.hashCode();
    }

    //Returns true if the card names are the same
    public boolean equals(Object other) {
        if (other instanceof Card) {
            Card o = (Card) other;
            return card.equalsIgnoreCase(o.card);
        }
        return false;
    }

    //Returns the values of the card
    public List<Integer> value() {
        return valList;
    }


}
