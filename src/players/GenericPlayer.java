package players;

import deck.Card;
import deck.Hand;


public abstract class GenericPlayer implements Actor{
    String name;
    private int balance = 10000; //$10,000 balance;;
    private int roundsWon = 0;
    private int roundsPlayed = 0;
    private Hand hand = new Hand();

    public String getName(){
        return name;
    }

    public abstract int getBet();

    public int getBalance(){
        return balance;
    }

    public void changeBalance(int delta) {
        balance += delta;
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

    @Override
    public boolean canHit() {
        return hand.value().size() > 0 && balance > 0;
    }
}
