package blackjack;

import deck.Deck;
import players.GenericPlayer;

import java.util.ArrayList;
import java.util.Collections;
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

    public void playRound(){
        //Obtaining player bets
        System.out.println("Obtaining Player Bets...");
        super.obtainBets();

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
            while (p.canHit() && p.getAction()){
                p.giveCard(deck.getCard());
                System.out.println(p.getName() + " has " + p.getHand().toString());
                System.out.println(p.getName() + " has possible totals of " + p.getHand().value());
            }
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
                p.changeBalance(2 * betMap.get(p));
                p.incrementRoundsWon();
                System.out.println(p.getName() + " has $" + p.getBalance() + " left");
            }
        } else {
            for (GenericPlayer p : playerRoster) {
                String pName = p.getName();
                System.out.println(p.getName() + " has " + p.getHand().toString());
                if (p.getHand().value().isEmpty()) {
                    System.out.println(pName + " has busted");
                    System.out.println(pName + " lost $" + betMap.get(p));
                } else {
                    int playerValue = Collections.max(p.getHand().value());
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
                System.out.println(p.getName() + " has $" + p.getBalance() + " left");
                if (p.getBalance() <= 0) {
                    eliminationList.add(p);
                }
            }
        }
        return eliminationList;
    }
}
