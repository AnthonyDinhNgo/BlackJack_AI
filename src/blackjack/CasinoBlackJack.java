package blackjack;

import deck.Deck;
import deck.Hand;
import players.GenericPlayer;

import java.util.LinkedList;
import java.util.List;


public class CasinoBlackJack extends GenericBlackJack{

    public CasinoBlackJack(int deckCount, List<GenericPlayer> playerRoster){
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
            String firstMove = p.getFirstMove(dealer.getHand().getCards().get(0));
            List<Hand> handsList = new LinkedList<>();
            handsList.add(p.getHand());
            if (firstMove.equalsIgnoreCase("surrender")) {
                System.out.println(p.getName() + " has surrendered");
                p.changeBalance((int) ((betMap.get(p) / 2.0) + 0.99));
                System.out.println(p.getName() + " got $" + ((int) ((betMap.get(p) / 2.0) + 0.4999999)) + " back");
                betMap.replace(p, 0);
            } else if (firstMove.equalsIgnoreCase("double")) {
                System.out.println(p.getName() + " has doubled");
                p.changeBalance(0-betMap.get(p));
                betMap.replace(p, 2 * betMap.get(p));
                p.giveCard(deck.getCard());
                System.out.println(p.getName() + " has " + p.getHand().toString());
                System.out.println(p.getName() + " has possible totals of " + p.getHand().value());
            } else {
                for (int i = 0; i < handsList.size(); i++) {
                    Hand hand = handsList.get(i);
                    if (hand.canSplit() && p.getSplit(hand, dealer.getHand().getCards().get(0))) {
                        handsList.remove(i);
                        handsList.add(hand.split(deck.getCard(), deck.getCard()));
                        handsList.add(hand);
                        p.changeBalance(0- betMap.get(p));
                        i--;
                    }
                }
                for (Hand hand : handsList) {
                    while (hand.canHit() && p.getAction(hand, dealer.getHand().getCards().get(0))) {
                        hand.addCard(deck.getCard());
                    }
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
