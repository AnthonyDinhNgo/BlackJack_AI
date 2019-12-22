package players;

import deck.Card;
import deck.Deck;
import deck.Hand;



public abstract class GenericPlayer implements Actor{
    String name;
    private int balance = 10000; //$10,000 default starting balance;;
    private int roundsWon = 0;
    private int roundsPlayed = 0;
    private Hand hand = new Hand(); // Cards in player hand


    //Returns the name of the player
    public String getName(){
        return name;
    }

    //Returns the amount player wants to bet
    public abstract int getBet(Deck deck);

    //Returns the amount of money player has left in their balance
    public int getBalance(){
        return balance;
    }

    //Takes an integer delta as a parameter
    //Adds the integer delta to the balance
    public void changeBalance(int delta) {
        balance += delta;
    }

    // Returns the number of rounds won
    public int getRoundsWon(){
        return roundsWon;
    }

    //Returns the number of rounds played
    public int getRoundsPlayed(){
        return roundsPlayed;
    }

    //Returns the current player hand
    public Hand getHand() {
        return hand;
    }

    //Takes a Card c as a parameter
    //Places the Card c in the player hand
    public void giveCard(Card c){
        hand.addCard(c);
    }

    //Clears the player hand
    public void clearHand(){
        hand.clear();
    }

    //Returns a boolean true if the player is allowed another card
    @Override
    public boolean canHit() {
        return hand.value().size() > 0;
    }

    public void incrementRoundsWon() {
        roundsWon++;
    }

    public void incrementRoundsPlayed() {
        roundsPlayed++;
    }

    public double getWinRate() {
        return (0.0 + roundsWon) / roundsPlayed;
    }

    public abstract String getFirstMove(Card dealerCard);

    public abstract boolean getSplit(Hand h, Card dealerCard);

    public abstract boolean getAction(Hand h, Card dealerCard);
}
