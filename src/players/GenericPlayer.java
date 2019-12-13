package players;

import deck.Card;
import deck.Hand;


public abstract class GenericPlayer implements Actor{
    String name;
    private int balance = 1000000; //$10,000.00 balance;;
    private int roundsWon = 0;
    private int roundsPlayed = 0;
    private Hand hand;

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance/100 ;
    }

    public void changeBalance(int delta) {
        balance -= delta;
    }

    public int getRoundsWon(){
        return roundsWon;
    }

    public int getRoundsPlayed(){
        return roundsPlayed;
    }

    public Hand getHand() {
        return hand;
    }

    public void giveCard(Card c){
        hand.addCard(c);
    }

    public void clearHand(){
        hand.clear();
    }
}