package deck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    private List<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    private Hand(List<Card> init){
        hand = init;
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public List<Card> getCards(){
        return hand;
    }

    public Hand copy(){
        return new Hand(hand);
    }

    public void clear(){
        hand = new ArrayList<>();
    }

    public Set<Integer> value(){
        return getHandValues(hand);
    }

    private Set<Integer> getHandValues(List<Card> currHand){
        Card ace = new Card("Ace", 1, 11);
        Set<Integer> retSet = new HashSet<>();
        if (currHand.contains(ace)){
            List<Card> hand1 = new ArrayList<>(currHand);
            List<Card> hand11 = new ArrayList<>(currHand);
            hand1.remove(ace);
            hand1.add(new Card("", 1));
            hand11.remove(ace);
            hand11.add(new Card("", 11));
            retSet.addAll(getHandValues(hand1));
            retSet.addAll(getHandValues(hand11));
            return retSet;
        }
        int val = 0;
        for (Card c : currHand) {
            val += c.value().get(0);
        }
        if (val <= 21){
            retSet.add(val);
        }
        return retSet;
    }

    public String toString() {
        String retString = hand.get(0).toString();
        for (int i = 1; i< hand.size(); i++){
            retString = retString + (", " + hand.get(i).toString());
        }
        return retString;
    }

    public Hand split(Card c1, Card c2) {
        Hand newHand = new Hand();
        newHand.addCard(hand.get(0));
        newHand.addCard(c1);
        hand.remove(0);
        addCard(c2);
        return newHand;
    }

    public boolean canSplit() {
        return hand.size() == 2 && hand.get(0).value().equals(hand.get(1).value());
    }

    public int size(){
        return hand.size();
    }

    public boolean canHit() {
        return value().size() > 0;
    }
}
