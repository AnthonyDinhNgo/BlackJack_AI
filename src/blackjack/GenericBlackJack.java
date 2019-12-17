package blackjack;

import deck.Deck;
import deck.Hand;
import players.Dealer;
import players.GenericPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericBlackJack implements BlackJack {

    List<GenericPlayer> playerRoster;
    Deck deck;
    Dealer dealer = new Dealer();
    Map<GenericPlayer, Integer> betMap = new HashMap<>();
    Map<GenericPlayer, List<Hand>> hands = new HashMap<>();

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
        }
        hands = new HashMap<>();
        dealer.clearHand();
        System.out.println();
    }

    void obtainBets() {
        for (GenericPlayer p : playerRoster) {
            int bet = p.getBet();
            p.changeBalance(0-bet);
            betMap.put(p, bet);
            System.out.println(p.getName() + " has bet $" + bet);
        }
    }

    abstract void distribute();

    List<GenericPlayer> conclude() {
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
                p.incrementRoundsPlayed();
                p.incrementRoundsWon();
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

    void eliminate(List<GenericPlayer> eliminationList) {
        for (GenericPlayer p : eliminationList) {
            playerRoster.remove(p);
            System.out.println(p.getName() + " has no more money and has left the game");
            int winPercent = (int) (p.getWinRate() * 100);
            System.out.println(p.getName() + " won " + p.getRoundsWon() + " rounds (" + winPercent + "%)");
        }
    }

    public boolean canPlay(){
        return deck.size() > Math.pow(playerRoster.size() + 5, 2) && playerRoster.size() > 0;
    }

    public void getMetrics() {
        System.out.println("Obtaining Player Bets...");
        for (GenericPlayer p : playerRoster) {
            betMap.put(p, 0);
            System.out.println(p.getName() + " has bet $0");
        }

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
        hands = new HashMap<>();
        dealer.clearHand();
        System.out.println();
    }
}
