package players;

import deck.Card;
import deck.Deck;
import deck.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StrategicPlayer extends GenericPlayer {

    //List of all the possible values of the cards in the deck to be used as references in conditional statements
    private final List<Card> cardOptions = new ArrayList<>();
    public StrategicPlayer(String name) {
        this.name = name;
        cardOptions.add(new Card("Ace", 1, 11));  //Index 0
        cardOptions.add(new Card("2", 2));              //Index 1
        cardOptions.add(new Card("3", 3));              //Index 2
        cardOptions.add(new Card("4", 4));              //Index 3
        cardOptions.add(new Card("5", 5));              //Index 4
        cardOptions.add(new Card("6", 6));              //Index 5
        cardOptions.add(new Card("7", 7));              //Index 6
        cardOptions.add(new Card("8", 8));              //Index 7
        cardOptions.add(new Card("9", 9));              //Index 8
        cardOptions.add(new Card("10", 10));            //Index 9
        cardOptions.add(new Card("Jack", 10));          //Index 10
        cardOptions.add(new Card("Queen", 10));         //Index 11
        cardOptions.add(new Card("King", 10));          //Index 12
    }

    //Returns a default bet of 100
    @Override
    public int getBet(Deck deck) {
        return 100;
    }

    //Returns a string "Double", "Surrender", or "Neither" depending on the values of the dealer and player hands
    @Override
    public String getFirstMove(Card dealerCard) {
        Hand hand = getHand();
        Set<Integer> values = hand.value();
        int maxValue = Collections.max(values);
        if ((maxValue == 16 && (!cardOptions.subList(1, 9).contains(dealerCard))) ||
                (maxValue == 15 && dealerCard.equals(cardOptions.get(9)))) {
            return "Surrender";
        } else if (hand.canSplit() && hand.getCards().contains(cardOptions.get(4)) &&
                cardOptions.subList(1, 9).contains(dealerCard)){
            return "Double";
        } else if (hand.getCards().contains(cardOptions.get(0))) {
            return getSoftDouble(maxValue, dealerCard);
        } else if (maxValue == 11) {
            return "Double";
        } else if (maxValue == 10 && cardOptions.subList(1, 9).contains(dealerCard)) {
            return "Double";
        } else if (maxValue == 9 && cardOptions.subList(2, 9).contains(dealerCard)) {
            return "Double";
        }
        return "Neither";
    }

    //Helper method to simplify the getFirstMove() method
    private String getSoftDouble(int maxValue, Card dealerCard) {
        if (maxValue == 19 && dealerCard.equals(cardOptions.get(5))) {
            return "Double";
        } else if ((maxValue == 18 && cardOptions.subList(1, 6).contains(dealerCard)) ||
                (maxValue == 17 && cardOptions.subList(2, 6).contains(dealerCard))) {
            return "Double";
        } else if ((maxValue == 16 && cardOptions.subList(3, 6).contains(dealerCard)) ||
                (maxValue == 15 && cardOptions.subList(3, 6).contains(dealerCard))) {
            return "Double";
        } else if (((maxValue == 14 && cardOptions.subList(4, 6).contains(dealerCard)) ||
                (maxValue == 17 && cardOptions.subList(4, 6).contains(dealerCard)))) {
            return "Double";
        }
        return "Neither";
    }

    @Override
    public boolean getSplit(Hand h, Card dealerCard) {
        Card dupCard = h.getCards().get(0);
        if (dupCard.equals(cardOptions.get(0)) || dupCard.equals(cardOptions.get(7))) {
            return true;
        } else if (dupCard.equals(cardOptions.get(8)) && cardOptions.subList(1, 9).contains(dealerCard) &&
                !dealerCard.equals(cardOptions.get(6))) {
            return true;
        } else if (dupCard.equals(cardOptions.get(6)) && cardOptions.subList(1, 7).contains(dealerCard)) {
            return true;
        } else if (dupCard.equals(cardOptions.get(5)) && cardOptions.subList(1, 6).contains(dealerCard)) {
            return  true;
        } else if (dupCard.equals(cardOptions.get(3)) && cardOptions.subList(4, 5).contains(dealerCard)) {
            return true;
        } else if (dupCard.equals(cardOptions.get(2)) && cardOptions.subList(1, 7).contains(dealerCard)) {
            return true;
        }
        return dupCard.equals(cardOptions.get(1)) && cardOptions.subList(1, 7).contains(dealerCard);
    }

    //Returns a true if the strategy indicates that the player should hit
    @Override
    public boolean getAction(Hand h, Card dealerCard) {
        int maxValue = Collections.max(h.value());
        if (h.getCards().contains(cardOptions.get(0)) && h.value().size() > 1){
            if (maxValue == 18 &&
                    (cardOptions.subList(8, 13).contains(dealerCard) || dealerCard.equals(cardOptions.get(0)))) {
                return true;
            }
            return maxValue < 18 && maxValue > 12;
        } else if (maxValue < 17) {
            List<Card> subCardOneToSix = cardOptions.subList(1, 6);
            if (maxValue > 12 && !subCardOneToSix.contains(dealerCard)) {
                return true;
            } else if (maxValue == 12 && !cardOptions.subList(3, 6).contains(dealerCard)) {
                return true;
            } else {
                return maxValue == 10 || maxValue == 9 || maxValue == 8;
            }
        }
        return false;
    }

}
