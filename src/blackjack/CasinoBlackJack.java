package blackjack;

import deck.Deck;
import deck.Hand;
import players.GenericPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class CasinoBlackJack extends GenericBlackJack{
    private Map<GenericPlayer, List<Hand>> hands;

    public CasinoBlackJack(int deckCount, List<GenericPlayer> playerRoster){
        if (playerRoster == null) {
            throw new IllegalArgumentException("playerRoster cannot be null");
        }
        if (playerRoster.isEmpty()) {
            throw new IllegalArgumentException("playerRoster must have at least one element");
        }
        deck = new Deck(deckCount);
        this.playerRoster = playerRoster;
        hands = new HashMap<>();
    }

    public void playRound(){
        //Obtaining player bets
        System.out.println("Obtaining Player Bets...");
        obtainBets();

        System.out.println("Distributing Cards...");
        distribute();

        System.out.println("Conclusions...");
        List<GenericPlayer> eliminationList = conclude();

        System.out.println("Eliminating Players...");
        eliminate(eliminationList);

        if (deck.size() <= 2 * (playerRoster.size() + 1)) {
            System.out.println("Not enough cards in deck; Game is over");
        }
        if (playerRoster.size() <= 0) {
            System.out.println("No more players; Game is over");
        }

        //Clearing all hands
        for (GenericPlayer p: playerRoster) {
            p.clearHand();
            hands = new HashMap<>();
        }
        dealer.clearHand();
        System.out.println();
    }

    private void distribute() {
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
            String firstMove = p.getFirstMove();
            List<Hand> handsList = new LinkedList<>();
            handsList.add(p.getHand());
            if (firstMove.equalsIgnoreCase("surrender")) {
                System.out.println(p.getName() + " has surrendered");
                p.changeBalance((int) ((betMap.get(p) / 2.0) + 0.99));
                System.out.println(p.getName() + " got $" + ((int) ((betMap.get(p) / 2.0) + 0.99)) + " back");
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
                    if (hand.canSplit() && p.getSplit(hand)) {
                        handsList.remove(i);
                        handsList.add(hand.split(deck.getCard(), deck.getCard()));
                        handsList.add(hand);
                        p.changeBalance(0- betMap.get(p));
                        i--;
                    }
                }
                for (Hand hand : handsList) {
                    while (hand.canHit() && p.getAction(hand)) {
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

    private List<GenericPlayer> conclude() {
        // dealer conclusion
        int dealerValue;
        System.out.println("Dealer has " + dealer.getHand().toString());
        if (dealer.getHand().value().isEmpty()){
            System.out.println("Dealer has busted");
            dealerValue = -1;
        } else {
            dealerValue = Collections.max(dealer.getHand().value());
            System.out.println("Dealer has " + dealerValue);
        }
        // Player conclusions and distributing winnings
        List<GenericPlayer> eliminationList = new ArrayList<>();
        if (dealerValue < 0) {
            System.out.println("Dealer busted. Everyone Wins");
            for (GenericPlayer p : playerRoster) {
                for (Hand h : hands.get(p)) {
                    p.changeBalance(2 * betMap.get(p));
                    p.incrementRoundsPlayed();
                    p.incrementRoundsWon();
                }
                System.out.println(p.getName() + " has $" + p.getBalance() + " left");
            }
        } else {
            for (GenericPlayer p : playerRoster) {
                String pName = p.getName();
                for (Hand h : hands.get(p)) {
                    p.incrementRoundsPlayed();
                    System.out.println(p.getName() + " has " + h.toString());
                    if (h.value().isEmpty()) {
                        System.out.println(pName + " has busted");
                        System.out.println(pName + " lost $" + betMap.get(p));
                    } else {
                        int playerValue = Collections.max(h.value());
                        System.out.println(pName + " has " + playerValue);
                        if (playerValue > dealerValue) {
                            p.changeBalance(2 * betMap.get(p));
                            p.incrementRoundsWon();
                            System.out.println(pName + " won $" + betMap.get(p));
                        } else if (playerValue == dealerValue) {
                            p.changeBalance(betMap.get(p));
                            System.out.println(pName + " tied with the dealer. Bet has been returned");
                        } else {
                            System.out.println(p.getName() + " lost $" + betMap.get(p));
                        }
                    }
                }
                System.out.println(p.getName() + " has $" + p.getBalance() + " left");
                if (p.getBalance() <= 0) {
                    eliminationList.add(p);
                }
            }
        }
        return eliminationList;
    }
}
