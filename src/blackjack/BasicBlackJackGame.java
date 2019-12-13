package blackjack;

import deck.Deck;
import players.Dealer;
import players.GenericPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class BasicBlackJackGame {
    private Deck deck;
    private Dealer dealer;
    private List<GenericPlayer> playerRoster;

    public BasicBlackJackGame(int deckCount, List<GenericPlayer> playerRoster){
        if (playerRoster == null) {
            throw new IllegalArgumentException("playerRoster cannot be null");
        }
        if (playerRoster.isEmpty()) {
            throw new IllegalArgumentException("playerRoster must have at least one element");
        }
        deck = new Deck(deckCount);
        this.playerRoster = playerRoster;
        dealer = new Dealer();
    }

    public void playRound(){
        //Obtaining player bets
        System.out.println("Obtaining Player Bets...");
        Map<GenericPlayer, Integer> betMap = obtainBets();

        System.out.println("Distributing Cards...");
        distribute();

        System.out.println("Conclusions...");
        List<GenericPlayer> eliminationList = conclude(betMap);

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

    private Map<GenericPlayer, Integer> obtainBets() {
        Map<GenericPlayer, Integer> betMap = new HashMap<>();
        for (GenericPlayer p : playerRoster) {
            int bet = p.getBet();
            p.changeBalance(0-bet);
            betMap.put(p, bet);
            p.incrementRoundsPlayed();
            System.out.println(p.getName() + " has bet $" + bet);
        }
        return betMap;
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

    private List<GenericPlayer> conclude(Map<GenericPlayer, Integer> betMap) {
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

    private void eliminate(List<GenericPlayer> eliminationList) {
        for (GenericPlayer p : eliminationList) {
            playerRoster.remove(p);
            System.out.println(p.getName() + " has no more money and has left the game");
            int winPercent = (int) (p.getWinRate() * 100);
            System.out.println(p.getName() + " won " + p.getRoundsWon() + " rounds (" + winPercent + "%)");
        }
    }

    public boolean canPlay(){
        return deck.size() > 2 * (playerRoster.size() + 1) && playerRoster.size() > 0;
    }
}
