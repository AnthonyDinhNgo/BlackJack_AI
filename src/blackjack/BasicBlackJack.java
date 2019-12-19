package blackjack;

import deck.Deck;
import deck.Hand;
import players.GenericPlayer;

import java.util.LinkedList;
import java.util.List;


public class BasicBlackJack extends GenericBlackJack{


    public BasicBlackJack(int deckCount, List<GenericPlayer> playerRoster){
        if (playerRoster == null) {
            throw new IllegalArgumentException("playerRoster cannot be null");
        }
        if (playerRoster.isEmpty()) {
            throw new IllegalArgumentException("playerRoster must have at least one element");
        }
        deck = new Deck(deckCount);
        this.playerRoster = playerRoster;
    }

    void distribute() {
        //Distributing player cards
        for (GenericPlayer p : playerRoster) {
            p.giveCard(deck.getCard());
        }
        for (GenericPlayer p : playerRoster) {
            p.giveCard(deck.getCard());
            System.out.println(p.getName() + " has " + p.getHand().toString());
            System.out.println(p.getName() + " has possible values of " + p.getHand().value());
        }
        //Distributing dealer cards
        dealer.giveCard(deck.getCard());
        dealer.giveCard(deck.getCard());
        System.out.println("Dealer has [] and " +
                dealer.getHand().getCards().get(1));

        // Player Actions
        for (GenericPlayer p : playerRoster) {
            List<Hand> handsList = new LinkedList<>();
            handsList.add(p.getHand());
            for (Hand hand : handsList) {
                while (hand.canHit() && p.getAction(hand, dealer.getHand().getCards().get(0))) {
                    hand.addCard(deck.getCard());
                }
            }
            hands.put(p, handsList);
        }

        //Dealer Action
        while (dealer.canHit() && dealer.getAction()){
            dealer.giveCard(deck.getCard());
        }
    }

}
